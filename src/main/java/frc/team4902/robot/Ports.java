package frc.team4902.robot;

public enum Ports {

	DriveTrainLeftFront(0), DriveTrainLeftBack(1), DriveTrainRightFront(2), DriveTrainRightBack(3),
	
	DriveTrainLeftEncoderA(0), DriveTrainLeftEncoderB(1),
	
	DriveTrainRightEncoderA(2), DriveTrainRightEncoderB(3),
	
	DriveTrainLeftSolenoid(0), DriveTrainRightSolenoid(1);
	
	public final int PORT;
	
	private Ports(int p) {
		this.PORT = p;
	}
	
}
