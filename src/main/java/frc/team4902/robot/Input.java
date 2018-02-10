package frc.team4902.robot;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Handles all driver input. Access using Input.getInstance()
 */
public final class Input {
	
	private Input(int port){
		this.stickPort = port;
		this.stick = new XboxController(this.stickPort);
		A = new JoystickButton(stick, 1);
		B = new JoystickButton(stick, 2);
		X = new JoystickButton(stick, 3);
		Y = new JoystickButton(stick, 4);
		LeftBumper = new JoystickButton(stick, 5);
		RightBumper = new JoystickButton(stick, 6);
	}

	private int stickPort;

	// Joystick object mapped to the port of the xbox controller
	private final XboxController stick;

	// JoystickButton objects mapped to ports of buttons on controller
	private final JoystickButton A, B, X, Y, LeftBumper, RightBumper;

	// Instance of this class
	private static Input primaryInstance = new Input(0);
//	private static Input secondaryInstance = new Input(1);

	/**
	 * Useless shit made by George to make life more difficult, but you have to use it to access Input methods
	 * -Gunvir Ranu
	 * @return Instance of the Input class
	 */
	public static Input getPrimaryInstance() {
		return primaryInstance;
	}

//	public static Input getSecondaryInstance() {
//		return secondaryInstance;
//	}

	public int getJoystickNumber(){
		return stickPort+1;
	}
	
	public void rumbleTime(float intensity, RumbleType type, Duration time) {
		this.rumble(intensity, type);
		//MasterTimer.getInstance().schedule(() -> this.rumble(0, type),time);
	}
	
	public void rumble(float intensity, RumbleType type) {
		this.stick.setRumble(type, intensity);
	}

	public XboxController getJoystick() {
		return stick;
	}
	
	public double getRawAxis(int axis) {
		return this.stick.getRawAxis(axis);
	}

	public double getLeftX(){
		return stick.getRawAxis(0);
	}

	public double getLeftY() {
		return stick.getRawAxis(1);
	}

	public double getLeftYThreshold() {
		double threshold = 0.18;
		if (Math.abs(getLeftY()) < threshold) {
			return 0;
		} else {
			return getLeftY();
		}
	}

	public double getRightYThreshold() {
		double threshold = 0.18;
		if (Math.abs(getRightY()) < threshold) {
			return 0;
		} else {
			return getRightY();
		}
	}

	public double getRightX() {
		return stick.getRawAxis(4);
	}

	public double getRightY() {
		return stick.getRawAxis(5);
	}

	public double getLeftTrigger(){
		return stick.getRawAxis(2);
	}

	public double getRightTrigger(){
		return stick.getRawAxis(3);
	}

	public JoystickButton getLeftBumper() {
		return LeftBumper;
	}

	public JoystickButton getRightBumper() {
		return RightBumper;
	}

	public JoystickButton getButtonA() {
		return A;
	}

	public JoystickButton getButtonB() {
		return B;
	}

	public JoystickButton getButtonX() {
		return X;
	}

	public JoystickButton getButtonY() {
		return Y;
	}

	/**
	 * @param button The name of the button in which you wish to inquire the nomenclatural information of.
	 * @return Name of the button 
	 */
	public String getButtonName(JoystickButton button) {
		String out = null;
		if (button.equals(A)) out = "A";
		else if (button.equals(B))  out = "B";
		else if (button.equals(X)) out = "X";
		else if (button.equals(Y)) out = "Y";
		else System.err.println("Unrecognized button passed to Input.getButtonName! Returning null!");
		return out;
	}

	/**
	 * If for some reason you ever need to get an ArrayList of all the buttons (???) George's got you covered lol
	 * @return An ArrayList holding all the buttons mapped to the controller
	 */
	public ArrayList<JoystickButton> getButtons() {
		ArrayList<JoystickButton> buttons = new ArrayList<>();
		buttons.addAll(Arrays.asList(A,B,X,Y,LeftBumper,RightBumper));
		return buttons;
	}
}