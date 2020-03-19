package ctci.solutions.aritra;

/*
 * Question: Write an algorithm which computes the number of trailing zeros in n factorial
 * 
 * Solution: We can make the previous approach a little bit more efficient by directly counting the factors of 5. Using this approach, we would first
 * count the number of multiples of 5 between 1 and n (which is n / 5), then the number of multiples of 25 (n/25), then 125, and so on
 * To count how many multiples of m are in n, we can just divide n by m. The subsequent squares and cubes of 5 add extra 1 and 2 fives to the result, which is taken
 * care of in the subsequent iterations.
 * For example if we have 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25..125
 * For 1st iteration on dividing just by 5 we get numbers which has one 1 factor of 5 such as 5,10,15,20. On dividing 25 by 5, we get only 1 count of 5, but there is another 5 as a factor of 25
 * .To count that missing 5, we again divide all the numbers by 5*5, hence the missing 5 from 25 will be counted this time, when we reach 125, there are 3 factors of 5, so on
 * dividing by 5, we get 1, on dividing by 25 we get 2nd and on dividing by 125 we get the 3rd factor, so like this we count all the factor of 5 in the entire range
 */

public class FactorialZeros {
	int countFactZeros(int num){
		int count = 0;
		if(num < 0){
			return -1;
		}
		for(int i = 5; num / i > 0; i *= 5){
			count += num / i;
		}
		return count;
	}
	
	public static void main(String[] args) {
		FactorialZeros fz = new FactorialZeros();
		System.out.println("The number of trailing zeros in !52 = " + fz.countFactZeros(52));
		System.out.println("The number of trailing zeros in !19 = " + fz.countFactZeros(19));
	}
}
