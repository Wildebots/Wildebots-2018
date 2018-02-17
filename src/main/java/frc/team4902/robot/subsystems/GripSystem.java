package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4902.robot.commands.GripCommand;

public class GripSystem extends Subsystem{
	
	private static GripSystem INSTANCE = new GripSystem();
	private static boolean inIntakePosition = false;
	
	private GripSystem() {
		super();
	}
	
	public void intake() {
		if (!inIntakePosition) {
			inIntakePosition = true;
		}
	}
	
	public void outake() {
		if (inIntakePosition) {
			inIntakePosition = false;
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new GripCommand());
	}

	public static GripSystem getInstance() {
		return INSTANCE;
	}
	
}
