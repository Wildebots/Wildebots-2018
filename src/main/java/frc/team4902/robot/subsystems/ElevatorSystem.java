package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorSystem extends Subsystem{

	private static ElevatorPosition elevatorPosition = ElevatorPosition.Bottom; 
	private static ElevatorMode mode = ElevatorMode.Presets;
	// private static ElevatorMode mode = ElevatorMode.Manual;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static void toPosition(ElevatorPosition newPosition) {
		switch (newPosition) {
		case Top:
			// if statements won't be needed if you make a command that goes to max
			if (elevatorPosition == ElevatorPosition.Middle) {
				// move up once
			}
			else if (elevatorPosition == ElevatorPosition.Bottom) {
				// move up twice
			}
			break;
		case Bottom:
			// if statements won't be needed if you make a command that goes to mix
			if (elevatorPosition == ElevatorPosition.Middle) {
				// move down once
			}
			else if (elevatorPosition == ElevatorPosition.Top) {
				// move down twice
			}
			break;
		case Middle:
			if (elevatorPosition == ElevatorPosition.Top) {
				// move down once
			}
			else if (elevatorPosition == ElevatorPosition.Bottom) {
				// move up once
			}
			break;
			
		default:
			break;
	}
	
	
	
	
}
