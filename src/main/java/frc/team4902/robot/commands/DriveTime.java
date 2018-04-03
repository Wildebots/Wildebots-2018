package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.subsystems.DriveSystem;

public class DriveTime extends Command {
	
	private double lspeed, rspeed;
	
	public DriveTime(double time, double lspeed, double rspeed) {
		
		requires(DriveSystem.getInstance());
		
		setTimeout(time);
			
		this.lspeed = lspeed;
		
		this.rspeed = rspeed;
		
	}
	
	public DriveTime(double time, double speed) {
		this(time, speed, speed);
	}
	
	@Override
	protected void execute() {
		DriveSystem.getInstance().setSpeed(lspeed, rspeed);
	}
	
	@Override
	protected void end() {
		DriveSystem.getInstance().setSpeed(0);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
