package frc.team972.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team972.robot.loops.Looper;
import frc.team972.robot.subsystems.ExampleSubsystem;
import frc.team972.robot.subsystems.SubsystemManager;

public class Robot extends TimedRobot {

	@Override
	public void robotInit() {
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void testInit() {
		SubsystemManager mSubsystemManager = new SubsystemManager(Arrays.asList(ExampleSubsystem.getInstance()));

		Looper mLooper = new Looper();
		mSubsystemManager.registerEnabledLoops(mLooper);

		mLooper.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mLooper.stop();
	}

}
