package frc.team972.robot.teleop;

import edu.wpi.first.wpilibj.Joystick;
import frc.team972.robot.Constants;

public class ElevatorJoystickControlBoard {
    private static ElevatorJoystickControlBoard mInstance = null;

    public static ElevatorJoystickControlBoard getInstance() {
        if (mInstance == null) {
            mInstance = new ElevatorJoystickControlBoard();
        }

        return mInstance;
    }

    private Joystick mJoystick;

    private ElevatorJoystickControlBoard() {

        mJoystick = new Joystick(Constants.kElevatorJoystickPort);
    }

}