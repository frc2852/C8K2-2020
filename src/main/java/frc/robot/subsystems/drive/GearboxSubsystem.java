/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GearboxSubsystem extends SubsystemBase {
  
  private DoubleSolenoid gearboxSolenoid = new DoubleSolenoid(Constants.DRIVE_GEAR_BOX_OPEN, Constants.DRIVE_GEAR_BOX_CLOSE);

  public GearboxSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveHighGear(){
    gearboxSolenoid.set(Value.kForward);
  }
  public void driveLowGear(){
    gearboxSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
}
