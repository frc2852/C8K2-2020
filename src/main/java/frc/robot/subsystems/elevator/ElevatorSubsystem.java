/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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

		mainElevator.setSensorPhase(true);
		mainElevator.setInverted(false);
		slaveElevator.setInverted(true);

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
