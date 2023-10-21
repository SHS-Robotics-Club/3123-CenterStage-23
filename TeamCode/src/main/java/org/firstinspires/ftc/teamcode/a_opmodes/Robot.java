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
public class Robot {

    // DEVICE DEFINITIONS
    public MotorEx frontLeft, backLeft, frontRight, backRight, intake;

    // MISC DEFINITIONS
    //public FtcDashboard     dashboard = FtcDashboard.getInstance(); //FTC Dashboard Instance
    public List<LynxModule> revHubs; //Lynx Module for REV Hubs
    public VoltageSensor    voltageSensor; // Voltage Sensor 🤯

    public Robot(HardwareMap hardwareMap) {
        this(hardwareMap, false);
    }

    public Robot(HardwareMap hardwareMap, boolean isAuto) {

        voltageSensor = hardwareMap.voltageSensor.iterator().next(); // Assign Voltage Sensor

        // Bulk Reads, see https://gm0.org/en/latest/docs/software/tutorials/bulk-reads.html
        revHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : revHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // MOTORS   ------------------------------------------------------------------------------------------------
        // Map
        frontLeft  = new MotorEx(hardwareMap, "fL", MotorEx.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "fR", MotorEx.GoBILDA.RPM_312);
        backLeft   = new MotorEx(hardwareMap, "bL", MotorEx.GoBILDA.RPM_312);
        backRight  = new MotorEx(hardwareMap, "bR", MotorEx.GoBILDA.RPM_312);

        intake = new MotorEx(hardwareMap, "intake", MotorEx.GoBILDA.RPM_312);

        // Reset
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();

        intake.resetEncoder();

        // Velocity Control
        frontLeft.setRunMode(MotorEx.RunMode.VelocityControl);
        frontRight.setRunMode(MotorEx.RunMode.VelocityControl);
        backLeft.setRunMode(MotorEx.RunMode.VelocityControl);
        backRight.setRunMode(MotorEx.RunMode.VelocityControl);

        intake.setRunMode(Motor.RunMode.VelocityControl);

        // BRAKE When No Power
        frontLeft.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        // AUTO CONFIG  ------------------------------------------------------------------------------------------------
        if (isAuto) {
            autoConfig(hardwareMap);
        }
    }

    // Autonomous specific configs
    private void autoConfig(HardwareMap hardwareMap) {
    }

}