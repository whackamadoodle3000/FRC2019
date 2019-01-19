package frc.team972.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.team972.robot.Constants;
import frc.team972.robot.driver_utils.TalonSRXFactory;
import frc.team972.robot.loops.ILooper;



public class ElevatorSubsystem extends Subsystem {
    private static ElevatorSubsystem mInstance = new ElevatorSubsystem();

    private TalonSRX elevatorTalon;
    private boolean autoMoving;

    private double voltage;

    public ElevatorSubsystem() {
        elevatorTalon = TalonSRXFactory.createDefaultTalon(Constants.kElevatorMotorId);
        autoMoving = false;
        voltage = 0;
    }

    public void writeToLog() {
        if (autoMoving) {
            System.out.println("Automatically moving. Voltage: " + voltage);
        }
    }

    public void readPeriodicInputs() {
    }

    public void writePeriodicOutputs() {
        if(autoMoving) {
            //Calculate Voltage based on desired stage
        } else {
            //Calculate Voltage based on ElevatorJoystick
        }

        elevatorTalon.set(ControlMode.PercentOutput, voltage);
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
