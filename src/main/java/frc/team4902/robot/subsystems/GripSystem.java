package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4902.robot.commands.GripCommand;

public class GripSystem extends Subsystem{
	
	private static boolean inIntakePosition = false;
	protected void execute() {
//		setDefaultCommand(new (Grip()));
	}
	
	public static void intake() {
		if (!inIntakePosition) {
			inIntakePosition = true;
		}
	}
	
	public static void outake() {
		if (inIntakePosition) {
			inIntakePosition = false;
		}
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
}
