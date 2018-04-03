package frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics {
	
	private static final Pneumatics INSTANCE = new Pneumatics();
	
	private Compressor compressor = new Compressor();
	
	private boolean comprstate = false;
	
	private Pneumatics() {
		stopCompressor();
	}
	
	public void stopCompressor() {
		compressor.stop();
		comprstate = false;
	}
	
	public void startCompressor() {
		compressor.start();
		comprstate = true;
	}
	
	public boolean compressorState() {
		return comprstate;
	}
	
	public static Pneumatics getInstance() {
		return INSTANCE;
	}

}
