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
	int step;
	public static boolean withinRange;
	double aimedRatio;
	double currentRatio;
	double sum;
	double requiredLeftDist;
	double requiredRightDist;	
	int currentState;
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
		double distance = 4000;
		double average = (lEnc + rEnc) / 2;
		switch(currentState)
		{
			case 0:
				if(average<distance)
				{
					driveForward(lEnc,rEnc);
					System.out.println(average); 
				}
				else
				{
					currentState = 1;
				}
				break;
			case 1:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 2;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 2:
				lEnc = Math.abs(lEnc);
				rEnc = Math.abs(rEnc);
				average = (lEnc + rEnc) / 2;
				System.out.println("check is we are in step 2");
				System.out.println(average); 
				if(average<distance)
				{
					driveBackward(lEnc, rEnc);
					System.out.println("check is we are in driving backward");
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
					System.out.println("check is RIP");
					Motors.stop();
					Encoders.resetEncoders();
			break;
		}
		
	}
	
	public void rightAuto(double lEnc, double rEnc)
	{
		double distance = 2000;
		double average = (lEnc + rEnc) / 2;
		switch(currentState)
		{
			case 0:
				if(average<distance)
				{
					driveForward(lEnc, rEnc);
				}
				else
				{
					currentState = 1;
				}
				break;
			case 1:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 2;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 2:
				if(lEnc<distance)
				{
					goForwardRight(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 4;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 4:
				lEnc = Math.abs(lEnc);
				rEnc = Math.abs(rEnc);
				if(lEnc<distance)
				{
					goBackwardRight(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
				}
				else
				{
					currentState = 5;
				}
				break;
			case 5:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 6;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 6:
				lEnc = Math.abs(lEnc);
				rEnc = Math.abs(rEnc);
				average = (lEnc + rEnc) / 2;  
				if(average<distance)
				{
					driveBackward(lEnc, rEnc);
				}
				else
				{
					currentState = 7;
				}
			case 7:
				Motors.stop();
				Encoders.resetEncoders();
				break;
		}
	}	
	
	public void leftAuto(double lEnc, double rEnc)
	{
		double distance = 2000;
		double average = (lEnc + rEnc) / 2; 
		switch(currentState)
		{
			case 0:
				if(average<distance)
				{
					driveForward(lEnc, rEnc);
				}
				else
				{
					currentState = 1;
				}
				break;
			case 1:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 2;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 2:
				if(lEnc<distance)
				{
					goForwardLeft(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 4;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 4:
				lEnc = Math.abs(lEnc);
				rEnc = Math.abs(rEnc);
				if(lEnc<distance)
				{
					goBackwardLeft(lEnc, rEnc, requiredLeftDist, requiredRightDist, leftSpeed, rightSpeed, adjustment);
				}
				else
				{
					currentState = 5;
				}
				break;
			case 5:
				Motors.stop();
				if(lEnc < 20 && rEnc < 20)
				{
				currentState = 6;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 6:
				lEnc = Math.abs(lEnc);
				rEnc = Math.abs(rEnc);
				average = (lEnc + rEnc) / 2;  
				if(average<distance)
				{
					driveBackward(lEnc, rEnc);
				}
				else
				{
					currentState = 7;
				}
			case 7:
				Motors.stop();
				Encoders.resetEncoders();
				break;
		}
		/*if(step == 1)
		{ 
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
			
		}*/
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
		else if(Math.abs(lEnc-rEnc) < 75)
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
		else
		{
			if(lEnc>rEnc)
			{
				Motors.leftMotor.set((leftSpeed - (4*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (4*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (4*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (4*adjustment)));
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
		else if(Math.abs(lEnc-rEnc) < 75)
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
		else
		{
			if(Math.abs(lEnc)>Math.abs(rEnc))
			{
				Motors.leftMotor.set(-(leftSpeed - (4*adjustment)));
				Motors.rightMotor.set((rightSpeed + (4*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (4*adjustment)));
				Motors.rightMotor.set((rightSpeed - (4*adjustment)));
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
			else if(currentRatio > 1.25 && currentRatio < 1.15)
			{
				Motors.leftMotor.set((leftSpeed - (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio > 1.15)
			{
				Motors.leftMotor.set((leftSpeed - (4*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (4*adjustment)));
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
			else if(currentRatio < .75 && currentRatio > .68)
			{
				Motors.leftMotor.set((leftSpeed + (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (3*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (4*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (4*adjustment)));
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
			else if(currentRatio > 1.25 && currentRatio < 1.15)
			{
				Motors.leftMotor.set((leftSpeed - (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio > 1.15)
			{
				Motors.leftMotor.set((leftSpeed - (4*adjustment)));
				Motors.rightMotor.set(-(rightSpeed + (4*adjustment)));
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
			else if(currentRatio < .75 && currentRatio > .68)
			{
				Motors.leftMotor.set((leftSpeed + (3*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (3*adjustment)));
			}
			else
			{
				Motors.leftMotor.set((leftSpeed + (4*adjustment)));
				Motors.rightMotor.set(-(rightSpeed - (4*adjustment)));
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
			else if(currentRatio > 1.25 && currentRatio < 1.15)
			{
				Motors.leftMotor.set(-(leftSpeed - (3*adjustment)));
				Motors.rightMotor.set((rightSpeed + (3*adjustment)));
			}
			else if(currentRatio > 1.15)
			{
				Motors.leftMotor.set(-(leftSpeed - (4*adjustment)));
				Motors.rightMotor.set((rightSpeed + (4*adjustment)));
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
			else if(currentRatio < .75 && currentRatio > .68)
			{
				Motors.leftMotor.set(-(leftSpeed + (3*adjustment)));
				Motors.rightMotor.set((rightSpeed - (3*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (4*adjustment)));
				Motors.rightMotor.set((rightSpeed - (4*adjustment)));
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
			else if(currentRatio > 1.25 && currentRatio < 1.15)
			{
				Motors.leftMotor.set(-(leftSpeed - (3*adjustment)));
				Motors.rightMotor.set((rightSpeed + (3*adjustment)));
			}
			else if(currentRatio > 1.15)
			{
				Motors.leftMotor.set(-(leftSpeed - (4*adjustment)));
				Motors.rightMotor.set((rightSpeed + (4*adjustment)));
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
			else if(currentRatio < .75 && currentRatio > .68)
			{
				Motors.leftMotor.set(-(leftSpeed + (3*adjustment)));
				Motors.rightMotor.set((rightSpeed - (3*adjustment)));
			}
			else
			{
				Motors.leftMotor.set(-(leftSpeed + (4*adjustment)));
				Motors.rightMotor.set((rightSpeed - (4*adjustment)));
			}
		}
		
	}

}

