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

public class MagazineSubsystem extends SubsystemBase { // loads and stores the balls
 	private CANSparkMax magazineMotor = new CANSparkMax(Constants.MAGAZINE, MotorType.kBrushless);
  	/**
   	* Creates a new MagazineSubsystem.
   	*/
	public MagazineSubsystem() {
		magazineMotor.restoreFactoryDefaults();

   		magazineMotor.setIdleMode(IdleMode.kBrake);

	}
 	@Override
  	public void periodic() {
    	// This method will be called once per scheduler run
	  }
	  
	public void manualLoad(){
		magazineMotor.set(0.30);
	}

	public void manualReverseLoad(){ //magazine runs in reverse
		magazineMotor.set(-0.25);
	}

	public void stopMagazine(){
		magazineMotor.set(0);
	}
}
