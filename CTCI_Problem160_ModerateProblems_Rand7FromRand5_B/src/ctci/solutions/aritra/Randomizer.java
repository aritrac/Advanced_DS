package ctci.solutions.aritra;

import java.util.Random;

/*
 * Question: Implement a method rand7() using rand5(). That is, given a method that generates a random number between 0 and 4(inclusive), write a method that generates a random number between
 * 0 and 6(inclusive)
 * 
 * Solution:As in the previous approach, we saw how we can generate equally likely numbers for rand7() with 1/7th probably. Now can we do the same with this new formula?
 * 2 * rand5() + rand5()? No, because the values wouldn't be equally distributed. For example, there would be three ways of getting a 6 (6 = 2 * 1 + 4) (6 = 2 * 2 + 2)
 * and (6 = 2 * 3 + 0), but only one way of getting a zero(0 = 2 * 0 + 0). The values in the range are not equally probable.
 * 
 * But there is a way that we can use 2 * rand5() and still get an identically distributed range, but it's much more complicated. See below:
 * 
 * Note: There is an infinite number of ranges we can use. The key is to make sure that the range is big enough that all values are equally likely.
 */

public class Randomizer {
	Random rnd = new Random();
	
	int rand7(){
		while(true){
			int r1 = 2 * rand5(); //equally distributed between 0 and 9
			int r2 = rand5(); //used later to generate a 0 and 1
			if(r2 != 4){	//r2 has extra even num-discard the extra
				int rand1 = r2 % 2;	//Generate 0 and 1
				int num = r1 + rand1; //will be in the range 0 to 9
				if(num < 7){
					return num;
				}
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
