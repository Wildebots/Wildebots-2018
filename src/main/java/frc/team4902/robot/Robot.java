package frc.team4902.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.subsystems.DriveSystem;
import frc.team4902.robot.subsystems.ElevatorSystem;

public class Robot extends TimedRobot {

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	@Override
	public void robotInit() {
		
		Input.initializeInputs();
		
		SmartDashboard.putData("Reset Elevator Encoder", RunnableCommand.create(() -> ElevatorSystem.getInstance().encoder.reset()));
		
	}

	@Override
	public void teleopInit() { }
	
	@Override
	public void autonomousInit() { }
	
	@Override
	public void disabledInit() { }
	
	@Override
	public void robotPeriodic() {
		
		SmartDashboard.putData(DriveSystem.getInstance().getDrive());
		
		SmartDashboard.putData(gyro);
		
		SmartDashboard.putData(pdp);
		
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousPeriodic() { }
	
	@Override
	public void disabledPeriodic() { }
	
}