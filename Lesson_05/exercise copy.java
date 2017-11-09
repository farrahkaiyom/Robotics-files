package returnFunctions;

/*
Lesson _05
In this exercise, you will use return functions to calculate the slope between 2 points and the distance between 2 points

If the variable var is equal to 1 then calculate the distance between 2 points
Else If variable var is equal to 2 then calculate the slope between 2 points
Else var does not have an appropriate value.

Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */

public class exercise
{
	public static void main(String[]args)
	{
		int var = 2;
		double x1 = 1;
		double y1 = 1;
		double x2 = 12;
		double y2 = 6;
		if(var == 1)
		{
			System.out.println("The distancee between two points is " + calcDist(x1, y1, x2, y2) + ".");
		}
		else if(var == 2)
		{
			System.out.println("The slope between two points is " + calcSlope(x1, y1, x2, y2) + ".");
		}
	}
	
	static double calcDist(double x1, double y1, double x2, double y2)//make sure you enter parameters
	{
		return Math.pow((Math.pow((y2 - y1), 2)) + (Math.pow((x2 - x1), 2)), .5);
	}
	
	static double calcSlope(double x1, double y1, double x2, double y2)//make sure you enter parameters
	{
		return ((y2 - y1) / (x2 - x1));
	}
}
