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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class InnerElevatorSubsystem extends SubsystemBase {
	/**
	 * Creates a new InnerElevatorSubsystem.
	 */
	private final TalonSRX innerElevator = new TalonSRX(Constants.INNER_ELEVATOR);

	public InnerElevatorSubsystem() {
		innerElevator.configFactoryDefault();

		innerElevator.setNeutralMode(NeutralMode.Brake);

		innerElevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run

	}

	// owen bullies me
	public void innerElevatorMaxPosition() {
		innerElevator.set(ControlMode.MotionMagic, 100);
	}

	public void innerElevatorMinPosition() {
		innerElevator.set(ControlMode.MotionMagic, 0);
	}	
}