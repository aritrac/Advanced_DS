package ctci.solutions.aritra;

import java.math.BigInteger;

/*
 * Question: You are given an array with all the numbers from 1 to N appearing exactly once, except for one number that is missing. How can you find the missing number in O(N) time
 * and O(1) space? What if there were two numbers missing?
 * 
 * Solution: Lets work on finding the only missing number, then we will work on finding out the 2 missing numbers. Lets say we multiply 1 * 2 * 3 * ... * n. Now, imagine crossing off one number.
 * This will give us a different result than if we crossed off any other number. Now if we compare what our product is to what it would have been without a number removed,
 * can we find the missing number? Sure, we just divide full_product by actual_product. This will tell us which number was missing from actual_product.
 * But there is just one issue. This product is really really really big. If n is 20, the product will be somewhere around 2000000000000000000. We can still approach it this way,
 * but we'll need to use the BigInteger class.
 * 
 * There is also another way, instead of using the product, we can use the sum as well. Doing the sum has another benefit. There is already a closed form expression to compute the sum of numbers
 * between 1 and n.This is n(n + 1)/2. Here is how you can derive this formula:
 * You can pair up low and high values in the sequence of 1 + 2 + 3 + ... + n to get: (0, n) + (1, n-1) + (2, n-2), and so on. Each of those pairs has a sum of n and there are (n+1)/2 pairs.
 * But what if n is even, such that (n+1)/2 is not an integer? In this case, pair up low and high values to get n/2 pairs with sum n + 1. Either way, the math works out to be n(n+1)/2, but below
 * we are going to use the product approach.
 */

public class MissingTwoNumbers {
	int missingOne(int[] array){
		BigInteger fullProduct = productToN(array.length + 1);
		
		BigInteger actualProduct = new BigInteger("1");
		for(int i = 0; i < array.length; i++){
			BigInteger value = new BigInteger(array[i] + "");
			actualProduct = actualProduct.multiply(value);
		}
		
		BigInteger missingNumber = fullProduct.divide(actualProduct);
		return Integer.parseInt(missingNumber.toString());
	}
	
	BigInteger productToN(int n){
		BigInteger fullProduct = new BigInteger("1");
		for(int i = 2; i <= n; i++){
			fullProduct = fullProduct.multiply(new BigInteger(i + ""));
		}
		return fullProduct;
	}
}
