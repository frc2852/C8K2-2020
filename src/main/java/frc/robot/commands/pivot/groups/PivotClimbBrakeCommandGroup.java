/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pivot.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.pivot.PivotBrakeEnageCommand;
import frc.robot.commands.pivot.RaiseThatPosteriorCommand;
import frc.robot.subsystems.pivot.PivotBrakeSubsystem;
import frc.robot.subsystems.pivot.PivotSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PivotClimbBrakeCommandGroup extends SequentialCommandGroup {

	/**
	 * Creates a new PivotClimbBrakeCommandGroup.
	 */
	public PivotClimbBrakeCommandGroup(PivotSubsystem _pivotSubsystem, PivotBrakeSubsystem _pivotBrakeSubsystem) {
		super(new RaiseThatPosteriorCommand(_pivotSubsystem), new PivotBrakeEnageCommand(_pivotBrakeSubsystem));
	}
}
