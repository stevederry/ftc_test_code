
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


// DEFINE OpMode
// type(name,group) 
@Autonomous(name="Derry1_Auton_OpMode_Linear_v01", group="Linear Opmode")    
//
// DEFINE Class
// access, class name, name of class this new class extends
public class Derry1_Auton_OpMode_Linear_v01 extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftDriveMotor = null;
    DcMotor rightDriveMotor = null;
    DcMotor spinnerMotor = null;
    Servo gripperServo= null;

    // Constants should generally be defined outside of
    //   method bodies (here) instead of below (inside runOpMode())
    //   (especially if you ever want to access them outside of this class)
    //
    // DEFINE CODE VARIABLES AND BEGINNING VALUES
    //    public means it can be accessed from other classes
    //    final means its value never changes (constant)
    //    static means there is only one copy no matter how many instances of the class you create
    //
    // Drive times: all values are in milliseconds
    public static final double DriveTimeToCapBall = 10000;
    public static final double DriveTimeCapBallToBase = 2000;
    public static final double DriveTime45DegTurn = 500;
    public static final double DriveTime90DegTurn = DriveTime45DegTurn*2;
    //
    // Drive speeds: all values use range of 0 to 1
    public static final double DrivePowerFast = .8;
    public static final double DrivePowerMedium = .5;
    public static final double DrivePowerSlow = .2;

    @Override                                                       // what does OVERRIDE do/mean?
    public void runOpMode() throws InterruptedException  {          // what is "interrupted exception"?
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name");
        telemetry.update();
        //
        // INITIALIZE HARDWARE VARIABLES
        //   Values after 'get' MUST match EXACTLY the names used when the
        //   robot configuration was built using the FTC Robot Controler app
        //   on the robot controller phone
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        spinnerMotor = hardwareMap.dcMotor.get("spinnerMotor");
        gripperServo = hardwareMap.servo.get("gripperServo");
        //
        // SET MOTOR DIRECTIONS
        // "Reverse" any motor that runs backwards (relative to robot) when powered by positive value
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);     // are ALL CAPS required?
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        spinnerMotor.setDirection(DcMotor.Direction.FORWARD);
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        robotStop();                                                // use method call to set all DC motors to STOP
        gripperServo.setPosition(100);                              // set SERVO motor to desired address
        //
        //
        // END OF PREPARATIONS
        //
        // WAIT for driver to press PLAY
        waitForStart();
        //
        ///////////////////////////////////////////////////////////////
        // AFTER driver presses PLAY, execute code below this line
        ///////////////////////////////////////////////////////////////
        //
        // Drive to Cap Ball, then stop
        driveForward(DriveTimeToCapBall,DrivePowerFast);    // arguments MUST be in order expected by method
        robotStop();                                        // stop allows Cap Ball to bounce/flex before rogbot moves again
        //
        // Pivot left to nudge Cap Ball off center vortex base, then stop
        pivotLeft(DriveTime45DegTurn,DrivePowerMedium);
        robotStop();                                        // stop allows Cap Ball to bounce/flex before rogbot moves again
        //
        // Pivot right to prepare to park on center vortex base
        pivotRight(DriveTime45DegTurn,DrivePowerMedium);
        //
        // Drive forward onto center vortex base, then stop
        driveForward(DriveTimeCapBallToBase,DrivePowerSlow);    
        robotStop();                                        // final stop until beginning of Teleop
        //
        // If any parts of robot need to be repositioned (arms, etc.) to prepare for Teleop,
        //   place that code here before next "}" character
    }
    ///////////////////////////////////////////////////////////////
    // END of AUTONOMOUS code 
    ///////////////////////////////////////////////////////////////
    //
    // DEFINE ALL METHODS
    //   Methods are small sections of code that are written once but can be used ("called")
    //   by the program multiple times. A method can have all of its values set internally (see robotStop, below)
    //   or use values passed to it each time the program calls the method (see driveForward, below). Variables allow the
    //   method's code to be written once but adapt to different situations in the section of code that is calling it.
    //
    // robotStop()
    // stop all motors at current location by setting all power to zero
    public void robotStop(){                                // the empty "()" section means that this method
        leftDriveMotor.setPower(0);                         //   does not rely on variables
        rightDriveMotor.setPower(0);
        spinnerMotor.setPower(0);
    }
    //
    //driveForward(Time,Power)
    public void driveForward(double Time, double Power){    // the variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(Power);                     // run motor with passed Power value
        rightDriveMotor.setPower(Power);                    // run motor with passed Power value
        sleep((long) Time);                                 // wait here in code for duration of passed Time value,
                                                            //   (allows motors to turn for duration of Time)
    }
    //
    //pivotRight(Time,Power)
    public void pivotRight(double Time, double Power){
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(-Power);
        sleep((long) Time);
    }
    //
    //pivotLeft(Time,Power)
    public void pivotLeft(double Time, double Power){
        leftDriveMotor.setPower(-Power);
        rightDriveMotor.setPower(Power);
        sleep((long) Time);
    }
}
