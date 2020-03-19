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
 * 
 * If the elements are not unique, the major change that needs to be made is to the partition function. When we partition the array around a pivot element, we now partition
 * it into three chunks: less than pivot, equal to pivot and greater than pivot
 */

public class SmallestK {
	int[] smallestK(int[] array, int k) {
		if (k <= 0 || k > array.length) {
			throw new IllegalArgumentException();
		}

		// Get item with rank k - 1
		int threshold = rank(array, k - 1);

		// Copy elements smaller than the threshold element.
		int[] smallest = new int[k];
		int count = 0;
		for (int a : array) {
			if (a < threshold) {
				smallest[count] = a;
				count++;
			}
		}

		// If there's still room left, this must be for the elements equal to
		// the threshold element. Copy those in
		while (count < k) {
			smallest[count] = threshold;
			count++;
		}
		return smallest;
	}

	// Find value with rank k in array
	int rank(int[] array, int k) {
		if (k >= array.length) {
			throw new IllegalArgumentException();
		}
		return rank(array, k, 0, array.length - 1);
	}

	// Get random integer within range, inclusive
	int randomIntInRange(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max + 1 - min) + min;
	}

	// Find a value with rank k in sub array between start and end
	int rank(int[] array, int k, int start, int end) {
		// Partition array around an arbitrary pivot.
		int pivot = array[randomIntInRange(start, end)];
		PartitionResult partition = partition(array, start, end, pivot);
		int leftSize = partition.leftSize;
		int middleSize = partition.middleSize;

		// Search position of array
		if (k < leftSize) { // Rank k is on the left half
			return rank(array, k, start, start + leftSize - 1);
		} else if (k < leftSize + middleSize) { // Rank k is in middle
			return pivot; // middle is all pivot values
		} else { // Rank k is on right
			return rank(array, k - leftSize - middleSize, start + leftSize
					+ middleSize, end);
		}
	}

	// Partition result into < pivot, equal to pivot, and > then pivot
	PartitionResult partition(int[] array, int start, int end, int pivot) {
		int left = start; // Stays at right edge of left side
		int right = end; // Stays at left edge of right side
		int middle = start; // Stays at right edge of middle
		while (middle <= right) {
			if (array[middle] < pivot) {
				// Middle is smaller than the pivot. Left is either smaller or
				// equal to the pivot. Either way, swap them. Then middle and
				// left should move by one.
				swap(array, middle, left);
				middle++;
				left++;
			} else if (array[middle] > pivot) {
				// Middle is bigger than the pivot. Right could have any value.
				// Swap them, then we know that the new right is bigger than the
				// pivot. Move right by one.
				swap(array, middle, right);
				right--;
			} else if (array[middle] == pivot) {
				// Middle is equal to the pivot. Move by one.
				middle++;
			}
		}
		return new PartitionResult(left - start, right - left + 1);
	}

	// Swap values at index i and j
	void swap(int[] array, int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
}

class PartitionResult {
	int leftSize;
	int middleSize;

	public PartitionResult(int left, int middle) {
		this.leftSize = left;
		this.middleSize = middle;
	}
}
