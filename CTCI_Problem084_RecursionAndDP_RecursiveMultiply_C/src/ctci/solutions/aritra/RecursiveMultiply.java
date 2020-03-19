package ctci.solutions.aritra;

/*
 * Question: Write a recursive function to multiply two positive integers without using the * operator (or / operator). You can use addition, subtraction, and bit shifting,
 * but you should minimize the number of those operations.
 * 
 * Solution: In the previous two approaches, we might notice that a call to minProduct(30,35), then we'll just do minProduct(15,35) and double the result. However, if we do
 * minProduct(31,35), then we'll need to call minProduct(15,35) and minProduct(16,35).
 * This is unnecessary. Instead we can do:
 * 	minProduct(31,35) = 2 * minProduct(15,35) + 35;
 * After all, since 31 = 2 * 15 + 1, then 31 * 35 = 2 * 15 * 35 + 35
 * 
 * The logic in this final solution is that, on even numbers, we just divide smaller by 2 and double the result of the recursive call. On odd numbers, we do the same, but then we 
 * also add bigger to this result.
 */

public class RecursiveMultiply {
	int minProduct(int a, int b){
		int bigger = a < b? b : a;
		int smaller = a < b? a : b;
		return minProductHelper(smaller,bigger);
	}
	
	int minProductHelper(int smaller, int bigger){
		if(smaller == 0){ //0 x bigger = 0
			return 0;
		}else if(smaller == 1){ //1 x bigger = bigger
			return bigger;
		}
		
		//Compute half. If uneven, compute other half. If even, double it
		int s = smaller >> 1; //Divide by 2
		int halfProd = minProduct(s,bigger); //Compute half
		
		if(smaller % 2 == 0){
			return halfProd + halfProd;
		}else{
			return halfProd + halfProd + bigger;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Product of 8 and 7 = " + new RecursiveMultiply().minProduct(7, 8));
		System.out.println("Product of 6 and 6 = " + new RecursiveMultiply().minProduct(6, 6));
	}
}
