package org.firstinspires.ftc.teamcode.a_opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
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
        Robot bot = new Robot(hardwareMap);

        // Telemetry Initialization for FTCDashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Gamepad Initialization
        GamepadEx gPad1 = new GamepadEx(gamepad1);

        // Command Initialization
        DriveCommand driveCommand = new DriveCommand(bot.driveSubsystem, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX);

        // CONTROLS ----------------------------------------------------------------------------------------------------
        // Toggle Intake On/Off when X button is pressed
        gPad1.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                bot.INTAKE_TOGGLE);


        // Reverse Intake when Y button is pressed
        gPad1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                bot.INTAKE_REVERSE);

        // Register and Schedule ----------------------------------------------------------------------------------------------------
        gPad1.readButtons(); // Read gamepad buttons (Idk what this actually does)
        register(bot.driveSubsystem); // Register drive subsystem
        // Schedule commands along with telemetry
        schedule(driveCommand.alongWith(new RunCommand(() -> {
            // Telemetry
            telemetry.update();
            telemetry.addData("Voltage", bot.getVoltage(VoltageUnit.VOLTS));
            telemetry.addData("Current", "%.3f A%n", bot.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Intake Position", bot.intakeSubsystem.getIntakeState());
        })));
    }
}
