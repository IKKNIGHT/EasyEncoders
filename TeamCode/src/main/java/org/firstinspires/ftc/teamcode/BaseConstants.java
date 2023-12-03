package org.firstinspires.ftc.teamcode;

/**
 * BaseConstants class has configuration constants for the base motors
 * @see EasyEncoders
 */
public class BaseConstants {
    // Config for the base motors
    /**
     * Config Name for the front right motor
     */
    public static final String FRONT_RIGHT = "frontright";
    /**
     * Config Name for the front left motor
     */
    public static final String FRONT_LEFT = "frontleft";
    /**
     * Config Name for the back right motor
     */
    public static final String BACK_RIGHT = "backright";
    /**
     * Config Name for the back left motor
     */
    public static final String BACK_LEFT = "backleft";
    // Config for the imu
    /**
     * Config Name for the imu
     */
    public static final String IMU = "imu";
    // boolean that tells us if motor is reversed
    /**
     * Boolean that tells us if the front right motor is reversed
     */
    public static final boolean FRONT_RIGHT_REVERSED = false;
    /**
     * Boolean that tells us if the front left motor is reversed
     */
    public static final boolean FRONT_LEFT_REVERSED = false;
    /**
     * Boolean that tells us if the back right motor is reversed
     */
    public static final boolean BACK_RIGHT_REVERSED = false;
    /**
     * Boolean that tells us if the back left motor is reversed
     */
    public static final boolean BACK_LEFT_REVERSED = false;
    // REQUIRED ONLY IF USING INCHESTOTICKS();
    /**
     * Ticks per rotation of the motor
     */
    public static final int TICKS_PER_ROTATION = 537;
    /**
     * Diameter of the wheel
     */
    public static final double WHEEL_DIAMETER = 4;
    /**
     * Gear ratio of the motor
     */
    public static final double GEAR_RATIO = 1;
    /**
     * Ticks per inch of the wheel <b>AUTO CALCULATED BY THE PROGRAM</b>
     */
    public static final double TICKS_PER_INCH = (TICKS_PER_ROTATION * GEAR_RATIO) / (WHEEL_DIAMETER * Math.PI);
}
