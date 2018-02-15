package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.subsystems.DriveSystem;

public class Autodrive extends Command {
	
	private final double leftspeed, rightspeed, duration;
	
	/**
	 * @param leftspeed the speed for the left drive
	 * @param rightspeed the speed for the right drive
	 * @param duration the amount of time in seconds to drive
	 */
	public Autodrive(double leftspeed, double rightspeed, double duration) {
		requires(DriveSystem.getInstance());
		this.leftspeed = leftspeed;
		this.rightspeed = rightspeed;
		this.duration = duration;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}
	
	@Override
	protected void execute() {
		DriveSystem.getInstance().getDrive().tankDrive(leftspeed, rightspeed);
	}
	
	@Override
	protected void end() {
		DriveSystem.getInstance().stop();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
