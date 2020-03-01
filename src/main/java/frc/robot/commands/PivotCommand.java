/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PivotSubsystem;

public class PivotCommand extends CommandBase {
  /**
   * Creates a new PivotCommand.
   */ 
  private final PivotSubsystem pivotSubsystem;

  public PivotCommand(PivotSubsystem _pivotSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    pivotSubsystem = _pivotSubsystem;
    addRequirements(pivotSubsystem);
  }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pivotSubsystem.pivotUp();
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
