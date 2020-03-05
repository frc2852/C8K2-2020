/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.intake.IntakeForwardCommand;
import frc.robot.commands.magazine.ManualLoadCommand;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class IntakeMagIntakeGroup extends ParallelCommandGroup {

	public IntakeMagIntakeGroup(MagazineSubsystem _magazineSubsystem, IntakeSubsystem _intakeSubsystem) {
		super(new ManualLoadCommand(_magazineSubsystem), new IntakeForwardCommand(_intakeSubsystem));
	}
}
