//////////////////////////////////////////////////////////////////////////////////////////////////
// Filename:    TeleopExample.java
////////////////////////////////////////////////////////////////////////////////////////////////////
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for TELEOP
// Description: 1.  Define robot actions
//              2.  Assign robot actions to specific inputs (buttons, joysticks, etc.)
//                  on game controllers
//              3.  Define how and when robot will listen for and react to game controller input
//              4.  This file is for controlling your robot during the TeleOp portion of the game.
//                  The code for the autonomous portion will be in a separate file. Most teams
//                  have one TELEOP file and several AUTONOMOUS files.
//////////////////////////////////////////////////////////////////////////////////////////////////
//
//////////////////////////////////////////////////////////////////////////////////////////////////
// BEGIN PREPARATIONS
//////////////////////////////////////////////////////////////////////////////////////////////////
//
// DEFINE CODE PACKAGE
// The place where this code file is saved on your computer and where all related files
//      are stored, such as you Autonomous program(s) or other resources you create
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE FOR USE IN THIS CODE
// OpModes (specific)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// Hardware types (ONE import per TYPE of hardware, NOT for each INSTANCE of that type of hardware)
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;   // NOTE: This is needed in addition to the
                                                        //      line above because the workings of
                                                        //      the REVERSE setting (line 91 below)
                                                        //      are stored in DcMotorSimple
//
// DEFINE OpMode
// @type(name = "name_of_this_program"*)
@TeleOp(name="TeleopExample")               // name does not have to match filename, but should
                                            //      make sense and be related
//
//////////////////////////////////////////////////////////////////////////////////////////////////
// END OF PREPARATIONS
//////////////////////////////////////////////////////////////////////////////////////////////////
//
//////////////////////////////////////////////////////////////////////////////////////////////////
// BEGIN CLASS
//////////////////////////////////////////////////////////////////////////////////////////////////
//
// Every Java program must have at least one CLASS, and can have several.
// This program has only one.
//
// DEFINE Class
// access level, class name, name of class this new class extends (if any)
public class TeleopExample extends OpMode { // public means this CLASS can be
                                            //      accessed from other code files
    //
    // DECLARE VARIABLE TYPES
    //      FORMAT: type, variable name
    Servo arm;
    DcMotor leftDrive;
    DcMotor rightDrive;
    //
    //////////////////////////////////////////////////////////////////////////////////////////////
    // BEGIN INIT METHOD
    //////////////////////////////////////////////////////////////////////////////////////////////
    //
    //      Init code runs once in reparation for remaining code, below (a LOOP in this file)
    @Override                               // @override means that you're about to
                                            //      use a METHOD defined elsewhere -- in this
                                            //      case INIT() -- and you want any valid things
                                            //      you type here inside INIT() to override any
                                            //      conflicting things in the original METHOD
    public void init() {                    // INIT is the name of this METHOD
                                            // public means this INIT() METHOD can be
                                            //      accessed from other code files
                                            // void means that, if other sections of code or
                                            //      code files access it, it does not send
                                            //      them any data; its return is "void" in
                                            //      the sense that it is empty (not invalid)
        //
        // DEFINE VARIABLE SOURCES RELATED TO HARDWARE ITEMS
        //      FORMAT: variable name, source*
        //              *the source must EXACTLY match its name as defined in hardwareMap
        //      DC MOTORS
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        //      SERVO MOTORS
        arm = hardwareMap.servo.get("arm");
        //      ADJUSTMENTS TO VARIABLES
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);   // reverse rotation of motor
                                                                    // so it can use same power
                                                                    // value as leftDrive
                                                                    // even though it's on opposite
                                                                    // side of robot and therefore
                                                                    // positioned exactly inverted
    }
    //
    //////////////////////////////////////////////////////////////////////////////////////////////
    // END INIT METHOD
    //////////////////////////////////////////////////////////////////////////////////////////////
    //
    //////////////////////////////////////////////////////////////////////////////////////////////
    // BEGIN LOOP METHOD
    //////////////////////////////////////////////////////////////////////////////////////////////
    //
    //      Once they start, loops run continuously. This one will constantly check for
    //      changes in controller inputs (buttons, joysticks, etc.)
    @Override
    public void loop() {
        //
        // DEFINE variables
        double LeftPower = gamepad1.left_stick_y;       // read positions of joysticks
        double RightPower = gamepad2.right_stick_y;     //      (joystick up = -y, down = + y)
        //
        // USE variables to control motors
        leftDrive.setPower(LeftPower);                  // Set motor powers. This is the code
        rightDrive.setPower(RightPower);                //      that drives the robot
        //
        // WATCH FOR INPUT FROM GAMEPAD BUTTONS
        //      FORMAT: device.button_name
        if (gamepad1.a){                                // Move servo arm based on gamepad input
            arm.setPosition(1.0);                       // These servo values are samples.
        } else if (gamepad2.b){                         //      Real values would depend on
            arm.setPosition((-1.0));                    //      specific robot setup and goals
        }
        //
        //////////////////////////////////////////////////////////////////////////////////////////
        // END LOOP METHOD
        //////////////////////////////////////////////////////////////////////////////////////////
        //
    }
}
//
//////////////////////////////////////////////////////////////////////////////////////////////////
// END CLASS
//////////////////////////////////////////////////////////////////////////////////////////////////
//
//////////////////////////////////////////////////////////////////////////////////////////////////
// END OF FILE
//////////////////////////////////////////////////////////////////////////////////////////////////
