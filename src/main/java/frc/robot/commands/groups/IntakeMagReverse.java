/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.intake.IntakeReverseCommand;
import frc.robot.commands.magazine.ManualReverseLoadCommand;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeMagReverse extends ParallelCommandGroup {

	public IntakeMagReverse(MagazineSubsystem _magazineSubsystem, IntakeSubsystem _intakeSubsystem) {
		super(new ManualReverseLoadCommand(_magazineSubsystem), new IntakeReverseCommand(_intakeSubsystem));
	}
}
