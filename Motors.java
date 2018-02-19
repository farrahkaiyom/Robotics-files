package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Spark;

public class Motors {
	public static Spark leftMotor = new Spark(2);
	public static Spark rightMotor = new Spark(1);
	
	public static void stop()
	{
		leftMotor.set(0);
		rightMotor.set(0);
	}
}
