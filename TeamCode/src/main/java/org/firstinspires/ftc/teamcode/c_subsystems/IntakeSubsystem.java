package org.firstinspires.ftc.teamcode.c_subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class IntakeSubsystem extends SubsystemBase {
    private final MotorEx intake;

    public static IntakeState intakeState    = IntakeState.STOPPED;

    /**
     * @param intake  The intake motor object.
     */
    public IntakeSubsystem(MotorEx intake) {
        this.intake  = intake;
    }

    /**
     * Start/Stop the intake.
     */
    public void toggle(){
        if (intakeState == IntakeState.STOPPED) {
            input();
        } else {
            stop();
        }
    }

    /**
     * Reverses the intake.
     */
    public void reverse(){
        if (intakeState == IntakeState.INPUTTING) {
            output();
        } else if (intakeState == IntakeState.OUTPUTTING){
            input();
        }
    }

    /**
     * Starts the intake.
     */
    public void input() {
        intakeState = IntakeState.INPUTTING;
        intake.set(1);
    }

    /**
     * Reverses the intake.
     */
    public void output() {
        intakeState = IntakeState.OUTPUTTING;
        intake.set(-1);
    }

    /**
     * Stops the intake.
     */
    public void stop() {
        intakeState = IntakeState.STOPPED;
        intake.set(0);
        intake.stopMotor();
    }

    public enum IntakeState {
        INPUTTING, OUTPUTTING, STOPPED;
    }
}