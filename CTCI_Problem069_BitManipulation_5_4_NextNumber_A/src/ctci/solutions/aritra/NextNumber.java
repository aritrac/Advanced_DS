package ctci.solutions.aritra;

/*
 * Question: Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary representation.
 * 
 * Solution: 
 * *****************************************************
 * GETTING THE NEXT HIGHER NUMBER
 * Getting the bigger number follows the following approach
 * We want to make this number bigger but not too big. We also need to keep the same number of ones.
 * 
 * Observation: Given a number n and two bit locations i and j, suppose we flip bit i from a 1 to a 0 and bit j from a 0 to a 1.
 * If i > j, then n will have decreased. If i < j, then n will have increased.
 * 
 * We know the following
 * 1)If we flip a zero to a one, we must flip a one to a zero
 * 2)When we do that, the number will be bigger if and only if the zero to one bit was to the left of the one-to-zero bit.
 * 3)We want to make the number bigger, but not unnecessarily bigger. Therefore, we need to flip the rightmost zero which has ones on the right of it.
 * 
 * To put this in a different way, we are flipping the rightmost non-trailing zero. That is, using the above example, the trailing zeros
 * are in the 0th and 1st spot. The rightmost non-trailing zero is at bit 7 as shown
 * 
 *  1	1	0	1	1	0	1	1	1	1	1	1	0	0 <- Bit values 
 *  13 12  11  10   9   8   7   6   5   4   3   2   1   0 <- Bit positions
 *  
 *  Step1: Flip rightmost non-trailing zero
 *  Step2: Clear bits to the right of p. From before, c0 = 2. c1 = 5. p = 7
 *  Step3: Add in c1 - 1 ones from the right
 *  
 *  To clear bits to the right of the first non-trailing zero , we need to create a mask that is a sequence of ones, followed by p zeros. We can do this as follows.
 *  a = 1 << p; //all zeros, except for a 1 at position p
 *  b = a - 1;  //all zeros, followed by p ones.
 *  mask = ~b;  //all ones, followed by p zeros
 *  n = n & mask; //clear rightmost p bits
 *  
 *  To insert n ones on the right, we do the following
 *  a = 1 << (n) //0s with a 1 at position n
 *  b = a - 1    //0s with 1s a position 0 through c1 - 1
 *  n = n | b    //inserts 1s at positions 0 through c1 - 1
 *  
 *  The code for getting the next bigger number is given below
 *  
 *  ************************************************************
 *  GETTING THE NEXT LOWER NUMBER
 *  
 *  To implement getPrev, we follow a very similar approach
 *  1. Compute c0 and c1. Note that c1 is the number of trailing ones, and c0 is the size of the block of zeros immediately to the left of the trailing ones
 *  2. Flip the rightmost non-trailing one to a zero. This will be at position p = c1 + c0
 *  3. Clear all bits to the right of bit p
 *  4. Insert c1 + 1 ones immediately to the right of position p.
 *  
 *  Note that Step 2 sets bits p to a zero and step 3 sets bit 0 through p-1 to a zero. We can merge these steps
 *  
 *  1	0	0	1	1	1	1	0	0	0	0	0	1	1 <- Bit values
 *  13  12  11  10  9   8   7   6   5   4   3   2   1   0 <- Bit positions
 *  Step1: Initial Number. p = 7. c1 = 2. c0 = 5.
 *  Step2 & 3: Clear bits 0 through p.
 *  
 *  We can do this as follows:
 *  int a = 1 << (c1 + 1) // Sequence of 1s
 *  int b = a - 1         //Sequence of 1s followed by p + 1 zeros
 *  n &= b;               //Clears bits 0 through p
 *  
 *  Step 4: Insert c1 + 1 ones immediately to the right of position p. Note that since p = c1 + c0, the (c1 + 1) ones will be followed by (c0 - 1) zeros
 *  We can do this as follows:
 *  int a = 1 << (c1 + 1) //0s with 1 at position (c1 + 1)
 *  int b = a - 1         //0s followed by c1 + 1 ones
 *  int c = b << (c0 - 1) //c1 + 1 ones followed y c0 - 1 zeroes
 *  
 *  The code to implement this is below
 *  
 */

public class NextNumber {
	
	public static void main(String[] args) {
		NextNumber n = new NextNumber();
		System.out.println("Next higher number than 13948 with the same number of 1s = " + n.getNext(13948));
		System.out.println("Next lower number than 13948 with the same number of 1s = " + n.getPrev(13948));
	}
	
	int getNext(int n){
		int c = n;
		int c0 = 0;
		int c1 = 0;
		while(((c & 1) == 0) && (c != 0)){
			c0++;
			c >>= 1;
		}
		
		while((c & 1) == 1){
			c1++;
			c >>= 1;
		}
		
		//Error: If n == 11..1100...00, then there is no bigger number with the same number of 1s.
		
		if(c0 + c1 == 31 || c0 + c1 == 0){
			return -1;
		}
		
		int p = c0 + c1; //position of rightmost non-trailing zero
		
		n |= (1 << p); //flip non-trailing rightmost zero
		n &= ~((1 << p) - 1); //Clear all bits to the right of p
		n |= (1 << (c1 - 1)) - 1; //Insert (c1 - 1) ones on the right
		return n;
	}
	
	int getPrev(int n){
		int temp = n;
		int c0 = 0;
		int c1 = 0;
		while((temp & 1) == 1){
			c1++;
			temp >>= 1;
		}
		
		if(temp == 0)
			return -1;
		
		while(((temp & 1) == 0) && (temp != 0)){
			c0++;
			temp >>= 1;
		}
		
		int p = c0 + c1; //position of rightmost non-trailing one
		n &= ((~0) << (p + 1)); //clears from bit p onwards
		
		int mask = (1 << (c1 + 1)) - 1; //sequence of (c1 + 1) ones
		n |= mask << (c0 - 1);
		
		return n;
	}
}
