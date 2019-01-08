package frc.team972.robot.util;

public class MecanumHelper {

    public CoordinateDriveSignal mecanumDrive(double x, double y, double rotation) {
        return new CoordinateDriveSignal(x, y, rotation);
    }

    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}
