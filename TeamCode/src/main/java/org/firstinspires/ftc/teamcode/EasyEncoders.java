package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;

/**
 * Easy Encoder Class has methods that make it easier to use encoders
 * **NOTE** this class requires the BaseConstants class to be in the same folder and configured
 * <b>TICKS IS THE MAIN UNIT USED IN ALL METHODS THAT MOVE</b>
 * @see BaseConstants
 */
@Autonomous
@Disabled
public class EasyEncoders extends LinearOpMode {
    private DcMotorEx frontRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backRight;
    private DcMotorEx backLeft;
    public LinearOpMode parent;
    public Telemetry telemetry;
    BNO055IMU imu;
    public static BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    private int frpos = 0;
    private int flpos = 0;
    private int brpos = 0;
    private int blpos = 0;
    @Override
    public void runOpMode() throws InterruptedException {}
    // constructor
    /**
    * constructor for the EasyEncoders class
    * @param hardwaremap the hardwaremap from the opmode
    * @throws NullPointerException if one or more motors are null
    *
     */
    public EasyEncoders(HardwareMap hardwaremap) throws NullPointerException{
        try{
            // initialize the motors
            frontRight = hardwaremap.get(DcMotorEx.class, BaseConstants.FRONT_RIGHT);
            frontLeft = hardwaremap.get(DcMotorEx.class, BaseConstants.FRONT_LEFT);
            backRight = hardwaremap.get(DcMotorEx.class, BaseConstants.BACK_RIGHT);
            backLeft = hardwaremap.get(DcMotorEx.class, BaseConstants.BACK_LEFT);
            // initialize the imu
            imu = hardwaremap.get(BNO055IMU.class, BaseConstants.IMU);

            // set the direction of the motors
            frontRight.setDirection(BaseConstants.FRONT_RIGHT_REVERSED ? DcMotorEx.Direction.REVERSE : DcMotorEx.Direction.FORWARD);
            frontLeft.setDirection(BaseConstants.FRONT_LEFT_REVERSED ? DcMotorEx.Direction.REVERSE : DcMotorEx.Direction.FORWARD);
            backRight.setDirection(BaseConstants.BACK_RIGHT_REVERSED ? DcMotorEx.Direction.REVERSE : DcMotorEx.Direction.FORWARD);
            backLeft.setDirection(BaseConstants.BACK_LEFT_REVERSED ? DcMotorEx.Direction.REVERSE : DcMotorEx.Direction.FORWARD);
            // reset imu

            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            imu.initialize(parameters);
            ResetEncoders();


        } catch (NullPointerException e){
            throw new NullPointerException("One or more motors are null Possibly because they are not configured in the config file");
        }
    }
    /**
    * turns motor encoder ticks into inches
     * @see BaseConstants
    * @param inches and converts it to ticks
    * @return ticks
     */
    public int InchesToTicks(double inches){
        return (int) (inches * BaseConstants.TICKS_PER_INCH);
    }

    /**
     * Gets the Absolute Position of the front right motor
     * @return the position
     */
    public int getFrpos() {
        return frpos;
    }
    /**
     * Gets the Absolute Position of the front left motor
     * @return the position
     */
    public int getFlpos() {
        return flpos;
    }
    /**
     * Gets the Absolute Position of the back right motor
     * @return the position
     */
    public int getBrpos() {
        return brpos;
    }
    /**
     * Gets the Absolute Position of the back left motor
     * @return the position
     */
    public int getBlpos() {
        return blpos;
    }
    /**
     * Gets the Absolute Position of all the motors
     * @return the position in a VectorF(front right, front left, back right, back left);
     */
    public VectorF getAbsolutePos(){
        return new VectorF(getFrpos(), getFlpos(), getBrpos(), getBlpos());
    }



    /**
     * Gets a motor so you can use it in your own methods
     *
     * @param MotorName the constant name of the motor EX : BaseConstants.FRONT_RIGHT
     * @return DcMotorEx
     */
    public DcMotorEx getMotor(String MotorName){
        switch (MotorName){
            case BaseConstants.FRONT_RIGHT:
                return frontRight;
            case BaseConstants.FRONT_LEFT:
                return frontLeft;
            case BaseConstants.BACK_RIGHT:
                return backRight;
            case BaseConstants.BACK_LEFT:
                return backLeft;
            default:
                return null;
        }
    }
    /**
     * Gets the imu so you can use it in your own methods
     *
     * @return BNO055IMU
     */
    public BNO055IMU getImu(){
        return imu;
    }
    /**
     * Sets the init Telemetry
     */
    public void setTelemetry(Telemetry telemetry){
        this.telemetry = telemetry;
    }
    /**
     * Sets the parent object
     */
    public void setParent(LinearOpMode parent){
        this.parent = parent;
    }

    /**
     * Decelerate the robot
     */
    public void slowdown(){
        sleep(1);
    }

    /**
     * Turns Number of Tiles into inches
     * @param numberoftiles that we need to move/use
     * @return inches that we need to move
     */
    public int TilesToInches(int numberoftiles){
        return (int) (numberoftiles*24);
    }

    /**
     * Turns Number of Tiles into ticks
     * @param numberoftiles this is the number of tiles you want to move
     * @return the number of ticks to move the number of tiles
     */
    public int TilesToTicks(int numberoftiles){
        return InchesToTicks(TilesToInches(numberoftiles));
    }

    /**
     * Resets the encoders
     */
    public void ResetEncoders(){
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Resets the angle of the imu
     */
    public void ResetAngle(){
        imu.initialize(parameters);
    }

    /**
     * Stops all motors
     */

    public void StopAllMotors(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    /**
     * Moves Robot Forward
     * @param ticks amount of ticks to move
     * @param power power to move at
     */
    public void Forward(int ticks , double power){
        ResetEncoders();
        frontRight.setTargetPosition(ticks);
        frontLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        // repeat until our position is close to the ticks position
        while (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy() && parent.opModeIsActive()) {
            sleep(1);
        }
        // stop the motors
        StopAllMotors();
        frpos += ticks;
        flpos += ticks;
        brpos += ticks;
        blpos += ticks;

    }

    /**
     * Moves Robot Backward
     * @param ticks amount of ticks to move
     * @param power power to move at
     */
    public void Backward(int ticks , double power) {
        Forward(-ticks, power);
    }

    /**
     * Makes robot strafe left
     * @param ticks to strafe keft
     * @param power power to strafe at
     */
    public void StrafeRight(int ticks, double power){
        ResetEncoders();
        frontRight.setTargetPosition(-ticks);
        frontLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(-ticks);
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        // repeat until our position is close to the ticks position
        while (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy() && parent.opModeIsActive()) {
            sleep(1);
        }
        // stop the motors
        StopAllMotors();
        frpos -= ticks;
        flpos += ticks;
        brpos += ticks;
        blpos -= ticks;
    }

    /**
     * Makes robot strafe right
     * @param ticks to strafe Left
     * @param power power to strafe at
     */
    public void StrafeLeft(int ticks, double power){
        StrafeRight(-ticks, power);
    }
    /**
     * Turns robot Right
     * @param ticks to turn Right
     * @param power power to turn at
     */
    public void TurnRight(int ticks, double power){
        ResetEncoders();
        frontRight.setTargetPosition(-ticks);
        frontLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(ticks);
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        // repeat until our position is close to the ticks position
        while (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy() && parent.opModeIsActive()) {
            sleep(1);
        }
        // stop the motors
        StopAllMotors();
        frpos -= ticks;
        flpos += ticks;
        brpos -= ticks;
        blpos += ticks;
    }
    /**
     * Turns robot Left
     * @param ticks to turn Left
     * @param power power to turn at
     */
    public void TurnLeft(int ticks, double power){
        TurnRight(-ticks, power);
    }
    /**
     * Turns robot to a specific angle
     * @param angle to turn to
     * @param power power to turn at
     */

    public void TurnToAngle(int angle, double power){
        ResetAngle();
        ResetEncoders();
        while(Math.abs(imu.getAngularOrientation().firstAngle - angle) > 1 && parent.opModeIsActive()) {
            if (angle > 0) {
                TurnRight(10, power);
            } else {
                TurnLeft(10, power);
            }
        }
        StopAllMotors();

    }
    /**
     * Drives the robot
     * @param frontRightTicks ticks for front right motor
     * @param frontLeftTicks ticks for front left motor
     * @param backRightTicks ticks for back right motor
     * @param backLeftTicks ticks for back left motor
     * @param power power to drive at
     */
    public void Drive(int frontRightTicks, int frontLeftTicks, int backRightTicks, int backLeftTicks, double power){
        ResetEncoders();
        ResetAngle();
        frontRight.setTargetPosition(frontRightTicks);
        frontLeft.setTargetPosition(frontLeftTicks);
        backRight.setTargetPosition(backRightTicks);
        backLeft.setTargetPosition(backLeftTicks);
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        // repeat until our position is close to the ticks position
        while (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy() && parent.opModeIsActive()) {
            sleep(1);
        }
        // stop the motors
        StopAllMotors();
        frpos += frontRightTicks;
        flpos += frontLeftTicks;
        brpos += backRightTicks;
        blpos += backLeftTicks;
    }

    /**
     * Goes to a position (x,y) in ticks Starting at (0,0)
     * @param x how far to go on the x axis
     * @param y how far to go on the y axis
     * @param power power to move at
     */

    public void LineTo(int x, int y, double power){
        ResetEncoders();
        ResetAngle();
        double h = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double z = Math.toDegrees(Math.acos(x/h));
        TurnToAngle((int) (90-z), power);
        Forward((int) h, power);

    }
    /**
     * Goes to a position (x,y) in ticks Starting at (0,0)
     * @param position the position to go to Vector2D use as new Vector2D(x,y)
     * @param power power to move at
     */
    public void LineTo(Vector2D position, double power){
        double x = position.getX();
        double y = position.getY();
        LineTo((int) x, (int) y, power);
    }

    /**
     * Goes to a position (x,y) in ticks Starting at (0,0) and turns to a specific angle
     * @param x how far to go on the x axis
     * @param y how far to go on the y axis
     * @param power power to move at
     * @param angle angle to turn to
     */
    public void LineToLinearHeading(int x, int y, double power, int angle){
        ResetEncoders();
        ResetAngle();
        double h = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double z = Math.toDegrees(Math.acos(x/h));
        TurnToAngle((int) z, power);
        Forward((int) h, power);
        TurnToAngle(angle, power);
    }
    /**
     * Goes to a position (x,y) in ticks Starting at (0,0) and turns to a specific angle
     * @param position the position to go to Vector2D use as new Vector2D(x,y)
     * @param power power to move at
     * @param angle angle to turn to
     */
    public void LineToLinearHeading(Vector2D position, double power, int angle) {
        double x = position.getX();
        double y = position.getY();
        LineToLinearHeading((int) x, (int) y, power, angle);
    }

}
