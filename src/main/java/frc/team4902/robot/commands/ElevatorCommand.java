package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.Input;
import frc.team4902.robot.subsystems.ElevatorSystem;

public class ElevatorCommand extends Command {
	
	public ElevatorCommand() {
		requires(ElevatorSystem.getInstance());
	}
	
	@Override
	protected void execute() {
		if (Input.primaryXBox.isPluggedIn()) {
			if (ElevatorSystem.isManualOverride()) {
				
				if (ElevatorSystem.getInstance().encoder.get() <= ElevatorSystem.LOW_LIM && Input.primaryXBox.getRightY() < 0) {
					return;
				}
				
				if (ElevatorSystem.getInstance().pid.isEnabled()) {
					ElevatorSystem.getInstance().pid.disable();
				}
					
				ElevatorSystem.getInstance().motors.set(Input.primaryXBox.getRightY());
			
			}
		} else if (Input.Attack3.isPluggedIn()) {
			
			if (!ElevatorSystem.getInstance().pid.isEnabled()) {
			
				ElevatorSystem.getInstance().pid.enable();
			
			}
			
			ElevatorSystem.getInstance().pid.setSetpoint(Input.Attack3.getZ());
		
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
