package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

public class RobotTemplate extends SimpleRobot{

public RobotTemplate()
{
    Compressor c = new Compressor(1, 1);
    c.start();
}

RobotDrive chassis = new RobotDrive(1, 2);
Joystick leftStick = new Joystick(1);
Relay myRelay = new Relay(2);

public void autonamous() {
    chassis.setSafetyEnabled (false);
    chassis.drive(-0.5, 0.0);
    Timer.delay(2.0);
    chassis.drive(0.0, 0.0);
}

public void operatorControl() {
    chassis.setSafetyEnabled(true);
    while (isOperatorControl() && isEnabled()){
        chassis.arcadeDrive(leftStick);
        if (leftStick.getRawAxis(3) > .1) {
            myRelay.set(Relay.Value.kForward);
        }
      else {
           myRelay.set(Relay.Value.kOff);
       }
        Timer.delay(0.01);
    }
}
}