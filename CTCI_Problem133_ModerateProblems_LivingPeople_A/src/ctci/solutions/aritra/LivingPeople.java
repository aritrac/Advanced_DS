package ctci.solutions.aritra;

/*
 * Question: Given a list of people with their year of birth and deaths, implement a method to compute the year with the most number of people alive. You may assume
 * that all people were born between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should be included in that year's count.
 * For example, Person(birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.
 * 
 * Solution: Brute Force: The brute force algorithm falls directly out from the wording of the problem. We need to find the year with the most number of people alive. Therefore, we go through
 * each year and check how many people are alive in that year.
 */

public class LivingPeople {
	int maxAliveYear(Person[] people, int min, int max){
		int maxAlive = 0;
		int maxAliveYear = min;
		
		for(int year = min; year <= max; year++){
			int alive = 0;
			for(Person person : people){
				if(person.birth <= year && year <= person.death){
					alive++;
				}
			}
			if(alive > maxAlive){
				maxAlive = alive;
				maxAliveYear = year;
			}
		}
		return maxAliveYear;
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
