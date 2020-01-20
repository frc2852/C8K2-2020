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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//this
public class DrivetrainSubsystem extends SubsystemBase {

  private CANSparkMax leftMaster = new CANSparkMax(Constants.DRIVE_LEFT_MASTER, MotorType.kBrushless);
  private CANSparkMax leftSlave = new CANSparkMax(Constants.DRIVE_LEFT_SLAVE, MotorType.kBrushless);

  private CANSparkMax rightMaster = new CANSparkMax(Constants.DRIVE_RIGHT_MASTER, MotorType.kBrushless);
  private CANSparkMax rightSlave = new CANSparkMax(Constants.DRIVE_RIGHT_SLAVE, MotorType.kBrushless);

  private DifferentialDrive differentialDrive;

  private final Gyro driveGyro = new ADXRS450_Gyro();
  
  /**
   * Creates a new DrivetrainSubsystem.
   */
  public DrivetrainSubsystem() {

    leftMaster.restoreFactoryDefaults();
    leftSlave.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave.restoreFactoryDefaults();
    
    leftMaster.setIdleMode(IdleMode.kCoast);
    leftSlave.setIdleMode(IdleMode.kCoast);
    rightMaster.setIdleMode(IdleMode.kCoast);
    rightSlave.setIdleMode(IdleMode.kCoast);

    rightMaster.setInverted(true);
    rightSlave.setInverted(true);

    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
    driveGyro.reset();


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("gyro", driveGyro.getAngle());
  }

  public void arcadeDrive(double xSpeed, double zRotation){
    differentialDrive.arcadeDrive(xSpeed, zRotation);
  }

  // public void zeroHeading(){
  //   driveGyro.reset();
  // }
  
  public double getHeading(){
    return Math.IEEEremainder(driveGyro.getAngle(), 360) * (Constants.kGyroReversed ? -1.0 : 1.0);
  }

  public double getTurnRate() {
    return driveGyro.getRate() * (Constants.kGyroReversed ? -1.0 : 1.0);
    // '?' is for short true/false if statement
    // ':' is the else
  }
}
