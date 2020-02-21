/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
// import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DetectColourCommand;
import frc.robot.commands.drive.DriveHighGearboxCommand;
import frc.robot.commands.drive.DriveLowGearboxCommand;
import frc.robot.commands.drive.DrivetrainCommand;
import frc.robot.subsystems.ColourWheelSubsystem;
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

	// Controllers
	private XboxController DriverController = new XboxController(Constants.DRIVER_CONTROLLER);
	private XboxController OperatorController = new XboxController(Constants.OPERATOR_CONTROLLER);

	// private Joystick DriverController = new Joystick(Constants.DRIVER_CONTROLLER);
	// private Joystick OperatorController = new Joystick(Constants.DRIVER_CONTROLLER);

	// Driver Buttons
	private Button DriveButtonX = new JoystickButton(DriverController, Constants.X_BUTTON);
	private Button DriveButtonA = new JoystickButton(DriverController, Constants.A_BUTTON);
	private Button DriveButtonB = new JoystickButton(DriverController, Constants.B_BUTTON);
	private Button DriveButtonY = new JoystickButton(DriverController, Constants.Y_BUTTON);
	private Button DriveButtonLeftBumper = new JoystickButton(DriverController, Constants.LEFT_BUMPER);
	private Button DriveButtonRightBumper = new JoystickButton(DriverController, Constants.RIGHT_BUMPER);
	private Button DriveButtonBack = new JoystickButton(DriverController, Constants.BACK_BUTTON);
	private Button DriveButtonStart = new JoystickButton(DriverController, Constants.START_BUTTON);
	private Button DriveButtonLeftJoystick = new JoystickButton(DriverController, Constants.LEFT_JOYSTICK_BUTTON);
	private Button DriveButtonRightJoystick = new JoystickButton(DriverController, Constants.RIGHT_JOYSTICK_BUTTON);
	// private Trigger DriverButtonLeftTrigger = new JoystickButton(DriverController, Constants.LEFT_TRIGGER);
	// private Trigger DriverButtonRightTrigger = new JoystickButton(DriverController, Constants.RIGHT_TRIGGER);	

	// Operator Buttons
	private Button OperatorButtonX = new JoystickButton(OperatorController, Constants.X_BUTTON);
	private Button OperatorButtonA = new JoystickButton(OperatorController, Constants.A_BUTTON);
	private Button OperatorButtonB = new JoystickButton(OperatorController, Constants.B_BUTTON);
	private Button OperatorButtonY = new JoystickButton(OperatorController, Constants.Y_BUTTON);
	private Button OperatorButtonLeftBumper = new JoystickButton(OperatorController, Constants.LEFT_BUMPER);
	private Button OperatorButtonRightBumper = new JoystickButton(OperatorController, Constants.RIGHT_BUMPER);
	private Button OperatorButtonBack = new JoystickButton(OperatorController, Constants.BACK_BUTTON);
	private Button OperatorButtonStart = new JoystickButton(OperatorController, Constants.START_BUTTON);
	private Button OperatorButtonLeftJoystick = new JoystickButton(DriverController, Constants.LEFT_JOYSTICK_BUTTON);
	private Button OperatorButtonRightJoystick = new JoystickButton(DriverController, Constants.RIGHT_JOYSTICK_BUTTON);
	// private Trigger OperatorButtonLeftTrigger = new JoystickButton(OperatorController, Constants.LEFT_TRIGGER);
	// private Trigger OperatorButtonRightTrigger = new JoystickButton(OperatorController, Constants.RIGHT_TRIGGER);


	private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

	private final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	private final DriveHighGearboxCommand driveHighGearboxCommand = new DriveHighGearboxCommand(gearboxSubsystem);
	private final DriveLowGearboxCommand driveLowGearboxCommand = new DriveLowGearboxCommand(gearboxSubsystem);

	private final ColourWheelSubsystem colourWheelSubsystem = new ColourWheelSubsystem();
	private final DetectColourCommand getColourCommand = new DetectColourCommand(colourWheelSubsystem);

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
		
		// Driver Stick
		drivetrainSubsystem.setDefaultCommand(new DrivetrainCommand(drivetrainSubsystem,
				() -> DriverController.getRawAxis(1), () -> -DriverController.getRawAxis(4)));

		DriveButtonLeftJoystick.toggleWhenPressed(driveHighGearboxCommand);

		DriveButtonA.whenPressed(getColourCommand);
		
		// Operator Stick

	}
}