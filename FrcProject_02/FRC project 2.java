package team3647robotPackage;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot 
{	
	//The speed of the robot while running the program

	double leftEncoderValue;
	double rightEncoderValue;
	double rightSpeed =  0.3;
	double leftSpeed = 0.3;
	double adjustment = .005;
	double speed = 0.3;
	double distance = 1400;

	//This function is run whenever the robot starts. This function is used for any initialization of code
	Encoders encodersObject;
	@Override
	public void robotInit() 
	{
		encodersObject = new Encoders();
	}
	 //This function runs once, right before autonomous period starts.
	
	@Override
	
	
	public void autonomousInit() 
	{
		encodersObject.resetEncoders();

	}
	
	public void goStraight(double distance)
	{
		leftEncoderValue = encodersObject.getLeftEncoder();
		rightEncoderValue = encodersObject.getRightEncoder();
		
		if(Math.abs(leftEncoderValue-rightEncoderValue)<5)
		{
			
		}
		else {
		if(leftEncoderValue>rightEncoderValue)
		{
			leftSpeed -= adjustment;
		}
		else
		{
			rightSpeed -= adjustment;
		}
		}
	}

	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() 
	{
		 leftEncoderValue = encodersObject.getLeftEncoder();
		 rightEncoderValue = encodersObject.getRightEncoder();
		
		if(rightEncoderValue>-360)
		{
			Motors.rightMotor.set(.4); //this is positive because remember the right motor is facing in the negative direction (Read FRC Project_01)
		}
		else
		{
			Motors.rightMotor.set(0);
		}
		if(leftEncoderValue<360)
		{
			Motors.leftMotor.set(.4);
		}
		else
		{
			Motors.leftMotor.set(0);
		}
	}
	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		
	}

	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() 
	{
		
	}
}

