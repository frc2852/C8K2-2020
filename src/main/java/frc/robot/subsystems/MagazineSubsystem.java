/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MagazineSubsystem extends SubsystemBase {
  /**
   * Creates a new MagazineSubsystem.
   */
  private final TalonSRX magazineMotor = new TalonSRX(Constants.MAGAZINE);

  public MagazineSubsystem() {

    magazineMotor.configFactoryDefault();

    magazineMotor.setNeutralMode(NeutralMode.Brake);
  
    magazineMotor.setInverted(true);

    magazineMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void magazineFowardSpeed(){
    magazineMotor.set(ControlMode.Velocity, 1);
  }
  public void magazineReverseSpeed(){
    magazineMotor.set(ControlMode.Velocity, -1);
  }

}
