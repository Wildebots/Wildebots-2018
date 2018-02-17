package frc.team4902.robot.subsystems;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4902.robot.commands.ElevatorCommand;

public class ElevatorSystem extends Subsystem implements PIDOutput {

	private static final ElevatorSystem INSTANCE = new ElevatorSystem();
	private static ElevatorPosition elevatorPosition = ElevatorPosition.Bottom;
	public final AtomicBoolean elevatorManualOveride = new AtomicBoolean(false);

	// private static ElevatorMode mode = ElevatorMode.Manual;
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

	public void toPosition(ElevatorPosition newPosition) {
		switch (newPosition) {
		case Top:
			// if statements won't be needed if you make a command that goes to max
			if (elevatorPosition == ElevatorPosition.Middle) {
				// move up once
			} else if (elevatorPosition == ElevatorPosition.Bottom) {
				// move up twice
			}
			break;
		case Bottom:
			// if statements won't be needed if you make a command that goes to mix
			if (elevatorPosition == ElevatorPosition.Middle) {
				// move down once
			} else if (elevatorPosition == ElevatorPosition.Top) {
				// move down twice
			}
			break;
		case Middle:
			if (elevatorPosition == ElevatorPosition.Top) {
				// move down once
			} else if (elevatorPosition == ElevatorPosition.Bottom) {
				// move up once
			}
			break;

		default:
			break;
		}
	}

	public void changeElevatorMode() {
		elevatorManualOveride.set(!elevatorManualOveride.get());
	}
	
	public boolean getElevatorMode() {
		return elevatorManualOveride.get();
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub

	}

}
