package ctci.solutions.aritra;

/*
 * Question: You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1s you could create.
 * EXAMPLE
 * Input: 1775 or 11011101111
 * Output: 8
 * 
 * Solution:
 * To reduce the space usage, note that we don't need to hang on to the length of each sequence the entire time. We only need it long enough to compare each 1s sequence
 * to the immediately preceding 1s sequence.
 * 
 * Therefore, we can just walk through the integer doing this, tracking the current 1s sequence length and the previous 1s sequence length. When we see a zero, update previousLength
 * 1)If the next bit is a 1, previousLength should be set to currentLength
 * 2)If the next bit is a 0, then we cant merge these sequences together. So the previous length is set to 0
 * 
 * Update maxLength as we go
 */

public class FlipBitToWin {
	
	public static void main(String[] args) {
		System.out.println("Longest sequence in 11011101111 = " + new FlipBitToWin().flipBit(1775));
	}
	
	int flipBit(int a){
		//If all 1s, this is already the longest sequence
		if(~a == 0) return 32;
		
		int currentLength = 0;
		int previousLength = 0;
		int maxLength = 1; //We can always have a sequence of at least one 1
		
		while(a != 0){
			if((a & 1) == 1){ //Current bit is 1
				currentLength++;
			}else if((a & 1) == 0){ //Current bit is 0
				//Update to 0 (if nextBit is 0) or currentLength (if next bit is 1)
				previousLength = (a & 2) == 0 ? 0 : currentLength;
				currentLength = 0;
			}
			maxLength = Math.max(previousLength + currentLength + 1, maxLength);
			a >>>= 1;
		}
		
		return maxLength;
	}
}
