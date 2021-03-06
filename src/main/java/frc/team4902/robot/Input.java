package frc.team4902.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4902.robot.commands.GripperShootCommand;
import frc.team4902.robot.subsystems.DriveSystem;
import frc.team4902.robot.subsystems.ElevatorSystem;
import frc.team4902.robot.subsystems.Pneumatics;

public final class Input {

	public static final XBoxInput primaryXBox = new XBoxInput(0), secondaryXBox = new XBoxInput(0);

	public static final Attack3 Attack3 = new Attack3(0);

	// true -> arcade ; false -> tank
	private static final AtomicBoolean driveType = new AtomicBoolean(true);

	public static final class XBoxInput extends XboxController {

		public final JoystickButton A = new JoystickButton(this, 1), B = new JoystickButton(this, 2),
				X = new JoystickButton(this, 3), Y = new JoystickButton(this, 4),
				leftBumper = new JoystickButton(this, 5), rightBumper = new JoystickButton(this, 6),
				back = new JoystickButton(this, 7), start = new JoystickButton(this, 8);

		public XBoxInput(int port) {
			super(port);
		}

		public double getLeftX() {
			return this.getRawAxis(0);
		}

		public double getLeftY() {
			return this.getRawAxis(1);
		}

		public double getRightX() {
			return this.getRawAxis(4);
		}

		public double getRightY() {
			return this.getRawAxis(5);
		}
		
		public double getLeftTrigger() {
			return this.getRawAxis(2);
		}
		
		public double getRightTrigger() {
			return this.getRawAxis(3);
		}

		public boolean isPluggedIn() {
			return DriverStation.getInstance().getJoystickIsXbox(getPort());
		}

	}

	public static final class Attack3 extends Joystick {

		public final List<JoystickButton> buttons = new ArrayList<>();

		public Attack3(int port) {
			super(port);
			for (int i = 0; i < 11; i++) {
				buttons.add(new JoystickButton(this, (i + 1)));
			}
		}

		public JoystickButton getButton(int num) {
			return buttons.get(num);
		}

		public boolean isPluggedIn() {
			return DriverStation.getInstance().getJoystickName(getPort()).equals("Logitech Attack 3");
		}

	}

	/**
	 * true -> arcade
	 * false -> tank
	 */
	public static void setDriveType(boolean mode) {
		driveType.set(mode);
		SmartDashboard.putString("Drive Type", (Input.driveType.get()) ? "Arcade" : "Tank");
	}
	
	public static boolean getDriveType() {
		return driveType.get();
	}
	
	public static void toggleDriveType() {
		Input.setDriveType(!Input.getDriveType());
	}

	public static void initializeInputs() {
		
		Input.primaryXBox.Y.whenPressed(RunnableCommand.create(() -> {
			if (!ElevatorSystem.isManualOverride()) {
				/* Elevator top pos */
				ElevatorSystem.getInstance().pid.setSetpoint(ElevatorSystem.TOP);
			}
		}));
		
		Input.primaryXBox.B.whenPressed(RunnableCommand.create(() -> {
			if (!ElevatorSystem.isManualOverride()) {
				/* Elevator middle pos */
				ElevatorSystem.getInstance().pid.setSetpoint(ElevatorSystem.MID);
			}
		}));
		
		Input.primaryXBox.A.whenPressed(RunnableCommand.create(() -> {
			if (!ElevatorSystem.isManualOverride()) {
				/* Elevator bottom pos */
				ElevatorSystem.getInstance().pid.setSetpoint(ElevatorSystem.BOT);
			}
		}));
		
		Input.primaryXBox.leftBumper.whenPressed(RunnableCommand.create(() -> {
			if (driveType.get()) {
				DriveSystem.getInstance().setHighGear(false);
			} //else {
			//	GripperShootCommand.pickup().start();
			//}
		}));
		
		Input.primaryXBox.rightBumper.whenPressed(RunnableCommand.create(() -> {
			if (driveType.get()) {
				DriveSystem.getInstance().setHighGear(true);
			} //else {
			//	GripperShootCommand.shoot().start();
			//}
		}));
		
		Input.primaryXBox.start.whenPressed(RunnableCommand.create(() -> {
			System.out.println("Toggle evelator override");
			ElevatorSystem.getInstance().toggleOverride();
		}));
		
		Input.primaryXBox.back.whenPressed(RunnableCommand.create(() -> {
			if (Pneumatics.getInstance().compressorState()) {
				Pneumatics.getInstance().stopCompressor();
			} else {
				Pneumatics.getInstance().startCompressor();
			}
		}));

		// XBOX CONTROLLER STUFF
		Input.primaryXBox.X.whenPressed(RunnableCommand.create(() -> Input.toggleDriveType()));
		
		// ^ OR USE START BUTTON

		// ATTACK 3 STUFF

		//Input.Attack3.getButton(3)
		//		.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(true)));

		//Input.Attack3.getButton(2)
		//		.whenPressed(RunnableCommand.create(() -> DriveSystem.getInstance().setHighGear(false)));
		
		//Input.Attack3.getButton(4).whenPressed(GripperShootCommand.pickup());
		
		//Input.Attack3.getButton(5).whenPressed(GripperShootCommand.shoot());

	}

}