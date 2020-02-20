/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// public class ShooterSubsystem extends PIDSubsystem {
// 	/**
// 	 * Creates a new ShooterSubsystem.
// 	 */
// 	//PID: proportional Integral Derivative Controller
// 	private CANSparkMax shootRightMotor = new CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);
// 	private CANSparkMax shootLeftMotor = new CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);
// 	double extra=0.5;

// 	public ShooterSubsystem() {
// 		super(new PIDController(2.0, 0.0, 0.0));// The constructor passes a name for the subsystem and the P, I and D constants that are used when computing the motor output
// 		shootLeftMotor.setAbsoluteTolerance​(extra); 
	
	
// 		shootRightMotor.getPIDController().setContinuous(false);

// 		shootRightMotor.restoreFactoryDefaults();
// 		shootLeftMotor.restoreFactoryDefaults();

// 		shootRightMotor.setInverted(true);
// // brake
// 		shootRightMotor.setIdleMode(IdleMode.kCoast);
// 		shootLeftMotor.setIdleMode(IdleMode.kCoast);

// 	}

// 	@Override
// 	public void periodic() {
// 		// This method will be called once per scheduler run
// 	}

// 	@Override
// 	public void useOutput(double output, double setpoint) {
// 	  // Use the output here
// 	}
  
// 	@Override
// 	public double getMeasurement() {
// 	  // Return the process variable measurement here
// 	  return 0;
// 	}

// 	public void shooterFullSpeed() {
// 		shootRightMotor.set(1);
// 		shootLeftMotor.set(1);
// 	}

// 	public void shooterReverse() {
// 		shootRightMotor.set(-1);
// 		shootLeftMotor.set(-1);
// 	}
// }

public class ShooterSubsystem extends SubsystemBase {
	/**
	 * Creates a new ShooterSubsystem.
	 */

	//PID: proportional Integral Derivative Controller

	private CANSparkMax shootRightMotor = new CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);
	private CANSparkMax shootLeftMotor = new CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);

	public ShooterSubsystem() {

		shootRightMotor.restoreFactoryDefaults();
		shootLeftMotor.restoreFactoryDefaults();

		shootRightMotor.setInverted(true);
	
		shootRightMotor.setIdleMode(IdleMode.kCoast);
		shootLeftMotor.setIdleMode(IdleMode.kCoast);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	} 
//keep shootFromTrench speed at -0.60
	public void shootFromTrench(){  // Right Bumper
		shootLeftMotor.set(-0.60);
		shootRightMotor.set(-0.60);
	}
//shootFromColourWheel speed is not yet set to proper speed because it hasn't been tested
	public void shootFromColourWheel(){  // Left Bumper
		shootLeftMotor.set(-0.59);
		shootRightMotor.set(-0.59);
	}
	public void stopShooter(){
		shootLeftMotor.set(0);
		shootRightMotor.set(0);
	}
}