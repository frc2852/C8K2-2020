/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ColourWheelSubsystem extends SubsystemBase {

	private final I2C.Port i2cPort = I2C.Port.kOnboard;

	private final ColorSensorV3 colourSensor = new ColorSensorV3(i2cPort);

	private Color kBlueTarget;
	private Color kGreenTarget;
	private Color kRedTarget;
	private Color kYellowTarget;

	private String colourString;

	private ColorMatchResult match;

	private ColorMatch colourMatcher = new ColorMatch();

	/**
	 * Creates a new ColourWheelSubsystem.
	 */
	public ColourWheelSubsystem() {

		// double rawIR = colourSensor.getIR(); // gets binary value for infrared
		kBlueTarget = ColorMatch.makeColor(0.12, 0.42, 0.46);
		kGreenTarget = ColorMatch.makeColor(0.161, 0.58, 0.258);
		kRedTarget = ColorMatch.makeColor(0.53, 0.338, 0.1318);
		kYellowTarget = ColorMatch.makeColor(0.318, 0.559, 0.1215);

		colourMatcher.addColorMatch(kBlueTarget);
		colourMatcher.addColorMatch(kGreenTarget);
		colourMatcher.addColorMatch(kRedTarget);
		colourMatcher.addColorMatch(kYellowTarget); 
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		// Color detectedColour = colourSensor.getColor();
		// double rawIR = colourSensor.getIR(); // gets binary value for infrared

		Color detectedColour = colourSensor.getColor();
		match = colourMatcher.matchClosestColor(detectedColour);
		if (match.color == kBlueTarget) {
			colourString = "Blue";
		} else if (match.color == kRedTarget) {
			colourString = "Red";
		} else if (match.color == kGreenTarget) {
			colourString = "Green";
		} else if (match.color == kYellowTarget) {
			colourString = "Yellow";
		} else {
			colourString = "unknown";
		}

		SmartDashboard.putNumber("Red", detectedColour.red);
		SmartDashboard.putNumber("Green", detectedColour.green);
		SmartDashboard.putNumber("Blue", detectedColour.blue);
		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", colourString);

	}

	public void detectColour() {

	}

	// public void zeroColourSensor(){
	// SmartDashboard.putNumber("red", 0);
	// }

}
