/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

	private TalonSRX mainElevator;
	private TalonSRX slaveElevator;

	public ElevatorSubsystem() {
		mainElevator = new TalonSRX(Constants.MAIN_ELEVATOR);
		slaveElevator = new TalonSRX(Constants.SLAVE_ELEVATOR);

		mainElevator.configFactoryDefault();
		slaveElevator.configFactoryDefault();

		// Set neutral mode
		mainElevator.setNeutralMode(NeutralMode.Brake);
		slaveElevator.setNeutralMode(NeutralMode.Brake);

		// Set feedback sensor
		// mainElevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
		// 		Constants.kTimeoutMs);

		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		mainElevator.setSensorPhase(true);
		mainElevator.setInverted(false);
		slaveElevator.setInverted(true);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		// mainElevator.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		// mainElevator.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		// mainElevator.configNominalOutputForward(0, Constants.kTimeoutMs);
		// mainElevator.configNominalOutputReverse(0, Constants.kTimeoutMs);
		// mainElevator.configPeakOutputForward(1, Constants.kTimeoutMs);
		// mainElevator.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		// mainElevator.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		// mainElevator.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		// mainElevator.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		// mainElevator.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		// mainElevator.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		// mainElevator.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		// mainElevator.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		// mainElevator.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		// Follow
		slaveElevator.follow(mainElevator);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void elevatorDrive(double xSpeed) {
		mainElevator.set(ControlMode.PercentOutput, xSpeed);
	}

	// Rocket side positions
	public void ElevatorFloor() {
		// mainElevator.set(ControlMode.MotionMagic, 0);
	}

	public void ElevatorClimb() {
		// mainElevator.set(ControlMode.MotionMagic, 500);
	}
}
