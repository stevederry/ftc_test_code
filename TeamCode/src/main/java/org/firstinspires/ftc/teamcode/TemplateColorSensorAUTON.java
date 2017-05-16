////////////////////////////////// THIS FILE TO BECOME TEACHING TEMPLATE ////////////////////////////////////////////
// Team Name:   Name
// Team Number: FTCNumber
// Code Type:   Code pieces to be placed into AUTON program
// Description: Use color sensor
//              1. Sense color
//              2. Compare value of sensed color to value of desired color
//              3. Return true if sensed color matches desired color within tolerance range, else return false
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Put this in IMPORT PROGRAMMING ELEMENTS > HARDWARE section
import com.qualcomm.robotcore.hardware.ColorSensor;           // tell program that ColorSensor is present on robot        
//
//
    // Put this in CLASS > DECLARE OpMode MEMBERS setion
    ColorSensor colorSensorForBeacon = null;                  // 
    //
    // Put this in CLASS > DEFINE CODE VARIABLES AND BEGINNING VALUES
    public static final double Red = 1;                       // 1 is sample value; this would need to be calibrated
    public static final double Green = 2;                     // 2 is sample value; this would need to be calibrated
    public static final double Blue = 3;                      // 3 is sample value; this would need to be calibrated
    public static final double ColorRangeTolerance = .2;      // .2 is sample value; this would need to be calibrated
    public static final string DesiredColorValue = "blue";    // this would be defined for proper version of AUTON code
    //
    // put this in the CLASS > runOpMode secion
        //### write code to read then send color values as telemetry
        // display status and OpMode name on controller phone
        telemetry.addData("Status", "Initialized", "name"); // specific info to send to controller phone
        telemetry.update();                                 // send info now
        //
        // put this in CLASS > runOpMode > INITIALIZE HARDWARE VARIABLES section
        colorSensorForBeacon = hardwareMap.colorSensor.get("colorSensorForBeacon");
        //
        // put this in CLASS > runOpMode > INITIALIZE HARDWARE VARIABLES section,
        //  just above END OF PREPARATIONS marker and with any other last-check-before-running items
        //  such as setting servo positions, confirming all-motors are stopped, etc.
        colorSensorForBeacon.enableLed(true);                       // Turn the LED on
        colorSensorForBeacon.enableLed(false);                      // Turn the LED off
        //
        ///////////////////////////////////////////////////////////////
        // END OF PREPARATIONS
        ///////////////////////////////////////////////////////////////
 
/*## text from website example
 Reading the Sensor

Chances are, we want to do everything you see below in the loop()  method
(or in runOpMode()  after the waitForStart()  if we’re writing a Linear OpMode).

There are a number of different values we can read from the sensor:
color_sensor.red();     // Red channel value
color_sensor.green();   // Green channel value
color_sensor.blue();    // Blue channel value
color_sensor.alpha();   // Total luminosity
color_sensor.argb();    // Hue measurement
*/
 //
        // put this in AFTER DRIVER PRESSES PLAY, EXECUTE CODE BELOW THIS LINE section
        //  and above END of AUTONOMOUS CODE section
        //
        // 1. Sense color
        // 2. Compare value of sensed color to value of desired color
        // 3. Return true if sensed color matches desired color within tolerance range, else return false
//        
//## write color sensing and decision making code
//
//
    //
    // put this in DEFINE ALL METHODS section
    //
    // spinLeft(Time,Power)
    public void spinLeft(double Time, double Power){        // The variable names Time and Power will be assigned
                                                            //   to the values passed into the method, in the order
                                                            //   they are received
        leftDriveMotor.setPower(-Power);                    // Run motor with passed Power value inverted
                                                            //   so motor will spin in reverse
        rightDriveMotor.setPower(Power);                    // Run motor with passed Power value
        sleep((long) Time);                                 // Wait here in code for duration of passed Time value,
                                                            //   (allows motors to run for duration of Time)
    }
    //
    // senseColor()
    public boolean senseColor(double DesiredColorValue, double ColorRangeTolerance){    //
        //## write color evaluation code
    }
}
