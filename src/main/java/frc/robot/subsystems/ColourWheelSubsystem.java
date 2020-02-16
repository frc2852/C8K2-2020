/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ColourWheelSubsystem extends SubsystemBase {

	private final I2C.Port i2cPort = I2C.Port.kOnboard;

	private final ColorSensorV3 colourSensor = new ColorSensorV3(i2cPort);

	private final Color kBlueTarget;
	private final Color kGreenTarget;
	private final Color kRedTarget;
	private final Color kYellowTarget;

	private String colourString;

	/**
	 * Creates a new ColourWheelSubsystem.
	 */
	public ColourWheelSubsystem() {

		double rawIR = colourSensor.getIR(); // gets binary value for infrared

		kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
		kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
		kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
		kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		Color detectColour = colourSensor.getColor();
		double rawIR = colourSensor.getIR(); // gets binary value for infrared

		SmartDashboard.putNumber("Red", detectColour.red);
		SmartDashboard.putNumber("Green", detectColour.green);
		SmartDashboard.putNumber("Blue", detectColour.blue);
		// SmartDashboard.putNumber("Yellow", detectColour.kYellow);
		SmartDashboard.putNumber("IR", rawIR);
	}

	public void red() {
		Color detectColour = colourSensor.getColor();

		if (detectColour.red > detectColour.blue && detectColour.red > detectColour.green) {
			SmartDashboard.putBoolean("Red", true);
		} else {
			SmartDashboard.putBoolean("Red", false);
		}

	}

	public void green() {
		Color detectColour = colourSensor.getColor();

		if (detectColour.green > detectColour.blue && detectColour.green > detectColour.red) {
			SmartDashboard.putBoolean("Green", true);
		} else {
			SmartDashboard.putBoolean("Green", false);
		}

	}

	public void blue() {
		Color detectColour = colourSensor.getColor();

		if (detectColour.blue > detectColour.red && detectColour.blue > detectColour.green) {
			SmartDashboard.putBoolean("Blue", true);
		} else {
			SmartDashboard.putBoolean("Blue", false);
		}

	}

	public void yellow() {
		Color detectColour = colourSensor.getColor();

		if (detectColour.red > detectColour.blue && detectColour.green > detectColour.blue) {
			SmartDashboard.putBoolean("Yellow", true);
		} else {
			SmartDashboard.putBoolean("Yellow", false);
		}

	}

	public void getColour() {
		Color detectColour = colourSensor.getColor();



	}

}
