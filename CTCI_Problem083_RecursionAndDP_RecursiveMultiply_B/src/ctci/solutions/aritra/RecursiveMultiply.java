package ctci.solutions.aritra;

/*
 * Question: Write a recursive function to multiply two positive integers without using the * operator (or / operator). You can use addition, subtraction, and bit shifting,
 * but you should minimize the number of those operations.
 * 
 * Solution: This solution is very tricky. Go through the dry run for both the even and odd cases to understand how the approach works.
 * We can think about multiplying 8x7 as doing 8+8+8+8+8+8+8 (or adding 7 eight times). We can also think about it as the number of squares in an 8x7 grid.
 * We can count half the squares and then double it (by adding this count to itself). To count half the squares, we repeat the same process.
 * Of course this doubling only works if the number is in fact even.When it's not even we need to do the counting/ summing from scratch
 * 
 * minProduct(7,8) -> gets 24 from below, and as small is 7 returns 24 + minProduct(7 - 7 >> 1, bigger), that is 24 + minProduct(4,8), that is 24 + 32 = 56
 * 					minProduct(4,8) returns 16 + 16 as small 4 is even
 * 					to
 * 					minProduct(2,8) returns 8 + 8 as small 2 is even
 * 					to
 * 					minProduct(1,8) returns 8 as base case is reached
 * to
 * 
 * minProduct(3,8) -> gets 8 from below, as small is 3 returns 8 + minProduct(3 - 3 >> 1, bigger), that is 8 + minProduct(2,8), that is 8 + 16 = 24
 * 					minProduct(2,8) return 8 + 8 which is returned from below which is 16
 * 					to
 * 					minProduct(1,8) returns 8 as base case is reached
 * 
 * to
 * 
 * minProduct(1,8) //Base Case, returns bigger
 * 
 * In the above case, we see we are making the same method call multiple times as for example minProduct(2,8) is called 2 times in the above explanation process. What we will
 * try to achieve is to cache these results and use it in our computations, which will make the algorithm a bit more efficient
 */

public class RecursiveMultiply {
	int minProduct(int a, int b){
		int bigger = a < b? b : a;
		int smaller = a < b? a : b;
		
		int[] memo = new int[smaller + 1];
		return minProduct(smaller,bigger,memo);
	}
	
	int minProduct(int smaller, int bigger, int[] memo){
		if(smaller == 0){ //0 x bigger = 0
			return 0;
		}else if(smaller == 1){ //1 x bigger = bigger
			return bigger;
		}else if(memo[smaller] > 0){
			return memo[smaller];
		}
		
		//Compute half. If uneven, compute other half. If even, double it
		int s = smaller >> 1; //Divide by 2
		int side1 = minProduct(s,bigger,memo); //Compute half
		int side2 = side1;
		if(smaller % 2 == 1){
			side2 = minProduct(smaller - s, bigger,memo);
		}
		//Sum and cache
		memo[smaller] = side1 + side2;
		return memo[smaller];
	}
	
	public static void main(String[] args) {
		System.out.println("Product of 8 and 7 = " + new RecursiveMultiply().minProduct(7, 8));
		System.out.println("Product of 6 and 6 = " + new RecursiveMultiply().minProduct(6, 6));
	}
}
