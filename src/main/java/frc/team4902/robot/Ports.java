package frc.team4902.robot;

public enum Ports {

	DriveTrainLeftFront(1), DriveTrainLeftBack(0), DriveTrainRightFront(8), DriveTrainRightBack(9), // Real values
	
	DriveTrainLeftEncoderA(2), DriveTrainLeftEncoderB(3), // Real values
	
	DriveTrainRightEncoderA(0), DriveTrainRightEncoderB(1), // Real values
	
	DriveTrainLeftSolenoid(0), DriveTrainRightSolenoid(1), // Real values
	
	ElevatorMotorA(3), ElevatorMotorB(6), // Real values
	
	ElevatorEncoderA(0), ElevatorEncoderB(1), // Todo
	
	GripperMotorA(0), GripperMotorB(1); // Todo
	
	public final int PORT;
	
	private Ports(int p) {
		this.PORT = p;
	}
	
}
