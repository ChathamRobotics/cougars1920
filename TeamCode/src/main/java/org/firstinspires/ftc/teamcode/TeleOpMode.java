package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOpMode", group = "Linear Opmode")
////THIS BELONGS TO 9853! :)
public class TeleOpMode extends LinearOpMode
{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft, frontRight, backLeft, backRight, leftCollection, rightCollection, arm;
    Servo grabBlock, rotateBlock, movePlateL, movePlateR, blockStabilizer;
    AnalogInput analogSensor;



    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration

        //Chassis Motors
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
        blockStabilizer = hardwareMap.get(Servo.class,"blockStabilizer");
        analogSensor = hardwareMap.get(AnalogInput.class, "sensor_analog");

        // Right motors are reversed because it is oriented differently then the Left side
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);
        leftCollection.setDirection(DcMotor.Direction.FORWARD);
        rightCollection.setDirection(DcMotor.Direction.FORWARD);


        //set position of servos
        // grabBlock.setPosition(0.5);
        //rotateBlock.setPosition(.75);
        movePlateL.setPosition(0);
        movePlateR.setPosition(0);
        grabBlock.setPosition(0);
        rotateBlock.setPosition(0.1);
        blockStabilizer.setPosition(0);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        //Make new servo for block stabalizer 4
        telemetry.addData("Status", "before While Loop");
        telemetry.update();
        while (opModeIsActive())
        {
            //Front left reverse
            //Front right forward
            //Back left forward
            //back right reverse
            //aliza--> two front = forward or above instructions?
            //gamepad 1
            if(gamepad1.left_stick_y!=0 || gamepad1.left_stick_x!=0)
            {
                //Up and down strafes and left and right go forward and back
                frontRight.setPower((-gamepad1.left_stick_x-gamepad1.left_stick_y));
                frontLeft.setPower((gamepad1.left_stick_x-gamepad1.left_stick_y));
                backRight.setPower((-gamepad1.left_stick_x+gamepad1.left_stick_y));
                backLeft.setPower((gamepad1.left_stick_x+gamepad1.left_stick_y));
            }
            //Right joystick - turning
            else if (gamepad1.right_stick_x!=0)
            {
                frontLeft.setPower(gamepad1.right_stick_x);
                frontRight.setPower(-gamepad1.right_stick_x);
                backLeft.setPower(-gamepad1.right_stick_x);
                backRight.setPower(gamepad1.right_stick_x);
            }
            else
            {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
            //platform grabber
            if (gamepad1.a)
            {
                movePlateL.setPosition(.5);
                movePlateR.setPosition(.5);
            }
            else
            {
                movePlateL.setPosition(0);
                movePlateR.setPosition(0);
            }
            //bloc stabalizer ---- test servo for directions
            if (gamepad1.x)
            {
                blockStabilizer.setPosition(.45);
            }
            else
            {
                blockStabilizer.setPosition(0);
            }
            //gamepad 2
            telemetry.addData("Analog sensor value: ", analogSensor.getVoltage());
            telemetry.update();
            //analogSensor

            if(analogSensor.getVoltage()<1.65)
            {
                if (gamepad2.left_trigger!=0)
                {
                    arm.setPower(-.4);
                }
                //arm up
                else if (gamepad2.right_trigger!=0)//right trigger is pressed
                {
                    arm.setPower(.4);
                }
                else
                {
                    arm.setPower(-.1);
                }
            }
            else
            {
                if (gamepad2.left_trigger!=0)
                {
                    arm.setPower(-.4);
                }
                //arm up
                else if (gamepad2.right_trigger!=0)//right trigger is pressed
                {
                    arm.setPower(.4);
                }
                else
                {
                    arm.setPower(.07);
                }
            }

            //if gamepad 2 left bumper is pressed then wheels will collect
            //reverse intake
            if (gamepad2.left_bumper)
            {
                leftCollection.setPower(-.5);
                rightCollection.setPower(-.5);
            }
            //intake in
            else if (gamepad2.right_bumper)
            {
                leftCollection.setPower(.5);
                rightCollection.setPower(.5);
            }
            else
            {
                leftCollection.setPower(0);
                rightCollection.setPower(0);
            }

            //rotate counterclockwise
            while (gamepad2.dpad_right)//close servo
            {
                if(rotateBlock.getPosition()<1)
                {
                    rotateBlock.setPosition(rotateBlock.getPosition() + .01);
                    sleep(1);
                }
            }
            //rotate normal
            while (gamepad2.dpad_left )
            {
                if(rotateBlock.getPosition()>0)
                {
                    rotateBlock.setPosition(rotateBlock.getPosition() - .01);
                    sleep(1);
                }
            }


            //Block grabber
            if (gamepad2.x)
            {
                //grabBlockPosition = grabBlock.getPosition();
                if(grabBlock.getPosition()==0)
                {
                    grabBlock.setPosition(.6);
                }
                else
                {
                    grabBlock.setPosition(0);
                }
            }/*Vedant slow arm lower*/if(gamepad2.y){arm.setPower(.25);}



            /* Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Arm", "left (%.2f), right(%.2f)", armPower);
            telemetry.addData("Collector", "speed (%.2f)",collectionPower);
            telemetry.update();
            */
        }
    }
}
