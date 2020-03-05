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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

	private CANSparkMax shooterLeft = new CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);
	private CANSparkMax shooterRight = new CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);

	/**
	 * Creates a new ShooterSubsystem.
	 */
	public ShooterSubsystem() {
		shooterLeft.restoreFactoryDefaults();
		shooterLeft.setIdleMode(IdleMode.kCoast);

		shooterRight.restoreFactoryDefaults();
		shooterRight.setIdleMode(IdleMode.kCoast);

		shooterRight.setInverted(true);
		shooterLeft.burnFlash();
		shooterRight.burnFlash();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void shootFromTrench(){
		shooterLeft.set(-1);
		shooterRight.set(-1);
	}

	public void shootFromColourWheel(){
		shooterLeft.set(-0.8);
		shooterRight.set(-0.8);
	}

	public void stop(){
		shooterLeft.set(0);
		shooterRight.set(0);
	}
}
