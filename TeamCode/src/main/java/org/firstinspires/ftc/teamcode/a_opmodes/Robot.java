package org.firstinspires.ftc.teamcode.a_opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
import org.firstinspires.ftc.teamcode.c_subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.c_subsystems.IntakeSubsystem;

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
    // Hardware components
    public MotorEx frontLeft, backLeft, frontRight, backRight, intake;
    public VoltageSensor voltageSensor;
    public FtcDashboard dashboard = FtcDashboard.getInstance();
    public List<LynxModule> revHubs;
    public InstantCommand INTAKE_TOGGLE, INTAKE_REVERSE ;

    public IntakeSubsystem intakeSubsystem;
    public DriveSubsystem driveSubsystem;


    /**
     * Constructor to initialize the robot hardware components.
     *
     * @param hardwareMap The hardware map containing all the robot components.
     * @param isAuto      A flag indicating whether the robot is in autonomous mode.
     */
    public Robot(HardwareMap hardwareMap, boolean isAuto) {
        configureRobot(hardwareMap, isAuto);

        // Initialize sensors and dashboard
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        dashboard = FtcDashboard.getInstance();

        // Bulk Read for REV Hubs
        revHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : revHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }

    /**
     * Constructor for the Robot class.
     *
     * @param hardwareMap The hardware map for accessing robot components.
     */
    public Robot(HardwareMap hardwareMap) {
        this(hardwareMap, false);
    }

    /**
     * Configures the robot's hardware components and subsystems.
     *
     * @param hardwareMap The hardware map for accessing robot components.
     * @param isAuto      A flag indicating if the robot is in autonomous mode.
     */
    private void configureRobot(HardwareMap hardwareMap, boolean isAuto) {
        // MOTORS   ------------------------------------------------------------------------------------------------
        // Initialize motors
        frontLeft = new MotorEx(hardwareMap, "fL", MotorEx.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "fR", MotorEx.GoBILDA.RPM_312);
        backLeft = new MotorEx(hardwareMap, "bL", MotorEx.GoBILDA.RPM_312);
        backRight = new MotorEx(hardwareMap, "bR", MotorEx.GoBILDA.RPM_312);

        intake = new MotorEx(hardwareMap, "intake", MotorEx.GoBILDA.RPM_312);

        // Reset encoders for accurate position tracking
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();

        intake.resetEncoder();

        // Set motors control mode
        frontLeft.setRunMode(MotorEx.RunMode.VelocityControl);
        frontLeft.setVeloCoefficients(0, 0, 0);
        frontRight.setRunMode(MotorEx.RunMode.VelocityControl);
        frontRight.setVeloCoefficients(0, 0, 0);
        backLeft.setRunMode(MotorEx.RunMode.VelocityControl);
        backLeft.setVeloCoefficients(0, 0, 0);
        backRight.setRunMode(MotorEx.RunMode.VelocityControl);
        backRight.setVeloCoefficients(0, 0, 0);

        intake.setRunMode(Motor.RunMode.RawPower);

        // Set zero power behavior to BRAKE when no power is applied
        frontLeft.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        // Subsystems
        intakeSubsystem = new IntakeSubsystem(intake, 0.75);
        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

        INTAKE_TOGGLE = new InstantCommand(intakeSubsystem::toggle, intakeSubsystem);
        INTAKE_REVERSE = new InstantCommand(intakeSubsystem::reverse, intakeSubsystem);

        // AUTO CONFIG  ------------------------------------------------------------------------------------------------
        if (isAuto) {
            // Add autonomous-specific configuration here
        }
    }

    /**
     * Retrieves and returns the current voltage of the robot.
     *
     * @param unit The unit in which the voltage is measured (e.g., VoltageUnit.VOLTS).
     * @return The current voltage of the robot in the specified unit.
     */
    public double getVoltage(VoltageUnit unit) {
        return revHubs.get(0).getInputVoltage(unit);
    }

    /**
     * Calculates and returns the total current draw of the robot.
     *
     * @param unit The unit in which the current is measured (e.g., CurrentUnit.AMPS).
     * @return The total current draw of the robot in the specified unit.
     */
    public double getCurrent(CurrentUnit unit) {
        double totalCurrent = 0;
        for (LynxModule hub : revHubs) {
            totalCurrent += hub.getCurrent(unit);
        }
        return totalCurrent;
    }
}