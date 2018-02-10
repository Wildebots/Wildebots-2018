package frc.team4902.robot;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class EventSystem extends Thread {

	/**
	 * Specifies when the action specified by this Runnable (which is inserted into a Handler) will run.
	 */
	public enum HandlerType {
		OnPress, OnRelease, WhilePressed;
	}

	private class Handler {

		private Runnable r;

		private HandlerType type;

		public Handler(Runnable r, HandlerType type) {
			this.r = r;
			this.type = type;
		}

		public Runnable getRunnable() {
			return r;
		}

		public HandlerType getType() {
			return type;
		}

	}

	private HashMap<JoystickButton, ArrayList<Handler>> eventMap = new HashMap<>();

	private HashMap<JoystickButton, Boolean> pressedMap = new HashMap<>();

	private final static EventSystem instance = new EventSystem();

	public static EventSystem getInstance() {
		return instance;
	}

	private EventSystem() {
		Input.getPrimaryInstance().getButtons().forEach(button -> {
			eventMap.put(button, new ArrayList<Handler>());
			pressedMap.put(button, false);
		});
		//		Input.getSecondaryInstance().getButtons().forEach(button -> {
		//			eventMap.put(button, new ArrayList<Handler>());
		//			pressedMap.put(button, false);
		//		});
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			if (!DriverStation.getInstance().isDisabled()) {
				eventMap.keySet().forEach(button -> {
					if (button.get()) {
						eventMap.get(button).forEach(handler -> {
							if (handler.getType().equals(HandlerType.OnPress)) {
								if (!pressedMap.get(button)) {
									handler.getRunnable().run();
								}
							} else if (handler.getType().equals(HandlerType.WhilePressed)) {
								handler.getRunnable().run();
							} else if (!handler.getType().equals(HandlerType.OnRelease)) {
								handler.getRunnable().run();
							}
						});
						pressedMap.put(button, true);
					} else if (!button.get()) {
						eventMap.get(button).forEach(handler -> {
							if (handler.getType().equals(HandlerType.OnRelease)) {
								if (pressedMap.get(button)) {
									handler.getRunnable().run();
								}
							}
						});
						pressedMap.put(button, false);
					}
				});
			}
		}
	}

	/**
	 * Attaches some code to be run to a button, this code is run when the condition for the HandlerType is met.
	 * @param r Runnable to be run when triggered
	 * @param button Button to attach handler to
	 * @param type The type of handler
	 */
	public void addHandler(Runnable r, JoystickButton button, HandlerType type) {
		if (!eventMap.keySet().contains(button)) {
			throw new RuntimeException("Attempted to map handler to unregistered button!");
		} else {
			eventMap.get(button).add(new Handler(r,type));
		}
	}

	/**
	 * Gets the number of handlers attached to a button
	 * @param button The button for which to get the number of handlers
	 * @return The number of handlers attached to this button
	 */
	public int getNumHandlers(JoystickButton button) {
		if (!eventMap.keySet().contains(button)) {
			System.err.println("Passed EventSystem.getNumHandlers(JoystickButton) an unregistered button!");
			return 0;
		} else {
			return eventMap.get(button).size();
		}
	}

	/**
	 * Removes all handlers attached to a button
	 * @param button The button of which to clear the handlers
	 */
	public void clearHandlers(JoystickButton button) {
		if (!eventMap.keySet().contains(button)) {
			System.err.println("Passed EventSystem.clearHandlers(JoystickButton) an unregistered button!");
			return;
		} else {
			eventMap.get(button).clear();
		}
	}

	@Override
	public String toString() {
		String out = "EventSystem ->";
		for (JoystickButton b : this.eventMap.keySet()) {
			out += "\n	Button "+Input.getPrimaryInstance().getButtonName(b)+": Handlers: "+this.getNumHandlers(b);
		}
		return out;
	}
}