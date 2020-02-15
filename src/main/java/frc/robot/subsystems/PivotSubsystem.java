/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PivotSubsystem extends SubsystemBase {
  /**
   * Creates a new PivotSubsystem.
   */
  private CANSparkMax pivotLeft = new CANSparkMax(Constants.PIVOT_LEFT, MotorType.kBrushless);
  private CANSparkMax pivotRight = new CANSparkMax(Constants.PIVOT_RIGHT, MotorType.kBrushless);

  public PivotSubsystem() {

    pivotLeft.restoreFactoryDefaults();
    pivotLeft.restoreFactoryDefaults();

    pivotLeft.setIdleMode(IdleMode.kBrake);
    pivotRight.setIdleMode(IdleMode.kBrake);

    pivotRight.follow(pivotLeft);

    pivotRight.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pivotUp(){
    pivotLeft.set(1);
  }
}
