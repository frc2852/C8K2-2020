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
  /**
   * Creates a new ShooterSubsystem.
   */
  private CANSparkMax shootRightMotor = new CANSparkMax(Constants.SHOOT_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax shootLeftMotor = new CANSparkMax(Constants.SHOOT_LEFT_MOTOR, MotorType.kBrushless);

  public ShooterSubsystem() {

    shootRightMotor.restoreFactoryDefaults(); 
    shootLeftMotor.restoreFactoryDefaults();

    // shootMotorSlave.follow(shootMotorMaster);

    shootRightMotor.setIdleMode(IdleMode.kCoast);
    shootLeftMotor.setIdleMode(IdleMode.kCoast);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shooterFullSpeed(){
    shootRightMotor.set(-1);
    shootLeftMotor.set(1);

  }

  public void shooterReverse(){
    shootRightMotor.set(1);
    shootLeftMotor.set(-1);
  }
}
