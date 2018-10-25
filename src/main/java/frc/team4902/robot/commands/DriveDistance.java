package frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team4902.robot.subsystems.DriveSystem;

public class DriveDistance extends PIDCommand {
	
	public static final double P = 0, I = 0, D = 0; // Determine values
	
	public static final double THRESHOLD = 0.2;
	
	/**
	 * @param dist Distance measured in inches
	 */
	public DriveDistance(double dist) {
		super(P, I, D);
		requires(DriveSystem.getInstance());
		this.setSetpoint(dist/DriveSystem.WHEEL_CIRCUMFERENCE);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(this.getPIDController().getError()) < THRESHOLD;
	}

	@Override
	protected double returnPIDInput() {
		return DriveSystem.getInstance().getRotations();
	}

	@Override
	protected void usePIDOutput(double output) {
		DriveSystem.getInstance().pidWrite(output);
	}

}
