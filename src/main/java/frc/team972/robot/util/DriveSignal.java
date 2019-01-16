package frc.team972.robot.util;

public class DriveSignal {

    protected double mLeftMotorFront;
    protected double mRightMotorFront;
    protected double mLeftMotorBack;
    protected double mRightMotorBack;
    protected boolean mBrakeMode;

    public DriveSignal(double left, double right, double left_b, double right_b) {
        this(left, right, left_b, right_b, false);
    }

    public DriveSignal(double left, double right, double left_b, double right_b, boolean brakeMode) {
        mLeftMotorFront = left;
        mRightMotorFront = right;
        mLeftMotorBack = left_b;
        mRightMotorBack = right_b;
        mBrakeMode = brakeMode;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0, 0, 0);
    public static DriveSignal BRAKE = new DriveSignal(0, 0, 0, 0, true);

    public double getLeftFront() {
        return mLeftMotorFront;
    }

    public double getRightFront() {
        return mRightMotorFront;
    }

    public double getLeftBack() {
        return mLeftMotorBack;
    }

    public double getRightBack() {
        return mRightMotorBack;
    }

    public boolean getBrakeMode() {
        return mBrakeMode;
    }

    public void set(double a, double b, double c, double d) {
        mLeftMotorFront = a;
        mLeftMotorBack = b;
        mRightMotorFront = c;
        mRightMotorBack = d;
    }

    @Override
    public String toString() {
        return "L: " + mLeftMotorFront + " " + mLeftMotorBack + ", R: " + mRightMotorFront + " " + mRightMotorBack + (mBrakeMode ? ", BRAKE" : "");
    }
}