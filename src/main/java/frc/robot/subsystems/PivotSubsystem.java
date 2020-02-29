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
	private final CANSparkMax pivotMotorMaster = new CANSparkMax(Constants.PIVOT_MASTER, MotorType.kBrushless);
	private final CANSparkMax pivotMotorSlave = new CANSparkMax(Constants.PIVOT_SLAVE, MotorType.kBrushless);

	/**
	 * Creates a new PivotSubsystem.
	 */
	public PivotSubsystem() {
		pivotMotorMaster.restoreFactoryDefaults();
		pivotMotorSlave.restoreFactoryDefaults();

		pivotMotorMaster.setIdleMode(IdleMode.kBrake);
		pivotMotorSlave.setIdleMode(IdleMode.kBrake);

		pivotMotorSlave.follow(pivotMotorMaster);

		pivotMotorMaster.burnFlash();
		pivotMotorSlave.burnFlash();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void pivotPickUp() {
		// pivotMotorSlave.set(0);
	}

	public void pivotColourWheel() {
		// pivotMotorMaster.set(0.2);
	}

	public void pivotTrench() {
		// pivotMotorMaster.set(-0.2);

	}

	public void pivotClimb() {

	}

}
