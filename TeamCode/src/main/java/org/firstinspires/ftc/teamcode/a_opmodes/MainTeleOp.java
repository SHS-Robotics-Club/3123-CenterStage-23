package org.firstinspires.ftc.teamcode.a_opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.b_commands.DriveCommand;
import org.firstinspires.ftc.teamcode.c_subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.c_subsystems.IntakeSubsystem;


/**
 * TeleOp mode for controlling the robot during the driver-controlled period.
 */
//@Disabled
@TeleOp(name = "TeleOp")
public class MainTeleOp extends CommandOpMode {

    @Override
    public void initialize() {
        // Robot Initialization
        Robot bot = new Robot(hardwareMap, false);

        // Telemetry Initialization for FTCDashboard
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Gamepad Initialization
        GamepadEx gPad1 = new GamepadEx(gamepad1);

        // Subsystem Initialization
        DriveSubsystem drive = new DriveSubsystem(bot.frontLeft, bot.frontRight, bot.backLeft, bot.backRight);
        IntakeSubsystem intake = new IntakeSubsystem(bot.intake, 0.75);

        // Command Initialization
        DriveCommand driveCommand = new DriveCommand(drive, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX);

        // CONTROLS ----------------------------------------------------------------------------------------------------
        // Toggle Intake On/Off when X button is pressed
        gPad1.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new InstantCommand(intake::toggle, intake));

        // Reverse Intake when Y button is pressed
        gPad1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new InstantCommand(intake::reverse, intake));

        // Register and Schedule ----------------------------------------------------------------------------------------------------
        gPad1.readButtons(); // Read gamepad buttons (Idk what this actually does)
        register(drive); // Register drive subsystem
        // Schedule commands along with telemetry
        schedule(driveCommand.alongWith(new RunCommand(() -> {
            // Telemetry
            telemetry.update();
            telemetry.addData("Voltage", bot.voltageSensor.getVoltage());
        })));
    }
}
