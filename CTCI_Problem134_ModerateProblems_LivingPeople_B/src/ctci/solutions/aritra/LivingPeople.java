package ctci.solutions.aritra;

/*
 * Question: Given a list of people with their year of birth and deaths, implement a method to compute the year with the most number of people alive. You may assume
 * that all people were born between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should be included in that year's count.
 * For example, Person(birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.
 * 
 * Solution: Slightly Better Brute Force: A slightly better way of doing this is to create an array where we track the number of people born in each year. Then, we iterate
 * through the list of people and increment the array for each year they are alive.
 */

public class LivingPeople {
	//Start here
	int maxAliveYear(Person[] people, int min, int max){
		int[] years = createYearMap(people, min, max);
		int best = getMaxIndex(years);
		return best + min;
	}
	
	//Add each person's years to a year map
	int[] createYearMap(Person[] people, int min, int max){
		int[] years = new int[max - min + 1];
		for(Person person : people){
			incrementRange(years, person.birth - min, person.death - min);
		}
		return years;
	}
	
	//Increment array for each value between left and right
	void incrementRange(int[] values, int left, int right){
		for(int i = left; i <= right; i++){
			values[i]++;
		}
	}
	
	//Get index of largest element in array
	int getMaxIndex(int[] values){
		int max = 0;
		for(int i = 1; i < values.length; i++){
			if(values[i] > values[max]){
				max = i;
			}
		}
		return max;
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
