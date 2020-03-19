package ctci.solution.aritra;

/*
 * Question: Write a method to count the number of 2s between 0 and n
 * 
 * Solution: Our first approach to this problem can be and probably should be - a brute force solution.
 */

public class CountOfTwos {
	//Counts the number of '2' digits between 0 and n
	int numberOf2sInRange(int n){
		int count = 0;
		for(int i = 2; i <= n; i++){	//Might as well start at 2
			count += numberOf2s(i);
		}
		return count;
	}
	
	//Counts the number of '2' digits in a single number
	int numberOf2s(int n){
		int count = 0;
		while(n > 0){
			if(n % 10 == 2){
				count++;
			}
			n = n / 10;
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println("Number of 2s between 0 and 70 = " + new CountOfTwos().numberOf2sInRange(70));
	}
}
