/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

	private CANSparkMax shooterLeft = new CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);
	private CANSparkMax shooterRight = new CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);

	private CANPIDController leftPidController, righPidController;
	private CANEncoder leftEncoder, rightEncoder;
	public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

	/**
	 * Creates a new ShooterSubsystem.
	 */
	public ShooterSubsystem() {
		shooterLeft.restoreFactoryDefaults();
		shooterRight.restoreFactoryDefaults();

		shooterLeft.setIdleMode(IdleMode.kCoast);
		shooterRight.setIdleMode(IdleMode.kCoast);

		leftPidController = shooterLeft.getPIDController();
		leftEncoder = shooterLeft.getEncoder();

		righPidController = shooterRight.getPIDController();
		rightEncoder = shooterRight.getEncoder();

		// PID coefficients
		kP = 0.000300;
		kI = 0.000000;
		kD = 0.000010;
		kIz = 0;
		kFF = 0.000015;
		kMaxOutput = 1;
		kMinOutput = -1;
		maxRPM = 5700;

		// SmartDashboard.putNumber("kP", kP);
		// set PID coefficients
		leftPidController.setP(kP);
		leftPidController.setI(kI);
		leftPidController.setD(kD);
		leftPidController.setIZone(kIz);
		leftPidController.setFF(kFF);
		leftPidController.setOutputRange(kMinOutput, kMaxOutput);

		// set PID coefficients
		righPidController.setP(kP);
		righPidController.setI(kI);
		righPidController.setD(kD);
		righPidController.setIZone(kIz);
		righPidController.setFF(kFF);
		righPidController.setOutputRange(kMinOutput, kMaxOutput);

		shooterLeft.setInverted(true);
		shooterRight.setInverted(false);
		shooterLeft.burnFlash();
		shooterRight.burnFlash();
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("LEFT", leftEncoder.getVelocity());
		SmartDashboard.putNumber("RIGHT", rightEncoder.getVelocity());
		// This method will be called once per scheduler run
	}

	public void shootFromTrench() {
		leftPidController.setReference(2525*2, ControlType.kVelocity);
		righPidController.setReference(2525*2, ControlType.kVelocity);
	}

	public void shootFromColourWheel() {
		leftPidController.setReference(1880*2, ControlType.kVelocity);
		righPidController.setReference(1880*2, ControlType.kVelocity);
	}

	//Sooting from line
	//Shooting from under the goal
	//Shooting for auto 1
	//Shooting for auto 2
	public void shootFromLineCenteredOnGoal(){
		leftPidController.setReference(1470*2, ControlType.kVelocity);
		righPidController.setReference(1470*2, ControlType.kVelocity);
	}

	public void shootFromLineCenteredAuto(){
		leftPidController.setReference(1570*2, ControlType.kVelocity);
		righPidController.setReference(1570*2, ControlType.kVelocity);
	}

	public void shootFromLineMiddleField(){
		leftPidController.setReference(1570*2, ControlType.kVelocity);
		righPidController.setReference(1570*2, ControlType.kVelocity);
	}

	public void shootFromLineLeftField(){
		leftPidController.setReference(1720*2, ControlType.kVelocity);
		righPidController.setReference(1720*2, ControlType.kVelocity);
	}

	public void stop() {
		leftPidController.setReference(0, ControlType.kVelocity);
		righPidController.setReference(0, ControlType.kVelocity);
	}

	public boolean atSetpoint() {
		double kVelocity = leftEncoder.getVelocity();
		if(kVelocity > 2100){
			return true;
		} else {
			return false;
		}
	  }
	
}
