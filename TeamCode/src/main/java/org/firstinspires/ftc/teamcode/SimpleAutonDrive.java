// Copyright (c) 2017 FTC Team 10262 HippoBotamuses

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * SimpleAutonDrive Mode
 * <p>
 */
@Autonomous(name="Simple Auton Drive", group="PTH")
public class SimpleAutonDrive extends OpMode {

    // remembered there is a built in variable "time"
    // that keeps track of seconds (and fractions)
    // since our loop started, used that to make things
    // easier
    public static final double MAX_SPEED = 0.5;
    public static final double RAMP_TIME = 2.0;
    public static final double DRIVE_TIME = 1.0;

    // calculated from above constants, don't modify
    private static final double END_RAMP_UP = RAMP_TIME;
    private static final double END_DRIVE = END_RAMP_UP + DRIVE_TIME;
    private static final double END_RAMP_DOWN = END_DRIVE + RAMP_TIME;

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
        left = hardwareMap.dcMotor.get("Left");
        right = hardwareMap.dcMotor.get("Right");
    }

    /*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
    @Override
    public void loop() {
        if (time < END_RAMP_UP) {
            // ramping up
            double percent = time / RAMP_TIME;
            left.setPower(MAX_SPEED * percent);
            right.setPower(MAX_SPEED * percent);
        } else if (time < END_DRIVE) {
            left.setPower(MAX_SPEED);
            right.setPower(MAX_SPEED);

        } else if (time < END_RAMP_DOWN) {
            double percent = (time - END_DRIVE) / RAMP_TIME;
            left.setPower(MAX_SPEED * (1.0 - percent));
            right.setPower(MAX_SPEED * (1.0 - percent));
        } else {
            left.setPower(0);
            right.setPower(0);
        }
    }

    /*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
    @Override
    public void stop() {
        left.setPower(0);
        right.setPower(0);
    }
}
