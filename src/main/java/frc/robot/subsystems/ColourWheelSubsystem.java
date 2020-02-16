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


		
  	}

  	@Override
  	public void periodic() {
		// This method will be called once per scheduler run		  
		Color detectColour = colourSensor.getColor();
		double rawIR = colourSensor.getIR(); //gets binary value for infrared
		
		SmartDashboard.putNumber("Red", detectColour.red);
		SmartDashboard.putNumber("Green", detectColour.green);
		SmartDashboard.putNumber("Blue", detectColour.blue);
		SmartDashboard.putNumber("IR", rawIR);
	}
	  
	public void autoPositionColourWheel(){
		if(gameData.length() > 0){
			switch (gameData.charAt(0)){
		    	case 'B' :
					  //Blue case code
					  colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
				case 'G' :
					//Green case code
					colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
		    	case 'R' :
					  //Red case cod
					  colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
		    	case 'Y' :
					  //Yellow case code
					  colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
		    	default :
					  //This is corrupt data
					  colourWheelMotor.set(ControlMode.Velocity, 0);
		      		break;
		  	}
		} 
		else {
			//Code for no data received yet
		}
	}

	public void autoRotationColourWheel(){

		if(gameData.length() > 0){
			switch (gameData.charAt(0)){
		    	case 'B' :
					  //Blue case code
					  colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
				case 'G' :
					//Green case code
					colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
		    	case 'R' :
					  //Red case cod
					  colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
		    	case 'Y' :
					  //Yellow case code
					  colourWheelMotor.set(ControlMode.Velocity, 1);
		      		break;
		    	default :
					  //This is corrupt data
					  colourWheelMotor.set(ControlMode.Velocity, 0);
		      		break;
		  	}
		} 
		else {
			//Code for no data received yet
		}
		
	}

	public void manualColourWheelRight(){
		colourWheelMotor.set(ControlMode.Velocity, 1);
	}

	public void manualColourWheelLeft(){
		colourWheelMotor.set(ControlMode.Velocity, -1);
	}
}
