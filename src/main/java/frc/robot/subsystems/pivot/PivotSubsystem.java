/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pivot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PivotSubsystem extends SubsystemBase {

	// Motor Controllers
	private final CANSparkMax pivotMotorMaster = new CANSparkMax(Constants.PIVOT_MASTER, MotorType.kBrushless);
	private final CANSparkMax pivotMotorSlave = new CANSparkMax(Constants.PIVOT_SLAVE, MotorType.kBrushless);

	// PID
	private CANPIDController m_pidController;
	private CANEncoder m_encoder;
	public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

	/**
	 * Creates a new PivotSubsystem.
	 */
	public PivotSubsystem() {
		// Motor Setup
		pivotMotorMaster.restoreFactoryDefaults();
		pivotMotorSlave.restoreFactoryDefaults();

		pivotMotorMaster.setIdleMode(IdleMode.kBrake);
		pivotMotorSlave.setIdleMode(IdleMode.kBrake);

		pivotMotorSlave.follow(pivotMotorMaster);

		// PID Setup
		m_pidController = pivotMotorMaster.getPIDController();
		m_encoder = pivotMotorMaster.getEncoder();
		m_encoder.setPosition(0);

		// PID coefficients
		kP = 0.100000;
		kI = 0.000100;
		kD = 1.200000;
		kIz = 0.000100;
		kFF = 0;
		kMaxOutput = 0.4;
		kMinOutput = -0.4;

		// set PID coefficients
		m_pidController.setP(kP);
		m_pidController.setI(kI);
		m_pidController.setD(kD);
		m_pidController.setIZone(kIz);
		m_pidController.setFF(kFF);
		m_pidController.setOutputRange(kMinOutput, kMaxOutput);

		// BURN
		pivotMotorMaster.burnFlash();
		pivotMotorSlave.burnFlash();

		m_pidController.setReference(0, ControlType.kPosition);

		// display PID coefficients on SmartDashboard
		SmartDashboard.putNumber("P Gain", kP);
		SmartDashboard.putNumber("I Gain", kI);
		SmartDashboard.putNumber("D Gain", kD);
		SmartDashboard.putNumber("I Zone", kIz);
		SmartDashboard.putNumber("Feed Forward", kFF);
		SmartDashboard.putNumber("Max Output", kMaxOutput);
		SmartDashboard.putNumber("Min Output", kMinOutput);
		SmartDashboard.putNumber("Set Rotations", 0);
	}

	@Override
	public void periodic() {
		// read PID coefficients from SmartDashboard
		double p = SmartDashboard.getNumber("P Gain", 0);
		double i = SmartDashboard.getNumber("I Gain", 0);
		double d = SmartDashboard.getNumber("D Gain", 0);
		double iz = SmartDashboard.getNumber("I Zone", 0);
		double ff = SmartDashboard.getNumber("Feed Forward", 0);
		double max = SmartDashboard.getNumber("Max Output", 0);
		double min = SmartDashboard.getNumber("Min Output", 0);
		double rotations = SmartDashboard.getNumber("Set Rotations", 0);

		// if PID coefficients on SmartDashboard have changed, write new values to
		// controller
		if ((p != kP)) {
			m_pidController.setP(p);
			kP = p;
		}
		if ((i != kI)) {
			m_pidController.setI(i);
			kI = i;
		}
		if ((d != kD)) {
			m_pidController.setD(d);
			kD = d;
		}
		if ((iz != kIz)) {
			m_pidController.setIZone(iz);
			kIz = iz;
		}
		if ((ff != kFF)) {
			m_pidController.setFF(ff);
			kFF = ff;
		}
		if ((max != kMaxOutput) || (min != kMinOutput)) {
			m_pidController.setOutputRange(min, max);
			kMinOutput = min;
			kMaxOutput = max;
		}

		/**
		 * PIDController objects are commanded to a set point using the SetReference()
		 * method.
		 * 
		 * The first parameter is the value of the set point, whose units vary depending
		 * on the control type set in the second parameter.
		 * 
		 * The second parameter is the control type can be set to one of four
		 * parameters: com.revrobotics.ControlType.kDutyCycle
		 * com.revrobotics.ControlType.kPosition com.revrobotics.ControlType.kVelocity
		 * com.revrobotics.ControlType.kVoltage
		 */
		m_pidController.setReference(rotations, ControlType.kPosition);
		SmartDashboard.putNumber("SetPoint", rotations);
		SmartDashboard.putNumber("ProcessVariable", m_encoder.getPosition());
	}

	public void PickUpPosition() {
		m_pidController.setReference(26, ControlType.kPosition);
	}

	public void ClimbPosition() {
		m_pidController.setReference(-18, ControlType.kPosition);
	}

	public void RaiseThatPosterior() {
		m_pidController.setReference(-10, ControlType.kPosition);
	}

	public boolean RaiseThatPosteriorFinished() {
		double currentPosition = m_encoder.getPosition();
		currentPosition = Math.abs(currentPosition);
		return (currentPosition < 11 && currentPosition > 9);
	}

	public void PivotTrench() {
		m_pidController.setReference(-2, ControlType.kPosition);
	}

	public void PivotColourWheel() {
		m_pidController.setReference(0, ControlType.kPosition);
	}

	public void PivotLowGoal(){
		m_pidController.setReference(10, ControlType.kPosition);
	}
}
