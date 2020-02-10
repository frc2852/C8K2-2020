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

public class PivotSubsystem extends SubsystemBase {

	private final CANSparkMax pivotMotorLeft = new CANSparkMax(Constants.PIVOT_LEFT, MotorType.kBrushless);
	private final CANSparkMax pivotMotorRight = new CANSparkMax(Constants.PIVOT_RIGHT, MotorType.kBrushless);

	/**
	 * Creates a new PivotSubsystem.
	 */
	public PivotSubsystem() {

		pivotMotorLeft.restoreFactoryDefaults();
		pivotMotorRight.restoreFactoryDefaults();

		pivotMotorRight.setInverted(true);

		pivotMotorRight.setIdleMode(IdleMode.kBrake);
		pivotMotorLeft.setIdleMode(IdleMode.kBrake);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void manualPivotUp() {
		pivotMotorLeft.set(0.5);
		pivotMotorRight.set(0.5);
	}

	public void manualPivotDown() {
		pivotMotorLeft.set(-0.5);
		pivotMotorRight.set(-0.5);
	}
}
