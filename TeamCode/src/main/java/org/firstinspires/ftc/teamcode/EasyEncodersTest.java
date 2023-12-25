package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit.VOLTS;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

@Autonomous()
public class EasyEncodersTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // initialize the motors
        EasyEncoders drive = new EasyEncoders(hardwareMap);
        drive.telemetry = this.telemetry;
        drive.parent = this;

        // wait for the start button to be pressed
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {

            drive.Forward(250, 0.5);
            drive.Backward(250, 0.5);
            drive.StrafeRight(250, 0.5);
            drive.StrafeLeft(250, 0.5);
            drive.TurnToAngle(90, 0.5);
            drive.TurnToAngle(-90, 0.5);
            drive.Drive(250, 250, 250, 250,0.5);
            drive.Drive(-250, -250, -250, -250,0.5);
            drive.LineTo(new Vector2D(250,250),0.5);
            drive.LineTo(-250,-250,0.5);
            telemetry.addData("Back Right AMPS : ",drive.getMotor(BaseConstants.BACK_RIGHT).getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Absolute pos FR",drive.getFrpos());
            telemetry.update();
            break;
        }
    }
}
