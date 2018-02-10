package frc.team4902.robot.subsystems;

import java.util.concurrent.atomic.AtomicBoolean;

import frc.team4902.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *The drive system controls the motors responsible for driving the robot
 *Interacts with move commands and any additional commands that
 *Affect movement
 */
public class DriveSystem extends Subsystem implements PIDOutput {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Spark
			m_frontLeft= new Spark(4), // int channel = 4 is an assumption, ask to see if it is
//			m_midLeft = new Spark(XXX),
			m_rearLeft = new Spark(5),
			
			m_frontRight = new Spark(2),
//			m_midRight = new Spark(XXX),
			m_rearRight = new Spark(3);

	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
//	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);
	private SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
//	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);
	
	//true = tank
	//false = false
	//arcade by default
	public AtomicBoolean driveType = new AtomicBoolean(false);
	
//	private RobotDrive drive = new RobotDrive(m_rearLeft, m_frontLeft, m_rearRight, m_frontRight);
	private DifferentialDrive drive = new DifferentialDrive(m_left, m_right);
//	http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.html
	public DriveSystem() {
		// Not exactly sure if needed
		m_frontLeft.setInverted(true);
//		m_midLeft.setInverted(true);
		m_rearLeft.setInverted(true);

		m_frontRight.setInverted(true);
//		m_midRight.setInverted(true);
		m_rearRight.setInverted(true);
	}
	
	public void stop() {
		drive.tankDrive(0, 0); // see link above
	}
	
	public DifferentialDrive getDrive() {
		return drive;
	}
		
	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	@Override
	public void pidWrite(double output) {
		drive.tankDrive(-output, output);
	}
}

