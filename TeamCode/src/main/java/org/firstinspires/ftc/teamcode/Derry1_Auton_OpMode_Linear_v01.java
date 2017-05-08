
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp; // NOT used
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Derry1_Auton_OpMode_Linear_v01", group="Linear Opmode")

public class Derry1_Auton_OpMode_Linear_v01 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime(); // when use private, public, or leave blank?
    DcMotor leftDriveMotor = null;
    DcMotor rightDriveMotor = null;
    DcMotor spinnerMotor = null;
    Servo gripperServo= null;

    @Override       // what does OVERRIDE do/mean?
    public void runOpMode() throws InterruptedException  { //what is interrupted exception?
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name");
        telemetry.update();
        //
        // INITIALIZE HARDWARE VARIABLES
        // Values after 'get' MUST match EXACTLY the names used when the
        // robot configuration was built using the FTC Robot Controler app
        // on the robot controller phone
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        spinnerMotor = hardwareMap.dcMotor.get("spinnerMotor");
        gripperServo = hardwareMap.servo.get("gripperServo");
        //
        // SET MOTOR DIRECTIONS
        // "Reverse" any motor that runs backwards (relative to robot) when powered by positive value
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        spinnerMotor.setDirection(DcMotor.Direction.FORWARD);
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        robotStop();                    // Use method call to set all DC motors to STOP
        gripperServo.setPosition(100);  // Set SERVO motor to desired address
        //
        // DEFINE CODE VARIABLES AND BEGINNING VALUES
        //
        // what does public static final double mean?
        //
        // Drive times: all values are in milliseconds
        public static final double DriveTimeToCapBall = 10000;
        public static final double DriveTime45DegTurn = 500;
        public static final double DriveTime90DegTurn = DriveTime45DegTurn*2;
        //
        // Drive speeds: all values use range of 0 to 1
        public static final double DrivePowerFast = .8;
        public static final double DrivePowerMedium = .5;
        public static final double DrivePowerSlow = .2;
        //
        // END OF PREPARATIONS
        //
        // WAIT for driver to press PLAY
        waitForStart();
        ///////////////////////////////////////////////////////////////
        // AFTER driver presses PLAY, execute code below this line
        ///////////////////////////////////////////////////////////////
        //
        // Drive to Cap Ball, then stop
        driveForward(DriveTimeToCapBall,DrivePowerFast);
        robotStop();
        //
        // Pivot left to nudge Cap Ball off center vortex base, then stop
        pivotLeft(DriveTime45DegTurn,DrivePowerMedium);
        robotStop();
    }
    ///////////////////////////////////////////////////////////////
    // END of AUTONOMOUS code 
    ///////////////////////////////////////////////////////////////
    //
    // DEFINE ALL METHODS
    //
    // robotStop()
    // stop all motors at current location by setting all power to zero
    public void robotStop(){
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        spinnerMotor.setPower(0);
    }
    //
    //driveForward(Time,Power)
    public void driveForward(Time,Power){
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(Power);
        sleep(Time);
    }
    //
    //pivotRight(Time,Power)
    public void pivotRight(Time,Power){
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(-Power);
        sleep(Time);
    }
    //
    //pivotLeft(Time,Power)
    public void pivotLeft(Time,Power){
        leftDriveMotor.setPower(-Power);
        rightDriveMotor.setPower(Power);
        sleep(Time);
    }
}
