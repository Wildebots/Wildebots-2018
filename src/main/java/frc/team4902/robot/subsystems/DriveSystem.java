package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.Ports;
import frc.team4902.robot.commands.DriveCommand;

/**
 * The drive system controls the motors responsible for driving the robot
 * Interacts with move commands and any additional commands that Affect movement
 */
public class DriveSystem extends Subsystem implements PIDOutput {

	private static final DriveSystem INSTANCE = new DriveSystem();

	public final Encoder leftEncoder = new Encoder(Ports.DriveTrainLeftEncoderA.PORT,
			Ports.DriveTrainLeftEncoderB.PORT),
			rightEncoder = new Encoder(Ports.DriveTrainRightEncoderA.PORT, Ports.DriveTrainRightEncoderB.PORT);

	private final Spark leftFront = new Spark(Ports.DriveTrainLeftFront.PORT),
			leftBack = new Spark(Ports.DriveTrainLeftBack.PORT),
			rightFront = new Spark(Ports.DriveTrainRightFront.PORT),
			rightBack = new Spark(Ports.DriveTrainRightBack.PORT);

	private final SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack),
			right = new SpeedControllerGroup(rightFront, rightBack);

	private final DifferentialDrive drive = new DifferentialDrive(left, right);

	private final DoubleSolenoid dsol = new DoubleSolenoid(Ports.DriveTrainLeftSolenoid.PORT, Ports.DriveTrainRightSolenoid.PORT);

	// 6 inch diameter, inches to revs
	public static final double WHEEL_CIRCUMFERENCE = 6 * Math.PI;

	private DriveSystem() {
		super();
		
		if (dsol.isFwdSolenoidBlackListed()) {
			System.out.println("FWD SOLOENOID BLACKLISTED");
		}
		
		if (dsol.isRevSolenoidBlackListed()) {
			System.out.println("REV SOLENOID BLACKLISTED");
		}
		
		if (dsol.getPCMSolenoidVoltageStickyFault()) {
			System.out.println("Clearing PCM stick faults");
			dsol.clearAllPCMStickyFaults();
		}
		
		//left.setInverted(true);
		
		//right.setInverted(true);
		
	}

	public static DriveSystem getInstance() {
		return INSTANCE;
	}

	public void stop() {
		drive.tankDrive(0, 0);
	}

	public DifferentialDrive getDrive() {
		return drive;
	}

	public double getRotations() {
		// Encoder counts 360 for one rotation
		// Encoder shaft turns once for every three rotations of the output shaft 3:1
		// ratio
		// Divide by two because two encoders
		return (leftEncoder.get() + rightEncoder.get()) / 240.0;
	}

	public void setHighGear(boolean high) {
		if (high) {
			SmartDashboard.putString("Transmission", "High");
			dsol.set(Value.kForward);
		} else {
			SmartDashboard.putString("Transmission", "Low");
			dsol.set(Value.kReverse);
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	@Override
	public void pidWrite(double output) {
		drive.tankDrive(-output, output);
	}
	
	public void setSpeed(double lspeed, double rspeed) {
		drive.tankDrive(lspeed, rspeed);
	}
	
	public void setSpeed(double speed) {
		drive.tankDrive(speed, speed);
	}
	
}
