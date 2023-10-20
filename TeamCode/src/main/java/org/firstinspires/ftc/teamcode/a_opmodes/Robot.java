package org.firstinspires.ftc.teamcode.a_opmodes;

/*
 * TYPE			NAME			ID		    DESCRIPTION
 * ------------------------------------------------------------
 * MOTOR		liftLeft		liftL		Lift Motor Left
 * MOTOR		liftRight		liftR		Lift Motor Right
 *
 * SERVO        clawLeft        clawLeft    Claw Left (Open/Close)
 * SERVO        clawRight       clawRight   Claw Right (Open/Close)
 *
 * CRSERVO		spool			spool		Tensions MGN Rail
 */

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.c_subsystems.MecanumSubsystem;

import java.util.List;

@Config
public class Robot {
	public static double akP = 0.005, akI = 0.0, akD = 0, akF = 0.0;
	public VoltageSensor voltageSensor;

	public MotorEx frontLeft, frontRight, backLeft, backRight;// Motor Group
	boolean isAuto;

	// MISC DEFINITIONS
	public FtcDashboard     dashboard = FtcDashboard.getInstance(); //FTC Dashboard Instance
	public List<LynxModule> revHubs; //Lynx Module for REV Hubs

	public MecanumSubsystem drive;

	public Robot(HardwareMap hardwareMap) {
		this(hardwareMap, false);
	}

	public Robot(HardwareMap hardwareMap, boolean isAuto) {

		voltageSensor = hardwareMap.voltageSensor.iterator().next();

		this.isAuto = isAuto;

		// Bulk Read
		revHubs = hardwareMap.getAll(LynxModule.class);

		for (LynxModule hub : revHubs) {
			hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
		}

		// MOTORS ----------------------------------------------------------------------------------------------------

		// SERVOS ----------------------------------------------------------------------------------------------------

		// COMMANDS & SUBSYSTEMS --------------------------------------------------------------------------------------
		drive = new MecanumSubsystem(frontLeft, frontRight, backLeft, backRight);

		if (isAuto) {
			autoConfig(hardwareMap);
		}

	}

	private void autoConfig(HardwareMap hardwareMap) {

	}
}