package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogChannel;

public class RobotTemplate extends SimpleRobot{
Compressor c = new Compressor(1, 1);
public RobotTemplate()
{
    c.start();
}

RobotDrive chassis = new RobotDrive(1, 2);
Joystick Controller = new Joystick(1);
Relay myRelay = new Relay(2);    
Solenoid solenoid = new Solenoid(1);
AnalogChannel pressureSensor = new AnalogChannel(1);

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
    double counter = 0.0;
    chassis.setSafetyEnabled(true);
    
    c.start();
    
    while (isOperatorControl() && isEnabled()){
        chassis.arcadeDrive(Controller);
        if(Controller.getRawAxis(3) > .7) {
        	myRelay.set(Relay.Value.kForward);
        	Timer.delay(2.0);
        	solenoid.set(true);
                Timer.delay(1.0);
        }
        else {
           solenoid.set(false);
           myRelay.set(Relay.Value.kOff);
        }
        if(Controller.getRawButton(4)){
        c.stop();
    }
        }
    double volts = pressureSensor.getVoltage();
    SmartDashboard.putNumber("LowSidePressure", volts);
     Timer.delay(0.10);
    Timer.delay(0.01);
    }
protected void exectute() {
    SmartDashboard.putNumber("PSI:", pressureSensor.getVoltage());
}
}


