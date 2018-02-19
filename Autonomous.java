package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous 
{
	String autoSelected = "middleAuto";
	double distance;
	double leftSpeed = 0.4;
	double rightSpeed = 0.4;
	double adjustment = 0.05;
	double leftEncoderValue;
	double rightEncoderValue;
	double speed = .3;
	int step = 1;
	public static boolean withinRange;
	double aimedRatio;
	double currentRatio;
	double sum;
	double requiredLeftDist;
	double requiredRightDist;	
	
	Encoders encoderObject;

	
	public void runAuto(double lEnc, double rEnc)
	{
		if(autoSelected.equals("middleAuto"))
		{
			middleAuto(lEnc, rEnc);
		}
		else if(autoSelected.equals("rightAuto"))
		{
			rightAuto(lEnc, rEnc);
		}
		else if(autoSelected.equals("leftAuto"))
		{
			leftAuto(lEnc, rEnc);
		}
		else
		{
			Motors.stop();
		}
	}
	
	public void middleAuto(double lEnc, double rEnc)
	{
		double distance = 1400;
		if(step == 1)
		{
			double average = (lEnc + rEnc) / 2;
			if(average>=distance)
			{
				Motors.stop();
				Encoders.resetEncoders();
				Timer.delay(.4);
				step = 2;
			}
			else
			{
				driveForward(lEnc, rEnc);
			}
		}
		if(step == 2)
		{
			lEnc = Math.abs(lEnc);
			rEnc = Math.abs(rEnc);
			double average = (lEnc + rEnc) / 2;  
			if(average>=distance)
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 3;
			}
			else
			{
				driveBackward(lEnc, rEnc);
			}
		}
		
	}
	
	public void rightAuto(double lEnc, double rEnc)
	{
		double distance = 700;
		if(step == 1)
		{
			double average = (lEnc + rEnc) / 2;  
			if(average<distance)
			{
				driveForward(lEnc, rEnc);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 2;
			}
		}
		if(step ==2)
		{
			if(lEnc<distance)
			{
				goForwardRight(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 3;
			}
		}
		if(step == 3);
		{
			lEnc = Math.abs(lEnc);
			rEnc = Math.abs(rEnc);
			if(lEnc<distance)
			{
				goBackwardRight(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 4;
			}
		}
		if(step == 4)
		{
			double average = (lEnc + rEnc) / 2;  
			if(average<distance)
			{
				driveBackward(lEnc, rEnc);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
			}
			
		}
	}
	
	public void leftAuto(double lEnc, double rEnc)
	{
		double distance = 700;
		if(step == 1)
		{
			double average = (lEnc + rEnc) / 2;  
			if(average<distance)
			{
				driveForward(lEnc, rEnc);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 2;
			}
		}
		if(step ==2)
		{
			if(rEnc<distance)
			{
				goForwardLeft(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 3;
			}
		}
		if(step == 3);
		{
			lEnc = Math.abs(lEnc);
			rEnc = Math.abs(rEnc);
			if(rEnc<distance)
			{
				goBackwardLeft(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
				step = 4;
			}
		}
		if(step == 4)
		{
			double average = (lEnc + rEnc) / 2;  
			if(average<distance)
			{
				driveBackward(lEnc, rEnc);
			}
			else
			{
				Motors.stop();
				Encoders.resetEncoders();
			}
			
		}
	}
	
	public void driveForward(double lEnc, double rEnc)
	{
		if(Math.abs(lEnc-rEnc) < 10)
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed);
			//robot is moving straight
		}
		else if(Math.abs(lEnc-rEnc) < 25)
		{
			if(lEnc>rEnc)
			{
				Motors.leftMotor.set((leftSpeed - adjustment));
				Motors.rightMotor.set(-(rightSpeed + adjustment));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + adjustment));
				Motors.rightMotor.set(-(rightSpeed - adjustment));
			}
			//somewhat off
		}
		else if(Math.abs(lEnc-rEnc) < 50)
		{
			if(lEnc>rEnc)
			{
				Motors.leftMotor.set((leftSpeed - (2*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (2*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (2*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (2*adjustment)));
			}
			//off
		}
		else
		{
			if(lEnc>rEnc)
			{
				Motors.leftMotor.set((leftSpeed - (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (3*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (3*adjustment)));
			//bigoff
			}
		}
	}
	public void driveBackward(double lEnc, double rEnc)
	{
		if(Math.abs(lEnc-rEnc) < 10)
		{
			Motors.leftMotor.set(-leftSpeed);
			Motors.rightMotor.set(rightSpeed);
			//robot is moving straight
		}
		else if(Math.abs(lEnc-rEnc) < 25)
		{
			if(Math.abs(lEnc)>Math.abs(rEnc))
			{
				Motors.leftMotor.set(-(leftSpeed - adjustment));
				Motors.rightMotor.set((rightSpeed + adjustment));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + adjustment));
				Motors.rightMotor.set((rightSpeed - adjustment));
			}
			//somwhat off
		}
		else if(Math.abs(lEnc-rEnc) < 50)
		{
			if(Math.abs(lEnc)>Math.abs(rEnc))
			{
				Motors.leftMotor.set(-(leftSpeed - (2*adjustment)));
				Motors.rightMotor.set((rightSpeed + (2*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (2*adjustment)));
				Motors.rightMotor.set((rightSpeed - (2*adjustment)));
			}
			//off
		}
		else
		{
			if(Math.abs(lEnc)>Math.abs(rEnc))
			{
				Motors.leftMotor.set(-(leftSpeed - (3*adjustment)));
				Motors.rightMotor.set((rightSpeed + (3*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (3*adjustment)));
				Motors.rightMotor.set((rightSpeed - (3*adjustment)));
			//bigoff
			}
		}
	}
	
	public static void goForwardRight(double lEnc, double rEnc, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		double aimedRatio = ((requiredLeftDist)/(requiredRightDist));
		double currentRatio = (((lEnc/rEnc))/aimedRatio);
		double sum = (rEnc) + (lEnc);
		if(currentRatio >= .9 && currentRatio <= 1.1)
		{
			withinRange = true;
		}
		else
		{
			withinRange = false;
		}
		if(withinRange || sum < 360)
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				Motors.leftMotor.set((leftSpeed - adjustment));
				Motors.rightMotor.set(-(rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				Motors.leftMotor.set(-(leftSpeed - (2*adjustment)));
				Motors.rightMotor.set((rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				Motors.leftMotor.set((leftSpeed - (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				Motors.leftMotor.set((leftSpeed + adjustment));
				Motors.rightMotor.set(-(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				Motors.leftMotor.set((leftSpeed + (2*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (2*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (3*adjustment)));
			}
		}
		
	}
	
	public static void goForwardLeft(double lEnc, double rEnc, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		double aimedRatio = ((requiredLeftDist)/(requiredRightDist));
		double currentRatio = (((rEnc/lEnc))/aimedRatio);
		double sum = (rEnc) + (lEnc);
		if(currentRatio >= .9 && currentRatio <= 1.1)
		{
			withinRange = true;
		}
		else
		{
			withinRange = false;
		}
		if(withinRange || sum < 360)
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				Motors.leftMotor.set((leftSpeed - adjustment));
				Motors.rightMotor.set(-(rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				Motors.leftMotor.set((leftSpeed - (2*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				Motors.leftMotor.set((leftSpeed - (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				Motors.leftMotor.set((leftSpeed + adjustment));
				Motors.rightMotor.set(-(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				Motors.leftMotor.set((leftSpeed + (2*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (2*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (3*adjustment)));
			}
		}
		
	}
	public static void goBackwardRight(double lEnc, double rEnc, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		double aimedRatio = ((requiredLeftDist)/(requiredRightDist));
		double currentRatio = (((lEnc/rEnc))/aimedRatio);
		double sum = (rEnc) + (lEnc);
		if(currentRatio >= .9 && currentRatio <= 1.1)
		{
			withinRange = true;
		}
		else
		{
			withinRange = false;
		}
		if(withinRange || sum < 360)
		{
			Motors.leftMotor.set(-leftSpeed);
			Motors.rightMotor.set(rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				Motors.leftMotor.set(-(leftSpeed - adjustment));
				Motors.rightMotor.set((rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				Motors.leftMotor.set(-(leftSpeed - (2*adjustment)));
				Motors.rightMotor.set((rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				Motors.leftMotor.set(-(leftSpeed - (3*adjustment)));
				Motors.rightMotor.set((rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				Motors.leftMotor.set(-(leftSpeed + adjustment));
				Motors.rightMotor.set((rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				Motors.leftMotor.set(-(leftSpeed + (2*adjustment)));
				Motors.rightMotor.set((rightSpeed - (2*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (3*adjustment)));
				Motors.rightMotor.set((rightSpeed - (3*adjustment)));
			}
		}
		
	}
	public static void goBackwardLeft(double lEnc, double rEnc, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		double aimedRatio = ((requiredLeftDist)/(requiredRightDist));
		double currentRatio = (((rEnc/lEnc))/aimedRatio);
		double sum = (rEnc) + (lEnc);
		if(currentRatio >= .9 && currentRatio <= 1.1)
		{
			withinRange = true;
		}
		else
		{
			withinRange = false;
		}
		if(withinRange || sum < 360)
		{
			Motors.leftMotor.set(-leftSpeed);
			Motors.rightMotor.set(rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				Motors.leftMotor.set(-(leftSpeed - adjustment));
				Motors.rightMotor.set((rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				Motors.leftMotor.set((leftSpeed - (2*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				Motors.leftMotor.set(-(leftSpeed - (3*adjustment)));
				Motors.rightMotor.set((rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				Motors.leftMotor.set(-(leftSpeed + adjustment));
				Motors.rightMotor.set((rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				Motors.leftMotor.set(-(leftSpeed + (2*adjustment)));
				Motors.rightMotor.set((rightSpeed - (2*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (3*adjustment)));
				Motors.rightMotor.set((rightSpeed - (3*adjustment)));
			}
		}
		
	}

}

