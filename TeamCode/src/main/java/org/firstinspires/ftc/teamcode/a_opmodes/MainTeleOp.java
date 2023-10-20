package org.firstinspires.ftc.teamcode.a_opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.b_commands.MecanumCommand;

//@Disabled
@Config
@TeleOp(name = "MainTeleOp", group = ".Drive")
public class MainTeleOp extends CommandOpMode {
	Robot bot;

	@Override
	public void initialize() {
		//CommandScheduler.getInstance().reset();
		bot = new Robot(hardwareMap);
		telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

		GamepadEx gPad1 = new GamepadEx(gamepad1);

		MecanumCommand driveCommand = new MecanumCommand(bot.drive, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightY);

		// CONTROLS ----------------------------------------------------------------------------------------------------


		// Register and Schedule ----------------------------------------------------------------------------------------------------
		schedule(driveCommand.alongWith(new RunCommand(() -> {
			// Telemetry
			telemetry.update();
			telemetry.addData("Voltage", bot.voltageSensor.getVoltage());
		})));
	}
}