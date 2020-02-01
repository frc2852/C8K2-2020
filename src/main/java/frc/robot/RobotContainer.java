/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drive.DriveHighGearboxCommand;
import frc.robot.commands.drive.DriveLowGearboxCommand;
import frc.robot.commands.drive.DrivetrainCommand;
import frc.robot.commands.shooter.ShooterFullSpeedCommand;
import frc.robot.commands.shooter.ShooterReverseFullSpeedCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.DrivetrainSubsystem;
import frc.robot.subsystems.drive.GearboxSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here. need to initialize
 * every command and every subsystem
 */
public class RobotContainer {
 
	// Driver Controller
	private Joystick DriverController = new Joystick(Constants.DRIVER_CONTROLLER);
	private Button DriveButtonX = new JoystickButton(DriverController, Constants.X_BUTTON);
	private Button DriveButtonA = new JoystickButton(DriverController, Constants.A_BUTTON);
	private Button DriveButtonB = new JoystickButton(DriverController, Constants.B_BUTTON);
	private Button DriveButtonY = new JoystickButton(DriverController, Constants.Y_BUTTON);
	private Button DriveButtonLeftBumper = new JoystickButton(DriverController, Constants.LEFT_BUMPER);
	private Button DriveButtonRightBumper = new JoystickButton(DriverController, Constants.RIGHT_BUMPER);
	private Button DriveButtonBack = new JoystickButton(DriverController, Constants.BACK_BUTTON);
	private Button DriveButtonStart = new JoystickButton(DriverController, Constants.START_BUTTON);

	private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

	private final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	private final DriveHighGearboxCommand driveHighGearboxCommand = new DriveHighGearboxCommand(gearboxSubsystem);
	private final DriveLowGearboxCommand driveLowGearboxCommand = new DriveLowGearboxCommand(gearboxSubsystem);

	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ShooterFullSpeedCommand shooterFullSpeedCommand = new ShooterFullSpeedCommand(shooterSubsystem);
	private final ShooterReverseFullSpeedCommand shooterReverseFullSpeedCommand = new ShooterReverseFullSpeedCommand(shooterSubsystem);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();	
		gearboxSubsystem.setDefaultCommand(driveLowGearboxCommand);

	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link Xbox Controller}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		drivetrainSubsystem.setDefaultCommand(new DrivetrainCommand(drivetrainSubsystem,
			() -> DriverController.getRawAxis(1), () -> -DriverController.getRawAxis(4)));

		DriveButtonBack.whenPressed(driveLowGearboxCommand);
		DriveButtonStart.whenPressed(driveHighGearboxCommand);

		DriveButtonRightBumper.whenPressed(shooterFullSpeedCommand);
		DriveButtonLeftBumper.whenPressed(shooterReverseFullSpeedCommand);
	}
}
