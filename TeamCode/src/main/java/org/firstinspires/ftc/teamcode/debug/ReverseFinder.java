package org.firstinspires.ftc.teamcode.debug;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.BaseConstants;
import org.firstinspires.ftc.teamcode.EasyEncoders;
@Config
@TeleOp()
public class ReverseFinder extends LinearOpMode {
    public static double power = 0.5;
    public static int distance = 500;
    // takes in the Base constants direction as default values...
    public static boolean flreverse = BaseConstants.FRONT_LEFT_REVERSED;
    public static boolean frreverse = BaseConstants.FRONT_RIGHT_REVERSED;
    public static boolean blreverse = BaseConstants.BACK_LEFT_REVERSED;
    public static boolean brreverse = BaseConstants.BACK_RIGHT_REVERSED;
    EasyEncoders drive = new EasyEncoders(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        drive.telemetry = this.telemetry;
        drive.parent = this;
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Press A to toggle FL direction");
            telemetry.addLine("Press B to toggle FR direction");
            telemetry.addLine("Press X to toggle BL direction");
            telemetry.addLine("Press Y to toggle BR direction");
            telemetry.addData("FL is Reversed: ", flreverse);
            telemetry.addData("FR is Reversed: ", frreverse);
            telemetry.addData("BL is Reversed: ", blreverse);
            telemetry.addData("BR is Reversed: ", brreverse);
            telemetry.update();
            reverseTick();
            drive.Forward(distance, power);
            reverseTick();
            drive.Backward(distance, power);
        }

    }
    public void checkReverse(){
        if (flreverse){
            drive.getMotor(BaseConstants.FRONT_LEFT).setDirection(DcMotorSimple.Direction.REVERSE);
        }else{
            drive.getMotor(BaseConstants.FRONT_LEFT).setDirection(DcMotorSimple.Direction.FORWARD);
        }
        if (frreverse){
            drive.getMotor(BaseConstants.FRONT_RIGHT).setDirection(DcMotorSimple.Direction.REVERSE);
        }else{
            drive.getMotor(BaseConstants.FRONT_RIGHT).setDirection(DcMotorSimple.Direction.FORWARD);
        }
        if (blreverse){
            drive.getMotor(BaseConstants.BACK_LEFT).setDirection(DcMotorSimple.Direction.REVERSE);
        }else{
            drive.getMotor(BaseConstants.BACK_LEFT).setDirection(DcMotorSimple.Direction.FORWARD);
        }
        if (brreverse){
            drive.getMotor(BaseConstants.BACK_RIGHT).setDirection(DcMotorSimple.Direction.REVERSE);
        }else{
            drive.getMotor(BaseConstants.BACK_RIGHT).setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }
    public void updateReverse(){
        if (gamepad1.a || gamepad2.a){
            flreverse = !flreverse;
        }
        if (gamepad1.b || gamepad2.b){
            frreverse = !frreverse;
        }
        if (gamepad1.x || gamepad2.x){
            blreverse = !blreverse;
        }
        if (gamepad1.y || gamepad2.y){
            brreverse = !brreverse;
        }
    }
    public void reverseTick(){
        updateReverse();
        checkReverse();
    }
}
