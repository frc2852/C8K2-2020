/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLightSubsystem extends SubsystemBase {
	
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx"); 
	NetworkTableEntry ty = table.getEntry("ty"); 
	NetworkTableEntry ta = table.getEntry("ta"); 
	NetworkTableEntry tv = table.getEntry("tv");
	NetworkTableEntry test = table.getEntry("ledMode");
	// NetworkTableEntry test = table.getEntry("ledMode").setNumber(1);
	
	// read values periodically
	double horizontalOffset = tx.getDouble(0.0);  // horizontal offset from target
	double verticalOffset = ty.getDouble(0.0);    // vertical offset from target
	double targetArea = ta.getDouble(0.0);        // target area %
	double validTarget = tv.getDouble(0);         // valid target (0, 1)
	// double _test = test.setDouble(1);


	/**
	 * Creates a new LimeLightSubsystem.
	 */
	public LimeLightSubsystem() {
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		// post to smart dashboard periodically
		SmartDashboard.putNumber("LimelightX", horizontalOffset);
		SmartDashboard.putNumber("LimelightY", verticalOffset);
		SmartDashboard.putNumber("LimelightArea", targetArea);
		SmartDashboard.putNumber("Limelight Valid Target", validTarget);
	}

	public void ledDefault(){
		table.getEntry("ledMode").setNumber(1);
	}

	public void ledOn(){
		table.getEntry("ledMode").setNumber(3);
	}
}
