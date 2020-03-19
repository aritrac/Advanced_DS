package ctci.solutions.aritra;

/*
 * Question: Write a program to swap odd and even bits in an integer with as few instructions as possible
 * (e.g. bit 0 is swapped with bit 1, bit 2 and bit 3 are swapped, and so on)
 * 
 * Solution:
 * We can mask all odd bits with 10101010 in binary (which is 0xAA), then shfit them right by 1 to put them in even spots.
 * For the even bits , we do an equivalent operation. Finally, we merge these two values
 * This takes a total of 5 instructions. The code below implements this approach
 * 
 * Note: We use the logical right shift, instead of the arihmetic right shift. This is because we want the sign bit to be filled with 0.
 */

public class PairwiseSwap {
	int swapOddEvenBits(int x){
		return (((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1));
	}
	
	public static void main(String[] args) {
		PairwiseSwap pws = new PairwiseSwap();
		System.out.println("Swapping pairwise even and odd bit positions in 13698 to get " + pws.swapOddEvenBits(13698));
		System.out.println("Swapping pairwise even and odd bit positions in 14913 to get " + pws.swapOddEvenBits(14913));
	}
}
