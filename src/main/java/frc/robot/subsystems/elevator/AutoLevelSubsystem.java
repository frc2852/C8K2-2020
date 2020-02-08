/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AutoLevelSubsystem extends SubsystemBase {
	private final TalonSRX autoLevelMasterMotor = new TalonSRX(Constants.AUTO_LEVEL_MASTER);
	private final TalonSRX autoLevelSlaveMotor = new TalonSRX(Constants.AUTO_LEVEL_SLAVE);

	/**
	 * Creates a new AutoLevelSubsystem.
	 */
	public AutoLevelSubsystem() {
		autoLevelMasterMotor.configFactoryDefault();
		autoLevelSlaveMotor.configFactoryDefault();

		autoLevelSlaveMotor.setNeutralMode(NeutralMode.Brake);
		autoLevelMasterMotor.setNeutralMode(NeutralMode.Brake);

		autoLevelSlaveMotor.follow(autoLevelMasterMotor);

	}

	@Override
	public void periodic() {
	  	// This method will be called once per scheduler run
	}

	public void autoLevel(){
		//TODO: ????????????????
	}
}