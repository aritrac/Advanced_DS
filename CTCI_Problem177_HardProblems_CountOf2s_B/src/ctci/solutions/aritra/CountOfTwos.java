package ctci.solutions.aritra;

/*
 * Question: Write a method to count the number of 2s between 0 and n
 * 
 * Solution: We know that roughly one tenth of the time, the last digit will be a 2 since it happens once in any sequence of ten numbers. In fact, any digit is a roughly
 * 2 one tenth of the time.
 * 
 * We say roughly because there are boundary conditions. For example, between 1 and 1000, the 10s digit is a 2 exactly 1/10th of the time. However, between 1 and 37, the 10's digit
 * is a 2 much more than 1/10 th of the time.
 * 
 * We can work out what exactly the ratio is by looking at the 3 cases individually. digit < 2, digit == 2 and digit > 2
 * 
 * Case digit < 2
 * Consider the value x = 61523 and d = 3, and observe that x[d] = 1 (i.e. the dth digit of x is 1). There are 2s at the third digit in the ranges 2000 - 2999, 12000 - 12999, 22000 - 22999,
 * 32000 - 32999, 42000 - 42999, 52000 - 52999. We will not yet have hit the range 62000 - 62999, so there are 6000 2s total in the 3rd digit. This is the same amount as
 * if we were just counting all the 2s in the 3rd digit between 1 and 60000
 * 
 * In other words, we can round down to the nearest 10^(d+1), and then divide by 10, to compute the number of twos in the dth digit
 * 
 *if x[d] < 2: count2sInRangeAtDigit(x,d) = 
 *		let y = round down to nearest 10^(d+1)
 *		return y / 10
 *
 * Case digit > 2
 * Now lets look at the case where dth digit of x is greater than 2(x[d] > 2). We can apply the exact same logic(2000 - 2999, 12000 - 12999, 22000 - 22999,
 * 32000 - 32999, 42000 - 42999, 52000 - 52999, 62000 - 62999 which equals 7000 or roundUp % 10) to see that there are the same number of 2s in the 3rd digit in the range
 * 0 - 63525 as there as in the range 0 - 70000. So, rather than rounding down, we round up
 * 
 * if x[d] > 2: count2sInRangeAtDigit(x, d) = 
 * 		let y = round up to nearest 10^(d+1)
 * 		return y / 10;
 * 
 * Case digit == 2
 * The final case may be the trickiest, but it follows from the earlier logic. Consider x = 62523 and d = 3. We know that there are the same ranges of 2s from before
 * (that is, the ranges 2000 - 2999, 12000 - 12999, 22000 - 22999,...,52000 - 52999). How many appear in the 3rd digit in the final, partial range from 62000 - 62523? Well,
 * that should be pretty easy. It's just 524 (62000,62001,...,62523)
 * 
 * if x[d] = 2: count2sInRangeAtDigit(x,d) = 
 * 		let y = round down to nearest 10^(d+1)
 * 		let z = right side of x (i.e., x % 10^d)
 * 		return y / 10 + z + 1
 * Now all you need is to iterate through each digit in the number. Implementing this code is reasonably straightforward
 */

public class CountOfTwos {
	int count2sInRangeAtDigit(int number, int d){
		int powerOf10 = (int)Math.pow(10, d);
		int nextPowerOf10 = powerOf10 * 10;
		int right = number % powerOf10;
		
		int roundDown = number - number % nextPowerOf10;
		int roundUp = roundDown + nextPowerOf10;
		
		int digit = (number / powerOf10) % 10;
		if(digit < 2){//If the digit in spot digit is
			return roundDown/10;
		}else if(digit == 2){
			return roundDown / 10 + right + 1;
		}else{
			return roundUp / 10;
		}
	}
	
	int count2sInRange(int number){
		int count = 0;
		int len = String.valueOf(number).length();
		for(int digit = 0; digit < len; digit++){
			count += count2sInRangeAtDigit(number, digit);
		}
		return count;
	}
	
	public static void main(String[] args) {
		CountOfTwos cot = new CountOfTwos();
		System.out.println("Number of 2s in 61523 = " + cot.count2sInRange(61523));
		System.out.println("Number of 2s in 62523 = " + cot.count2sInRange(62523));
		System.out.println("Number of 2s in 63523 = " + cot.count2sInRange(63523));
	}
}
