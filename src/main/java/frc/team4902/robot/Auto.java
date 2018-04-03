package frc.team4902.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4902.robot.GameState.Direction;
import frc.team4902.robot.Robot.Pos;
import frc.team4902.robot.commands.DriveTime;
import frc.team4902.robot.commands.GripperShootCommand;

public class Auto {
	
	/*
	 * 
	 * Approximations:
	 * 
	 * Rotate 90 degrees:
	 *  - time = 
	 *  - speed = 
	 *  
	 *  Drive 1 meter:
	 *  
	 * - time = 
	 * - speed = 
	 * 
	 */
	
	public static enum AutoType {
		Baseline;
	}
	
	public static void autoInit() {
		
		CommandGroup cg = new CommandGroup();
		
		cg.addSequential(new DriveTime(6, 0.72, 0.7));
		
		cg.addSequential(new GripperShootCommand(-0.5, 0.5));
		
		cg.start();
		
		/*GameState.getInstance().acquireGameState();
		
		Pos p = Robot.getInstance().robotPos();
				
		if (GameState.getInstance().isClose(Direction.Left)) {
			
			if (p.equals(Pos.Left)) {
				
				Auto.sideAuto(AutoType.Baseline).start();
				
			} else if (p.equals(Pos.Center)) {
				
				// OK
				
			} else if (p.equals(Pos.Right)) {
				
				Auto.sideAuto(AutoType.Baseline).start();
				
			} else {
				
				DriverStation.reportError("This should **never happen** - Robot position indeterminate", false);
				
			}
			
		} else {
			
			if (p.equals(Pos.Left)) {
				
				// Difficult
				
			} else if (p.equals(Pos.Center)) {
				
				// OK
				
			} else if (p.equals(Pos.Right)) {
				
				// Ez
				
			} else {
				
				DriverStation.reportError("This should **never happen** - Robot position indeterminate", false);
				
			}
			
		}*/
		
	}
	
	public static CommandGroup sideAuto(AutoType at) {
		
		Pos p = Robot.getInstance().robotPos();
		
		if (p.equals(Pos.Left)) {
			
			CommandGroup cg = new CommandGroup();
			
			if (at.equals(AutoType.Baseline)) {
			
				cg.addSequential(new DriveTime(0.5, -0.3, 3));
				
				cg.addSequential(new DriveTime(2.5, 0.6));
				
			}
			
			return cg;
			
		} else if (p.equals(Pos.Right)) {
			
			CommandGroup cg = new CommandGroup();
			
			if (at.equals(AutoType.Baseline)) {
			
				cg.addSequential(new DriveTime(0.5, 0.3, -0.3));
				
				cg.addSequential(new DriveTime(2.5, 0.6));
				
			}
			
			return cg;
			
		} else { // Center
			
			return centerAuto(at);
			
		}
		
	}
	
	public static CommandGroup centerAuto(AutoType at) {
		if (Robot.getInstance().robotPos().equals(Pos.Center)) {
			
			CommandGroup cg = new CommandGroup();
			
			cg.addSequential(new DriveTime(1, -0.3, 0.3));
			
			cg.addSequential(new DriveTime(2, 0.5));
			
			cg.addSequential(new DriveTime(1, 0.3, -0.3));
			
			cg.addSequential(new DriveTime(3, 0.6));
			
			return cg;
			
		} else {
			return sideAuto(at);
		}
	}

}
