/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//This is an example
/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // #region Controllers

    // Controllers Mapping
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;

    // Controller Button Mapping
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int BACK_BUTTON = 7;
    public static final int START_BUTTON = 8;

    // #endregion Controllers

    // #region CAN Bus

    // FRC Devices
    public static final int PDP = 0;
    public static final int PCM = 5;

    // Drive
    public static final int DRIVE_LEFT_MASTER = 1;
    public static final int DRIVE_LEFT_SLAVE = 2;

    public static final int DRIVE_RIGHT_MASTER = 3;
    public static final int DRIVE_RIGHT_SLAVE = 4;
    
    // Shooter
    public static final int SHOOT_RIGHT_MOTOR = 5;
    public static final int SHOOT_LEFT_MOTOR = 6;
    
    //Gearbox
    public static final int DRIVE_GEAR_BOX_OPEN = 0;
    public static final int DRIVE_GEAR_BOX_CLOSE = 1;

    // #endregion CAN Bus

    // #region PCM Bus

    // #endregion PCM Bus
}
