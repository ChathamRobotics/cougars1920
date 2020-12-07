package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

/**
 * This is an example LinearOpMode that shows how to use an analog sensor, such as the REV Robotics Potentiometer.
 *
 * It assumes that the sensor is configured with a name of "sensor_analog".
 * The REV Robotics Potentiometer must be configured on analog port 0, 2, 4, or 6.
 *
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 */

@TeleOp(name = "Sensor: Analog Potentiometer", group = "Sensor")
public class PotentiometerTest extends LinearOpMode {
    AnalogInput analogSensor;
    DcMotor arm;
    @Override
    public void runOpMode() {
        // Get the sensor from the hardwareMap

        analogSensor = hardwareMap.get(AnalogInput.class, "sensor_analog");
        arm = hardwareMap.get(DcMotor.class, "Arm");
        arm.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the Run button to be pressed
        waitForStart();
        while (opModeIsActive())
        {
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
        }
    }
}
