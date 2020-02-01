/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.drive.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToAngleDegrees extends PIDCommand {
  /**
   * Creates a new TurnToAngle90Degrees.
   */
  public TurnToAngleDegrees(double targetAngleDegrees, DrivetrainSubsystem drivetrainSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kTurnP, Constants.kTurnI, Constants.kTurnD),
        // This should return the measurement
        drivetrainSubsystem::getHeading,
        // This should return the setpoint (can also be a constant)
        targetAngleDegrees,
        // This uses the output
        output -> drivetrainSubsystem.arcadeDrive(0, output),
          // Use the output here
        drivetrainSubsystem);
        getController().enableContinuousInput(-180, 180);
        getController()
        .setTolerance(Constants.kTurnToleranceDeg, Constants.kTurnRateToleranceDegPerS);
        addRequirements(drivetrainSubsystem);
  }
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // final boolean atTargetAngle = false;
    // if(DrivetrainSubsystem.getTurnRate() == targetAngleDegrees){
    //   atTargetAngle = true;
    // }
    // return atTargetAngle;
    return getController().atSetpoint();

  }
}
