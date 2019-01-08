package frc.team972.robot.driver_utils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class LazyTalonSRX extends TalonSRX {
    protected double mLastSet = Double.NaN;
    protected ControlMode mLastControlMode = null;

    public LazyTalonSRX(int deviceNumber) {
        super(deviceNumber);
    }

    public double getLastSet() {
        return mLastSet;
    }

    @Override
    public void set(ControlMode mode, double value) {
        if (value != mLastSet || mode != mLastControlMode) {
            mLastSet = value;
            mLastControlMode = mode;
            super.set(mode, value);
        }
    }
}