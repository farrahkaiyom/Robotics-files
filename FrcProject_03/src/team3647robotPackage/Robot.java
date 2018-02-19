package team3647robotPackage;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot 
{	
	double leftJoySticky;
	double rightSpeed = 0.3;
	double leftSpeed = 0.3;
	double adjustment = 0.05;
	double distance = 1400;
	double leftEncoderValue;
	double rightEncoderValue;
	
	//This function is run whenever the robot starts. This function is used for any initialization of code
	Encoders encodersObject;
	Joysticks joy;
	@Override
	public void robotInit() 
	{
		encodersObject = new Encoders();
		joy = new Joysticks();
	}

	 //This function runs once, right before autonomous period starts. 
	@Override
	public void autonomousInit() 
	{
		encodersObject.resetEncoders();
	}

	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() 
	{
		 leftEncoderValue = encodersObject.getLeftEncoder(); 
		 rightEncoderValue = encodersObject.getRightEncoder(); 
	}

	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		leftSpeed = leftJoySticky; 
		rightSpeed=leftJoySticky;
		double leftEncoderValue = encodersObject.getLeftEncoder(); 
		double rightEncoderValue = encodersObject.getRightEncoder(); 
		double leftspeed = Joysticks.leftJoySticky;
		double rightspeed  = Joysticks.leftJoySticky;
		joy.updateMainController();
		if(Joysticks.leftJoySticky > 0)
		{
			if(Math.abs(leftEncoderValue-rightEncoderValue) < 6)
			{
				Motors.leftMotor.set(leftspeed);
				Motors.rightMotor.set(-rightspeed);
			}
			else
			{
				if(leftEncoderValue>rightEncoderValue)
				{
					Motors.leftMotor.set((leftspeed - adjustment));
					Motors.rightMotor.set(-(rightspeed + adjustment));
				}
				else
				{
					Motors.leftMotor.set((leftspeed + adjustment));
					Motors.rightMotor.set(-(rightspeed - adjustment));
				}
			}
			//move forward
		}
		else if(Joysticks.leftJoySticky < 0)
		{
			if(Math.abs(leftEncoderValue-rightEncoderValue) < 6)
			{
				Motors.leftMotor.set(-leftspeed);
				Motors.rightMotor.set(rightspeed);
			}
			else
			{
				if(leftEncoderValue>rightEncoderValue)
				{
					Motors.leftMotor.set(-(leftspeed - adjustment));
					Motors.rightMotor.set((rightspeed + adjustment));
				}
				else
				{
					Motors.leftMotor.set(-(leftspeed + adjustment));
					Motors.rightMotor.set((rightspeed - adjustment));
				}
			}
		}
		else
		{
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);
		}
	
//		//Reference code
//		double speed =1;
//		double adjustment =.1;
//		Motors.leftMotor.set(speed - adjustment); // assuming that left is greater than right
//		Motors.rightMotor.set(adjustment - speed);
		
	}

	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() 
	{
		
	}
}

