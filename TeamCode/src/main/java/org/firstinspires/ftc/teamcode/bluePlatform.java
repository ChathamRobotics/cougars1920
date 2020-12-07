package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class bluePlatform extends LinearOpMode
{
    Servo grabBlock, rotateBlock, movePlateL, movePlateR;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft, frontRight, backLeft, backRight, leftCollection, rightCollection, arm;

    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "FrontLeftDrive");
        frontRight = hardwareMap.get(DcMotor.class, "FrontRightDrive");
        backLeft = hardwareMap.get(DcMotor.class, "BackLeftDrive");
        backRight = hardwareMap.get(DcMotor.class, "BackRightDrive");
        leftCollection = hardwareMap.get(DcMotor.class, "LeftCollection");
        rightCollection = hardwareMap.get(DcMotor.class, "RightCollection");
        arm = hardwareMap.get(DcMotor.class, "Arm");
        grabBlock = hardwareMap.get(Servo.class, "grabBlock");
        rotateBlock = hardwareMap.get(Servo.class, "rotateBlock");
        movePlateL = hardwareMap.get(Servo.class, "movePlateLeft");
        movePlateR = hardwareMap.get(Servo.class, "movePlateRight");

        // Right motors are reversed because it is oriented differently then the Left side
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);
        leftCollection.setDirection(DcMotor.Direction.FORWARD);
        rightCollection.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //  detect.runOpMode();
        waitForStart();


        //grabBlock.setPosition(0);
        //rotateBlock.setPosition(0);
        movePlateL.setPosition(0);
        movePlateR.setPosition(0);

        runtime.reset();
        strafeRight(4.1, .4);
        //turnRight(.5,.3);
        driveBackward(3.25,.3);
        sleep(1000);
        movePlateL.setPosition(.5);
        movePlateR.setPosition(.5);
        sleep(1000);
        //turnLeft(1, .4);
        driveForward(3, .5);
        //turnRight(2,.5);
        sleep(1000);
        movePlateL.setPosition(0);
        movePlateR.setPosition(0);
        sleep(1000);
        //driveForward(1.75,.4);
        strafeLeft( 4.95,  .5);
        //driveBackward(1,.1);
        //turnRight(.15,.05);
        driveForward(.38,.2);
    }
    public void driveBackward(double secs, double power)
    {

        ElapsedTime methodTimeB = new ElapsedTime();
        methodTimeB.reset();
        telemetry.addData("Direction:", "backwards");
        telemetry.update();

        while (opModeIsActive() && methodTimeB.seconds() < secs)
        {
            frontLeft.setPower(-power);
            backLeft.setPower(power);
            frontRight.setPower(-power);
            backRight.setPower(power);
        }
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public void driveForward(double secs, double power)
    {

        telemetry.addData("Direction:", "forwards");
        telemetry.update();
        ElapsedTime methodTimeF = new ElapsedTime();
        methodTimeF.reset();
        while (opModeIsActive() && methodTimeF.seconds() < secs)
        {
            frontRight.setPower(power);
            backLeft.setPower(-power);
            frontLeft.setPower(power);
            backRight.setPower(-power);
        }
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public void strafeLeft(double secs, double power)
    {
        telemetry.addData("Direction:", "strafing left");
        telemetry.update();
        ElapsedTime methodTimeSL = new ElapsedTime();
        methodTimeSL.reset();
        while (opModeIsActive() && methodTimeSL.seconds() < secs)
        {
            frontLeft.setPower(-power);
            backLeft.setPower(-power);
            frontRight.setPower(power);
            backRight.setPower(power);
        }

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }



    public void strafeRight(double secs, double power)
    {

        telemetry.addData("Direction:", "strafing right");
        telemetry.update();
        ElapsedTime methodTimeSR = new ElapsedTime();
        methodTimeSR.reset();

        while (opModeIsActive() && methodTimeSR.seconds() < secs)
        {
            frontLeft.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(-power);
            frontRight.setPower(-power);
        }
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public void turnLeft(double secs, double power)
    {
        telemetry.addData("Direction:", "turning left");
        telemetry.update();
        ElapsedTime methodTimeTL = new ElapsedTime();
        methodTimeTL.reset();

        while (opModeIsActive() && methodTimeTL.seconds() < secs)
        {
            frontLeft.setPower(power);
            backLeft.setPower(power);
            frontRight.setPower(-power);
            backRight.setPower(-power);
        }
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public void turnRight(double secs, double power)
    {
        telemetry.addData("Direction:", "turning right");
        telemetry.update();
        ElapsedTime methodTimeTR = new ElapsedTime();
        methodTimeTR.reset();

        while (opModeIsActive() && methodTimeTR.seconds() < secs)
        {
            frontLeft.setPower(power);
            backLeft.setPower(-power);
            frontRight.setPower(-power);
            backRight.setPower(power);
        }
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

}

