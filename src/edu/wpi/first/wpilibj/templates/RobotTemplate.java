package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotTemplate extends SimpleRobot{

public RobotTemplate()
{
    Compressor c = new Compressor(1, 1);
    c.start();
}

RobotDrive chassis = new RobotDrive(1, 2);
Joystick Controller = new Joystick(1);
Relay myRelay = new Relay(2);
Solenoid solenoid = new Solenoid(1);

public void autonomous() {
    chassis.setSafetyEnabled (false);
    //drive forward halfspeed
    chassis.drive(-0.5, 0.0);
    //drive 1 second
    Timer.delay(5.0);
    //spin right
    chassis.drive(0.0, 0.5);
    //spin 2 seconds
    Timer.delay(5.0);
    //spin left
    chassis.drive(0.0, -0.5);
    //spin for 2 seconds
    Timer.delay(5.0);
    //Drive in reverse
    chassis.drive(0.5, 0.0);
    //drive 1 second
    Timer.delay(5.0);
    //stop
    chassis.drive(0.0, 0.0);
    }

public void operatorControl() {
    chassis.setSafetyEnabled(true);
    
    while (isOperatorControl() && isEnabled()){
        chassis.arcadeDrive(Controller);
        if (Controller.getRawAxis(3) > .1) {
            myRelay.set(Relay.Value.kForward);
        }
      else {
           myRelay.set(Relay.Value.kOff);
       }
        if(Controller.getRawAxis(1) > .1) {
           solenoid.set(true);
       }
      else {
           solenoid.set(false);
        }
        Timer.delay(0.01);
    }
    
}

}
