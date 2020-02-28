/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class DrivetrainSubsystem extends PIDSubsystem {

	private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(Constants.DRIVE_LEFT_MASTER);
	private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(Constants.DRIVE_LEFT_SLAVE);

	private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(Constants.DRIVE_RIGHT_MASTER);
	private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(Constants.DRIVE_RIGHT_SLAVE);

	private final Encoder driveLeftEncoder = new Encoder(Constants.kLEFT_ENCODER_PORT, 
	Constants.kLEFT_ENCODER_PORT, false); // boolean --> is inverted, no
	// Constants.kEncoderPorts[1]

	private final Encoder driveRightEncoder = new Encoder(Constants.kRIGHT_ENCODER_PORT, 
	Constants.kRIGHT_ENCODER_PORT, true); // boolean --> is inverted, yes

	private final SimpleMotorFeedforward driveLeftFeedForward = new SimpleMotorFeedforward(0, 0);
	private final SimpleMotorFeedforward driveRightFeedForward = new SimpleMotorFeedforward(0, 0);

	private final DifferentialDrive differentialDrive;

	/**
	 * Creates a new DrivetrainSubsystem.
	 */
	public DrivetrainSubsystem() {
		super(new PIDController(0, 0, 0));
		getController().setTolerance(0);
		driveLeftEncoder.setDistancePerPulse(0);
		driveRightEncoder.setDistancePerPulse(0);
		setSetpoint(0);


		leftMaster.configFactoryDefault();
		leftSlave.configFactoryDefault();
		rightMaster.configFactoryDefault();
		rightSlave.configFactoryDefault();

		leftMaster.setNeutralMode(NeutralMode.Coast);
		leftSlave.setNeutralMode(NeutralMode.Coast);
		rightMaster.setNeutralMode(NeutralMode.Coast);
		rightSlave.setNeutralMode(NeutralMode.Coast);

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);

		// Lights: right: green = forward
		// Lights: right: red = reverse
		// Lights: left: red = forward
		// Lights: left: green = reverse
		// when trouble shooting, don't rely on lights to tell direction

		differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void arcadeDrive(final double xSpeed, final double zRotation) {
		SmartDashboard.putNumber("xSpeed: ", xSpeed);
		SmartDashboard.putNumber("zRotation: ", zRotation);

		differentialDrive.arcadeDrive(xSpeed, zRotation);
	}

	@Override
	public void useOutput(double output, double setpoint) {
		leftMaster.setVoltage(output + driveLeftFeedForward.calculate(setpoint));
		rightMaster.setVoltage(output + driveRightFeedForward.calculate(setpoint));
	}

	@Override
	public double getMeasurement() {
		if(driveRightEncoder.getRate() > driveLeftEncoder.getRate()){
			return driveRightEncoder.getRate();
		}
		else{
		  return driveLeftEncoder.getRate();
		}
	}

	

	public boolean atSetpoint() {
	  return m_controller.atSetpoint();
	}

}