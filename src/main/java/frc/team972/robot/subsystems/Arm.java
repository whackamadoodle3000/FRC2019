package frc.team972.robot.subsystems;

import frc.team972.robot.driver_utils.TalonSRXFactory;
import frc.team972.robot.loops.ILooper;
import edu.wpi.first.wpilibj.Encoder;

public class Arm extends Subsystem {
    private static final double ClicksToDegrees = 1;
    private static Arm mArm = new Arm();
    private static TalonSRXFactory mArmTalon = new TalonSRXFactory(1);
    private static Encoder armEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    public static armDegrees = 0;
    public static desiredArmDegrees = 0;


    public void setDesiredArmDegrees(double degrees){
        desiredArmDegrees = degrees;
    }
    public double getArmDegrees(){
        return armDegrees;
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
