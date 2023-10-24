package org.firstinspires.ftc.teamcode.a_opmodes;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.List;

/*
 * TYPE			NAME			ID		DESCRIPTION
 * ------------------------------------------------------------
 * MOTOR		frontLeft		fL		    Front Left Mecanum
 * MOTOR		frontRight		fR          Front Right Mecanum
 * MOTOR		backLeft		bL		    Back Left Mecanum
 * MOTOR		backRight		bR		    Back Right Mecanum
 * MOTOR        intake          intake      The Intake
 */

//@Config

/**
 * This class represents the robot configuration and provides methods to initialize and control its components.
 */
public class Robot {
    // DEVICE DEFINITIONS
    public MotorEx frontLeft, backLeft, frontRight, backRight, intake;

    // MISC DEFINITIONS
    //public FtcDashboard     dashboard = FtcDashboard.getInstance(); //FTC Dashboard Instance
    public List<LynxModule> revHubs; // List to store REV Hubs
    public VoltageSensor    voltageSensor; // Voltage Sensor to monitor battery voltage

    /**
     * Constructor to initialize the robot hardware components.
     * @param hardwareMap The hardware map containing all the robot components.
     * @param isAuto A flag indicating whether the robot is in autonomous mode.
     */
    public Robot(HardwareMap hardwareMap, boolean isAuto) {
        // Initialize voltage sensor
        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        // Initialize REV Hubs for bulk reads, see https://gm0.org/en/latest/docs/software/tutorials/bulk-reads.html
        revHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : revHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // MOTORS   ------------------------------------------------------------------------------------------------
        // Initialize motors
        frontLeft  = new MotorEx(hardwareMap, "fL", MotorEx.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "fR", MotorEx.GoBILDA.RPM_312);
        backLeft   = new MotorEx(hardwareMap, "bL", MotorEx.GoBILDA.RPM_312);
        backRight  = new MotorEx(hardwareMap, "bR", MotorEx.GoBILDA.RPM_312);

        intake = new MotorEx(hardwareMap, "intake", MotorEx.GoBILDA.RPM_312);

        // Reset encoders for accurate position tracking
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();

        intake.resetEncoder();

        // Set motors control mode
        frontLeft.setRunMode(MotorEx.RunMode.VelocityControl);
        frontRight.setRunMode(MotorEx.RunMode.VelocityControl);
        backLeft.setRunMode(MotorEx.RunMode.VelocityControl);
        backRight.setRunMode(MotorEx.RunMode.VelocityControl);

        intake.setRunMode(Motor.RunMode.VelocityControl);

        // Set zero power behavior to BRAKE when no power is applied
        frontLeft.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        // AUTO CONFIG  ------------------------------------------------------------------------------------------------
        if (isAuto) {
            // Add autonomous-specific configuration here
        }
    }
}