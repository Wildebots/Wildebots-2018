package frc.team4902.robot.subsystems;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.Ports;
import frc.team4902.robot.commands.ElevatorCommand;

public class ElevatorSystem extends Subsystem implements PIDOutput {

	private static final ElevatorSystem INSTANCE = new ElevatorSystem();
	
	public static final double TOP = 0, MID = 0, BOT = 0, LOW_LIM = 0, MAX_SPEED = 0.4;
	
	public final Encoder encoder = new Encoder(Ports.ElevatorEncoderA.PORT, Ports.ElevatorEncoderB.PORT);
	
	public final Spark motorA = new Spark(Ports.ElevatorMotorA.PORT), motorB = new Spark(Ports.ElevatorMotorB.PORT);
	
	public final SpeedControllerGroup motors = new SpeedControllerGroup(motorA, motorB);
	
	public final PIDController pid = new PIDController(0, 0, 0, encoder, this);

	private final AtomicBoolean manualOverride = new AtomicBoolean(true);
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorCommand());
	}
	
	private ElevatorSystem() {
		super();
	}

	public static ElevatorSystem getInstance() {
		return INSTANCE;
	}
	
	public static boolean isManualOverride() {
		return getInstance().manualOverride.get();
	}
	
	public void toggleOverride() {
		manualOverride.set(!manualOverride.get());
		SmartDashboard.putString("Elevator Override", "Override " + ((manualOverride.get()) ? "Enabled" : "Disabled"));
		if (manualOverride.get()) {
			pid.disable();
		} else {
			pid.enable();
		}
	}

	@Override
	public void pidWrite(double output) {
		this.setSpeed(output);
	}
	
	public void setSpeed(double speed) {
		if (speed > MAX_SPEED) {
			motors.set(MAX_SPEED);
		} else if (speed < -MAX_SPEED) {
			motors.set(-MAX_SPEED);
		} else {
			motors.set(speed);
		}
	}

}
