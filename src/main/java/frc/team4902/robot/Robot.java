package frc.team4902.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.subsystems.DriveSystem;
import frc.team4902.robot.subsystems.ElevatorSystem;
import frc.team4902.robot.subsystems.GripSystem;
import frc.team4902.robot.subsystems.Pneumatics;

public class Robot extends TimedRobot {

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	private static Robot INSTANCE;
	
	public static enum Pos {
		Left, Center, Right;
	}
	
	private static final SendableChooser<Pos> pchoose = new SendableChooser<>();
	
	static {
		pchoose.addDefault("Left", Pos.Left);
		
		pchoose.addObject("Center", Pos.Center);
		
		pchoose.addObject("Right", Pos.Right);
		
		SmartDashboard.putData(pchoose);
	}
	
	public Robot() {
		INSTANCE = this;
	}
	
	public Pos robotPos() {
		return pchoose.getSelected();
	}

	@Override
	public void robotInit() {
		
		Robot.INSTANCE = this;
		
		if (pdp.getVoltage() > 6.5) {
			
			pdp.clearStickyFaults();
		
		}
		
		{
			
			DriveSystem.getInstance();
			
			ElevatorSystem.getInstance();
			//george is a gay
			// thanks daniel, but you're gayer
			GripSystem.getInstance(); // Enabled tho
			
			Pneumatics.getInstance();
			
		}
		
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		
		cam.setResolution(640, 480);
		
		cam.setFPS(30);
		
		System.out.println("Welcome to " + DriverStation.getInstance().getEventName() + "!");
		
		Input.initializeInputs();
		
		SmartDashboard.putData("Reset Elevator Encoder", RunnableCommand.create(() -> ElevatorSystem.getInstance().encoder.reset()));
		
		DriveSystem.getInstance();
		
	}

	@Override
	public void teleopInit() { }
	
	@Override
	public void autonomousInit() {
		
		Auto.autoInit();
		
	}
	
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
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void disabledPeriodic() { }
	
	public static Robot getInstance() {
		return INSTANCE;
	}
	
}