package frc.team972.robot.subsystems;

import frc.team972.robot.Constants;
import frc.team972.robot.driver_utils.TalonSRXFactory;
import frc.team972.robot.loops.ILooper;

public class ElevatorSubsystem extends Subsystem {
    private static ElevatorSubsystem mInstance = new ElevatorSubsystem();

    private TalonSRX elevatorTalon;
    private boolean autoMoving;

    public ElevatorSystem() {
        elevatorTalon = TalonSRXFactory.createDefaultTalon(Constants.kElevatorMotorId);
        autoMoving = false;
    }

    public void writeToLog() {
    }

    public void readPeriodicInputs() {
    }

    public void writePeriodicOutputs() {
        if(autoMoving) {

        } else {
            
        }
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

    public static ElevatorSubsystem getInstance() {
        return mInstance;
    }
}
