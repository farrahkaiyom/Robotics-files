package team3647robotPackage;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot 
{	
	//This function is run whenever the robot starts. This function is used for any initialization of code
	Joysticks joysticksObject;
	double rightSpeed = 0.3;
	double leftSpeed = 0.3;
	@Override
	public void robotInit() 
	{
		joysticksObject = new Joysticks(); 
		joysticksObject.updateMainController();
	}

	 //This function runs once, right before autonomous period starts. 
	@Override
	public void autonomousInit() 
	{

	}

	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() 
	{

	}

	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		double scalingFactor = 0.0005;
		double turnDegrees;
		
		if(getDegrees() > 90 && getDegrees() < 180)
		{
			turnDegrees = getDegrees() - 90; 
			rightSpeed = -(getMagnitude() + scalingFactor*turnDegrees);
			leftSpeed = getMagnitude() + scalingFactor*turnDegrees;
		}
		else
		{
			turnDegrees = getDegrees() - 270; 
			rightSpeed = getMagnitude() + scalingFactor*turnDegrees;
			leftSpeed = -(getMagnitude() + scalingFactor*turnDegrees);
		}
		joysticksObject.updateMainController();
		if(getMagnitude() == 0)
		{
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);
		}
		else 
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed); 
		}
	}

	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() 
	{
		
	}
	
	//This function returns the angle of the vector of the right Joystick values
	//You may need to edit this function depending upon what type of controller you are using
	public static double getDegrees()
	{
		if(Joysticks.rightJoySticky > 0  && Joysticks.rightJoyStickx > 0) //1st quadrant
		{
			return ( 270 +Math.toDegrees(Math.atan(Joysticks.rightJoyStickx/Joysticks.rightJoySticky)));
		}
		if(Joysticks.rightJoySticky > 0  && Joysticks.rightJoyStickx < 0)
		{
			return 180- Math.toDegrees(Math.atan(Joysticks.rightJoySticky/Joysticks.rightJoyStickx));
		}
		if(Joysticks.rightJoySticky < 0  && Joysticks.rightJoyStickx < 0)
		{
			return 180- Math.toDegrees(Math.atan(Joysticks.rightJoySticky/Joysticks.rightJoyStickx));
		}
		if(Joysticks.rightJoySticky < 0  && Joysticks.rightJoyStickx > 0)
		{
			return -Math.toDegrees(Math.atan(Joysticks.rightJoySticky/Joysticks.rightJoyStickx));
		}
		if(Joysticks.rightJoySticky == 0  && Joysticks.rightJoyStickx < 0)
		{
			return 180;
		}
		if(Joysticks.rightJoySticky == 0  && Joysticks.rightJoyStickx < 0)
		{
			return 0;
		}
		if(Joysticks.rightJoySticky > 0  && Joysticks.rightJoyStickx == 0)
		{
			return 270;
		}
		if(Joysticks.rightJoySticky < 0  && Joysticks.rightJoyStickx == 0)
		{
			return 90;
		}
		else
		{
			return 0;
		}
	}
	
	//This function returns the magnitude of the vector of the right Joystick
	public static double getMagnitude()
	{
		return Math.sqrt(Math.pow(Joysticks.rightJoySticky, 2) + Math.pow(Joysticks.rightJoyStickx, 2));
	}
	
	public void testFunctions()
	{
		double magnitude, angle;
		magnitude =  getMagnitude();
		angle = getDegrees();
		
		System.out.println("Magnitude: " + magnitude + "; " + "Angle: " + angle + ";");
	}
}

