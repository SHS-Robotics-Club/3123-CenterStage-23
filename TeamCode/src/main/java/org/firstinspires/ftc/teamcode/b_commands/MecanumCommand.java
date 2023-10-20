package org.firstinspires.ftc.teamcode.b_commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.c_subsystems.MecanumSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumCommand extends CommandBase {
	private final MecanumSubsystem mecMecanum;
	private final DoubleSupplier m_strafe, m_forward, m_turn;
	private double multiplier;

	public MecanumCommand(MecanumSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, double mult){
		mecMecanum = subsystem;
		m_strafe = strafe;
		m_forward = forward;
		m_turn = turn;
		multiplier = mult;

		addRequirements(subsystem);
	}
	public MecanumCommand(MecanumSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn){
		mecMecanum = subsystem;
		m_strafe = strafe;
		m_forward = forward;
		m_turn = turn;
		multiplier = 1.0;

		addRequirements(subsystem);
	}

	@Override
	public void execute(){
//        mecMecanum.Mecanum(m_strafe.getAsDouble() * 0.8 * multiplier,
//                m_forward.getAsDouble() * 0.8 * multiplier,
//                m_turn.getAsDouble() * 0.78 * multiplier);
		mecMecanum.drive(m_strafe.getAsDouble() * 0.9 * multiplier,
				m_forward.getAsDouble() * 0.9 * multiplier,
				m_turn.getAsDouble() * 0.88 * multiplier);
	}
}