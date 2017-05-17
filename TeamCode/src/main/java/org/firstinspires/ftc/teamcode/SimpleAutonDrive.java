// Copyright (c) 2017 FTC Team 10262 HippoBotamuses

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor; // not used in this document
import com.qualcomm.robotcore.hardware.Servo;       // not used in this document

/**
 * SimpleAutonDrive Mode
 * <p>
 */
@Autonomous(name="Simple Auton Drive", group="PTH")
public class SimpleAutonDrive extends OpMode {

    // there is a built in variable "time"
    //   that keeps track of seconds (and fractions)
    //   since our loop started, used that to make things
    //   easier
    public static final double MAX_SPEED = 0.5;     // max speed at any time
    public static final double RAMP_TIME = 2.0;     // how long to ramp up or down
    public static final double DRIVE_TIME = 1.0;    // how long to drive at constant speed
                                                    //   between ramp up and ramp down
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
    private DcMotor left;
    private DcMotor right;

    /**
     * Constructor
     */
    public SimpleAutonDrive() {
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
        left = hardwareMap.dcMotor.get("Left");     // assign hardware map items to
        right = hardwareMap.dcMotor.get("Right");   //   hardware variables
    }
    /*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
    @Override
    public void loop() {                            // do everything in this section
                                                    //   of code by doing each sub-section until
                                                    //   the conditions are no longer true
        if (time < END_RAMP_UP) {                   // if before end of ramp up time...
                                                    //   ramp up speed
            double percent = time / RAMP_TIME;      //   calc. percent of RAMP_TIME that has passed
            left.setPower(MAX_SPEED * percent);     //   set power to (increasing) proportionate
            right.setPower(MAX_SPEED * percent);    //   speed on both motors
                                                    // when ramp up time is over
                                                    //   determine which other time block
                                                    //   robot is in
        } else if (time < END_DRIVE) {              // if before end of driving time
                                                    //   keep speed constant
            left.setPower(MAX_SPEED);               //   set power to MAX_SPEED
            right.setPower(MAX_SPEED);              //   on both motors
                                                    //
        } else if (time < END_RAMP_DOWN) {          // if after end of driving time and
                                                    //   before end of ramp down time...
                                                    //   ramp down speed
                                                    //   calc. percent of RAMP_TIME that has passed
            double percent = (time - END_DRIVE) / RAMP_TIME;
                                                    //   set power to (decreasing) proportionate
                                                    //   speed on both motors
            left.setPower(MAX_SPEED * (1.0 - percent));
            right.setPower(MAX_SPEED * (1.0 - percent));
                                                    //
        } else {                                    // if robot is not in one of the above
                                                    //   time blocks, then time is up, so...
            left.setPower(0);                       //   stop robot by setting power to
            right.setPower(0);                      //   zero on both motors
        }
    }

    /*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
    @Override
    public void stop() {                            // stop robot
        left.setPower(0);                           //   by setting power to
        right.setPower(0);                          //   zero on both motors
    }
}
