package frc.team4902.robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunnableCommand extends Command {
	
	private final Runnable rinit, rend;
	
	private RunnableCommand(Runnable rinit) {
		this.rinit = rinit;
		this.rend = null;
	}
	
	private RunnableCommand(Runnable rinit, Runnable rend) {
		this.rinit = rinit;
		this.rend = rend;
	}
	
	@Override
	protected void initialize() {
		rinit.run();
	}
	
	@Override
	protected void end() {
		if (rend != null) {
			rend.run();
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	public static RunnableCommand create(Runnable rinit) {
		return new RunnableCommand(rinit);
	}
	
	public static RunnableCommand create(Runnable rinit, Runnable rend) {
		return new RunnableCommand(rinit, rend);
	}

}
