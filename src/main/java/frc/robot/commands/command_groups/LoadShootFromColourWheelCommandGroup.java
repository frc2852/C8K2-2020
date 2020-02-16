/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.command_groups;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.magazine.ManualLoadCommand;
import frc.robot.commands.shooter.ShootFromColourWheelCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LoadShootFromColourWheelCommandGroup extends ParallelCommandGroup {
  private ManualLoadCommand manualLoadCommand;
  private ShootFromColourWheelCommand shootFromColourWheelCommand;

  public LoadShootFromColourWheelCommandGroup(ManualLoadCommand _manualLoadCommand, ShootFromColourWheelCommand _shootFromColourWheelCommand) {
    manualLoadCommand = _manualLoadCommand;
    shootFromColourWheelCommand = _shootFromColourWheelCommand;

    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    // addCommands(shootFromColourWheelCommand);
    // Timer.delay(2);
    // addCommands(manualLoadCommand);
  }
}
