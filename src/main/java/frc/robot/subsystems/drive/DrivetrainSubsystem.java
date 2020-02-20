/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {

	private CANSparkMax leftMaster = new CANSparkMax(Constants.DRIVE_LEFT_MASTER, MotorType.kBrushless);
	private CANSparkMax leftSlave = new CANSparkMax(Constants.DRIVE_LEFT_SLAVE, MotorType.kBrushless);

	private CANSparkMax rightMaster = new CANSparkMax(Constants.DRIVE_RIGHT_MASTER, MotorType.kBrushless);
	private CANSparkMax rightSlave = new CANSparkMax(Constants.DRIVE_RIGHT_SLAVE, MotorType.kBrushless);

	private DifferentialDrive differentialDrive;

	/**
	 * Creates a new DrivetrainSubsystem.
	 */
	public DrivetrainSubsystem() {

		leftMaster.restoreFactoryDefaults();
		leftSlave.restoreFactoryDefaults();
		rightMaster.restoreFactoryDefaults();
		rightSlave.restoreFactoryDefaults();


		leftMaster.setIdleMode(IdleMode.kCoast);
		leftSlave.setIdleMode(IdleMode.kCoast);
		rightMaster.setIdleMode(IdleMode.kCoast);
		rightSlave.setIdleMode(IdleMode.kCoast);

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);

		rightSlave.setInverted(true);
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