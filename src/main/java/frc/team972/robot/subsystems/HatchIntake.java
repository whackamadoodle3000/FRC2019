package frc.team972.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import frc.team972.robot.*;
import frc.team972.robot.loops.ILooper;

public class HatchIntake extends Subsystem {

    private static ExampleSubsystem mInstance = new ExampleSubsystem();

    public DoubleSolenoid creativeVariableName = new DoubleSolenoid(-1, -1);

    public void writeToLog() {
    }

    public void readPeriodicInputs() {
    }

    public void writePeriodicOutputs() {
        //System.out.println("Test");
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

    public static ExampleSubsystem getInstance() {
        return mInstance;
    }

    public void open(){
        creativeVariableName.set(DoubleSolenoid.Value.kForward);
    }

    public void close(){
        creativeVariableName.set(DoubleSolenoid.Value.kReverse);
    }
}