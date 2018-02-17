package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.subsystems.ElevatorSystem;

public class ElevatorCommand extends Command{
	
	public ElevatorCommand() {
		requires(ElevatorSystem.getInstance());
	}
	
	@Override
	protected void execute() {
		if(ElevatorSystem.getInstance().getElevatorMode()) {
			
		}
		else{
			
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
