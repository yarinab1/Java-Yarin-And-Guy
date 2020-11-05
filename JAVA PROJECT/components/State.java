package components;

import GUI.DrivingGameFrame;

public interface State {
	/**
	 * do the the action of the exit menu button by state instance (Can/'tExitState exit)
	 * @param frame
	 */
	public void exitMenuAction(DrivingGameFrame frame);
}
