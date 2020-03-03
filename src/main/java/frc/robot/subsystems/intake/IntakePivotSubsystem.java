/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakePivotSubsystem extends SubsystemBase {

	private DoubleSolenoid intakePivotSolenoid = new DoubleSolenoid(Constants.INTAKE_UP, Constants.INTAKE_DOWN);

	public IntakePivotSubsystem() {

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void intakePivotPickUp() {
		intakePivotSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void intakePivotStartingConfig() {
		intakePivotSolenoid.set(DoubleSolenoid.Value.kForward);
	}
}
