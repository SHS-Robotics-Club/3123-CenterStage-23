package org.firstinspires.ftc.teamcode.a_opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.b_commands.DriveCommand;
import org.firstinspires.ftc.teamcode.c_subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.c_subsystems.IntakeSubsystem;


//@Disabled
@Config
@TeleOp(name = "TeleOp", group = ".Drive")
public class MainTeleOp extends CommandOpMode {

    @Override
    public void initialize() {
        // General
        Robot bot = new Robot(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Gamepads
        GamepadEx gPad1 = new GamepadEx(gamepad1);

        // Subsystems
        DriveSubsystem drive = new DriveSubsystem(bot.frontLeft, bot.frontRight,
                                                           bot.backLeft, bot.backRight);
        IntakeSubsystem intake = new IntakeSubsystem(bot.intake);

        // Commands
        DriveCommand driveCommand = new DriveCommand(drive, gPad1::getLeftX,
                                                     gPad1::getLeftY, gPad1::getRightX);
        // CONTROLS ----------------------------------------------------------------------------------------------------
        gPad1.getGamepadButton(GamepadKeys.Button.X)
             .whenPressed(new InstantCommand(intake::toggle, intake));

        gPad1.getGamepadButton(GamepadKeys.Button.Y)
             .whenPressed(new InstantCommand(intake::reverse, intake));

        // Register and Schedule ----------------------------------------------------------------------------------------------------
        gPad1.readButtons();
        register(drive);
        schedule(driveCommand.alongWith(new RunCommand(() -> {
            // Telemetry
            telemetry.update();
            telemetry.addData("Voltage", bot.voltageSensor.getVoltage());
        })));
    }
}
