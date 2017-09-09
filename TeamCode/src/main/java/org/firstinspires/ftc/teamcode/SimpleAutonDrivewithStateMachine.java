// Copyright (c) 2017 FTC Team 10262 HippoBotamuses

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * SimpleAutonDrive Mode
 * <p>
 */
@Autonomous(name="Simple Auton Drive", group="PTH")
public class SimpleAutonDrivewithStateMachine extends OpMode {

    //
    //
    // enuerated values (contsant "variables")
    //   there is a built-in variable "time" that keeps track of seconds (and fractions)
    //   since our loop started,
    public static final double MAX_SPEED = 0.5;     // max speed at any time
    public static final double RAMP_TIME = 2.0;     // how long to ramp up or down
    public static final double DRIVE_TIME = 1.0;    // how long to drive at constant speed
                                                    //   between ramp up and ramp down

    // variables for use with state machine to make code more readable
    //   values MUST be integers
    private static int state = 0;
    private final static int state_initialize_or_reset = 0;
    private final static int state_drive_segment_1 = 10;
    private final static int state_turn_segment_1 = 12
    private final static int state_drive_segment_2 = 20;

    // variables for motor control
    public static long direction;
    public static long power;
    public static long duration;




    // *********************************************************
    // calculated from above constants, DON'T modify
    //   end ramp up at end of ramp time
    private static final double END_RAMP_UP = RAMP_TIME;
    //   end constant speed drive at sum of ramp up time + constant speed drive time
    private static final double END_DRIVE = END_RAMP_UP + DRIVE_TIME;
    //   end all driving at sum of all time blocks
    private static final double END_RAMP_DOWN = END_DRIVE + RAMP_TIME;
    // *********************************************************
    //
    // create hardware variables for drive motors
    private DcMotor leftDriveMotor;
    private DcMotor rightDriveMotor;

    /**
     * Constructor
     */
    public SimpleAutonDrivewithStateMachine() {
        // most if not all of your setup code
        // belongs in init, not here (see below)
    }

    /*
     * Code to run when the op mode is initialized goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
	 */
    @Override
    public void init() {
        leftDriveMotor = hardwareMap.dcMotor.get("Left");     // assign hardware map items to
        rightDriveMotor = hardwareMap.dcMotor.get("Right");   //   hardware variables
    }
    /*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
    @Override


        public void loop () {                           // do everything in this section

            // telemetry sends info from robot phone to controller phone
            telemetry.addData("Status","Running" + runtime.textstring);
            telemetry.addData("State","State" + state);
            //
            // need to add code to define state and set it to start at 0 (state_start)
            //
            switch (state) {

                case state_initialize_or_reset {

                    // code for case
                    //
                    // set drive motors power to zero
                    // set get gyro to baseline value by letting it settle then resetting value
                    state = state_drive_segment_1;
                    break;
                }
                case state_drive_segment_1 {

                    // code for case
                    //

                    // drive forward rom stat location to destination 1
                    driveStraight(direction,MAX_SPEED,DRIVE_TIME);
                    break;

                }
                case state_turn_segment_1 {

                    // code for case

                }

                }

            }


        }




    /*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
    @Override
    public void stop() {                            // stop robot
        leftDriveMotor.setPower(0);                           //   by setting power to
        rightDriveMotor.setPower(0);                          //   zero on both motors
    }
    //
    public void driveStraight(direction,power,duration) {
        leftDriveMotor.setPower(power * direction);
        rightDriveMotor.setPower(power * direction);
        //sleep(duration);
    }


}
