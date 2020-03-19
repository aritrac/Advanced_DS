package ctci.solutions.aritra;

/*
 *	Question: Write a function to determine the number of bits you would need to flip to convert integer A into integer B
 *  Example:
 *  Input: 29 (or 11101), 15 (or 01111)
 *  Output: 2
 *  
 *  Solution:
 *  Approach 1:
 *  ***********
 *  This seemingly complex problem is actually rather straightforward. To approach this, ask yourself how you would figure out which
 *  bits in two numbers are different. Simple: with an XOR
 *  Each 1 in the XOR represents a bit that is dirrerent between A and B. Therefore, to check the number of bits that are different
 *  between A and B, we simply need to count the number of bits in A^B that are 1
 *  
 *  
 *  Approach 2:
 *  ***********
 *  This code is good, but we can make it a bit better. Rather than simply shifting c repeatedly while checking the least significant
 *  bit, we can continuously flip the least significant bit and count how long it takes c to reach 0. The operation c = c & (c - 1)
 *  will clear the least significant 1 in c
 */

public class Conversion {
	int bitSwapRequired(int a, int b){
		int count = 0;
		for(int c = a ^ b; c != 0; c = c >> 1){
			count += c & 1;
		}
		return count;
	}
	
	int bitSwapRequiredEfficient(int a, int b){
		int count = 0;
		for(int c = a ^ b; c != 0; c = c & (c - 1)){
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		Conversion con = new Conversion();
		System.out.println("Conversion of 29 to 15 requires " + con.bitSwapRequired(29, 15) + " bit swaps");
		System.out.println("Conversion of 29 to 15 requires " + con.bitSwapRequiredEfficient(29, 15) + " bit swaps");
	}
}
