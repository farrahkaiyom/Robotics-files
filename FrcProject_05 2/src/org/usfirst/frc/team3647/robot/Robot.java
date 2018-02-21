package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot 
{	
	Autonomous auto;
	Encoders enc;
	DifferentialDrive yes = new DifferentialDrive(Motors.leftMotor, Motors.rightMotor);
	//This function is run whenever the robot starts. This function is used for any initialization of code
	@Override
	public void robotInit() 
	{
		auto = new Autonomous();
		enc = new Encoders();
		Encoders.resetEncoders();
	}

	 //This function runs once, right before autonomous period starts. 
	@Override
	public void autonomousInit() 
	{
		Encoders.resetEncoders();
		auto.currentState=0;
	}

	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() 
	{
		enc.setEncoderValues();
		auto.runAuto(enc.leftEncoderValue, enc.rightEncoderValue);
	}

	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		enc.setEncoderValues();
		Joysticks.updateMainController();
		yes.arcadeDrive(Joysticks.leftJoySticky, Joysticks.rightJoyStickx, false);
		enc.testEncoders();
		if(Joysticks.buttonA)
		{
			Encoders.resetEncoders();
		}
		//don't need this function for this exercise
	}

	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() 
	{
		
	}
}

