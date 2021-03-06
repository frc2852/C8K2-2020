/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    public static final int LEFT_JOYSTICK_BUTTON = 9;
    public static final int RIGHT_JOYSTICK_BUTTON = 10;

    // Controller POV Mapping
    public final static int PovUp = 0;
    public final static int PovRight = 90;
    public final static int PovDown = 180;
    public final static int PovLeft = 270;

    // Controller Trigger Mapping
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

    // FRC Devices
    public static final int PDP = 0;
    public static final int PCM = 0;

    // Drive
    public static final int DRIVE_LEFT_MASTER = 1; // done
    public static final int DRIVE_LEFT_SLAVE = 2; // done

    public static final int DRIVE_RIGHT_MASTER = 3; // done
    public static final int DRIVE_RIGHT_SLAVE = 4; // done

    // Shooter
    public static final int SHOOT_RIGHT_MOTOR = 5; // done
    public static final int SHOOT_LEFT_MOTOR = 6; // done

    public static final int PIGEON_IMU = 7; // done

    // Elevators
    public static final int LEFT_ELEVATOR = 8; // done
    public static final int RIGHT_ELEVATOR = 9; // done, has encoder

    // Accessories
    public static final int INTAKE = 10; // done
    public static final int MAGAZINE = 11; // done
    public static final int PIVOT_MASTER = 15; // done
    public static final int PIVOT_SLAVE = 16; // done

    // Mag encoder
    public static final int kTimeoutMs = 30;
    public final static Gains kGains = new Gains(0.2, 0.0, 0.0, 0.2, 0, 1.0);
    public static final int kSlotIdx = 0;
    public static final int kPIDLoopIdx = 0;

    // Gearbox
    public static final int DRIVE_GEAR_BOX_OPEN = 0;
    public static final int DRIVE_GEAR_BOX_CLOSE = 1;
}