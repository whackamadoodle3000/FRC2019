package frc.team972.robot.subsystems;

import frc.team972.robot.loops.ILooper;
import frc.team972.robot.driver_utils.TalonSRXFactory;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Arm extends Subsystem {
    private static Arm mArm = new Arm();

    //Arm initializations
    public static double desiredArmAngle = 0;
    private static double currentArmAngle = 0;
    public static final double clicksToDegrees = 1;
    private static Encoder armEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    private static boolean isFirstTime = true;

    //PID initializations
    private static final double proportionFactor = 0;
    private static final double integralFactor = 0;
    private static final double derivativeFactor = 0;
    private static double currentValue = 0;
    private static double previousValue = 0;
    private static double integral = 0;

    public static TalonSRX armTalon = TalonSRXFactory.createDefaultTalon(1);

    public double getCurrentArmAngle(){
        return currentArmAngle;
    }
    public void initialize(){
        zeroSensors();
        armEncoder.setDistancePerPulse(clicksToDegrees);
        isFirstTime = false;
    }
    public void writeToLog() {
    }

    public void readPeriodicInputs() {
    }

    public void writePeriodicOutputs() {
        if (isFirstTime){
            initialize();
        }
        moveToAngle();
        //System.out.println("Test");
    }

    public void moveToAngle(){
        previousValue = currentValue;
        currentValue = armEncoder.get();
        if ((desiredArmAngle-currentArmAngle)<1.5){
            integral=0;
        }
        else {
            integral = integral + currentValue - desiredArmAngle;
        }
        armTalon.set(proportionFactor * (desiredArmAngle - currentValue) + integralFactor * (integral) + derivativeFactor * (currentValue - previousValue));

    }

    public boolean checkSystem() {
        return true;
    }

    public void outputTelemetry() {
    }

    public void stop() {
        armEncoder.reset();
    }

    public void zeroSensors() {
        armEncoder.reset();
    }

    public void registerEnabledLoops(ILooper enabledLooper) {
    }

    public void setDesiredArmAngle(double angle){
        desiredArmAngle = angle;
    }

    public double getDesiredArmAngle(){
        return desiredArmAngle;
    }

    public static ExampleSubsystem getInstance() {
        return mInstance;
    }
}
