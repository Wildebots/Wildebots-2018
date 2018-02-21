package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.Input;
import frc.team4902.robot.subsystems.GripSystem;

public class GripCommand extends Command {

	@Override
	protected void execute() {
		
		if (Input.primaryXBox.isPluggedIn() && Input.getDriveType()) {
			
			GripSystem.getInstance().setMotors(Input.primaryXBox.getLeftTrigger() - Input.primaryXBox.getRightTrigger());
			
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
