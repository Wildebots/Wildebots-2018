package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4902.robot.commands.Grip;
import frc.team4902.robot.commands.GripMode;

public class GripperSystem extends Subsystem{

	@Override
	protected void execute() {
		setDefaultCommand(new (Grip()));
	}
	
}
