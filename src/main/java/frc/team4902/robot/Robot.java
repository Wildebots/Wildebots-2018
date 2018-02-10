package frc.team4902.robot;

import frc.team4902.robot.commands.Autodrive;
import frc.team4902.robot.subsystems.DriveSystem;
//import frc.team4902.robot.subsystems.WinchSystem;

import java.util.concurrent.atomic.AtomicBoolean;

import frc.team4902.robot.EventSystem;
import frc.team4902.robot.Input;
import frc.team4902.robot.EventSystem.HandlerType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	// Initialize subsystem classes
	public static DriverStation driverStation = DriverStation.getInstance();
	public static DriveSystem driveSystem = new DriveSystem();
//	public static GrabSystem grabSystem = new GrabSystem();
//	public static WinchSystem winchSystem = new WinchSystem();	

	// Initialize built-in classes
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static AtomicBoolean enabled = new AtomicBoolean(true), currentCount = new AtomicBoolean(false);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	/*
	 * This is the Robot Initializing
	 */
	@Override
	public void robotInit() {

		// Start the vision system here
		
		// Put Smart dashboard or shuffle board stuff here
		
		// Put EventSystem stuff here
		
		
		EventSystem.getInstance().addHandler(() -> driveSystem.driveType.set(!driveSystem.driveType.get()),
				Input.getPrimaryInstance().getButtonX(), HandlerType.OnPress);
//		This switches between arcade mode and tank

	}

	@Override
	public void disabledInit() {
	}
	
	@Override
	public void disabledPeriodic() {
	}
	
	/*
	 * The AutoDrive class
	 * new AutoDrive(LEFTSPEED, RIGHTSPEED, DURATION)
	 * Leftspeed 0 to 1
	 * Right speed 0 to 1
	 * Duration in seconds
	 */
	@Override
	public void autonomousInit() {
		// What to do starting left, middle, or right side
		// Probably want to go to the left side and then go to the crane to drop off crate
		
		String auto_option = SmartDashboard.getString("DB/String 3", "default");
		CommandGroup auto = new CommandGroup("Autonomous");
		// naming covention: start_goto_type
		// OR start_powerup
		// start: left, right, mid
		// goto: left, right
		// type: scale, switch
		if (auto_option.equalsIgnoreCase("left_left_scale")) {
			auto.addSequential(new Autodrive(0.85, 0.85, 6)); // -1 <= left/right speed <= 1
			auto.addSequential(new Command() {	
				
				@Override
				protected void initialize() {
					// Something about the moving the grabber
					setTimeout(15);
				}
				
				@Override
				protected boolean isFinished() {
					return isTimedOut(); // or return false
				} });
			auto.addSequential(new Autodrive(0.85, -0.85, 0.5)); // turn EDIT VALUES
		}
		if (auto_option.equalsIgnoreCase("left_left_switch")) { 
			auto.addSequential(new Autodrive(0.85, 0.85, 3)); // -1 <= left/right speed <= 1
			auto.addSequential(new Command() {	
				
				@Override
				protected void initialize() {
					// Something about the moving the grabber
					setTimeout(15);
				}
				
				@Override
				protected boolean isFinished() {
					return isTimedOut(); // or return false
				} });
			auto.addSequential(new Autodrive(0.85, -0.85, 0.5));
		}
		if (auto_option.equalsIgnoreCase("left_right_scale")) { 
			auto.addSequential(new Autodrive(0.85, -0.85, 0.5));
		}
		if (auto_option.equalsIgnoreCase("left_right_switch")) { }
		if (auto_option.equalsIgnoreCase("left_powerup")) { }
		if (auto_option.equalsIgnoreCase("middle_left_scale")) { }
		if (auto_option.equalsIgnoreCase("middle_left_switch")) { }
		if (auto_option.equalsIgnoreCase("middle_right_scale")) { }
		if (auto_option.equalsIgnoreCase("middle_right_switch")) { }
		if (auto_option.equalsIgnoreCase("middle_powerup")) { }
		if (auto_option.equalsIgnoreCase("right_left_scale")) { }
		if (auto_option.equalsIgnoreCase("right_left_switch")) { }
		if (auto_option.equalsIgnoreCase("right_right_scale")) { }
		if (auto_option.equalsIgnoreCase("right_right_switch")) { }
		if (auto_option.equalsIgnoreCase("right_right_switch")) { }
		if (auto_option.equalsIgnoreCase("right_powerup")) { }
		
		
		if (auto_option.equalsIgnoreCase("left")) {
			// move forward
			auto.addSequential(new Autodrive(2, 2, 6));
			auto.addSequential(new Command() {	

				@Override
				protected boolean isFinished() {
					return isTimedOut(); // or return false
				} });

		}
		else if (auto_option.equalsIgnoreCase("right")) {
			// NOT SURE WHAT THE STRATEGY IS FOR THIS
//			auto.addSequential(new Autodrive(2, 2, 6));
			auto.addSequential(new Command() {

				@Override
				protected boolean isFinished() {
					return isTimedOut(); // or return false
				} });
			auto.start();
		}
		else if (auto_option.equalsIgnoreCase("middle")) {
			auto.addSequential(new Command() {

				@Override
				protected boolean isFinished() {
					return isTimedOut(); // or return false
				} });
			auto.start();
		}	
//		else {
//			System.out.println("ERROR, AN OPTION WAS NOT SELECTED SOME HOW");
//		}
		
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); 
		// I have absolutely no clue what this does
	}
	
	@Override
	public void teleopInit() {
	}
	
	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
	}
}