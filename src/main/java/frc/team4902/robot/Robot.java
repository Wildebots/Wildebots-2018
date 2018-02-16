package frc.team4902.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team4902.robot.subsystems.DriveSystem;
import frc.team4902.robot.subsystems.ElevatorPosition;
import frc.team4902.robot.subsystems.ElevatorSystem;
import frc.team4902.robot.subsystems.GripSystem;

public class Robot extends TimedRobot {

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	@Override
	public void robotInit() {
		
		// XBOX CONTROLLER STUFF
		Input.primaryXBox.X.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().driveType.set(!DriveSystem.getInstance().driveType.get())));
		// ^ OR USE START BUTTON
		
		Input.primaryXBox.rightBumper.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(true)));
		
		Input.primaryXBox.leftBumper.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(false)));
		
		Input.primaryXBox.leftTrigger.whenPressed(RunnableCommand.create(() -> GripSystem.intake()));
		
		Input.primaryXBox.rightTrigger.whenPressed(RunnableCommand.create(() -> GripSystem.outake()));
		
		Input.primaryXBox.rightTrigger.whenPressed(RunnableCommand.create(() -> GripSystem.outake()));
		
		// ELEVATOR STUFF
		
		Input.primaryXBox.Y.whenPressed(RunnableCommand.create(() -> ElevatorSystem.toPosition(ElevatorPosition.Top)));
		
		Input.primaryXBox.B.whenPressed(RunnableCommand.create(() -> ElevatorSystem.toPosition(ElevatorPosition.Middle)));
		
		Input.primaryXBox.A.whenPressed(RunnableCommand.create(() -> ElevatorSystem.toPosition(ElevatorPosition.Bottom)));
		
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