/*-------------------------------------------------------on---------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.FowardSpeedIntakeCommand;
import frc.robot.commands.MagFowardSpeedCommand;
import frc.robot.commands.MagReverseSpeedCommand;
import frc.robot.commands.MaxInnerElevatorPositionCommand;
import frc.robot.commands.MinInnerElevatorPositionCommand;
import frc.robot.commands.OuterElevatorMaxPositionCommand;
import frc.robot.commands.OuterElevatorMinPositionCommand;
import frc.robot.commands.PivotCommand;
import frc.robot.commands.ReverseSpeedIntakeCommand;
import frc.robot.commands.drive.DriveHighGearboxCommand;
import frc.robot.commands.drive.DriveLowGearboxCommand;
import frc.robot.commands.drive.DrivetrainCommand;
import frc.robot.commands.shooter.ShooterFullSpeedCommand;
import frc.robot.commands.shooter.ShooterReverseFullSpeedCommand;
import frc.robot.subsystems.InnerElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.OuterElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;
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
	private XboxController DriverController = new XboxController(Constants.DRIVER_CONTROLLER);
	private Button DriveButtonX = new JoystickButton(DriverController, Constants.X_BUTTON);
	private Button DriveButtonA = new JoystickButton(DriverController, Constants.A_BUTTON);
	private Button DriveButtonB = new JoystickButton(DriverController, Constants.B_BUTTON);
	private Button DriveButtonY = new JoystickButton(DriverController, Constants.Y_BUTTON);
	private Button DriveButtonLeftBumper = new JoystickButton(DriverController, Constants.LEFT_BUMPER);
	private Button DriveButtonRightBumper = new JoystickButton(DriverController, Constants.RIGHT_BUMPER);
	private Button DriveButtonBack = new JoystickButton(DriverController, Constants.BACK_BUTTON);
	private Button DriveButtonStart = new JoystickButton(DriverController, Constants.START_BUTTON);
	private Button DriveLeftStickButton = new JoystickButton(DriverController, Constants.LEFT_STICK_BUTTON);
	private Button DriveRightStickButton = new JoystickButton(DriverController, Constants.RIGHT_STICK_BUTTON);

	private POVButton DriveButtonDPadUp = new POVButton(DriverController, Constants.DPAD_UP);
	private POVButton DriveButtonDPadRight = new POVButton(DriverController, Constants.DPAD_RIGHT);
	private POVButton DriveButtonDPadDown = new POVButton(DriverController, Constants.DPAD_DOWN);
	private POVButton DriveButtonDPadLeft = new POVButton(DriverController, Constants.DPAD_LEFT);

	//Operator Controller
	private XboxController OperatorController = new XboxController(Constants.OPERATOR_CONTROLLER);
	private Button OperatorButtonX = new JoystickButton(OperatorController, Constants.X_BUTTON);
	private Button OperatorButtonA = new JoystickButton(OperatorController, Constants.A_BUTTON);
	private Button OperatorButtonB = new JoystickButton(OperatorController, Constants.B_BUTTON);
	private Button OperatorButtonY = new JoystickButton(OperatorController, Constants.Y_BUTTON);
	private Button OperatorButtonLeftBumper = new JoystickButton(OperatorController, Constants.LEFT_BUMPER);
	private Button OperatorButtonRightBumper = new JoystickButton(OperatorController, Constants.RIGHT_BUMPER);
	private Button OperatorButtonBack = new JoystickButton(OperatorController, Constants.BACK_BUTTON);
	private Button OperatorButtonStart = new JoystickButton(OperatorController, Constants.START_BUTTON);
	private Button OperatorLeftStickButton = new JoystickButton(OperatorController, Constants.LEFT_STICK_BUTTON);
	private Button OperatorRightStickButton = new JoystickButton(OperatorController, Constants.RIGHT_STICK_BUTTON);

	private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

	private final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	private final DriveHighGearboxCommand driveHighGearboxCommand = new DriveHighGearboxCommand(gearboxSubsystem);
	private final DriveLowGearboxCommand driveLowGearboxCommand = new DriveLowGearboxCommand(gearboxSubsystem);

	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ShooterFullSpeedCommand shooterFullSpeedCommand = new ShooterFullSpeedCommand(shooterSubsystem);
	private final ShooterReverseFullSpeedCommand shooterReverseFullSpeedCommand = new ShooterReverseFullSpeedCommand(
			shooterSubsystem);

	private final MagazineSubsystem magazineSubsystem = new MagazineSubsystem();
	private final MagFowardSpeedCommand magFowardSpeedCommand = new MagFowardSpeedCommand(magazineSubsystem);
	private final MagReverseSpeedCommand magReverseSpeedCommand = new MagReverseSpeedCommand(magazineSubsystem);

	private final InnerElevatorSubsystem innerElevatorSubsystem = new InnerElevatorSubsystem();
	private final MaxInnerElevatorPositionCommand maxInnerElevatorPositionCommand = new MaxInnerElevatorPositionCommand(innerElevatorSubsystem);
	private final MinInnerElevatorPositionCommand minInnerElevatorPositionCommand = new MinInnerElevatorPositionCommand(innerElevatorSubsystem);

	private final OuterElevatorSubsystem outerElevatorSubsystem = new OuterElevatorSubsystem();
	private final OuterElevatorMaxPositionCommand outerElevatorMaxPositionCommand = new OuterElevatorMaxPositionCommand(outerElevatorSubsystem);
	private final OuterElevatorMinPositionCommand outerElevatorMinPositionCommand = new OuterElevatorMinPositionCommand(outerElevatorSubsystem);


	private final PivotSubsystem pivotSubsystem = new PivotSubsystem();
	private final PivotCommand pivotCommand = new PivotCommand(pivotSubsystem);

	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final FowardSpeedIntakeCommand fowardSpeedIntakeCommand  = new FowardSpeedIntakeCommand(intakeSubsystem);
	private final ReverseSpeedIntakeCommand reverseSpeedIntakeCommand = new ReverseSpeedIntakeCommand(intakeSubsystem);
	//TODO: foward => foRward
	
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
				() -> DriverController.getRawAxis(1), () -> DriverController.getRawAxis(4)));

		DriveLeftStickButton.toggleWhenPressed(driveHighGearboxCommand);

		DriveButtonRightBumper.whenPressed(shooterFullSpeedCommand);
		DriveButtonLeftBumper.whenPressed(shooterReverseFullSpeedCommand);

		DriveButtonA.whenPressed(magFowardSpeedCommand);
		DriveButtonB.whenPressed(magReverseSpeedCommand);

		DriveButtonX.whenPressed(fowardSpeedIntakeCommand);
		DriveButtonY.whenPressed(reverseSpeedIntakeCommand);

		DriveButtonDPadUp.whenPressed(maxInnerElevatorPositionCommand);
		DriveButtonDPadDown.whenPressed(minInnerElevatorPositionCommand);

		DriveButtonDPadRight.whenPressed(pivotCommand);
	}
}