package ctci.solutions.aritra;

/*
 * Question: Write an algorithm which computes the number of trailing zeros in n factorial
 * 
 * Solution: A trailing zero is created with multiples of 10 and multiples of 10 are created with pairs of 5 multiples and 2 multiples.
 * Therefore to count the number of zeros, we only need to count the pairs of multiples of 5 and 2. There will always be more multiples of 2 than 5,hence we will always get a 10 as number of 5s will be less than number of 2s, 
 * though, so simply counting
 * the number of multiples of 5 is sufficient.
 * One 'gotcha' here is 15 contributes a multiple of 5(and therefore one trailing zero), while 25 contributes two (because 25 = 5 * 5)
 * In this method we will iterate through all the numbers from 2 through n, counting the number of times that 5 goes into each number.
 */

public class FactorialZeros {
	//If the number is a 5 of five, return which power of 5. For example: 5 -> 1, 25 -> 2, etc. 
	int factorOf5(int i){
		int count = 0;
		while(i % 5 == 0){
			count++;
			i /= 5;
		}
		return count;
	}
	
	int countFactZeros(int num){
		int count = 0;
		for(int i = 2; i <= num; i++){
			count += factorOf5(i);
		}
		return count;
	}
	
	public static void main(String[] args) {
		FactorialZeros fz = new FactorialZeros();
		System.out.println("The number of trailing zeros in !52 = " + fz.countFactZeros(52));
		System.out.println("The number of trailing zeros in !19 = " + fz.countFactZeros(19));
	}
}
