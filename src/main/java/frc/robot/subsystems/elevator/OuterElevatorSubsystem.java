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

public class OuterElevatorSubsystem extends SubsystemBase {

  private final TalonSRX outerElevatorMotor = new TalonSRX(Constants.OUTER_ELEVATOR);
  
  /**
   * Creates a new ElevatorSubsystem.
   */
  public OuterElevatorSubsystem() {
    outerElevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    outerElevatorMotor.configFactoryDefault();

    outerElevatorMotor.setNeutralMode(NeutralMode.Brake);



		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		outerElevatorMotor.setSensorPhase(false);
		outerElevatorMotor.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		outerElevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		outerElevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		outerElevatorMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		outerElevatorMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		outerElevatorMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		outerElevatorMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		outerElevatorMotor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		outerElevatorMotor.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		outerElevatorMotor.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		outerElevatorMotor.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		outerElevatorMotor.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		outerElevatorMotor.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		outerElevatorMotor.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		outerElevatorMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void outerElevatorMaxPosition(){
    outerElevatorMotor.set(ControlMode.MotionMagic, 100);
  }

  public void outerElevatorMinimumPosition(){
    outerElevatorMotor.set(ControlMode.MotionMagic, 0);
  }
}
