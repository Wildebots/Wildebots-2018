package frc.team4902.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.subsystems.DriveSystem;
import frc.team4902.robot.subsystems.ElevatorSystem;
import frc.team4902.robot.subsystems.GripSystem;

public class Robot extends TimedRobot {

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static SendableChooser<Integer> inputModeChooser = new SendableChooser<>();
	
	static {
		inputModeChooser.addDefault("First", 1);
		inputModeChooser.addObject("Second", 2);
		SmartDashboard.putData(inputModeChooser);
	}

	@Override
	public void robotInit() {
		
		// XBOX CONTROLLER STUFF
		Input.primaryXBox.X.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().changeDriveMode()));
		// ^ OR USE START BUTTON

		Input.primaryXBox.rightBumper.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(true)));
		
		Input.primaryXBox.leftBumper.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(false)));
		
		Input.primaryXBox.leftTrigger.whenPressed(RunnableCommand.create(() -> GripSystem.getInstance().intake()));
		
		Input.primaryXBox.rightTrigger.whenPressed(RunnableCommand.create(() -> GripSystem.getInstance().outake()));
		
		// ELEVATOR STUFF
		
		Input.primaryXBox.back.whenPressed(RunnableCommand.create(() -> ElevatorSystem.getInstance().changeElevatorMode()));
		
//		Input.primaryXBox.Y.whenPressed(RunnableCommand.create(() -> ElevatorSystem.getInstance().toPosition(ElevatorPosition.Top)));
		
//		Input.primaryXBox.B.whenPressed(RunnableCommand.create(() -> ElevatorSystem.getInstance().toPosition(ElevatorPosition.Middle)));
		
//		Input.primaryXBox.A.whenPressed(RunnableCommand.create(() -> ElevatorSystem.getInstance().toPosition(ElevatorPosition.Bottom)));
		
		// ATTACK 3 STUFF
		
		Input.Attack3.getButton(3).whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(true)));
		
		Input.Attack3.getButton(2).whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(false)));
		
		
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
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousPeriodic() { }
	
	@Override
	public void disabledPeriodic() { }
	
}