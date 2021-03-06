
////////////////////////////////// THIS FILE TO BECOME TEACHING TEMPLATE ////////////////////////////////////////////
// Team Name:   Lightning Robotics
// Team Number: FRC862
// Code Type:   OpMode for AUTONOMOUS
// Description: This code is for the 2017-18 FTC Relic Recovery game.
//              See game description video here for clarification: https://www.youtube.com/watch?v=7Wc1LhG2FEs&t=9s
//              1.  Start at predetermined location (positioned by drivers prior to game start) with robot
//                  facing toward Crypto Box
//              2.  Deploy Color Senor Arm
//              3.  Compare sensed color to alliance color
//              4.  Drive robot toward unwanted color to move unwanted object, stop, stow Color Senor Arm
//              5.  If Step 4 drove robot backward (away from crypto box), drive robot back to starting point
//              6.  Drive forward toward Crypto Box, stop
//              7.  Pivot to right, toward Crypto Box, stop
//              6.  Drive forward toward Crypto Box, stop
//              7.  Release gripper to drop glyph (hopefully in cryptobox)
//              8.  Wait for AUTON to end and TELEOP to begin
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// DEFINE CODE PACKAGE
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE FOR USE IN THIS CODE
// OpModes (specific)
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
// Hardware types (ONE import per TYPE of hardware, NOT for each INSTANCE of that type of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//
// Utilities (specific)
import com.qualcomm.robotcore.util.ElapsedTime;
// 
// DEFINE OpMode
// FORMAT: @type(name,group) 
@Autonomous(name="RRGameAutonV01", group="Derry_FTC_Templates")  
//
// DEFINE Class
// FORMAT: access level, class name, name of class this new class extends (if any)
public class RRGameAutonV01 extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    //   Utilities
    //   FORMAT: access level, utility name, starting value
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    //   Hardware
    //   FORMAT: hardware type, specific name of hardware, starting value
    DcMotor leftDriveMotor = null;                          // One line for each hardware item
    DcMotor rightDriveMotor = null;                         // Values before '=' MUST match EXACTLY the names used when the
    Servo colorSensorArmServo = null;                       //   robot configuration was built using the FTC Robot Controler app
    Servo gripperServo= null;                               //   on the robot controller phone
    ColorSensor colorSensor= null;                               
    //
    //   Constants should generally be defined outside of
    //     method bodies (here) instead of below (inside runOpMode()),
    //     especially if you ever want to access them outside of this class
    //
    // DEFINE CODE CONSTANTS, VARIABLES, AND BEGINNING VALUES
    // FORMAT: access level, static yes/no, constant yes/no, value type, value name, beginning value
    //   public means it can be accessed from other classes
    //   static means there is only one copy no matter how many instances of the class you create
    //   final means its value never changes (constant)
    //   long is the type of value held by the variable
    //
    // Drive times: all values are in milliseconds
    public static final long DRIVE_TIME_TO_MOVE_JEWEL = 10000;
    public static final long DRIVE_TIME_FWD_JEWEL_TO_SAFE_ZONE = 3000;
    public static final long DRIVE_TIME_45_DEG_TURN = 500;
    public static final long DRIVE_TIME_90_DEG_TURN = DRIVE_TIME_45_DEG_TURN * 2;
    //
    // Drive powers (speeds): all values use range of 0 to 1
    public static final double DRIVE_POWER_FAST = .8;
    public static final double DRIVE_POWER_MEDIUM = .5;
    public static final double DRIVE_POWER_SLOW = .2;
    //
    // Servo addresses (locations)
    public static final double COLOR_SENSOR_ARM_STOWED = 0;
    public static final double COLOR_SENSOR_ARM_DEPLOYED = 0;
    public static final double GRIPPER_OPEN = 0;
    public static final double GRIPPER_GRIP_GLYPH = 180;
    //
    // Sensor values
    public static final double ALLIANCE_COLOR = BLUE;            // "BLUE" is not a real value. NEED VALUES OF RED AND BLUE
    public static double SensedColor = null;                   // Null will be replaced by sensed value during execution
    //
    @Override
    // Override is a note to the compiler, that you expect that you are replacing a method
    //   with the same name from the parent (extends XXX class). That way if you typo/change
    //   the method signature you will get an error.
    //
    // call runOpMode() method from the parent class of LinearOpMode
    public void runOpMode() throws InterruptedException  {              // "interrupted exception" keeps the program 
                                                                        //    from freezing completely if there is an error
                                                                        //    that it does not know how to handle
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name");             // specific info to send to controller phone
        telemetry.update();                                             // send info now
        //
        // INITIALIZE HARDWARE VARIABLES
        // FORMAT: hardware variable name = location within hardware map (" value as defined in hardware map ");
        //   Values after 'get' MUST match EXACTLY the names used when the
        //      robot configuration was built using the FTC Robot Controler app
        //      on the robot controller phone.
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        colorSensorArmServo = hardwareMap.dcMotor.get("colorSensorArmServo");
        gripperServo = hardwareMap.servo.get("gripperServo");
        //
        // SET MOTOR DIRECTIONS
        // "Reverse" any motor that runs backwards (relative to "forward" direction of robot) when powered by positive value
        // hardware name.setDirection(DcMotor.Direction.DIRECTION)
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);     
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);    
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        stopRobot();                        // Use method call to set all DC motors to STOP (power value = 0)
                                            // Methods are defined in one of two places:
                                            //   1. In separate files that are part of your overall group of code
                                            //      files, such as runOpMode(), as used above. The runOpMode method
                                            //      is in a file supplied by FTC. You can write your own separate files
                                            //      that contain methods, as well.
                                            //   2. Inside this file, in the DEFINE ALL METHODS section, below.
        // SET ALL SERVOS TO DESIRED STARTING STATUS
        gripperServo.setPosition(GRIPPER_GRIP_GLYPH);              
        colorSensorArmServo.setPosition(COLOR_SENSOR_ARM_STOWED);
        //
        ///////////////////////////////////////////////////////////////
        // END OF PREPARATIONS
        ///////////////////////////////////////////////////////////////
        //
        // WAIT for driver to press PLAY
        waitForStart();                     // The waitForStart() method is part of the LinearOpMode class,
                                            //   which is defined elsewhere in the FTC resrouce code
        //
        ///////////////////////////////////////////////////////////////
        // AFTER driver presses PLAY, execute code below this line
        ///////////////////////////////////////////////////////////////
        //
        //  1.  Start at predetermined location (positioned by drivers prior to game start) with robot
        //      facing toward Crypto Box
        //  2.  Deploy Color Senor Arm
        colorSensorArmServo.setPosition(COLOR_SENSOR_ARM_DEPLOYED);
        //  3.  Compare sensed color to desired color. (Color sensor faces rearward)
        //  4.  Drive robot toward unwanted color to move unwanted object, stop, stow Color Senor Arm
        //  5.  If Step 4 drove robot backward (away from crypto box), drive robot back to starting point

        SensedColor = colorSensor.getValue();
        If Sensed Color == ALLIANCE_COLOR           
        {
            driveForward(DRIVE_TIME_TO_MOVE_JEWEL,DRIVE_POWER_MEDIUM);  // Arguments MUST be in order expected by method
            stopRobot();          
            colorSensorArmServo.setPosition(COLOR_SENSOR_ARM_STOWED);   // Stow color sensor arm
        else
            driveRearward(DRIVE_TIME_TO_MOVE_JEWEL,DRIVE_POWER_MEDIUM); // Arguments MUST be in order expected by method
            stopRobot();          
            colorSensorArmServo.setPosition(COLOR_SENSOR_ARM_STOWED);   // Stow color sensor arm
            driveForeward(DRIVE_TIME_TO_MOVE_JEWEL * 2,DRIVE_POWER_MEDIUM); // Moves robot to end position of forward drive
            stopRobot();                                             
        } 
        //  6.  Drive forward toward Crypto Box, stop
        driveForward(DRIVE_TIME_FWD_JEWEL_TO_SAFE_ZONE,DRIVE_POWER_FAST);
        stopRobot();                                             
        //  7.  Pivot to right, toward Crypto Box, stop
        //  6.  Drive forward toward Crypto Box, stop
        //  7.  Release gripper to drop glyph (hopefully in cryptobox)
        //  8.  Wait for AUTON to end and TELEOP to begin
        //??????????????????????
        
        
        
        
        
        
        
        
        
        driveForward(DRIVE_TIME_TO_MOVE_JEWEL,DRIVE_POWER_MEDIUM);  // Arguments MUST be in order expected by method
        stopRobot();                                        // Stop then sleep allows Object to bounce/flex before rogbot moves again        
        sleep((long) 2);                                    // 2 seconds
        //
        // 3. Spin left to push object off its base, then pause to let object flex/bounce/roll
        spinLeft(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);// Spin 45 deg. to left
        stopRobot();                                        // Stop then sleep allows Object to bounce/flex before rogbot moves again        
        sleep((long) 2);                                    // 2 seconds
        //
        // 4. Spin right to prepare to park on base
        spinRight(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);// Spin 45 deg. to right to return to original orientation
        //
        // 5. Drive forward onto base, then stop
        driveForward(DRIVE_TIME_OBJECT_TO_BASE,DRIVE_POWER_SLOW);    
        stopRobot();                                        // Final stop until beginning of Teleop
        //
        // 6. If any parts of robot need to be repositioned (arms, etc.) to prepare for Teleop,
        //    place that code here before next "}" character
        //
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
    // METHOD stopRobot()
    //    stop all motors at current location by setting all power to zero
    public void stopRobot(){                                // The empty "()" section means that this method
        leftDriveMotor.setPower(0);                         //   does not rely on values passed into it
        rightDriveMotor.setPower(0);                        //   from the section of code that calls it
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
    // METHOD driveRearward(Time,Power)
    public void driveRearward(double Time, double Power){   // The variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(-Power);                    // Run both motors with passed Power value inverted
                                                            //   with passed Power value inverted
        rightDriveMotor.setPower(-Power);                   //   so motors will rotate in reverse
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
                                                            //   so motor will rotate in reverse
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value,
                                                            //   (allows motors to run for duration of Time)
    }
    //
    // METHOD spinLeft(Time,Power)
    public void spinLeft(long Time, double Power){          // The variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(-Power);                    // Run motor with passed Power value inverted
                                                            //   so motor will rotate in reverse
        rightDriveMotor.setPower(Power);                    // Run motor with passed Power value
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value,
                                                            //   (allows motors to run for duration of Time)
    }
}
