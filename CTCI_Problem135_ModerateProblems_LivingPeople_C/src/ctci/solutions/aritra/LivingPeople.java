package ctci.solutions.aritra;

import java.util.Arrays;

/*
 * Question: Given a list of people with their year of birth and deaths, implement a method to compute the year with the most number of people alive. You may assume
 * that all people were born between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should be included in that year's count.
 * For example, Person(birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.
 * 
 * Solution: For this solution, we will just write the last two digits of the year.
 * 
 * birth: 12 20 10 01 10 23 13 90 83 75
 * death: 15 90 98 72 98 82 98 98 99 94
 * 
 * It's worth noting that it doesn't really matter whether these years are matched up. Every birth adds a person and every death removes a person.
 * Since we don't actually need to match up the births and deaths, let's sort both. A sorted version of the years might help us solve the problem.
 * 
 * birth: 01 10 10 12 13 20 23 75 83 90
 * death: 15 72 82 90 94 98 98 98 98 99
 * 
 * We can try walking through the years.
 * At year 0, no one is alive.
 * At year 1, we see one birth
 * At year 2 through 9, nothing happens
 * Let's skip ahead until year 10, when we have two births. Now we have 2 people alive.
 * At year 15, one person dies. We are now down to two people alive
 * And so on
 * 
 * If we walk through the two arrays like this, we can track the number of people alive at each point.
 */

public class LivingPeople {
	int maxAliveYear(Person[] people, int min, int max){
		int[] births = getSortedYears(people, true);
		int[] deaths = getSortedYears(people, false);
		
		int birthIndex = 0;
		int deathIndex = 0;
		int currentlyAlive = 0;
		int maxAlive = 0;
		int maxAliveYear = min;
		
		//Walk through arrays
		while(birthIndex < births.length){
			if(births[birthIndex] <= deaths[deathIndex]){
				currentlyAlive++; //include birth
				if(currentlyAlive > maxAlive){
					maxAlive = currentlyAlive;
					maxAliveYear = births[birthIndex];
				}
				birthIndex++; //move birth index
			}else if(births[birthIndex] > deaths[deathIndex]){
				currentlyAlive--; //include death
				deathIndex++; //move death index
			}
		}
		return maxAliveYear;
	}
	
	//Copy birth years or death years (depending on the value of copyBirthYear into integer array, then sort array)
	int[] getSortedYears(Person[] people, boolean copyBirthYear){
		int[] years = new int[people.length];
		for(int i = 0; i < people.length; i++){
			years[i] = copyBirthYear? people[i].birth : people[i].death;
		}
		Arrays.sort(years);
		return years;
	}
}

class Person{
	public int birth;
	public int death;
	public Person(int birthYear, int deathYear){
		birth = birthYear;
		death = deathYear;
	}
}
