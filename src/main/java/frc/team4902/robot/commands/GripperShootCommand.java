package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.subsystems.GripSystem;

public class GripperShootCommand extends Command {
	
	private final double speed;
	
	public GripperShootCommand(double speed, double timeout) {
		requires(GripSystem.getInstance());
		this.setTimeout(timeout);
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		GripSystem.getInstance().setMotors(speed);
	}
	
	@Override
	protected void end() {
		GripSystem.getInstance().setMotors(0);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
