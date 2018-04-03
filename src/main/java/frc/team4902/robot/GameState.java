package frc.team4902.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class GameState {
	
	public static enum Direction {
		Left, Right;
	}
	
	private static final GameState INSTANCE = new GameState();
	
	private final Direction[] SwitchStates = new Direction[3];
	
	private GameState() {}
	
	public static GameState getInstance() {
		return INSTANCE;
	}
	
	public void acquireGameState() {
		
		String DSMessage = "";
		
		do {
			
			DSMessage = DriverStation.getInstance().getGameSpecificMessage();
			
		} while (DSMessage.length() != 3);
		
		for (int i = 0; i < 3; i++) {
		
			SwitchStates[i] = (DSMessage.charAt(i) == 'L' ? Direction.Left : Direction.Right);
		
		}
		
		
	}
	
	public Direction[] getSwitchStates() {
		return SwitchStates;
	}
	
	public boolean isClose(Direction d) {
		return SwitchStates[0].equals(d);
	}
	
	public boolean isMiddle(Direction d) {
		return SwitchStates[1].equals(d);
	}
	
	public boolean isFar(Direction d) {
		return SwitchStates[2].equals(d);
	}

}
