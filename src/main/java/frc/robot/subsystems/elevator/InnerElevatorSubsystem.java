/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants; 

public class InnerElevatorSubsystem extends SubsystemBase {
  private final TalonSRX innerElevatorMotor = new TalonSRX(Constants.INNER_ELEVATOR);

  /**
   * Creates a new InnerElevatorSusbsystem.
   */
  public InnerElevatorSubsystem() {

    innerElevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    innerElevatorMotor.configFactoryDefault();

    innerElevatorMotor.setNeutralMode(NeutralMode.Brake);

    
		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		innerElevatorMotor.setSensorPhase(false);
		innerElevatorMotor.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		innerElevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		innerElevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		innerElevatorMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		innerElevatorMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		innerElevatorMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		innerElevatorMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		innerElevatorMotor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		innerElevatorMotor.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		innerElevatorMotor.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		innerElevatorMotor.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		innerElevatorMotor.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		innerElevatorMotor.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		innerElevatorMotor.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		innerElevatorMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void innerElevatorMaxPosition(){
    innerElevatorMotor.set(ControlMode.MotionMagic, 100);
  }

  public void innerElevatorMinimumPosition(){
    innerElevatorMotor.set(ControlMode.MotionMagic, 0);
  }
}
