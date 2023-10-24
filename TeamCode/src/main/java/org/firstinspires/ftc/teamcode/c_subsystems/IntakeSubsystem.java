package org.firstinspires.ftc.teamcode.c_subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

/**
 * IntakeSubsystem represents the intake mechanism of the robot.
 */
public class IntakeSubsystem extends SubsystemBase {
    // Current state of the intake
    public static IntakeState intakeState = IntakeState.STOPPED;
    private final MotorEx intake; // Intake motor object
    private final double  mult; // Multiplier for adjusting intake power

    /**
     * Constructor for IntakeSubsystem.
     *
     * @param intake The intake motor object.
     * @param mult   Multiplier for adjusting intake power.
     */
    public IntakeSubsystem(MotorEx intake, double mult) {
        this.intake = intake;
        this.mult   = mult;
    }

    /**
     * Constructor for IntakeSubsystem.
     *
     * @param intake The intake motor object.
     */
    public IntakeSubsystem(MotorEx intake) {
        this(intake, 1.0);
    }

    /**
     * Toggles the intake on/off based on its current state.
     */
    public void toggle() {
        if (intakeState == IntakeState.STOPPED) {
            input();
        } else {
            stop();
        }
    }

    /**
     * Reverses the intake direction based on its current state.
     */
    public void reverse() {
        if (intakeState == IntakeState.INPUTTING) {
            output();
        } else if (intakeState == IntakeState.OUTPUTTING) {
            input();
        }
    }

    /**
     * Starts the intake, allowing input of objects.
     */
    public void input() {
        intakeState = IntakeState.INPUTTING;
        intake.set(1 * mult); // Set intake power to positive value
    }

    /**
     * Reverses the intake, allowing output of objects.
     */
    public void output() {
        intakeState = IntakeState.OUTPUTTING;
        intake.set(-1 * mult); // Set intake power to negative value for reverse direction
    }

    /**
     * Stops the intake, preventing any movement.
     */
    public void stop() {
        intakeState = IntakeState.STOPPED;
        intake.set(0); // Set intake power to 0 to stop
        intake.stopMotor(); // Ensure motor is fully stopped
    }

    // Enum to represent different states of the intake
    public enum IntakeState {
        INPUTTING, OUTPUTTING, STOPPED
    }
}