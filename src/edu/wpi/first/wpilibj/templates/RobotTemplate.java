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

public void autonomous() {
    chassis.setSafetyEnabled (false);
    //drive forward halfspeed
    chassis.drive(-0.5, 0.0);
    //drives 1 seconds
    Timer.delay(1.0);
    //spin right
    chassis.drive(0, 0.5);
    //spin 5 seconds
    Timer.delay(2.0);
    //spin left
    chassis.drive(0.0, -0.5);
    //spin for 5 seconds
    Timer.delay(2.0);
    //Drive in reverse
    chassis.drive(0.5,0.0);
    //stop
    Timer.delay(1.0);
    
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