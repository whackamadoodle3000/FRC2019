package frc.team972.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team972.robot.Constants;
import frc.team972.robot.driver_utils.TalonSRXFactory;
import frc.team972.robot.teleop.ControlBoard;
import frc.team972.robot.util.CoordinateDriveSignal;
import frc.team972.robot.util.DriveSignal;
import frc.team972.robot.util.MecanumHelper;

public class Drive extends Subsystem {

    private PeriodicIO mPeriodicIO;
    private DriveControlState mDriveControlState;
    private TalonSRX mLeftFront, mLeftBack, mRightFront, mRightBack;

    private boolean mIsBrakeMode;

    private static Drive mInstance = null;

    public Drive() {
        mLeftFront = TalonSRXFactory.createDefaultTalon(Constants.mLeftFrontId);
        configureMaster(mLeftFront, true);

        mLeftBack = TalonSRXFactory.createDefaultTalon(Constants.mLeftBackId);
        configureMaster(mLeftBack, true);

        mRightFront = TalonSRXFactory.createDefaultTalon(Constants.mRightFrontId);
        configureMaster(mRightFront, false);

        mRightBack = TalonSRXFactory.createDefaultTalon(Constants.mRightBackId);
        configureMaster(mRightBack, false);

        mIsBrakeMode = true;
        setBrakeMode(false);
    }

    public static Drive getInstance() {
        if (mInstance == null) {
            mInstance = new Drive();
        }
        return mInstance;
    }


    private void configureMaster(TalonSRX talon, boolean left) {
        //TODO: Configure Talons for Sensored operation
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        /*final ErrorCode sensorPresent = talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
        if (sensorPresent != ErrorCode.OK) {
            DriverStation.reportError("Could not detect " + (left ? "left" : "right") + " encoder: " + sensorPresent, false);
        }
        */
        //talon.setInverted(!left);
        //talon.setSensorPhase(true);
        talon.enableVoltageCompensation(true);
        talon.configVoltageCompSaturation(12.0, Constants.kLongCANTimeoutMs);
        //talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, Constants.kLongCANTimeoutMs);
        //talon.configVelocityMeasurementWindow(1, Constants.kLongCANTimeoutMs);
        //talon.configClosedloopRamp(Constants.kDriveVoltageRampRate, Constants.kLongCANTimeoutMs);
        talon.configNeutralDeadband(0.04, 0);
    }

    public synchronized  void setOpenLoopMecanum(CoordinateDriveSignal signal) {
        double current_angle = 0; //TODO: Grab estimated robot rotation state from RobotState after sensor-fusion.
        DriveSignal driveSignal = MecanumHelper.cartesianCalculate(signal, current_angle);

        //Feed transformed Mecanum values into traditional motor values
        setOpenLoop(driveSignal);
    }

    public synchronized void setOpenLoop(DriveSignal signal) {
        if (mDriveControlState != DriveControlState.OPEN_LOOP) {
            setBrakeMode(false);

            System.out.println("Switching to open loop");
            System.out.println(signal);
            mDriveControlState = DriveControlState.OPEN_LOOP;
        }

        mPeriodicIO.left_front_demand = signal.getLeftFront();
        mPeriodicIO.right_front_demand = signal.getRightFront();
        mPeriodicIO.left_back_demand = signal.getLeftBack();
        mPeriodicIO.right_back_demand = signal.getRightBack();
    }

    public synchronized void setBrakeMode(boolean on) {
        if (mIsBrakeMode != on) {
            mIsBrakeMode = on;
            NeutralMode mode = on ? NeutralMode.Brake : NeutralMode.Coast;

            mLeftBack.setNeutralMode(mode);
            mLeftFront.setNeutralMode(mode);
            mRightBack.setNeutralMode(mode);
            mRightFront.setNeutralMode(mode);
        }
    }

    public boolean isBrakeMode() {
        return mIsBrakeMode;
    }

    public enum DriveControlState {
        OPEN_LOOP, // voltage control
        PATH_FOLLOWING, // velocity control
    }

    @Override
    public boolean checkSystem() {
        //TODO: Implement
        return true;
    }

    @Override
    public synchronized void writePeriodicOutputs() {
        if (mDriveControlState == DriveControlState.OPEN_LOOP) {
            mRightFront.set(ControlMode.PercentOutput, mPeriodicIO.right_front_demand, DemandType.ArbitraryFeedForward, 0.0);
            mLeftFront.set(ControlMode.PercentOutput, mPeriodicIO.left_front_demand, DemandType.ArbitraryFeedForward, 0.0);
            mRightBack.set(ControlMode.PercentOutput, mPeriodicIO.right_back_demand, DemandType.ArbitraryFeedForward, 0.0);
            mLeftBack.set(ControlMode.PercentOutput, mPeriodicIO.left_back_demand, DemandType.ArbitraryFeedForward, 0.0);
        }
    }

    @Override
    public void outputTelemetry() {

    }

    @Override
    public void stop() {

    }

    public static class PeriodicIO {
        // OUTPUTS
        public double left_front_demand;
        public double right_front_demand;
        public double left_back_demand;
        public double right_back_demand;
    }

}


