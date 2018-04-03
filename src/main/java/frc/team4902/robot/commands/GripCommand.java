package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4902.robot.Input;
import frc.team4902.robot.subsystems.GripSystem;

public class GripCommand extends Command {

	public GripCommand() {
		requires(GripSystem.getInstance());
	}
	
	@Override
	protected void execute() {
		
		if (Input.primaryXBox.isPluggedIn() && Input.getDriveType()) {
			
		GripSystem.getInstance().setMotors(Input.primaryXBox.getLeftTrigger() - Input.primaryXBox.getRightTrigger());
			
		} else if (Input.primaryXBox.isPluggedIn() && !Input.getDriveType()) {
			
			double speed = 0;
			
			if (Input.primaryXBox.rightBumper.get()) {
				speed -= 0.6;
			}
			
			if (Input.primaryXBox.leftBumper.get()) {
				speed += 0.6;
			}
			
			GripSystem.getInstance().setMotors(speed);
			
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
