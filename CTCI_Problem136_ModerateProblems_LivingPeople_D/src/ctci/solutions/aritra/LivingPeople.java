package ctci.solutions.aritra;

/*
 * Question: Given a list of people with their year of birth and deaths, implement a method to compute the year with the most number of people alive. You may assume
 * that all people were born between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should be included in that year's count.
 * For example, Person(birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.
 * 
 * Solution: We can optimize this further. To optimize this, we need to get rid of the sorting step. We're back to dealing with unsorted values:
 * birth: 12 20 10 01 10 23 13 90 83 75
 * death: 15 90 98 72 98 82 98 98 99 94
 * 
 *  Earlier, we had logic that said that a birth is just adding a person and a death is just subtracting a person. Therefore, let's present the data using the logic
 *  01: +1	10: +1	10: +1	12: +1	13: +1
 *  15: -1	20: +1	23: +1	72: -1	75: +1
 *  82: -1	83: +1	90: +1	90: -1	94: -1
 *  98: -1	98: -1	98: -1  98: -1  99: -1
 *  
 *  We can create an array of the years, where the value at array[year] indicates how the population changed in that year. To create this array, we walk through the list of people and
 *  increment when they're born and decrement when they die.
 *  Once we have this array, we can walk through each of the years, tracking the current population as we go(adding the value at array[year] each time)
 *  This logic is reasonably good, but we should think about it more. Does it really work?
 *  One edge case we should consider is when a person dies the same year that yhey're born. The increment and decrement operations will cancel out to give
 *  0 population change. According to the wording of the problem, this person should be counted as living in that year.
 *  
 *  In fact, the "bug" in our algorithm is broader than that. This same issue applies to all people. People who die in 1908 shouldn't be removed from the population count until 1909.
 *  
 *  There's a simple fix: instead of decrementing array[deathYear], we should decrement array[deathYear + 1]
 */

public class LivingPeople {
	
	int maxAliveYear(Person[] people, int min, int max){
		//Build population delta array
		int[] populationDeltas = getPopulationDeltas(people, min, max);
		int maxAliveYear = getMaxAliveYear(populationDeltas);
		return maxAliveYear + min;
	}
	
	//Add birth and death years to deltas array.
	int[] getPopulationDeltas(Person[] people, int min, int max){
		int[] populationDeltas = new int[max - min + 2];
		for(Person person : people){
			int birth = person.birth - min;
			populationDeltas[birth]++;
			
			int death = person.death - min;
			populationDeltas[death + 1]--;
		}
		return populationDeltas;
	}
	
	//Compute running sums and return index with max
	int getMaxAliveYear(int[] deltas){
		int maxAliveYear = 0;
		int maxAlive = 0;
		int currentlyAlive = 0;
		for(int year = 0; year < deltas.length; year++){
			currentlyAlive += deltas[year];
			if(currentlyAlive > maxAlive){
				maxAliveYear = year;
				maxAlive = currentlyAlive;
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
