package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.subsystems.DriveSystem;

public class DriveStraight extends Command {
	
	public static final double ADJUSTMENT_CONSTANT = 0.1;
	
	private double lspeed, rspeed, distime;
	
	private boolean istime;
	
	public DriveStraight(double speed, double distime, boolean istime) {
		requires(DriveSystem.getInstance());
		lspeed = speed;
		rspeed = speed;
		this.distime = distime;
		if (istime) {
			setTimeout(distime);
		}
	}
	
	@Override
	protected void execute() {
		double lsp = DriveSystem.getInstance().leftEncoder.getRate(),
				rsp = DriveSystem.getInstance().rightEncoder.getRate();
		if (lsp > rsp) {
			
			lspeed -= ADJUSTMENT_CONSTANT;
			
		} else if (rsp > lsp) {
			
			lspeed += ADJUSTMENT_CONSTANT;
			
		}
		
		DriveSystem.getInstance().setSpeed(lspeed, rspeed);
		
	}

	@Override
	protected boolean isFinished() {
		if (istime) {
			return isTimedOut();
		} else {
			return DriveSystem.getInstance().getRotations() * DriveSystem.WHEEL_CIRCUMFERENCE >= distime;
		}
	}

}
