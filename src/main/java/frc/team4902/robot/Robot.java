package frc.team4902.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.team4902.robot.subsystems.DriveSystem;

public class Robot extends TimedRobot {

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	@Override
	public void robotInit() {
		
		Input.primaryXBox.A.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().driveType.set(!DriveSystem.getInstance().driveType.get())));
		
	}

	@Override
	public void teleopInit() { }
	
	@Override
	public void autonomousInit() { }
	
	@Override
	public void disabledInit() { }
	
	@Override
	public void robotPeriodic() { }
	
	@Override
	public void teleopPeriodic() { }
	
	@Override
	public void autonomousPeriodic() { }
	
	@Override
	public void disabledPeriodic() { }
	
}