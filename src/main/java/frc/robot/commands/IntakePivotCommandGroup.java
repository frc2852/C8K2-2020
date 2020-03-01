/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.IntakePivotLowCommand;
import frc.robot.commands.pivot.PivotPickUpCommand;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.intake.IntakePivotSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakePivotCommandGroup extends SequentialCommandGroup {
  private IntakePivotSubsystem intakePivotSubsystem;

  private PivotSubsystem pivotSubsystem;
  /**
   * Creates a new IntakePivotCommandGroup.
   */
  public IntakePivotCommandGroup(IntakePivotSubsystem _intakePivotSubsystem, PivotSubsystem _pivotSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    intakePivotSubsystem = _intakePivotSubsystem;
    pivotSubsystem = _pivotSubsystem;

    addCommands(new IntakePivotLowCommand(intakePivotSubsystem), new PivotPickUpCommand(pivotSubsystem));

  }
}
