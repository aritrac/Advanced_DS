package ctci.solutions.aritra;

import java.util.Random;

/*
 * Question: Design an algorithm to find the smallest K numbers in an array
 * 
 * Solution: Select Rank is a well known algorithm in computer science to find the ith smallest (or largest) element in the array in linear time.
 * If the elements are unique, you can find the ith smallest element in expected O(n) time. The basic algorithm operates like this.
 * 1. Pick a random element in the array and use it as a pivot. Partition elements around the pivot, keeping track of the number of elements on the left side of the partition.
 * 2. If there are exactly i elements on the left, then you just return the biggest element on the left.
 * 3. If the left side is bigger than i, repeat the algorithm on just the left part of the array
 * 4. If the left side is smaller than i, repeat the algorithm on the right, but look for element with rank i - leftSize.
 * 
 * Once you have found the ith smallest element, you know that all elements smaller than this will be to the left of this. (Since you have partitioned the array accordingly.)
 * You just now need to return the first i elements.
 */

public class SmallestK {
	int[] smallestK(int[] array, int k){
		if(k <= 0 || k > array.length){
			throw new IllegalArgumentException();
		}
		
		int threshold = rank(array, k-1);
		int[] smallest = new int[k];
		int count = 0;
		for(int a : array){
			if(a <= threshold){
				smallest[count] = a;
				count++;
			}
		}
		return smallest;
	}
	
	//Get element with rank
	int rank(int[] array, int rank){
		return rank(array, 0, array.length - 1, rank);
	}
	
	//Get element with rank between left and right indices
	int rank(int[] array, int left, int right, int rank){
		int pivot = array[randomIntInRange(left,right)];
		int leftEnd = partition(array, left, right, pivot);
		int leftSize = leftEnd - left + 1;
		if(rank == leftSize - 1){
			return max(array, left, leftEnd);
		}else if(rank < leftSize){
			return rank(array, left, leftEnd, rank);
		}else{
			return rank(array, leftEnd + 1, right, rank - leftSize);
		}
	}
	
	//Partition array around pivot such that all elements <= pivot come before all elements > pivot
	int partition(int[] array, int left, int right, int pivot){
		while(left <= right){
			if(array[left] > pivot){
				//Left is bigger than pivot. Swap it to the right side, where we know it should be.
				swap(array, left, right);
				right--;
			}else if(array[right] <= pivot){
				//Right is smaller than the pivot. Swap it to the left side, where we know it should be.
				swap(array, left, right);
				left++;
			}else{
				//Left and right are in correct places. Reduce both sides
				left++;
				right--;
			}
		}
		return left - 1;
	}
	
	//Get random integer within range, inclusive
	int randomIntInRange(int min, int max){
		Random rand = new Random();
		return rand.nextInt(max + 1 - min) + min;
	}
	
	//Swap values at index i and j
	void swap(int[] array, int i, int j){
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
	
	//Get largest element in array between left and right indices
	int max(int[] array, int left, int right){
		int max = Integer.MIN_VALUE;
		for(int i = left; i <= right; i++){
			max = Math.max(array[i], max);
		}
		return max;
	}
}
