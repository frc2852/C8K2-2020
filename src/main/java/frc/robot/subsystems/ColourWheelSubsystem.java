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
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ColourWheelSubsystem extends SubsystemBase {
	private final TalonSRX colourWheelMotor = new TalonSRX(Constants.PI_WHEEL);

	private final I2C.Port i2cPort = I2C.Port.kOnboard;

	private final ColorSensorV3 colourSensor = new ColorSensorV3(i2cPort);

	private Color kBlueTarget;
	private Color kGreenTarget;
	private Color kRedTarget;
	private Color kYellowTarget;

	private String colourString;

	private ColorMatchResult match;

	private ColorMatch colourMatcher = new ColorMatch();

	String gameData = Robot.colourWheelData;

  	/**
  	 * Creates a new ColourWheelSubsystem.
  	 */
  	public ColourWheelSubsystem() { 

		colourWheelMotor.configFactoryDefault();

		colourWheelMotor.setNeutralMode(NeutralMode.Brake);

		colourWheelMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

	 	/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		colourWheelMotor.setSensorPhase(false);
		colourWheelMotor.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		colourWheelMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		colourWheelMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		colourWheelMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		colourWheelMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		colourWheelMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		colourWheelMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		colourWheelMotor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		colourWheelMotor.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		colourWheelMotor.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		colourWheelMotor.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		colourWheelMotor.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		colourWheelMotor.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		colourWheelMotor.configMotionAcceleration(6000, Constants.kTimeoutMs);

		/* Zero the sensor */
		colourWheelMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);


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
	  
	public void autoPositionColourWheel(){
		if(gameData.length() > 0){
			switch (gameData.charAt(0)){   //All encoder positions are angles for logic
		    	case 'B' :
					//Blue case code
					if(colourString.equalsIgnoreCase("red")){
						colourWheelMotor.set(ControlMode.MotionMagic, 0);
					}
					else if(colourString.equalsIgnoreCase("green")){
						colourWheelMotor.set(ControlMode.MotionMagic, 45);
					}
					else if(colourString.equalsIgnoreCase("yellow")){
						colourWheelMotor.set(ControlMode.MotionMagic, -45);

					}
					else if(colourString.equalsIgnoreCase("blue")){
						colourWheelMotor.set(ControlMode.MotionMagic, 90);

					}
					else{
						SmartDashboard.putString("Colour Auto", "ERROR");
						colourWheelMotor.set(ControlMode.Velocity, 0);
					}
					break;
				case 'G' :
					//Green case code
					if(colourString.equalsIgnoreCase("red")){
						colourWheelMotor.set(ControlMode.MotionMagic, -45);

					}
					else if(colourString.equalsIgnoreCase("green")){
						colourWheelMotor.set(ControlMode.MotionMagic, 90);

					}
					else if(colourString.equalsIgnoreCase("yellow")){
						colourWheelMotor.set(ControlMode.MotionMagic, 0);

					}
					else if(colourString.equalsIgnoreCase("blue")){
						colourWheelMotor.set(ControlMode.MotionMagic, 45);

					}
					else{
						SmartDashboard.putString("Colour Auto", "ERROR");
						colourWheelMotor.set(ControlMode.Velocity, 0);
					}
		      		break;
		    	case 'R' :
					  //Red case cod
					if(colourString.equalsIgnoreCase("red")){
						colourWheelMotor.set(ControlMode.MotionMagic, 90);

					}
					else if(colourString.equalsIgnoreCase("green")){
						colourWheelMotor.set(ControlMode.MotionMagic, -45);

					}
					else if(colourString.equalsIgnoreCase("yellow")){
						colourWheelMotor.set(ControlMode.MotionMagic, 45);

					}
					else if(colourString.equalsIgnoreCase("blue")){
						colourWheelMotor.set(ControlMode.MotionMagic, 0);

					}
					else{
						SmartDashboard.putString("Colour Auto", "ERROR");
						colourWheelMotor.set(ControlMode.Velocity, 0);

					}
		      		break;
		    	case 'Y' :
					//Yellow case code
					if(colourString.equalsIgnoreCase("red")){
						colourWheelMotor.set(ControlMode.MotionMagic, -45);
				
					}
					else if(colourString.equalsIgnoreCase("green")){
						colourWheelMotor.set(ControlMode.MotionMagic, 0);
						
					}
					else if(colourString.equalsIgnoreCase("yellow")){
						colourWheelMotor.set(ControlMode.MotionMagic, 90);
						
					}
					else if(colourString.equalsIgnoreCase("blue")){
						colourWheelMotor.set(ControlMode.MotionMagic, 45);
						
					}
					else{
						SmartDashboard.putString("Colour Auto", "ERROR");
						colourWheelMotor.set(ControlMode.Velocity, 0);

					}
		      		break;
		    	default :
					//This is corrupt data
					colourWheelMotor.set(ControlMode.Velocity, 0);
					SmartDashboard.putString("Colour Auto", "Corrupt");
		      		break;
		  	}
		} 
		else {
			//Code for no data received yet
			colourWheelMotor.set(ControlMode.Velocity, 0);
		}
	}

	public void autoRotationColourWheel(){
		colourWheelMotor.set(ControlMode.MotionMagic, 720);
		
	}

	public void manualColourWheelRight(){
		colourWheelMotor.set(ControlMode.Velocity, 1);
	}

	public void manualColourWheelLeft(){
		colourWheelMotor.set(ControlMode.Velocity, -1);
	}

	 //red:blue green:yellow
	}
