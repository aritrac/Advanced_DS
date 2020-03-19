package ctci.solutions.aritra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*
 * Question: Given an input file with four billion non-negative integers, provide an algorithm to generate an integer that is not contained in the file.
 * Assume you have 10 MB of memory available for this task.
 * 
 * Solution: It's possible to find a missing integer with two passes of the data set. We can divide up the integers into blocks of some size (we'll discuss how to decide on a size later).
 * Let's just assume that we divide up the integers into blocks of 1000. So, block 0 represents the numbers 0 through 999, block 1 represents numbers 1000 - 1999 and so on.
 * 
 * Since a;; the values are distinct, we know how many values we should find in each block. So, we search through the file and count how many values are between 0 and 999,
 * how many are between 1000 and 1999 and so on. If we count only 999 values in a particular range, then we know that a missing int must be in that range.
 * 
 * In the second pass, we'll actually look for which number in that range is missing. We use the bit vector approach from the first part of this problem. We can ignore any number
 * outside of this specific range.
 * 
 * The question, now , is what is the appropriate block size? Let's define some variables as follows:
 * Let rangeSize be the size of the ranges that each block in the first pass represents.
 * Let arraySize represents the number of blocks in the first pass. Note that arraySize = 2^31 / rangeSize since there are 2^31 non-negative integers
 * 
 * We need to select a value for rangeSize such that the memory from the first pass (the array) and the second pass (bit vector) fit.
 * 
 * First Pass: The Array
 * The array in the first pass can fit in 10 megabytes, or roughly 2^23 bytes of memory. Since each element in the array is an int and an int is 4 bytes, we can hold
 * an array of at most about 2^21 elements. So, we can deduce the following:
 * 
 * arraySize = 2^31 / rangeSize <= 2^21
 * rangeSize >= 2^31 / 2^21
 * rangeSize >= 2^10
 * 
 * Second Pass: The Bit Vector
 * We need to have enough space to store rangeSize bits. Since we can fit 2^23 bytes in memory, we can fit 2^26 bits in memory. Therefore, we can conclude the following.
 * 
 * 2^11 <= rangeSize <= 2^26
 */

public class MissingInt {
	public static void main(String[] args) throws FileNotFoundException{
		MissingInt mi = new MissingInt();
		System.out.println("The missing number in the file is " + mi.findOpenNumber("src/numbers.txt"));
	}
	
	int findOpenNumber(String fileName) throws FileNotFoundException{
		int rangeSize = (1 << 20); //2^20 bits (2^17 bytes)
		
		//Get count of number of values within each block
		int[] blocks = getCountPerBlock(fileName, rangeSize);
		
		//Find a block with a missing value.
		int blockIndex = findBlockWithMissing(blocks, rangeSize);
		if(blockIndex < 0)
			return -1;
		
		//Create bit vector for items within this range
		byte[] bitVector = getBitVectorForRange(fileName, blockIndex, rangeSize);
		
		//Find a zero in the bit vector
		int offset = findZero(bitVector);
		if(offset < 0)
			return -1;
		
		//Compute missing value
		return blockIndex * rangeSize + offset;
	}
	
	//Get count of items within each range
	int[] getCountPerBlock(String filename, int rangeSize) throws FileNotFoundException{
		int arraySize = Integer.MAX_VALUE / rangeSize + 1;
		int[] blocks = new int[arraySize];
		
		Scanner in = new Scanner(new FileReader(filename));
		while(in.hasNextInt()){
			int value = in.nextInt();
			blocks[value / rangeSize]++;
		}
		in.close();
		return blocks;
	}
	
	//Find a block whose count is low.
	int findBlockWithMissing(int[] blocks, int rangeSize){
		for(int i = 0; i < blocks.length; i++){
			if(blocks[i] < rangeSize){
				return i;
			}
		}
		return -1;
	}
	
	//Create a bit vector for the values within a specific range
	byte[] getBitVectorForRange(String filename, int blockIndex, int rangeSize) throws FileNotFoundException{
		int startRange = blockIndex * rangeSize;
		int endRange = startRange + rangeSize;
		byte[] bitVector = new byte[rangeSize / Byte.SIZE];
		
		Scanner in = new Scanner(new FileReader(filename));
		while(in.hasNextInt()){
			int value = in.nextInt();
			//If the number is inside the block that's missing numbers, we record it
			if(startRange <= value && value < endRange){
				int offSet = value - startRange;
				int mask = (1 << (offSet % Byte.SIZE));
				bitVector[offSet/Byte.SIZE] |= mask;
			}
		}
		in.close();
		return bitVector;
	}
	
	//Find bit index that is 0 within byte
	int findZero(byte b){
		for(int i = 0; i < Byte.SIZE; i++){
			int mask = 1 << i;
			if((b & mask) == 0){
				return i;
			}
		}
		return -1;
	}
	
	//Find a zero within the bit vector and return the index
	int findZero(byte[] bitVector){
		for(int i = 0; i < bitVector.length; i++){
			if(bitVector[i] != ~0){ //If not all 1s
				int bitIndex = findZero(bitVector[i]);
				return i * Byte.SIZE + bitIndex;
			}
		}
		return -1;
	}
}
