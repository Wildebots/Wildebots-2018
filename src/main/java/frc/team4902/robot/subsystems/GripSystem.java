package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4902.robot.Ports;
import frc.team4902.robot.commands.GripCommand;

public class GripSystem extends Subsystem{
	
	private static GripSystem INSTANCE = new GripSystem();
	
	public final Spark motorA = new Spark(Ports.GripperMotorA.PORT), motorB = new Spark(Ports.GripperMotorB.PORT);
	
	private GripSystem() {
		super();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new GripCommand());
	}

	public static GripSystem getInstance() {
		return INSTANCE;
	}
	
	public void setMotors(double val) {
		motorA.set(val);
		motorB.set(-val);
	}
	
}
