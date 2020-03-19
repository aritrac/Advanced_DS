package ctci.solutions.aritra;

import java.util.Random;

/*
 * Question: Implement a method rand7() using rand5(). That is, given a method that generates a random number between 0 and 4(inclusive), write a method that generates a random number between
 * 0 and 6(inclusive)
 * 
 * Solution: We will do this in a non deterministic number of calls to rand5. 
 * We will generate a value set between 0 to 24 using the following formula:
 * 5 * rand5() + rand5() which will generate each number in the range exactly once as shown below
 * 
 * rand5()      rand5()      5 * rand5() + rand5()			(5 * rand5() + rand5()) % 7
 * 0			0			 0											0
 * 0			1			 1											1
 * 0			2			 2											2
 * 0			3			 3											3
 * 0			4			 4											4
 * 1			0			 5											5
 * 1			1			 6											6
 * 1			2			 7											0
 * 1			3			 8											1
 * 1			4			 9											2
 * 2			0			 10											3
 * 2			1			 11											4
 * 2			2			 12											5
 * 2			3			 13											6
 * 2			4			 14											0
 * 3			0			 15											1
 * 3			1			 16											2
 * 3			2			 17											3
 * 3			3			 18											4
 * 3			4			 19											5
 * 4			0			 20											6
 * 4			1			 21											0
 * 4			2			 22											1
 * 4			3			 23											2
 * 4			4			 24											3
 * 
 * Number of repeatitions of outcomes
 * 0 -> 4
 * 1 -> 4
 * 2 -> 4
 * 3 -> 4
 * 4 -> 3
 * 5 -> 3
 * 6 -> 3
 * 
 * So we can see 0,1,2,3 is occurring 4 times each due to inclusion of 21,22,23 and 24. If we remove this values and make the range 0 to 20 (inclusive) i.e. 21 outcomes, each outcome will
 * occur exactly 3 times which means each outcomes probability is 3/21 = 1/7 which is what we require in this case.
 * The code below does exactly this.
 */

public class Randomizer {
	Random rnd = new Random();
	
	int rand7(){
		while(true){
			int num = 5 * rand5() + rand5();
			if(num < 21){
				return num % 7;
			}
		}
	}
	
	int rand5(){
		return rnd.nextInt(5);
	}
	
	public static void main(String[] args) {
		Randomizer r = new Randomizer();
		for(int i = 0; i < 30; i++){
			System.out.println(r.rand7());
		}
	}
}
