package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by brian_000 on 6/24/2017.
 */

Teleop name="Basic"                 // names allow you to chose among multiple programs
                                    //  all available on phone

public class basicprogram extends OpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;

    Servo arm;

    @Override
    public void init() {            // init runs once
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");

        arm = hardwareMap.servo.get("arm");

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {            // loop runs continuosly, after init is finished
        double LeftPower = gamepad1.left_stick_y;
        double RightPower = gamepad2.right_stick_y      // joystick up = -y, down = + y

        leftDrive.setPower(LeftPower);                  // use variables above to define power
                                                        //  settings to match the position of
                                                        //  the joysticks
        rightDrive.setPower(RightPower);

        if (gamepad1.a){                                // move servo arm based on gamepad input
            arm.setPosition(1.0);
        } else if (gamepad2.b){
            arm.setPosition((-1.0));
        }
    }
}
