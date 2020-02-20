/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.level;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.AutoLevelSubsystem;

public class AutoLevelManualRightCommand extends CommandBase {
  private AutoLevelSubsystem autoLevelSubsystem;
  /**
   * Creates a new AutoLevelManualRightCommand.
   */
  public AutoLevelManualRightCommand(AutoLevelSubsystem _autoLevelSubsystem) {
    autoLevelSubsystem = _autoLevelSubsystem;
    addRequirements(autoLevelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    autoLevelSubsystem.autoLevelManualRight();
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
