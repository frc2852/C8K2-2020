/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.DrivetrainSubsystem;

public class DrivetrainCommand extends CommandBase {
	
	/* Creates a new DrivetrainCommand. */
	private final DrivetrainSubsystem drivetrainSubsystem;
	private final DoubleSupplier xSpeed;
	private final DoubleSupplier zRotation;

	public DrivetrainCommand(DrivetrainSubsystem _drivetrainSubsystem, DoubleSupplier _xSpeed, DoubleSupplier _zRotation) {
		drivetrainSubsystem = _drivetrainSubsystem;
		xSpeed = _xSpeed;
		zRotation = _zRotation;
		addRequirements(drivetrainSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		drivetrainSubsystem.arcadeDrive(xSpeed.getAsDouble(), zRotation.getAsDouble());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}