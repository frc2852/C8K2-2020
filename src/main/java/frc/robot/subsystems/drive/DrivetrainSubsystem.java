/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {

	private CANSparkMax leftMaster = new CANSparkMax(Constants.DRIVE_LEFT_MASTER, MotorType.kBrushless);
	private CANSparkMax leftSlave = new CANSparkMax(Constants.DRIVE_LEFT_SLAVE, MotorType.kBrushless);

	private CANSparkMax rightMaster = new CANSparkMax(Constants.DRIVE_RIGHT_MASTER, MotorType.kBrushless);
	private CANSparkMax rightSlave = new CANSparkMax(Constants.DRIVE_RIGHT_SLAVE, MotorType.kBrushless);

	private DifferentialDrive differentialDrive;

	private PigeonIMU pidgey = new PigeonIMU(Constants.PIGEON_IMU);
	private Constants.PigeonDrive pigeonDrive = Constants.PigeonDrive.Off;

	/** Holds the current angle to servo to */
	private double targetAngle = 0;

	/**
	 * Some gains for heading servo, these were tweaked by using the web-based
	 * config (CAN Talon) and pressing gamepad button 6 to load them.
	 */
	double kPgain = 0.04; // percent throttle per degree of error */
	double kDgain = 0.0004; // percent throttle per angular velocity dps */
	double kMaxCorrectionRatio = 0.30; // cap corrective turning throttle to 30 percent of forward throttle

	/**
	 * Creates a new DrivetrainSubsystem.
	 */
	public DrivetrainSubsystem() {

		// PigeonIMU
		// pidgey.configFactoryDefault();
		// pidgey.setFusedHeading(0.0, Constants.kTimeoutMs);
		// pigeonDrive = Constants.PigeonDrive.Off;

		leftMaster.restoreFactoryDefaults();
		leftSlave.restoreFactoryDefaults();
		rightMaster.restoreFactoryDefaults();
		rightSlave.restoreFactoryDefaults();

		leftMaster.setIdleMode(IdleMode.kBrake);
		leftSlave.setIdleMode(IdleMode.kBrake);
		rightMaster.setIdleMode(IdleMode.kBrake);
		rightSlave.setIdleMode(IdleMode.kBrake);

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);

		rightSlave.setInverted(true);
		differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void pidgeyDrive(double xSpeed, double zRotation) {
		/* get Pigeon status information from Pigeon API */
		PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
		PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
		double[] xyz_dps = new double[3];

		/* grab some input data from Pigeon and gamepad */
		pidgey.getGeneralStatus(genStatus);
		pidgey.getRawGyro(xyz_dps);
		pidgey.getFusedHeading(fusionStatus);
		double currentAngle = fusionStatus.heading;
		boolean angleIsGood = (pidgey.getState() == PigeonIMU.PigeonState.Ready) ? true : false;
		double currentAngularRate = xyz_dps[2];

		/* get input from gamepad */
		boolean userWantsGoStraight = true; //_driveStick.getRawButton(5); /* top left shoulder button */

		/* state machine to update our goStraight selection */
		switch (pigeonDrive) {
		/*
		 * go straight is off, better check gamepad to see if we should enable the
		 * feature
		 */
		case Off:
			if (userWantsGoStraight == false) {
				/* nothing to do */
			} else if (angleIsGood == false) {
				/* user wants to servo but Pigeon isn't connected? */
				pigeonDrive = Constants.PigeonDrive.SameThrottle; /* just apply same throttle to both sides */
			} else {
				/*
				 * user wants to servo, save the current heading so we know where to servo to.
				 */
				pigeonDrive = Constants.PigeonDrive.UsePigeon;
				targetAngle = currentAngle;
			}
			break;

		/* we are servo-ing heading with Pigeon */
		case UsePigeon:
			if (userWantsGoStraight == false) {
				pigeonDrive = Constants.PigeonDrive.Off; /* user let go, turn off the feature */
			} else if (angleIsGood == false) {
				pigeonDrive = Constants.PigeonDrive.SameThrottle; /*
																	 * we were servo-ing with pidgy, but we lost
																	 * connection? Check wiring and deviceID setup
																	 */
			} else {
				/* user still wants to drive straight, keep doing it */
			}
			break;

		/*
		 * we are simply applying the same throttle to both sides, apparently Pigeon is
		 * not connected
		 */
		case SameThrottle:
			if (userWantsGoStraight == false) {
				pigeonDrive = Constants.PigeonDrive.Off; /* user let go, turn off the feature */
			} else {
				/* user still wants to drive straight, keep doing it */
			}
			break;
		}

		/* if we can servo with IMU, do the math here */
		if (pigeonDrive == Constants.PigeonDrive.UsePigeon) {
			/*
			 * very simple Proportional and Derivative (PD) loop with a cap, replace with
			 * favorite close loop strategy or leverage future Talon <=> Pigeon features.
			 */
			zRotation = (targetAngle - currentAngle) * kPgain - (currentAngularRate) * kDgain;
			/*
			 * the max correction is the forward throttle times a scalar, This can be done a
			 * number of ways but basically only apply small turning correction when we are
			 * moving slow and larger correction the faster we move. Otherwise you may need
			 * stiffer pgain at higher velocities.
			 */
			double maxThrot = MaxCorrection(xSpeed, kMaxCorrectionRatio);
			zRotation = Cap(zRotation, maxThrot);
		} else if (pigeonDrive == Constants.PigeonDrive.SameThrottle) {
			/* clear the turn throttle, just apply same throttle to both sides */
			zRotation = 0;
		} else {
			/* do nothing */
		}

		/*
		 * positive zRotation means turn to the left, this can be replaced with
		 * ArcadeDrive object, or teams drivetrain object
		 */
		xSpeed = Cap(xSpeed, 1.0);
		zRotation = Cap(zRotation, 1.0);

		differentialDrive.arcadeDrive(xSpeed, zRotation);

		/* Prints for debugging */
		// if (++_printLoops > 50) {
		// _printLoops = 0;
		// /* Create Print Block */
		// System.out.println("------------------------------------------");
		// System.out.println("error: " + (_targetAngle - currentAngle));
		// System.out.println("angle: " + currentAngle);
		// System.out.println("rate: " + currentAngularRate);
		// System.out.println("noMotionBiasCount: " + genStatus.noMotionBiasCount);
		// System.out.println("tempCompensationCount: " +
		// genStatus.tempCompensationCount);
		// System.out.println(angleIsGood ? "Angle is good" : "Angle is NOT GOOD");
		// System.out.println("------------------------------------------");
		// }
	}

	/**
	 * @param axisVal to deadband.
	 * @return 10% deadbanded joystick value
	 */
	double Deadband(double axisVal) {
		if (axisVal < -0.10)
			return axisVal;
		if (axisVal > +0.10)
			return axisVal;
		return 0;
	}

	/**
	 * @param value to cap.
	 * @param peak  positive double representing the maximum (peak) value.
	 * @return a capped value.
	 */
	double Cap(double value, double peak) {
		if (value < -peak)
			return -peak;
		if (value > +peak)
			return +peak;
		return value;
	}

	/**
	 * Given the robot forward throttle and ratio, return the max corrective turning
	 * throttle to adjust for heading. This is a simple method of avoiding using
	 * different gains for low speed, high speed, and no-speed (zero turns).
	 */
	double MaxCorrection(double forwardThrot, double scalor) {
		/* make it positive */
		if (forwardThrot < 0) {
			forwardThrot = -forwardThrot;
		}
		/* max correction is the current forward throttle scaled down */
		forwardThrot *= scalor;
		/*
		 * ensure caller is allowed at least 10% throttle, regardless of forward
		 * throttle
		 */
		if (forwardThrot < 0.10)
			return 0.10;
		return forwardThrot;
	}

	public void arcadeDrive(double xSpeed, double zRotation) {
		SmartDashboard.putNumber("xSpeed: ", xSpeed);
		SmartDashboard.putNumber("zRotation: ", zRotation);
		differentialDrive.arcadeDrive(xSpeed, zRotation);
	}
}