package org.firstinspires.ftc.teamcode.b_commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.c_subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

/**
 * DriveCommand allows for controlling the robot's movement using user inputs.
 */
public class DriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem; // DriveSubsystem object to execute the command on
    private final DoubleSupplier strafe, forward, turn; // Input functions for strafe, forward, and turn
    private final double multiplier; // Multiplier to adjust the speed of the robot

    /**
     * Constructor for DriveCommand with a multiplier for speed adjustment.
     *
     * @param driveSubsystem The drive subsystem this command will run on.
     * @param strafe         The control input for driving left/right.
     * @param forward        The control input for driving forwards/backwards.
     * @param turn           The control input for turning.
     * @param multiplier     A multiplier for adjusting robot speed.
     */
    public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, double multiplier) {
        this.driveSubsystem = driveSubsystem;
        this.strafe         = strafe;
        this.forward        = forward;
        this.turn           = turn;
        this.multiplier     = multiplier;

        addRequirements(driveSubsystem); // Ensure that the driveSubsystem is not used elsewhere while this command is running
    }

    /**
     * Constructor for DriveCommand without a speed multiplier (default multiplier: 1.0).
     *
     * @param driveSubsystem The drive subsystem this command will run on.
     * @param strafe         The control input for driving left/right.
     * @param forward        The control input for driving forwards/backwards.
     * @param turn           The control input for turning.
     */
    public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        this(driveSubsystem, strafe, forward, turn, 1.0); // Use default multiplier of 1.0
    }

    /**
     * Executes the drive command using input values from strafe, forward, and turn functions.
     */
    @Override
    public void execute() {
        driveSubsystem.drive(strafe.getAsDouble() * 0.9 * multiplier,
                             forward.getAsDouble() * 0.9 * multiplier,
                             turn.getAsDouble() * 0.8 * multiplier);
    }
}