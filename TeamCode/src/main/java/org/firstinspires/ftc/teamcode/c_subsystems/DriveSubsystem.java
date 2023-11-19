package org.firstinspires.ftc.teamcode.c_subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

/**
 * DriveSubsystem represents the robot's drive system.
 */
public class DriveSubsystem extends SubsystemBase {
    private final MecanumDrive mecanumDrive; // Mecanum drive object for holonomic movement

    /**
     * Constructor for DriveSubsystem.
     *
     * @param frontLeft  The Front Left drive motor object.
     * @param frontRight The Front Right drive motor object.
     * @param backLeft   The Back Left drive motor object.
     * @param backRight  The Back Right drive motor object.
     */
    public DriveSubsystem(MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight) {
        // Initialize MecanumDrive with drive motors
        mecanumDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    /**
     * Drive the robot using Mecanum drive.
     *
     * @param strafeSpeed  Speed for strafing left/right (range: -1.0 to 1.0).
     * @param forwardSpeed Speed for moving forward/backward (range: -1.0 to 1.0).
     * @param turnSpeed    Speed for turning left/right (range: -1.0 to 1.0).
     */
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        mecanumDrive.driveRobotCentric(-strafeSpeed, -forwardSpeed, -turnSpeed, true);
    }


}