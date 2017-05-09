// Copyright (c) 2017 FTC Team 10262 HippoBotamuses

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * SimpleTeleop Mode
 * <p>
 */
@TeleOp(name="Simple Teleop", group="PTH")
public class SimpleTeleop extends OpMode {

    private DcMotor left;
    private DcMotor right;
    private DcMotor spinner;

    /**
     * Constructor
     */
    public SimpleTeleop() {
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
        spinner = hardwareMap.dcMotor.get("Spinner");
    }

    /*
	 * Code to run when the op mode is initialized goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
	 */
    @Override
    public void init_loop() {
    }

    /*
	 * Code to run when the op mode is initialized goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
    @Override
    public void start() {
    }

    /*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
    @Override
    public void loop() {
        left.setPower(gamepad1.left_stick_y);
        right.setPower(-gamepad1.right_stick_y);
        spinner.setPower(gamepad1.left_trigger + gamepad1.right_trigger * -1);
    }

    /*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
    @Override
    public void stop() {
    }
}
