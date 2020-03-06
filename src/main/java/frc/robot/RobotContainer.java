/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.drive.DriveHighGearboxCommand;
import frc.robot.commands.drive.DriveLowGearboxCommand;
import frc.robot.commands.drive.DrivetrainCommand;
import frc.robot.commands.elevator.ElevatorMovementCommand;
import frc.robot.commands.groups.IntakeMagIntakeGroup;
import frc.robot.commands.groups.IntakeMagReverse;
import frc.robot.commands.intake.IntakeForwardCommand;
import frc.robot.commands.intake.IntakePivotDownCommand;
import frc.robot.commands.intake.IntakePivotUpCommand;
import frc.robot.commands.intake.IntakeReverseCommand;
import frc.robot.commands.intake.IntakeStopCommand;
import frc.robot.commands.magazine.ManualLoadCommand;
import frc.robot.commands.magazine.ManualReverseLoadCommand;
import frc.robot.commands.magazine.StopMagazineCommand;
import frc.robot.commands.pivot.PivotBrakeDisenageCommand;
import frc.robot.commands.pivot.PivotBrakeEnageCommand;
import frc.robot.commands.pivot.PivotClimbCommand;
import frc.robot.commands.pivot.PivotColourWheelCommand;
import frc.robot.commands.pivot.PivotLowGoalCommand;
import frc.robot.commands.pivot.PivotPickUpCommand;
import frc.robot.commands.pivot.PivotTrenchCommand;
import frc.robot.commands.pivot.RaiseThatPosteriorCommand;
import frc.robot.commands.shooter.ShootFromColourWheelCommand;
import frc.robot.commands.shooter.ShootFromLineCommand;
import frc.robot.commands.shooter.ShootFromTrenchCommand;
import frc.robot.commands.shooter.ShooterStopped;
import frc.robot.subsystems.intake.IntakePivotSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.pivot.PivotSubsystem;
import frc.robot.subsystems.drive.DrivetrainSubsystem;
import frc.robot.subsystems.drive.GearboxSubsystem;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.pivot.PivotBrakeSubsystem;

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

	// Driver Triggers
	private Button DriveButtonLeftTrigger = new Button(() -> DriverController.getTriggerAxis(Hand.kLeft) >= 0.5);
	private Button DriveButtonRightTrigger = new Button(() -> DriverController.getTriggerAxis(Hand.kRight) >= 0.5);

	// Operator Buttons
	private Button OperatorButtonX = new JoystickButton(OperatorController, Constants.X_BUTTON);
	private Button OperatorButtonA = new JoystickButton(OperatorController, Constants.A_BUTTON);
	private Button OperatorButtonB = new JoystickButton(OperatorController, Constants.B_BUTTON);
	private Button OperatorButtonY = new JoystickButton(OperatorController, Constants.Y_BUTTON);
	private Button OperatorButtonLeftBumper = new JoystickButton(OperatorController, Constants.LEFT_BUMPER);
	private Button OperatorButtonRightBumper = new JoystickButton(OperatorController, Constants.RIGHT_BUMPER);
	// private Button OperatorButtonBack = new JoystickButton(OperatorController,
	// Constants.BACK_BUTTON);
	// private Button OperatorButtonStart = new JoystickButton(OperatorController,
	// Constants.START_BUTTON);

	private Button OperatorButtonLeftTrigger = new Button(() -> OperatorController.getTriggerAxis(Hand.kLeft) >= 0.5);
	private Button OperatorButtonRightTrigger = new Button(() -> OperatorController.getTriggerAxis(Hand.kRight) >= 0.5);

	// Operator D-Pad
	// private POVButton OperatorDpadUp = new POVButton(OperatorController,
	// Constants.PovUp);
	// private POVButton OperatorDpadRight = new POVButton(OperatorController,
	// Constants.PovRight);
	// private POVButton OperatorDpadDown = new POVButton(OperatorController,
	// Constants.PovDown);
	// private POVButton OperatorDpadLeft = new POVButton(OperatorController,
	// Constants.PovLeft);

	// Subsystems & Commands
	private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

	// Drive
	private final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	private final DriveHighGearboxCommand driveHighGearboxCommand = new DriveHighGearboxCommand(gearboxSubsystem);
	private final DriveLowGearboxCommand driveLowGearboxCommand = new DriveLowGearboxCommand(gearboxSubsystem);

	// Intake
	private final IntakePivotSubsystem intakePivotSubsystem = new IntakePivotSubsystem();
	private final IntakePivotUpCommand intakePivotUpCommand = new IntakePivotUpCommand(intakePivotSubsystem);

	// Pivot
	private final PivotSubsystem pivotSubsystem = new PivotSubsystem();
	private final PivotClimbCommand pivotClimbCommand = new PivotClimbCommand(pivotSubsystem, intakePivotSubsystem);
	private final PivotColourWheelCommand pivotColourWheelCommand = new PivotColourWheelCommand(pivotSubsystem,
			intakePivotSubsystem);
	private final PivotPickUpCommand pivotPickUpCommand = new PivotPickUpCommand(pivotSubsystem, intakePivotSubsystem);
	private final PivotTrenchCommand pivotTrenchCommand = new PivotTrenchCommand(pivotSubsystem, intakePivotSubsystem);
	private final PivotLowGoalCommand pivotLowGoalCommand = new PivotLowGoalCommand(pivotSubsystem,
			intakePivotSubsystem);

	// Pivot Brake
	private final PivotBrakeSubsystem pivotBrakeSubsystem = new PivotBrakeSubsystem();
	private final PivotBrakeDisenageCommand pivotBrakeDisenageCommand = new PivotBrakeDisenageCommand(
			pivotBrakeSubsystem);
	private final PivotBrakeEnageCommand pivotBrakeEnageCommand = new PivotBrakeEnageCommand(pivotBrakeSubsystem);
	private final RaiseThatPosteriorCommand raiseThatPosteriorCommand = new RaiseThatPosteriorCommand(pivotSubsystem,
			pivotBrakeSubsystem);

	// Mag
	private final MagazineSubsystem magazineSubsystem = new MagazineSubsystem();
	private final StopMagazineCommand stopMagazineCommand = new StopMagazineCommand(magazineSubsystem);

	// Intake
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final IntakeStopCommand intakeStopCommand = new IntakeStopCommand(intakeSubsystem);
	private final IntakeMagIntakeGroup intakeMagIntakeGroup = new IntakeMagIntakeGroup(magazineSubsystem,
			intakeSubsystem);
	private final IntakeMagReverse intakeMagReverse = new IntakeMagReverse(magazineSubsystem, intakeSubsystem);

	// Elevator
	private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();

	// Shooter
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ShootFromColourWheelCommand ShootFromColourWheel = new ShootFromColourWheelCommand(shooterSubsystem);
	private final ShootFromTrenchCommand ShootFromTrenchCommand = new ShootFromTrenchCommand(shooterSubsystem);
	private final ShooterStopped shooterStopped = new ShooterStopped(shooterSubsystem);
	private final ShootFromLineCommand shootFromLine = new ShootFromLineCommand(shooterSubsystem);

	// Auto
	private final Command goalInline =
			// Start the command by spinning up the shooter...
			new InstantCommand(shooterSubsystem::shootFromLineCenteredAuto, shooterSubsystem).andThen(
					// Wait until the shooter is at speed before balls
					new WaitCommand(Constants.kFlyWheelSpeedDelay),
					// Start running the mag
					new InstantCommand(magazineSubsystem::manualReverseLoad, magazineSubsystem),
					// Shoot for the specified time
					new WaitCommand(Constants.kAutoShootTimeSeconds),
					// Drive backwards
					new RunCommand(drivetrainSubsystem::driveBackwards, drivetrainSubsystem))
					.withTimeout(Constants.kDriveBackwards).andThen(() -> {
						shooterSubsystem.stop();
						magazineSubsystem.stopMagazine();
					});

	private final Command middleOfField =
			// Start the command by spinning up the shooter...
			new InstantCommand(shooterSubsystem::shootFromLineMiddleField, shooterSubsystem).andThen(
					// Wait until the shooter is at speed before balls
					new WaitCommand(Constants.kFlyWheelSpeedDelay),
					// Start running the mag
					new InstantCommand(magazineSubsystem::manualReverseLoad, magazineSubsystem),
					// Shoot for the specified time
					new WaitCommand(Constants.kAutoShootTimeSeconds),
					// Drive backwards
					new RunCommand(drivetrainSubsystem::driveBackwards, drivetrainSubsystem))
					.withTimeout(Constants.kDriveBackwards).andThen(() -> {
						shooterSubsystem.stop();
						magazineSubsystem.stopMagazine();
					});

	private final Command farLeftField =
			// Start the command by spinning up the shooter...
			new InstantCommand(shooterSubsystem::shootFromLineLeftField, shooterSubsystem).andThen(
					// Wait until the shooter is at speed before balls
					new WaitCommand(Constants.kFlyWheelSpeedDelay),
					// Start running the mag
					new InstantCommand(magazineSubsystem::manualReverseLoad, magazineSubsystem),
					// Shoot for the specified time
					new WaitCommand(Constants.kAutoShootTimeSeconds),
					// Drive backwards
					new RunCommand(drivetrainSubsystem::driveBackwards, drivetrainSubsystem))
					.withTimeout(Constants.kDriveBackwards).andThen(() -> {
						shooterSubsystem.stop();
						magazineSubsystem.stopMagazine();
					});

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the button bindings
		configureButtonBindings();
		gearboxSubsystem.setDefaultCommand(driveLowGearboxCommand);
		magazineSubsystem.setDefaultCommand(stopMagazineCommand);
		pivotBrakeSubsystem.setDefaultCommand(pivotBrakeDisenageCommand);
		intakeSubsystem.setDefaultCommand(intakeStopCommand);
		intakePivotSubsystem.setDefaultCommand(intakePivotUpCommand);
		shooterSubsystem.setDefaultCommand(shooterStopped);
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link Xbox Controller}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {

		// Driver
		drivetrainSubsystem.setDefaultCommand(new DrivetrainCommand(drivetrainSubsystem,
				() -> -DriverController.getRawAxis(1), () -> DriverController.getRawAxis(4)));
		DriveButtonLeftJoystick.toggleWhenPressed(driveHighGearboxCommand);

		DriveButtonLeftTrigger.whenPressed(ShootFromColourWheel);
		DriveButtonRightTrigger.whenPressed(ShootFromTrenchCommand);
		DriveButtonLeftBumper.whenPressed(shootFromLine);
		DriveButtonLeftTrigger.whenReleased(shooterStopped);
		DriveButtonRightTrigger.whenReleased(shooterStopped);
		DriveButtonLeftBumper.whenReleased(shooterStopped);

		// Operator
		elevatorSubsystem.setDefaultCommand(
				new ElevatorMovementCommand(elevatorSubsystem, () -> -OperatorController.getRawAxis(1)));
		OperatorButtonRightBumper.whenPressed(raiseThatPosteriorCommand);

		OperatorButtonA.whenPressed(pivotPickUpCommand);
		OperatorButtonB.whenPressed(pivotColourWheelCommand);
		OperatorButtonX.whenPressed(pivotTrenchCommand);
		OperatorButtonY.whenPressed(pivotClimbCommand);
		OperatorButtonLeftBumper.whenPressed(pivotLowGoalCommand);

		OperatorButtonLeftTrigger.whenHeld(intakeMagIntakeGroup);
		OperatorButtonRightTrigger.whenHeld(intakeMagReverse);
	}

	public Command getAutonomousCommand() {
		// return goalInline;
		// return middleOfField;
		return farLeftField;
	}
}
