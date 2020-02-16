/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {

	private WPI_TalonSRX leftMaster = new WPI_TalonSRX(Constants.DRIVE_LEFT_MASTER);
	private WPI_TalonSRX leftSlave = new WPI_TalonSRX(Constants.DRIVE_LEFT_SLAVE);

	private WPI_TalonSRX rightMaster = new WPI_TalonSRX(Constants.DRIVE_RIGHT_MASTER);
	private WPI_TalonSRX rightSlave = new WPI_TalonSRX(Constants.DRIVE_RIGHT_SLAVE);
	
	private DifferentialDrive differentialDrive;

	/**
	 * Creates a new DrivetrainSubsystem.
	 */
	public DrivetrainSubsystem() {

		leftMaster.configFactoryDefault();
		leftSlave.configFactoryDefault();
		rightMaster.configFactoryDefault();
		rightSlave.configFactoryDefault();


		leftMaster.setNeutralMode(NeutralMode.Coast);
		leftSlave.setNeutralMode(NeutralMode.Coast);
		rightMaster.setNeutralMode(NeutralMode.Coast);
		rightSlave.setNeutralMode(NeutralMode.Coast);

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);

		//Lights: right: green = forward
		//Lights: right: red = reverse
		//Lights: left: red = forward
		//Lights: left: green = reverse
		//when trouble shooting, don't rely on lights to tell direction

		differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void arcadeDrive(double xSpeed, double zRotation) {
		SmartDashboard.putNumber("xSpeed: ", xSpeed);
		SmartDashboard.putNumber("zRotation: ", zRotation);

		differentialDrive.arcadeDrive(xSpeed, zRotation);

	}


}