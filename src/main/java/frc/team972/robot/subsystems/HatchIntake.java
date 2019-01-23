package frc.team972.robot.subsystems;

import frc.team972.robot.loops.ILooper;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HatchIntake extends Subsystem {

    public boolean hatchIntakeWorks = false;

    private static ExampleSubsystem mInstance = new ExampleSubsystem();

    public DoubleSolenoid hatchIntakeDoubleSolenoid;

    public void init() {
        hatchIntakeDoubleSolenoid = new DoubleSolenoid(-1, -1);
    }

    public void open() {
        hatchIntakeDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void close() {
        hatchIntakeDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void off() {
        hatchIntakeDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    public void toggle() {
        if (hatchIntakeWorks){
            open();
        }
        else{
            close();
        }
    }

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
}