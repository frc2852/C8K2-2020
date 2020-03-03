/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

	private final TalonSRX elevatorMotorMaster = new TalonSRX(Constants.LEFT_ELEVATOR);
	private final TalonSRX elevatorMotorSlave = new TalonSRX(Constants.RIGHT_ELEVATOR);

	/**
	 * Creates a new ElevatorSubsystem.
	 */
	public ElevatorSubsystem() {

		elevatorMotorMaster.configFactoryDefault();
		elevatorMotorSlave.configFactoryDefault();

		elevatorMotorSlave.setInverted(false);
		elevatorMotorMaster.setInverted(false);

		// elevatorMotorSlave.follow(elevatorMotorMaster);
		elevatorMotorSlave.set(ControlMode.Follower, Constants.LEFT_ELEVATOR);

		elevatorMotorMaster.setNeutralMode(NeutralMode.Brake);
		elevatorMotorSlave.setNeutralMode(NeutralMode.Brake);


		// elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
		// 		Constants.kTimeoutMs);

		// elevatorMotor.configFactoryDefault();
		// elevatorMotor.setNeutralMode(NeutralMode.Brake);

		// /**
		//  * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		//  * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		//  * sensor to have positive increment when driving Talon Forward (Green LED)
		//  */
		// elevatorMotor.setSensorPhase(false);
		// elevatorMotor.setInverted(false);

		// /* Set relevant frame periods to be at least as fast as periodic rate */
		// elevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		// elevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		// /* Set the peak and nominal outputs */
		// elevatorMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		// elevatorMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		// elevatorMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		// elevatorMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		// /* Set Motion Magic gains in slot0 - see documentation */
		// elevatorMotor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		// elevatorMotor.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		// elevatorMotor.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		// elevatorMotor.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		// elevatorMotor.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		// /* Set acceleration and vcruise velocity - see documentation */
		// elevatorMotor.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		// elevatorMotor.configMotionAcceleration(6000, Constants.kTimeoutMs);

		// /* Zero the sensor */
		// elevatorMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);


	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void elevatorMaxPosition() {
		elevatorMotorMaster.set(ControlMode.PercentOutput, 0.15);
		// elevatorMotor.set(ControlMode.MotionMagic, 100);
	}

	public void elevatorMinimumPosition() {
		elevatorMotorMaster.set(ControlMode.PercentOutput, -0.15);
		// elevatorMotor.set(ControlMode.MotionMagic, 0);
	}

	public void elevatorStop(){
		elevatorMotorMaster.set(ControlMode.PercentOutput, 0);
	}
}