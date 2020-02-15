/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  private final VictorSPX intakeMotor = new VictorSPX(Constants.INTAKE);

  public IntakeSubsystem() {
    intakeMotor.configFactoryDefault();

    intakeMotor.setNeutralMode(NeutralMode.Coast);

    intakeMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeFowardSpeed(){
    intakeMotor.set(ControlMode.Velocity, 1);
  }
  public void intakeReverseSpeed(){
    intakeMotor.set(ControlMode.Velocity, -1);
  }




}
