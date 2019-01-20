package frc.team972.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.team972.robot.loops.ILooper;
import frc.team972.robot.util.CoordinateDriveSignal;
import frc.team972.robot.Constants;

public class PistonClimb extends Subsystem {
	
	private final double HAB_LEVEL_ONE_LEVEL_TWO_DIFF = Constants.HabLevelOneElevationInches - Constants.HabLevelOneElevationInches;
	
	private boolean[] pistonClimbStage = new boolean[6];
	
	private boolean frontPistonsState = false;
	private boolean backPistonsState = false;
	
	private Timer waitTimer = new Timer();
	private double time = 0;
	
	private Ultrasonic ultra1 = new Ultrasonic(1, 1);
	private double range = 0;
	
	private DoubleSolenoid frontPistons;
	private DoubleSolenoid backPistons;
	
    private static PistonClimb mInstance = new PistonClimb();
    private Drive driveControl = new Drive();
    
    public void PistonClimb() {
    	frontPistons = new DoubleSolenoid(1, 2);
    	backPistons = new DoubleSolenoid(3, 4);
    }
    
    public void climbManager(boolean value) {
    	pistonClimbStage[0] = value;
    	
    	if (pistonClimbStage[5] == true) {
    		frontPistonsState = false;
    		backPistonsState = false;
    		
    	} else if (pistonClimbStage[4] == true) {
    		
    	} else if (pistonClimbStage[3] == true) {
    		
    	} else if (pistonClimbStage[2] == true) {
    		frontPistonsState = false;
    		backPistonsState = true;
    		if (range <= HAB_LEVEL_ONE_LEVEL_TWO_DIFF) {
    			
    		}
    	} else if (pistonClimbStage[1] == true) {
    		CoordinateDriveSignal forward = new CoordinateDriveSignal(1.0, 0.0, 0.0, false);
    		driveControl.setOpenLoopMecanum(forward);
    		
    	} else if (pistonClimbStage[0] == true) {
    		frontPistonsState = true;
    		waitTimer.start();
    		if (time >= 0.5) {
    			pistonClimbStage[1] = true;
    			waitTimer.stop();
    			time = 0;
    		}
    	}
    }
    
    public void writeToLog() {
    	
    }

    public void readPeriodicInputs() {

    }

    public void writePeriodicOutputs() {
    	if (pistonClimbStage[0] && !pistonClimbStage[1]) {
    		time = waitTimer.get();
    	}
    	
    	if (pistonClimbStage[1] && !pistonClimbStage[2]) {
    		range = ultra1.getRangeInches();
    	}
    }

    public void setFrontPistonsState(boolean value) {
    	frontPistonsState = value;
    }
    
    public void setBackPistonsState(boolean value) {
    	backPistonsState = value;
    }
    
    public boolean checkSystem() {
        return true;
    }

    public void outputTelemetry() {
    	
    }

    public void stop() {
    	
    }

    public void zeroSensors() {
    	
    }

    public void registerEnabledLoops(ILooper enabledLooper) {
    	
    }

    public static PistonClimb getInstance() {
        return mInstance;
    }
}
