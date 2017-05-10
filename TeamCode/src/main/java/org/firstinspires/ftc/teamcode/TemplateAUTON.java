// Team Name:   Name
// Team Number: FTCNumber
// Code Type:   AUTON or TELEOP
// Description: Brief description of what this code does, such as
//              1. Start at midpoint of wall
//              2. Drive forward to Cap Ball, making contact, then pause to let Cap Ball flex/bounce/roll
//              3. Spin left to push Cap Ball off center vortex base, then pause to let Cap Ball flex/bounce/roll
//              4. Spin right to prepare to park on center vortex base
//              5. Drive forward onto center vortex base, then stop
//              6. If any parts of robot need to be reporsitioned (arms, etc.) to prepare for Teleop,
//                 place that code after step 5 and before next "}" character
//              7. Wait for Teleop
//
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
// DEFINE OpMode
// @type(name,group) 
@Autonomous(name="TemplateAUTON.java", group="Linear OpMode")    
//
// DEFINE Class
// access level, class name, name of class this new class extends
public class TemplateAUTON extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    // Utilities
    // access level, utility name, starting value
    private ElapsedTime runtime = new ElapsedTime();        // when use private, public, or nothing?
                                                            // use private unless you need access
                                                            // from other classes. Try to be explicit

    // Hardware
    // hardware type, specific name of hardware, starting value
    DcMotor leftDriveMotor = null;                          // One line for each hardware item
    DcMotor rightDriveMotor = null;                         // Values before '=' MUST match EXACTLY the names used when the
    DcMotor sweeperMotor = null;                            // robot configuration was built using the FTC Robot Controler app
    Servo gripperServo= null;                               // on the robot controller phone

    // DEFINE CODE VARIABLES AND BEGINNING VALUES
    // access level, modifiers, variable type, variable name = value
    //
    // Drive times: all values are in milliseconds
    public static final double DriveTimeToCapBall = 10000;          // what does public static final double mean?
    public static final double DriveTimeCapBallToBase = 2000;
    public static final double DriveTime45DegTurn = 500;
    public static final double DriveTime90DegTurn = DriveTime45DegTurn*2;
    //
    // Drive speeds: all values use range of 0 to 1
    public static final double DrivePowerFast = .8;
    public static final double DrivePowerMedium = .5;
    public static final double DrivePowerSlow = .2;

    @Override                                               // what does OVERRIDE do/mean?
    // Override is a note to the compiler, that you expect that you are replacing a method
    // with the same name from the parent (extends XXX) class). That way if you typo/change
    // the method signature you will get an error.
    public void runOpMode() throws InterruptedException  {  // what is "interrupted exception"?
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name"); // what info to send to controller phone
        telemetry.update();                                 // send info now
        //
        // INITIALIZE HARDWARE VARIABLES
        // Values after 'get' MUST match EXACTLY the names used when the
        // robot configuration was built using the FTC Robot Controler app
        // on the robot controller phone
        // hardware variable name = location within hardware map (" value as defined in hardware map ");
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
        robotStop();                        // Use method call to set all DC motors to STOP (power value = 0)
        gripperServo.setPosition(100);      // Set SERVO motor to desired address (100 is just an example; value depends on robot)
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
        // 1. Start at midpoint of wall
        // 2. Drive forward to Cap Ball, making contact, then pause to let Cap Ball flex/bounce/roll
        driveForward(DriveTimeToCapBall,DrivePowerFast);    // arguments MUST be in order expected by method
        robotStop();                                        
        // 3. Spin left to push Cap Ball off center vortex base, then pause to let Cap Ball flex/bounce/roll
        spinLeft(DriveTime45DegTurn,DrivePowerMedium);
        robotStop();                                       
        // 4. Spin right to prepare to park on center vortex base
        spinRight(DriveTime45DegTurn,DrivePowerMedium);
        // 5. Drive forward onto center vortex base, then stop
        driveForward(DriveTimeCapBallToBase,DrivePowerSlow);    
        robotStop();                                        // final stop until beginning of Teleop
        //
        // 6. If any parts of robot need to be reporsitioned (arms, etc.) to prepare for Teleop,
        //    place that code here before next "}" character
        // 7. Wait for Teleop
    }
    ///////////////////////////////////////////////////////////////
    // END of AUTONOMOUS code 
    ///////////////////////////////////////////////////////////////
    //
    // DEFINE ALL METHODS
    // access type, return type, methodName(argumentName1, argumentName2, ...){
    //     commands inside method
    // }
    //
    // robotStop()
    // stop all motors at current location by setting all power to zero
    public void robotStop(){
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        sweeperMotor.setPower(0);
    }
    //
    //driveForward(Time,Power)
    public void driveForward(double Time, double Power){
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(Power);
        sleep((long) Time);
    }
    //
    //spinRight(Time,Power)
    public void spinRight(double Time, double Power){
        leftDriveMotor.setPower(Power);
        rightDriveMotor.setPower(-Power);
        sleep((long) Time);
    }
    //
    //spinLeft(Time,Power)
    public void spinLeft(double Time, double Power){
        leftDriveMotor.setPower(-Power);
        rightDriveMotor.setPower(Power);
        sleep((long) Time);
    }
}
