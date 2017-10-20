/*
Lesson _01
In this exercise, we will use variables to print a sample ID card
A sample ID Card will look like this:
***************************************
Surya Madhan         Millennium Falcons
Programming                   Team 3647
Proficient in Java            2017-2018
***************************************
Good Luck Completing the exercise!
*/
/* ***************************************
Surya Madhan         Millennium Falcons
Programming                   Team 3647
Proficient in Java            2017-2018
***************************************
*/
/*
 * @author Surya
 */

public class ExerciseOne 
{

    public static void main(String[] args) 
	{
		String name, teamName, department, somethingToBragAbout, schoolYear;
		int teamNumber;
		name="Farrah";
		teamName="Milennium Falcons";
		department="Programming";
		somethingToBragAbout="nothing";
		schoolYear="2017-2018";
		teamNumber=3647;
		
		System.out.println("***************************************");
		System.out.println(name + "                " + teamName);
		System.out.println(department + "                   " + "Team " + teamNumber);
		System.out.println(somethingToBragAbout + "                       " + schoolYear);
		System.out.println("***************************************");
  	}
}