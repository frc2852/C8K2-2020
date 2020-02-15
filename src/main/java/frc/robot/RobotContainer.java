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
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.drive.DriveHighGearboxCommand;
import frc.robot.commands.drive.DriveLowGearboxCommand;
import frc.robot.commands.drive.DrivetrainCommand;
import frc.robot.commands.elevator.innerElevator.MaxInnerElevatorPositionCommand;
import frc.robot.commands.elevator.innerElevator.MinInnerElevatorPositionCommand;
import frc.robot.commands.elevator.outerElevator.MaxOuterElevatorPositionCommand;
import frc.robot.commands.elevator.outerElevator.MinOuterElevatorPositionCommand;
import frc.robot.commands.intake.IntakeForwardCommand;
import frc.robot.commands.intake.IntakeReverseCommand;
import frc.robot.commands.magazine.ManualLoadCommand;
import frc.robot.commands.magazine.ManualReverseLoadCommand;
import frc.robot.commands.pivot.ManualPivotDownCommand;
import frc.robot.commands.pivot.ManualPivotUpCommand;
import frc.robot.commands.shooter.ShooterFullSpeedCommand;
import frc.robot.commands.shooter.ShooterReverseFullSpeedCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.DrivetrainSubsystem;
import frc.robot.subsystems.drive.GearboxSubsystem;
import frc.robot.subsystems.elevator.InnerElevatorSubsystem;
import frc.robot.subsystems.elevator.OuterElevatorSubsystem;

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
	private Trigger DriveButtonLeftTrigger = new JoystickButton(DriverController, Constants.LEFT_TRIGGER);
	private Trigger DriveButtonRightTrigger = new JoystickButton(DriverController, Constants.RIGHT_TRIGGER);	

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
	private Trigger OperatorButtonLeftTrigger = new JoystickButton(OperatorController, Constants.LEFT_TRIGGER);
	private Trigger OperatorButtonRightTrigger = new JoystickButton(OperatorController, Constants.RIGHT_TRIGGER);


	private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

	private final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	private final DriveHighGearboxCommand driveHighGearboxCommand = new DriveHighGearboxCommand(gearboxSubsystem);
	private final DriveLowGearboxCommand driveLowGearboxCommand = new DriveLowGearboxCommand(gearboxSubsystem);

	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ShooterFullSpeedCommand shooterFullSpeedCommand = new ShooterFullSpeedCommand(shooterSubsystem);
	private final ShooterReverseFullSpeedCommand shooterReverseFullSpeedCommand = new ShooterReverseFullSpeedCommand (shooterSubsystem);

	private final PivotSubsystem pivotSubsystem = new PivotSubsystem();
	private final ManualPivotDownCommand manualPivotDownCommand = new ManualPivotDownCommand(pivotSubsystem);
	private final ManualPivotUpCommand manualPivotUpCommand = new ManualPivotUpCommand(pivotSubsystem);

	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final IntakeForwardCommand intakeForwardCommand = new IntakeForwardCommand(intakeSubsystem);
	private final IntakeReverseCommand intakeReverseCommand = new IntakeReverseCommand(intakeSubsystem);

	private final InnerElevatorSubsystem innerElevatorSubsystem = new InnerElevatorSubsystem();
	private final MaxInnerElevatorPositionCommand maxInnerElevatorPositionCommand = new MaxInnerElevatorPositionCommand(innerElevatorSubsystem);
	private final MinInnerElevatorPositionCommand minInnerElevatorPositionCommand = new MinInnerElevatorPositionCommand(innerElevatorSubsystem);

	private final OuterElevatorSubsystem outerElevatorSubsystem = new OuterElevatorSubsystem();
	private final MaxOuterElevatorPositionCommand maxOuterElevatorPositionCommand = new MaxOuterElevatorPositionCommand(outerElevatorSubsystem);
	private final MinOuterElevatorPositionCommand minOuterElevatorPositionCommand = new MinOuterElevatorPositionCommand(outerElevatorSubsystem);

	private final MagazineSubsystem magazineSubsystem = new MagazineSubsystem();
	private final ManualLoadCommand manualLoadCommand = new ManualLoadCommand(magazineSubsystem);
	private final ManualReverseLoadCommand manualReverseLoadCommand = new ManualReverseLoadCommand(magazineSubsystem);
	

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
				() -> DriverController.getRawAxis(1), () -> DriverController.getRawAxis(4)));

		DriveButtonLeftJoystick.toggleWhenPressed(driveHighGearboxCommand);

		DriveButtonRightBumper.whenPressed(shooterFullSpeedCommand);
		DriveButtonLeftBumper.whenPressed(shooterReverseFullSpeedCommand);

		DriveButtonRightTrigger.whenActive(shooterFullSpeedCommand);

		DriveButtonA.whenPressed(intakeForwardCommand);
		DriveButtonY.whenPressed(intakeReverseCommand);

		DriveButtonB.whenPressed(manualLoadCommand);
		DriveButtonX.whenPressed(manualReverseLoadCommand);


		// Operator Stick

		OperatorButtonLeftBumper.whenPressed(manualPivotDownCommand);
		OperatorButtonRightBumper.whenPressed(manualPivotUpCommand);

		OperatorButtonA.whenPressed(maxInnerElevatorPositionCommand);
		OperatorButtonX.whenPressed(maxOuterElevatorPositionCommand);
		
		OperatorButtonB.whenPressed(minInnerElevatorPositionCommand);
		OperatorButtonY.whenPressed(minOuterElevatorPositionCommand);

	}
}
