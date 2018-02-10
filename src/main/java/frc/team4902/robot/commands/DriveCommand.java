package frc.team4902.robot.commands;

import frc.team4902.robot.Input;
import frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class DriveCommand extends Command {
	
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveSystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.driveSystem.driveType.get()/*SmartDashboard.getBoolean("DB/Button 0", true)*/) {
			Robot.driveSystem.getDrive().tankDrive(Input.getPrimaryInstance().getRawAxis(1), Input.getPrimaryInstance().getRawAxis(5));
		} else {
			Robot.driveSystem.getDrive().arcadeDrive(Input.getPrimaryInstance().getLeftY(), Input.getPrimaryInstance().getLeftX());
//			Robot.driveSystem.getDrive().arcadeDrive(Input.getPrimaryInstance().getJoystick());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}