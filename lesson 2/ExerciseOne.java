package exercises;

/*
Lesson _02
In this exercise, you will use if, else if, and else statements to determine dedication of robotics members depending on build season hours
For example:
300+ hours: dedication level = "dedication on steroids"
200+ hours: dedication level = "very dedicated"
150+ hours: dedication level = "really dedicated"
120+ hours: dedication level = "dedicated"
90+ hours: dedication level = "average dedication"
50+ hours: dedication level = "un-dedicated"
else: dedication level = "Eamon"

Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */

public class ExerciseOne 
{
	public static void main(String[] args) 
	{
		 int buildSeasonHours = 200;
		 String dedicationLevel;
		 if(buildSeasonHours >= 300)
		 {
			 dedicationLevel = "dedication on steroids";
		 }
		 else if(buildSeasonHours >= 200)
		 {
			 dedicationLevel = "very dedicated";
		 }
		 else if(buildSeasonHours >= 150)
		 {
			 dedicationLevel = "really dedicated";
		 }
		 else if(buildSeasonHours >= 120)
		 {
			 dedicationLevel = "dedicated";
		 }
		 else if(buildSeasonHours >= 90)
		 {
			 dedicationLevel = "average dedication";
		 }
		 else if(buildSeasonHours >= 50)
		 {
			 dedicationLevel = "un-dedicated";
		 }
		 else
		 {
			 dedicationLevel = "Eamon";
		 }
		 System.out.println(dedicationLevel);
	}
}
