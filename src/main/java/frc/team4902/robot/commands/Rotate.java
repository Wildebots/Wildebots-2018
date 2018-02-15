package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team4902.robot.Robot;
import frc.team4902.robot.subsystems.DriveSystem;

public class Rotate extends PIDCommand {
	
	public static final double P = 0, I = 0, D = 0;
	
	public static final double THRESHOLD = 3;

	public Rotate(double angle) {
		super(P, I, D);
		this.setSetpoint(angle);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		DriveSystem.getInstance().pidWrite(output);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(this.getPIDController().getError()) < THRESHOLD;
	}

}
