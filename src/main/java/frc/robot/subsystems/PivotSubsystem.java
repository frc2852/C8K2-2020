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
	//shooter is attached to elevator, pivots to shoot, go under trench
	//and to climb

	private final CANSparkMax pivotMotorMaster = new CANSparkMax(Constants.PIVOT_LEFT, MotorType.kBrushless);
	private final CANSparkMax pivotMotorSlave = new CANSparkMax(Constants.PIVOT_RIGHT, MotorType.kBrushless);

	/**
	 * Creates a new PivotSubsystem.
	 */
	public PivotSubsystem() {
		pivotMotorMaster.burnFlash();
		pivotMotorSlave.burnFlash();

		pivotMotorMaster.restoreFactoryDefaults();
		pivotMotorSlave.restoreFactoryDefaults();

		// pivotMotorTwo.setInverted(true);

		pivotMotorMaster.setIdleMode(IdleMode.kBrake);
		pivotMotorSlave.setIdleMode(IdleMode.kBrake);

		pivotMotorSlave.follow(pivotMotorMaster);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void pivotPickUp(){ 
		pivotMotorSlave.set(0);
	}

	public void pivotColourWheel(){
		//TODO: change
		pivotMotorMaster.set(0.2);
	}

	public void pivotTrench(){
		pivotMotorMaster.set(-0.2);

	}

	public void pivotClimb(){
		
	}

}
