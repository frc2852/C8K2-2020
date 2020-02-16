/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.command_groups.LoadShootFromColourWheelCommandGroup;
import frc.robot.commands.command_groups.LoadShootFromTrenchCommandGroup;
import frc.robot.commands.drive.DriveHighGearboxCommand;
import frc.robot.commands.drive.DriveLowGearboxCommand;
import frc.robot.commands.drive.DrivetrainCommand;
import frc.robot.commands.elevator.MaxElevatorPositionCommand;
import frc.robot.commands.elevator.MinElevatorPositionCommand;
import frc.robot.commands.intake.IntakeForwardCommand;
import frc.robot.commands.intake.IntakeReverseCommand;
import frc.robot.commands.level.AutoLevelManualLeftCommand;
import frc.robot.commands.level.AutoLevelManualRightCommand;
import frc.robot.commands.magazine.ManualLoadCommand;
import frc.robot.commands.magazine.ManualReverseLoadCommand;
import frc.robot.commands.magazine.StopMagazineCommand;
import frc.robot.commands.pivot.PivotClimbCommand;
import frc.robot.commands.pivot.PivotColourWheelCommand;
import frc.robot.commands.pivot.PivotPickUpCommand;
import frc.robot.commands.pivot.PivotTrenchCommand;
import frc.robot.commands.shooter.ShootFromColourWheelCommand;
import frc.robot.commands.shooter.ShootFromTrenchCommand;
import frc.robot.commands.shooter.StopShooterCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.DrivetrainSubsystem;
import frc.robot.subsystems.drive.GearboxSubsystem;
import frc.robot.subsystems.elevator.AutoLevelSubsystem;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

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
	private XboxController OperatorController = new XboxController(Constants.OPERATOR_CONTROLLER);

	// Driver Buttons
	private Button DriveButtonX = new JoystickButton(DriverController, Constants.X_BUTTON);
	private Button DriveButtonA = new JoystickButton(DriverController, Constants.A_BUTTON);
	private Button DriveButtonB = new JoystickButton(DriverController, Constants.B_BUTTON);
	private Button DriveButtonY = new JoystickButton(DriverController, Constants.Y_BUTTON);
	private Button DriveButtonLeftBumper = new JoystickButton(DriverController, Constants.LEFT_BUMPER);
	private Button DriveButtonRightBumper = new JoystickButton(DriverController, Constants.RIGHT_BUMPER);
	private Button DriveButtonBack = new JoystickButton(DriverController, Constants.BACK_BUTTON);
	private Button DriveButtonStart = new JoystickButton(DriverController, Constants.START_BUTTON);
	private Button DriveButtonRightJoystick = new JoystickButton(DriverController, Constants.RIGHT_JOYSTICK_BUTTON);
	private Button DriveButtonLeftJoystick = new JoystickButton(DriverController, Constants.LEFT_JOYSTICK_BUTTON);

	// Driver D-Pad
	private POVButton DriveDpadUp = new POVButton(DriverController, Constants.PovUp);
	private POVButton DriveDpadRight = new POVButton(DriverController, Constants.PovRight);
	private POVButton DriveDpadDown = new POVButton(DriverController, Constants.PovDown);
	private POVButton DriveDpadLeft = new POVButton(DriverController, Constants.PovLeft);	
	
	// Driver Triggers
	private Trigger DriveButtonLeftTrigger = new JoystickButton(DriverController, Constants.LEFT_TRIGGER);
	private Trigger DriveButtonRightTrigger = new JoystickButton(DriverController, Constants.RIGHT_TRIGGER);	

	// Operator Buttons
	private Button OperatorButtonX = new JoystickButton(OperatorController, Constants.X_BUTTON);
	private Button OperatorButtonA = new JoystickButton(OperatorController, Constants.A_BUTTON);
	private Button OperatorButtonB = new JoystickButton(OperatorController, Constants.B_BUTTON);
	private Button OperatorButtonY = new JoystickButton(OperatorController, Constants.Y_BUTTON);
	private Button OperatorButtonLeftBumper = new JoystickButton(OperatorController, Constants.LEFT_BUMPER);
	private Button OperatorButtonRightBumper = new JoystickButton(OperatorController, Constants.RIGHT_BUMPER);
	private Button OperatorButtonBack = new JoystickButton(OperatorController, Constants.BACK_BUTTON);
	private Button OperatorButtonStart = new JoystickButton(OperatorController, Constants.START_BUTTON);
	private Button OperatorButtonRightJoystick = new JoystickButton(DriverController, Constants.RIGHT_JOYSTICK_BUTTON);
	private Button OperatorButtonLeftJoystick = new JoystickButton(DriverController, Constants.LEFT_JOYSTICK_BUTTON);

	// Operator D-Pad
	private POVButton OperatorDpadUp = new POVButton(DriverController, Constants.PovUp);
	private POVButton OperatorDpadRight = new POVButton(DriverController, Constants.PovRight);
	private POVButton OperatorDpadDown = new POVButton(DriverController, Constants.PovDown);
	private POVButton OperatorDpadLeft = new POVButton(DriverController, Constants.PovLeft);	

	// Operator Triggers
	private Trigger OperatorButtonLeftTrigger = new JoystickButton(OperatorController, Constants.LEFT_TRIGGER);
	private Trigger OperatorButtonRightTrigger = new JoystickButton(OperatorController, Constants.RIGHT_TRIGGER);

	private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

	private final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	private final DriveHighGearboxCommand driveHighGearboxCommand = new DriveHighGearboxCommand(gearboxSubsystem);
	private final DriveLowGearboxCommand driveLowGearboxCommand = new DriveLowGearboxCommand(gearboxSubsystem);

	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ShootFromColourWheelCommand shootFromColourWheelCommand = new ShootFromColourWheelCommand(shooterSubsystem);
	private final ShootFromTrenchCommand shootFromTrenchCommand = new ShootFromTrenchCommand(shooterSubsystem);
	private final StopShooterCommand stopShooterCommand = new StopShooterCommand(shooterSubsystem);

	private final PivotSubsystem pivotSubsystem = new PivotSubsystem();
	private final PivotClimbCommand pivotClimbCommand = new PivotClimbCommand(pivotSubsystem);
	private final PivotTrenchCommand pivotTrenchCommand = new PivotTrenchCommand(pivotSubsystem);
	private final PivotColourWheelCommand pivotColourWheelCommand = new PivotColourWheelCommand(pivotSubsystem);
	private final PivotPickUpCommand pivotPickUpCommand = new PivotPickUpCommand(pivotSubsystem);

	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final IntakeForwardCommand intakeForwardCommand = new IntakeForwardCommand(intakeSubsystem);
	private final IntakeReverseCommand intakeReverseCommand = new IntakeReverseCommand(intakeSubsystem);

	private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	private final MaxElevatorPositionCommand MaxElevatorPositionCommand = new MaxElevatorPositionCommand(elevatorSubsystem);
	private final MinElevatorPositionCommand MinElevatorPositionCommand = new MinElevatorPositionCommand(elevatorSubsystem);

	private final MagazineSubsystem magazineSubsystem = new MagazineSubsystem();
	private final ManualLoadCommand manualLoadCommand = new ManualLoadCommand(magazineSubsystem);
	private final ManualReverseLoadCommand manualReverseLoadCommand = new ManualReverseLoadCommand(magazineSubsystem);
	private final StopMagazineCommand stopMagazineCommand = new StopMagazineCommand(magazineSubsystem);

	private final AutoLevelSubsystem autoLevelSubsystem = new AutoLevelSubsystem();
	private final AutoLevelManualLeftCommand autoLevelManualLeftCommand = new AutoLevelManualLeftCommand(autoLevelSubsystem);
	private final AutoLevelManualRightCommand autoLevelManualRightCommand = new AutoLevelManualRightCommand(autoLevelSubsystem);

	// Command Groups
	private final LoadShootFromTrenchCommandGroup loadShootFromTrenchCommandGroup = new LoadShootFromTrenchCommandGroup(shootFromTrenchCommand, manualLoadCommand);
	private final LoadShootFromColourWheelCommandGroup loadShootFromColourWheelCommandGroup = new LoadShootFromColourWheelCommandGroup(manualLoadCommand, shootFromColourWheelCommand);
	

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();
		gearboxSubsystem.setDefaultCommand(driveLowGearboxCommand);
		shooterSubsystem.setDefaultCommand(stopShooterCommand);
		magazineSubsystem.setDefaultCommand(stopMagazineCommand);

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
				() -> DriverController.getRawAxis(1), () -> DriverController.getRawAxis(4)));

		DriveButtonLeftJoystick.toggleWhenPressed(driveHighGearboxCommand);

		// DriveButtonRightBumper.whenPressed(loadShootFromColourWheelCommandGroup);
		// DriveButtonLeftBumper.whenPressed(loadShootFromTrenchCommandGroup);

		DriveButtonRightBumper.toggleWhenPressed(shootFromTrenchCommand);
		DriveButtonLeftBumper.toggleWhenPressed(shootFromColourWheelCommand);

		DriveButtonA.whenPressed(intakeForwardCommand);
		DriveButtonY.whenPressed(intakeReverseCommand);

		DriveButtonB.toggleWhenPressed(manualLoadCommand);
		DriveButtonX.toggleWhenPressed(manualReverseLoadCommand);

		DriveDpadRight.whenPressed(autoLevelManualRightCommand);
		DriveDpadLeft.whenPressed(autoLevelManualRightCommand);


		// Operator Stick

		OperatorButtonA.whenPressed(pivotPickUpCommand);
		OperatorButtonB.whenPressed(pivotColourWheelCommand);
		OperatorButtonX.whenPressed(pivotTrenchCommand);
		OperatorButtonY.whenPressed(pivotClimbCommand);

		OperatorButtonLeftTrigger.whenActive(intakeForwardCommand);
		OperatorButtonRightTrigger.whenActive(intakeReverseCommand);

	}
}
