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
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("BOOOO", m_encoder.getPosition());
	}

	public void PickUpPosition() {
		m_pidController.setReference(26, ControlType.kPosition);
	}

	public void ClimbPosition() {
		m_pidController.setReference(-15, ControlType.kPosition);
	}

	public void RaiseThatPosterior() {
		m_pidController.setReference(-5, ControlType.kPosition);
	}

	public boolean RaiseThatPosteriorFinished() {
		double currentPosition = m_encoder.getPosition();
		currentPosition = Math.abs(currentPosition);
		return (currentPosition < 6 && currentPosition > 4);
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
