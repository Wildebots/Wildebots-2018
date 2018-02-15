package frc.team4902.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public final class Input {
	
	public static final XBoxInput primaryXBox = new XBoxInput(0), secondaryXBox = new XBoxInput(0);
	
	public static final Attack3 Attack3 = new Attack3(0);
	
	public static final class XBoxInput extends XboxController {
		
		public final JoystickButton A = new JoystickButton(this, 1),
				B = new JoystickButton(this, 2),
				X = new JoystickButton(this, 3),
				Y = new JoystickButton(this, 4),
				leftBumper = new JoystickButton(this, 5),
				rightBumper = new JoystickButton(this, 6),
				start = new JoystickButton(this, 7),
				select = new JoystickButton(this, 8);
		
		public XBoxInput(int port) {
			super(port);
		}
		
		public double getLeftX(){
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
		
		public boolean isPluggedIn() {
			return DriverStation.getInstance().getJoystickIsXbox(getPort());
		}
		
	}
	
	public static final class Attack3 extends Joystick {
		
		public final List<JoystickButton> buttons = new ArrayList<>();
		
		public Attack3(int port) {
			super(port);
			for (int i = 0; i < 11; i++) {
				buttons.add(new JoystickButton(this, (i+1)));
			}
		}
		
		public JoystickButton getButton(int num) {
			return buttons.get(num);
		}
		
		public boolean isPluggedIn() {
			return DriverStation.getInstance().getJoystickName(getPort()).equals("Logitech Attack 3");
		}
		
	}
	
}