
////////////////////////////////// THIS FILE TO BECOME TEACHING TEMPLATE ////////////////////////////////////////////
// Team Name:   Name
// Team Number: FTCNumber
// Code Type:   OpMode for AUTONOMOUS
// Description: Brief description of what this code does, such as
//              1. Start at predetermined location (positioned by drivers prior to game start)
//              2. Drive forward to make contact with game object, then pause to let object flex/bounce/roll/slide
//              3. Spin left to push object off its original position, then pause to let object flex/bounce/roll/slide
//              4. Spin right to return to original orientation and prepare to park on object's oroginal location
//              5. Drive forward onto object's oroginal location, then stop
//              6. If any parts of robot need to be repositioned (arms, etc.) to prepare for Teleop,
//                 place that code after step 5 and before next "}" character
//              7. Wait for Teleop
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEFINE CODE PACKAGE
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE FOR USE IN THIS CODE
// OpModes (specific)
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// Hardware types (ONE import per TYPE of hardware, NOT for each INSTANCE of that type of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
// Utilities (specific)
import com.qualcomm.robotcore.util.ElapsedTime;
// 
// DEFINE OpMode
// @type(name,group) 
@Autonomous(name="TemplateAUTON.java", group="Derry_FTC_Templates")  
//
// DEFINE Class
// access level, class name, name of class this new class extends (if any)
public class TemplateAutonGenericV02 extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    //   Utilities
    //   access level, utility name, starting value
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    //   Hardware
    //   hardware type, specific name of hardware, starting value
    DcMotor leftDriveMotor = null;                          // One line for each hardware item
    DcMotor rightDriveMotor = null;                         // Values before '=' MUST match EXACTLY the names used when the
    DcMotor sweeperMotor = null;                            //   robot configuration was built using the FTC Robot Controler app
    Servo gripperServo= null;                               //   on the robot controller phone
    Servo sweeperServo= null;                               
    //   Constants should generally be defined outside of
    //     method bodies (here) instead of below (inside runOpMode()),
    //     especially if you ever want to access them outside of this class
    //
    // DEFINE CODE CONSTANTS, VARIABLES, AND BEGINNING VALUES
    //   public means it can be accessed from other classes
    //   static means there is only one copy no matter how many instances of the class you create
    //   final means its value never changes (constant)
    //   long is the type of value held by the variable
    //
    // Drive times: all values are in milliseconds
    public static final long DRIVE_TIME_TO_CAP_BALL = 10000;
    public static final long DRIVE_TIME_TO_CAP_BALL_TO_BASE = 2000;
    public static final long DRIVE_TIME_45_DEG_TURN = 500;
    public static final long DRIVE_TIME_90_DEG_TURN = DRIVE_TIME_45_DEG_TURN * 2;
    //
    // Drive powers (speeds): all values use range of 0 to 1
    public static final double DRIVE_POWER_FAST = .8;
    public static final double DRIVE_POWER_MEDIUM = .5;
    public static final double DRIVE_POWER_SLOW = .2;
    //
    //  ?????????? Should the INITIALIZE HARDWARE VARIABLES and/or SET MOTOR DIRECTIONS sections
    //  ??????????   below move to here to be grouped with other variables?
    //
    @Override
    // Override is a note to the compiler, that you expect that you are replacing a method
    //   with the same name from the parent (extends XXX class). That way if you typo/change
    //   the method signature you will get an error.
    //
    // call runOpMode() method from the parent class of LinearOpMode
    public void runOpMode() throws InterruptedException  {              // ?????????? what is "interrupted exception"?
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name"); // specific info to send to controller phone
                                                            // ?????????? where does "name" come from?
        telemetry.update();                                 // send info now
        //
        // INITIALIZE HARDWARE VARIABLES
        // Values after 'get' MUST match EXACTLY the names used when the
        //   robot configuration was built using the FTC Robot Controler app
        //   on the robot controller phone
        //   hardware variable name = location within hardware map (" value as defined in hardware map ");
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");
        gripperServo = hardwareMap.servo.get("gripperServo");
        //
        // SET MOTOR DIRECTIONS
        // "Reverse" any motor that runs backwards (relative to robot) when powered by positive value
        // hardware name.setDirection(DcMotor.Direction.DIRECTION)
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);     
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);    
        sweeperMotor.setDirection(DcMotor.Direction.FORWARD);       // assumes sweeperMotor is same orientation as rightDriveMotor
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        stopRobot();                        // Use method call to set all DC motors to STOP (power value = 0)
        gripperServo.setPosition(100);      // Set SERVO motor to desired address (100 is just an example; value depends on robot)
        //
        ///////////////////////////////////////////////////////////////
        // END OF PREPARATIONS
        ///////////////////////////////////////////////////////////////
        //
        // WAIT for driver to press PLAY
        waitForStart();                                     // The waitForStart() method is part of the LinearOpMode class,
                                                            //   which is defined elsewhere in the FTC resrouce code
        //
        ///////////////////////////////////////////////////////////////
        // AFTER driver presses PLAY, execute code below this line
        ///////////////////////////////////////////////////////////////
        //
        // 1. Start at midpoint of wall (positioned by drivers prior to game start)
        //
        // 2. Drive forward to Cap Ball, making contact, then pause to let Cap Ball flex/bounce/roll
        driveForward(DRIVE_TIME_TO_CAP_BALL,DRIVE_POWER_FAST);    // Arguments MUST be in order expected by method
        stopRobot();                                        // Stop then sleep allows Cap Ball to bounce/flex before rogbot moves again        
        sleep((long) 2);                                    // 2 seconds
        //
        // 3. Spin left to push Cap Ball off center vortex base, then pause to let Cap Ball flex/bounce/roll
        spinLeft(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);      // Spin 45 deg. to left
        stopRobot();                                        // Stop then sleep allows Cap Ball to bounce/flex before rogbot moves again        
        sleep((long) 2);                                    // 2 seconds
        //
        // 4. Spin right to prepare to park on center vortex base
        spinRight(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);     // Spin 45 deg. to right to return to original orientation
        //
        // 5. Drive forward onto center vortex base, then stop
        driveForward(DRIVE_TIME_TO_CAP_BALL_TO_BASE,DRIVE_POWER_SLOW);    
        stopRobot();                                        // Final stop until beginning of Teleop
        //
        // 6. If any parts of robot need to be repositioned (arms, etc.) to prepare for Teleop,
        //    place that code here before next "}" character
        // Open gripper hand
        gripperServo.setPosition(200);      // Set SERVO motor to desired address (200 is just an example; value depends on robot)
        // 7. Wait for Teleop
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
    //   ?????????? Can/should METHODS be stored in a separate file so that more than one program file
    //   ??????????  can access them? And so that changes to those METHODS can be made in one place rather
    //   ??????????  than in each instance of a "calling" program?
    //
    // METHOD stopRobot()
    //    stop all motors at current location by setting all power to zero
    public void stopRobot(){                                // The empty "()" section means that this method
        leftDriveMotor.setPower(0);                         //   does not rely on values passed into it
        rightDriveMotor.setPower(0);                        //   from the section of code that calls it
        sweeperMotor.setPower(0);                           //   and uses the values entered directly here (0 in this case)
    }
    //
    // METHOD driveForward(Time,Power)
    public void driveForward(double Time, double Power){    // The variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(Power);                     // Run motor with passed Power value
        rightDriveMotor.setPower(Power);                    // Run motor with passed Power value
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value,
                                                            //   (allows motors to turn for duration of Time)
    }
    //
    // METHOD spinRight(Time,Power)
    public void spinRight(double Time, double Power){       // The variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(Power);                     // Run motor with passed Power value
        rightDriveMotor.setPower(-Power);                   // Run motor with passed Power value inverted
                                                            //   so motor will spin in reverse
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value,
                                                            //   (allows motors to run for duration of Time)
    }
    //
    // METHOD spinLeft(Time,Power)
    public void spinLeft(long Time, double Power){        // The variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(-Power);                    // Run motor with passed Power value inverted
                                                            //   so motor will spin in reverse
        rightDriveMotor.setPower(Power);                    // Run motor with passed Power value
        sleep(Time);                                 // Wait here in code for duration of passed Time value,
                                                            //   (allows motors to run for duration of Time)
    }
}
