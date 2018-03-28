package frc.team4902.robot;

public enum Ports {

	DriveTrainLeftFront(1), DriveTrainLeftBack(0), DriveTrainRightFront(8), DriveTrainRightBack(9), // Real values
	
	DriveTrainLeftEncoderA(2), DriveTrainLeftEncoderB(3), // Real values
	
	DriveTrainRightEncoderA(0), DriveTrainRightEncoderB(1), // Real values
	
	DriveTrainLeftSolenoid(0), DriveTrainRightSolenoid(1), // Real values
	
	ElevatorMotorA(3), ElevatorMotorB(6), // Real values
	
	ElevatorEncoderA(4), ElevatorEncoderB(5), // Todo
	
	GripperMotorA(7), GripperMotorB(2); // Todo
	
	public final int PORT;
	
	private Ports(int p) {
		this.PORT = p;
	}
	
}
