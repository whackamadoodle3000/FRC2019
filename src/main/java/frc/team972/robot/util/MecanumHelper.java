package frc.team972.robot.util;

public class MecanumHelper {

    public static CoordinateDriveSignal mecanumDrive(double x, double y, double rotation) {
        return new CoordinateDriveSignal(handleDeadband(x, 0.05), handleDeadband(y, 0.05), handleDeadband(rotation, 0.05));
    }

    /*
        This is only used by classes that need to calculate the necessary Mecanum transforms!
        Just a fun math class.
        TODO: Profile performance. Lots of recycling can be done.
     */
    public static DriveSignal cartesianCalculate(CoordinateDriveSignal signal, double current_angle) {
        double[] rotatedVector = rotateVector(signal.x, signal.y, current_angle);

        signal.x = rotatedVector[0];
        signal.y = rotatedVector[1];

        DriveSignal driveSignal = new DriveSignal(
                signal.x + signal.y + signal.rotation,
                -signal.x + signal.y - signal.rotation,
                -signal.x + signal.y + signal.rotation,
                signal.x + signal.y - signal.rotation
        );

        return driveSignal;
    }

    private static double[] rotateVector(double x, double y, double angle) {
        double angleRad = Math.toRadians(angle);
        double cosA = Math.cos(angleRad);
        double sinA = Math.sin(angleRad);

        return new double[]{(x * cosA) - (y * sinA), (x * sinA) + (y * cosA)};
    }

    public static double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}
