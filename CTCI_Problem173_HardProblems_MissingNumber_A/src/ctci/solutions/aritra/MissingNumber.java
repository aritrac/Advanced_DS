package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: An array A contains all the integers from 0 to n, except for one number which is missing. In this problem, we cannot access an entire integer in A,
 * with a single operation. The elements of A are represented in binary, and the only operation we can use to access them is 'fetch the jth bit of A[i]', which takes
 * constant time. Write code to find the missing integer. Can this be done in O(n) time?
 * 
 * Solution:
 * Picture a list of binary numbers...
 * 00000
 * 00001
 * 00010
 * 00011 <- this 3 is missing from 0 to 13 where n = 14
 * 00100
 * 00101
 * 00110
 * 00111
 * 01000
 * 01001
 * 01010
 * 01011
 * 01100
 * 01101
 * 
 * Removing the number above creates an imbalance of 1s and 0s in the least significant bit, which we'll call LSB1. In a list of numbers from 0 to n, we would expect there
 * to be the same number of 0s and 1s (if n is odd), or an additional 0 if n is even. That is:
 * 
 * If n % 2 == 1 then count(0s) = count(1s)
 * if n % 2 == 0 then count(0s) = 1 + count(1s)
 * 
 * Note that this means count(0s) is always greater than or equal to count(1s)
 * 
 * When we remove a value v from the list, we'll know immediately if v is even or odd just by looking at the least significant bits of all the other values in the list.
 * 
 * 						n % 2 == 0								n % 2 == 1
 * 						count(0s) = 1 + count(1s)				count(0s) = count(1s)
 * 
 * v % 2 == 0			a 0 is removed.							a 0 is removed.
 * LSB1(v) = 0			count(0s) = count(1s)					count(0s) < count(1s)
 * 
 * v % 2 == 1			a 1 is removed.							a 1 is removed
 * LSB1(v) = 1			count(0s) > count(1s)					count(0s) > count(1s)
 * 
 * So if count(0s) <= count(1s), then v is even. If count(0s) > count(1s), then v is odd
 * 
 * We can now remove all the evens and focus on the odds, or remove all the odds and focus on the evens.
 * But, how do we figure out what the next bit in v is? If v were contained in our (now smaller) list, then we should expect to find the following (where count2 indicates the number
 * of 0s or 1s in the second least significant bit):
 * 
 * count2(0s) = count2(1) OR count2(0s) = 1 + count2(1s)
 * 
 * As in the earlier example, we can deduce the value of the second least significant bit (LSB2) of v
 * 
 * 						n % 2 == 0								n % 2 == 1
 * 						count2(0s) = 1 + count2(1s)				count2(0s) = count2(1s)
 * 
 * v % 2 == 0			a 0 is removed.							a 0 is removed.
 * LSB2(v) = 0			count2(0s) = count2(1s)					count2(0s) < count2(1s)
 * 
 * v % 2 == 0			a 1 is removed.							a 1 is removed
 * LSB2(v) = 1			count2(0s) > count2(1s)					count2(0s) > count2(1s)
 * 
 * Again we have the same conclusion:
 * 1)if count2(0s) <= count2(1s), then LSB2(v) = 0
 * 2)if count2(0s) > count2(1s), then LSB2(v) = 1
 * 
 * We can repeat this process for each bit. On each iteration, we count the number of 0s and 1s in bit i to check if LSB1(v) is 0 or 1.
 * Then we discard the numbers where LSBi(x) != LSBi(v). That is, if v is even, we discard the odd numbers, and so on.
 * 
 * By the end of this process, we will have computed all bits in v. In each successive iteration, we look at n, then n / 2, then n / 4 and so on bits. This results in a runtime of O(log n)
 */

public class MissingNumber {
	public static void main(String[] args) {
		ArrayList<BitInteger> list = initialize(14, 13);
		MissingNumber mn = new MissingNumber();
		int number = mn.findMissing(list);
		System.out.println("The missing integer in the above list is " + mn.reverseBitInteger(14,number)); //Reversing as the bit representation will be reverse while coming from recursion
																										   //for example 3 will be returned as 12 1100 -> 0011
	}
	
	int findMissing(ArrayList<BitInteger> array){
		//Start from the least significant bit and work our way up.
		return findMissing(array,0);
	}
	
	int findMissing(ArrayList<BitInteger> input, int column){
		if(column >= BitInteger.INTEGER_SIZE){//We're done
			return 0;
		}
		ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>(input.size()/2);
		ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size()/2);
		
		for(BitInteger t : input){
			if(t.fetch(column) == 0){
				zeroBits.add(t);
			}else{
				oneBits.add(t);
			}
		}
		if(zeroBits.size() <= oneBits.size()){ //Even number is missing
			int v = findMissing(zeroBits, column + 1);
			return (v << 1);
		}else{ //Odd number is missing
			int v = findMissing(oneBits, column + 1);
			return (v << 1) | 1;
		}
	}
	
	/* Create a random array of numbers from 0 to n, but skip 'missing' */
    public static ArrayList<BitInteger> initialize(int n, int missing) {
        BitInteger.INTEGER_SIZE = Integer.toBinaryString(n).length();
        ArrayList<BitInteger> array = new ArrayList<BitInteger>();
        
        for (int i = 1; i <= n; i++) {
        	array.add(new BitInteger(i == missing ? 0 : i));
        }
        return array;
    }
    
    public int reverseBitInteger(int n,int num){
    	BitInteger.INTEGER_SIZE = Integer.toBinaryString(n).length();
    	BitInteger bi = new BitInteger(num);
    	return bi.toReverseInt();
    }
}

class BitInteger {
	public static int INTEGER_SIZE;
	private boolean[] bits;
	public BitInteger() {
		bits = new boolean[INTEGER_SIZE];
	}
	/* Creates a number equal to given value. Takes time proportional 
	 * to INTEGER_SIZE. */
	public BitInteger(int value){
		//System.out.println("value = " + value);
		bits = new boolean[INTEGER_SIZE];
		for (int j = 0; j < INTEGER_SIZE; j++){
			if (((value >> j) & 1) == 1){ 
				bits[INTEGER_SIZE - 1 - j] = true;
			}else{ 
				bits[INTEGER_SIZE - 1 - j] = false;
			}
		}
	}
	
	/** Returns k-th most-significant bit. */ 
	public int fetch(int k){
		if (bits[k]) return 1;
		else return 0;
	}
	
	/** Sets k-th most-significant bit. */
	public void set(int k, int bitValue){
		if (bitValue == 0 ) bits[k] = false;
		else bits[k] = true;
	}
	
	/** Sets k-th most-significant bit. */
	public void set(int k, char bitValue){
		if (bitValue == '0' ) bits[k] = false;
		else bits[k] = true;
	}
	
	/** Sets k-th most-significant bit. */
	public void set(int k, boolean bitValue){
		bits[k] = bitValue;
	}	
	
	public void swapValues(BitInteger number) {
		for (int i = 0; i < INTEGER_SIZE; i++) {
			int temp = number.fetch(i);
			number.set(i, this.fetch(i));
			this.set(i, temp);
		}
	}
		
	public int toInt() {
		int number = 0;
		for (int j = 0; j < INTEGER_SIZE; j++){
			number = number | fetch(j);
			if (j != INTEGER_SIZE - 1) {
				number = number << 1;
			}
		}
		return number;
	}
	
	public int toReverseInt(){
		int number = 0;
		for (int j = INTEGER_SIZE - 1; j >= 0; j--){
			number = number | fetch(j);
			if (j != 0) {
				number = number << 1;
			}
		}
		return number;
	}
}
