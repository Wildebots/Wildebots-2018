package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.Input;
import frc.team4902.robot.subsystems.DriveSystem;

public class DriveCommand extends Command {
	
	public DriveCommand() {
		requires(DriveSystem.getInstance());
	}

	@Override
	protected void execute() {
		
		if (Input.primaryXBox.isPluggedIn()) {
			
			if (Input.getDriveType()) {
				
				DriveSystem.getInstance().getDrive().arcadeDrive(Input.primaryXBox.getLeftY(), Input.primaryXBox.getLeftX());
				
				SmartDashboard.putString("Drive Type", "Arcade Drive");
			
			} else {
				
				DriveSystem.getInstance().getDrive().tankDrive(Input.primaryXBox.getLeftY(), Input.primaryXBox.getRightY());
			
				SmartDashboard.putString("Drive Type", "Tank Drive");
			
			}
			
		} else if (Input.Attack3.isPluggedIn()) {
			
			DriveSystem.getInstance().getDrive().arcadeDrive(Input.Attack3.getY(), Input.Attack3.getX());
			
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}