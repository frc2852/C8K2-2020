/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ShooterSubsystem extends PIDSubsystem {
	/**
	 * Creates a new ShooterSubsystem.
	 */ 
	// PID: proportional Integral Derivative Controller
	private CANSparkMax shootSlaveMotor = new CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);
	private CANSparkMax shootMasterMotor = new CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);

	private final SimpleMotorFeedforward ShooterFeedforward = new SimpleMotorFeedforward(0, 0);
	private CANEncoder shootEncoder;

	public ShooterSubsystem() {
		// The constructor passes a name for the subsystem and the P, I and D constants
		// that are used when computing the motor output
		super(new PIDController(0, 0, 0));
		shootEncoder = shootSlaveMotor.getAlternateEncoder(AlternateEncoderType.kQuadrature, 0);

		getController().setTolerance(0);
		shootEncoder.setPosition(0);

		setSetpoint(0);

		shootMasterMotor.restoreFactoryDefaults();
		shootSlaveMotor.restoreFactoryDefaults();

		shootSlaveMotor.setInverted(true);

		shootSlaveMotor.setIdleMode(IdleMode.kBrake);
		shootMasterMotor.setIdleMode(IdleMode.kBrake);

		shootSlaveMotor.follow(shootMasterMotor);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
	public void shootFromTrench(){ // Right Bumper
		shootMasterMotor.set(0.60);
	}

	public void shootFromColourWheel(){ // Left Bumper
		shootMasterMotor.set(0.59);
	}

	public void stopShooter(){
		shootMasterMotor.set(0);
	}

	@Override
	public void useOutput(double output, double setpoint) {
		shootMasterMotor.setVoltage(output + ShooterFeedforward.calculate(setpoint));
	}

	@Override
	public double getMeasurement() {
		return shootEncoder.getCountsPerRevolution();
	}

	public boolean atSetpoint() {
		return m_controller.atSetpoint();
	}
}

// public class ShooterSubsystem extends SubsystemBase {
// /**
// * Creates a new ShooterSubsystem.
// */

// //PID: proportional Integral Derivative Controller

// private CANSparkMax shootRightMotor = new
// CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);
// private CANSparkMax shootLeftMotor = new
// CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);

// public ShooterSubsystem() {

// shootRightMotor.restoreFactoryDefaults();
// shootLeftMotor.restoreFactoryDefaults();

// shootRightMotor.setInverted(true);

// shootRightMotor.setIdleMode(IdleMode.kCoast);
// shootLeftMotor.setIdleMode(IdleMode.kCoast);

// }

// @Override
// public void periodic() {
// // This method will be called once per scheduler run
// }
// //keep shootFromTrench speed at -0.60
// public void shootFromTrench(){ // Right Bumper
// shootLeftMotor.set(-0.60);
// shootRightMotor.set(-0.60);
// }
// //shootFromColourWheel speed is not yet set to proper speed because it hasn't
// been tested
// public void shootFromColourWheel(){ // Left Bumper
// shootLeftMotor.set(-0.59);
// shootRightMotor.set(-0.59);
// }
// public void stopShooter(){
// shootLeftMotor.set(0);
// shootRightMotor.set(0);
// }
// }