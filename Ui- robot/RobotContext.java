public class RobotContext implements RobotState {

	private RobotState thisState;

	public void changeState(RobotState newState) {
		this.thisState = newState;
	}

	public String getTheState() {
		return this.thisState.toString();
	}

	@Override
	public int goNorth() {
		// TODO Auto-generated method stub
		return this.thisState.goNorth();
	}

	@Override
	public int goSouth() {
		// TODO Auto-generated method stub
		return this.thisState.goSouth();
	}

	@Override
	public int goEast() {
		// TODO Auto-generated method stub
		return this.thisState.goEast();
	}

	@Override
	public int goWest() {
		// TODO Auto-generated method stub
		return this.thisState.goWest();
	}

}
