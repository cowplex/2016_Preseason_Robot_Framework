package org.usfirst.frc.team1504.robot;

public class IO
{
	private static Latch_Joystick _drive_forward = new Latch_Joystick(Map.DRIVE_CARTESIAN_JOYSTICK);
	private static Latch_Joystick _drive_rotation = new Latch_Joystick(Map.DRIVE_POLAR_JOYSTICK);
	
	private static Latch_Joystick _secondary = new Latch_Joystick(Map.SECONDARY_JOYSTICK);
	private static Latch_Joystick _tertiary_shooter = new Latch_Joystick(Map.TERTIARY_JOYSTICK);
	private static Latch_Joystick _tertiary_aim = new Latch_Joystick(Map.TERTIARY_JOYSTICK);
	
	public static final long ROBOT_START_TIME = System.currentTimeMillis();
	
	/**
	 * Override button
	 */
	public static boolean override()
	{
		return _secondary.getRawButton(Map.UTIL_OVERRIDE_BUTTON) ||
				_tertiary_shooter.getRawButton(Map.TERTIARY_FIRE_BUTTON);
	}
	
	/**
	 * Drive stuff
	 */
	
	/**
	 * Handle getting joystick values
	 * @return
	 */
	public static double[] drive_input() {
		double[] inputs = new double[2];

		inputs[0] = Map.DRIVE_INPUT_MAGIC_NUMBERS[0] * Math.pow(Utils.deadzone(_drive_forward.getRawAxis(Map.JOYSTICK_Y_AXIS)), 2) * Math.signum(_drive_forward.getRawAxis(Map.JOYSTICK_Y_AXIS));// y
		inputs[1] = Map.DRIVE_INPUT_MAGIC_NUMBERS[1] * Math.pow(Utils.deadzone(_drive_rotation.getRawAxis(Map.JOYSTICK_X_AXIS)), 2) * Math.signum(_drive_rotation.getRawAxis(Map.JOYSTICK_X_AXIS));// w
		
		//if(!_drive_rotation.getRawButton(Map.DRIVE_INPUT_TURN_FACTOR_OVERRIDE_BUTTON))
		//	inputs[1] *= Math.abs(inputs[0]) <= 0.01 ? 0.85 : Math.min((Math.abs(inputs[0]) + .05) / Map.DRIVE_INPUT_TURN_FACTOR, 1);
		
		return inputs;
	}
	
	public static double drive_wiggle()
	{
		return (_drive_rotation.getRawButton(4) ? -1.0 : 0.0) + (_drive_rotation.getRawButton(5) ? 1.0 : 0.0);
	}
	
	public static double front_side() {
		/*if(_drive_rotation.getRawButton(11))
			return 0.0;
		else
			return 180.0;*/
		/*if (_drive_rotation.getRawButtonLatch(Map.DRIVE_FRONTSIDE_BACK) || _drive_forward.getRawButton(11)) {
			return 180.0;
		} else if (_drive_rotation.getRawButtonLatch(Map.DRIVE_FRONTSIDE_FRONT)) {
			return 0.0;
		}
		return Double.NaN;*/
		/*if(_drive_forward.getRawButton(8))
			return 180.0;
		if(_drive_rotation.getRawButton(11))
			return 0.0;
		return Double.NaN;*/
		return 0.0;
	}
	
	/**
	 * Lifter stuff
	 */
	
	/*public static Map.LIFTER_STATE lift_state()
	{
		if(_secondary.getRawButtonLatch(Map.LIFTER_DOWN_BUTTON))
			return Map.LIFTER_STATE.DOWN;
		if(_secondary.getRawButtonLatch(Map.LIFTER_UP_BUTTON))
			return Map.LIFTER_STATE.UP;
		if(_secondary.getRawButtonOnRisingEdge(Map.LIFTER_TOGGLE_BUTTON))
			return Map.LIFTER_STATE.TOGGLE;
		return null;
	}*/
	
	/**
	 * Wheel shooter stuff
	 */
	public static Wheel_Shooter.WHEEL_SHOOTER_STATE wheel_shooter_state()
	{
		if(_secondary.getRawButton(Map.WHEEL_SHOOTER_INTAKE_ON_BUTTON) && 
		   _secondary.getRawButton(Map.WHEEL_SHOOTER_INTAKE_OFF_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.PICKUP_OUT;
		if(_secondary.getRawButton(Map.WHEEL_SHOOTER_INTAKE_ON_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.PICKUP;
		if(_secondary.getRawButton(Map.WHEEL_SHOOTER_INTAKE_OFF_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.READY;
		//if(_secondary.getRawButtonLatch(Map.WHEEL_SHOOTER_INTAKE_REVERSE_BUTTON))
		//	return Wheel_Shooter.WHEEL_SHOOTER_STATE.PICKUP_OUT;
		if(_secondary.getRawButton/*OnRisingEdge*/(Map.WHEEL_SHOOTER_SPINUP_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.SPINUP;
		if(_secondary.getRawButton/*Latch*/(Map.WHEEL_SHOOTER_FIRE_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.FIRE;
		
		// Win button
		if(_tertiary_shooter.getRawButtonOnRisingEdge(Map.TERTIARY_WIN_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.SPINUP;
		if(_tertiary_shooter.getRawButtonLatch(Map.TERTIARY_WIN_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.FIRE;
		
		// Fire button
		if(_tertiary_shooter.getRawButtonOnRisingEdge(Map.TERTIARY_FIRE_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.SPINUP;
		if(_tertiary_shooter.getRawButtonLatch(Map.TERTIARY_FIRE_BUTTON))
			return Wheel_Shooter.WHEEL_SHOOTER_STATE.FIRE;
		
		return null;
	}
	
	/**
	 * LEGO shooter stuff
	 */
	/*public static Lego_Shooter.LEGO_SHOOTER_ACTION_STATE lego_shooter_action()
	{
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_INTAKE_ON_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.PICKUP;
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_INTAKE_OFF_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.READY;
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_INTAKE_REVERSE_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.PICKUP_OUT;
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_FIRE_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.FIRE;
		return null;
	}*/
	
	/*public static Lego_Shooter.LEGO_SHOOTER_POSITION_STATE lego_shooter_position()
	{
		/*if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_INTAKE_ON_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.PICKUP;
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_INTAKE_OFF_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.READY;
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_INTAKE_REVERSE_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.PICKUP_OUT;
		if(_secondary.getRawButtonOnRisingEdge(Map.LEGO_SHOOTER_FIRE_BUTTON))
			return Lego_Shooter.LEGO_SHOOTER_ACTION_STATE.FIRE;*
		return null;
	}*/
	
	/**
	 * Vision Interface stuff
	 */
	public static boolean vision_target_override()
	{
		return _secondary.getRawButton(Map.VISION_INTERFACE_OVERRIDE_BUTTON) ||
				_tertiary_aim.getRawButton(Map.TERTIARY_WIN_BUTTON) ||
				_tertiary_aim.getRawButton(Map.TERTIARY_AIM_BUTTON);
	}
	
	public static boolean vision_target_override_rising()
	{
		return _secondary.getRawButtonOnRisingEdge(Map.VISION_INTERFACE_OVERRIDE_BUTTON) ||
				_tertiary_aim.getRawButtonOnRisingEdge(Map.TERTIARY_WIN_BUTTON) ||
				_tertiary_aim.getRawButtonOnRisingEdge(Map.TERTIARY_AIM_BUTTON);
	}
	
	/**
	 * Endgame stuff
	 */
	public static Endgame.ENDGAME_STATE endgame_state()
	{
		if(_secondary.getRawButton/*OnRisingEdge*/(8))
			return Endgame.ENDGAME_STATE.EXTEND;
		if(_secondary.getRawButton/*OnRisingEdge*/(9))
			return Endgame.ENDGAME_STATE.RETRACT;
		return null;
	}
}
