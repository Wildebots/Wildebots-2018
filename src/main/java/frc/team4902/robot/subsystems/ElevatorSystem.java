package frc.team4902.robot.subsystems;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.Ports;
import frc.team4902.robot.commands.ElevatorCommand;

public class ElevatorSystem extends Subsystem implements PIDOutput {

	private static final ElevatorSystem INSTANCE = new ElevatorSystem();
	
	public static final double TOP = 0, MID = 0, BOT = 0, LOW_LIM = 0;
	
	public final Encoder encoder = new Encoder(Ports.ElevatorEncoderA.PORT, Ports.ElevatorEncoderB.PORT);
	
	public final Spark elevatorMotor = new Spark(Ports.ElevatorMotor.PORT);
	
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
	}

	@Override
	public void pidWrite(double output) {
		elevatorMotor.set(output);
	}

}
