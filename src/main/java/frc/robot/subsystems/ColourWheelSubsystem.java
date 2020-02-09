/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ColourWheelSubsystem extends SubsystemBase {
	private final TalonSRX colourWheelMotor = new TalonSRX(Constants.PI_WHEEL);
	String gameData = Robot.colourWheelData;

  	/**
  	 * Creates a new ColourWheelSubsystem.
  	 */
  	public ColourWheelSubsystem() {
		colourWheelMotor.configFactoryDefault();

		colourWheelMotor.setNeutralMode(NeutralMode.Brake);

		colourWheelMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

	 	/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		colourWheelMotor.setSensorPhase(false);
		colourWheelMotor.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		colourWheelMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		colourWheelMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		colourWheelMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		colourWheelMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		colourWheelMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		colourWheelMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		colourWheelMotor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		colourWheelMotor.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		colourWheelMotor.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		colourWheelMotor.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		colourWheelMotor.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		colourWheelMotor.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		colourWheelMotor.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		colourWheelMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		
  	}

  	@Override
  	public void periodic() {
  		// This method will be called once per scheduler run
	  }
	  
	public void autoPositionColourWheel(){
		colourWheelMotor.set(ControlMode.Velocity, 1);
	}

	public void autoRotationColourWheel(){
		colourWheelMotor.set(ControlMode.Velocity, 1);
		
	}
}
