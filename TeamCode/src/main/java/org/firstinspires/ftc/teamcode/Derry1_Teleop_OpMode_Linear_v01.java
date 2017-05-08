
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Derry1_Teleop_OpMode_Linear_v01", group="Linear Opmode")

public class Derry1_Teleop_OpMode_Linear_v01 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftDriveMotor = null;
    DcMotor rightDriveMotor = null;
    DcMotor spinnerMotor = null;
    Servo gripperServo= null;

    @Override
    public void runOpMode() {
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name");
        telemetry.update();
        //
        // INITIALIZE HARDWARE VARIABLES
        // Values after 'get' MUST match EXACTLY the names used when the
        // robot configuration was built using the FTC Robot Controler app
        // on the robot controller phone
        leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        spinnerMotor = hardwareMap.dcMotor.get("spinnerMotor");
        gripperServo = hardwareMap.dcMotor.get("gripperServo");
        //
        // SET MOTOR DIRECTIONS
        // "Reverse" any motor that runs backwards when connected directly to the battery
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        spinnerMotor.setDirection(DcMotor.Direction.FORWARD);
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        robotStop();                    // Use method call to set all DC motors to STOP
        gripperServo.setPosition(100);  // Set SERVO motor to desired address
        //
        // END OF PREPARATIONS
        //
        // WAIT for driver to press PLAY
        waitForStart();
        ///////////////////////////////////////////////////////////////
        // AFTER driver presses PLAY, execute code below this line
        ///////////////////////////////////////////////////////////////
        runtime.reset();    // reset robot clock to zero
        //
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // telemetry sends update text from robot phone to controller phone
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            // Run wheels in tank mode
            // Values are inverted with "-" because joystick sends:
            //      forward = negative
            //      rearward = positive
            leftDriveMotor.setPower(-gamepad1.left_stick_y);
            rightDriveMotor.setPower(-gamepad1.right_stick_y);
        }

    }

    //
    // DEFINE ALL METHODS
    //
    // robotStop()
    // stop all motors in current locationby setting all power to zero
    public void robotStop(){
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        spinnerMotor.setPower(0);
    }
}
