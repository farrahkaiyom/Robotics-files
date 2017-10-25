package exercises;

/*
Lesson _02
In this exercise, you will use if statements, relational and logical operators to complete this exercise.
In this exercise you will use 6 booleans to determine if the robot is Moving forward, backward, right or left
For example:
if(rightMotorMovingForward && leftMotorMovingForward)
{
	movingStatus = "Robot is moving forward";
}
if(rightMotorMovingForward && leftMotorMovingBackward)
{
	movingStatus = "Robot is spinning towards the right";
}
Good Luck Completing the exercise!

Clue:
Only one right side motor and left side motor boolean can be set to true. So for example: If rightMotorMovingForward = true; rightMotorMovingBackward and rightMotorNotMoving should be set to false.
This is because if the rightMotor is moving forward, then the rightMotor can't be moving backward or not moving.
*/

/*
 * @author Surya
 */

public class ExerciseTwo 
{
	public static void main(String[] args) 
	{
		 boolean rightMotorMovingForward, leftMotorMovingForward, rightMotorMovingBackward, leftMotorMovingBackward, rightMotorNotMoving, leftMotorNotMoving;
		 String movingStatus;
		 rightMotorMovingForward = false; 
		 leftMotorMovingForward = false; 
		 rightMotorMovingBackward = true;
		 leftMotorMovingBackward = false;
		 rightMotorNotMoving = false; 
		 leftMotorNotMoving = true;
		 if(rightMotorMovingForward && leftMotorMovingForward)
		 {
		 	movingStatus = "Robot is moving forward";
		 }
		 else if(rightMotorMovingForward && leftMotorMovingBackward)
		 {
		 	movingStatus = "Robot is spinning towards the right";
		 }
		 else if(rightMotorMovingForward && leftMotorNotMoving)
		 {
			 movingStatus = "Robot is spinning right";
		 }
		 else if(rightMotorMovingBackward && leftMotorMovingForward)
		 {
		 	movingStatus = "Robot is static";
		 }
		 else if(rightMotorMovingBackward && leftMotorMovingBackward)
		 {
		 	movingStatus = "Robot is moving backwards";
		 }
		 else if(rightMotorMovingBackward && leftMotorNotMoving)
		 {
			 movingStatus = "Robot is spinning right";
		 }
		 else if(rightMotorNotMoving && leftMotorMovingForward)
		 {
		 	movingStatus = "Robot is spinning right";
		 }
		 else if(rightMotorNotMoving && leftMotorMovingBackward)
		 {
		 	movingStatus = "Robot is spinning backwards";
		 }
		 else if(rightMotorNotMoving && leftMotorNotMoving)
		 {
			 movingStatus = "Robot is not moving";
		 }
		 else
		 {
			 movingStatus = "none";
		 }
		 System.out.println(movingStatus);
	}

}
