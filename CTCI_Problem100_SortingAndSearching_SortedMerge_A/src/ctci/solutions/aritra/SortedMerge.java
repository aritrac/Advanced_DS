package ctci.solutions.aritra;

/*
 * Question: You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted order.
 * 
 * Solution: Since we know that A has enough buffer at the end, we won't need to allocate additional space. Or logic should involve simply comparing elements
 * of A and B and inserting them in order, until we've exhausted all elements in A and in B.
 * 
 * The only issue with this is that if we insert an element into the front of A, then we'll have to shift the existing elements backwards to make room for it.
 * It's better to insert elements into the back of the array, where there's empty space. 
 * 
 * The code below does just that. It works from the back of A and B, moving the largest elements to the back of A.
 */

public class SortedMerge {
	public static void main(String[] args) {
		//total 13 elements
		int[] a = new int[13];
		a[0] = 1;
		a[1] = 4;
		a[2] = 5;
		a[3] = 10;
		a[4] = 15;
		a[5] = 16;
		
		int[] b = {2,6,8,9,11,18,21};
		
		SortedMerge sm = new SortedMerge();
		sm.merge(a, b, 6, 7);
		
		//Printing out the sorted elements
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + "->");
		}
	}
	
	void merge(int[] a, int[] b, int lastA, int lastB){
		int indexA = lastA - 1; //Index of last element in array a
		int indexB = lastB - 1; //Index of last element in array b
		int indexMerged = lastB + lastA - 1; //end of merged array
		
		//Merge a and b, starting from the last element in each
		while(indexB >= 0){
			//end of a is > than end of b
			if(indexA >= 0 && a[indexA] > b[indexB]){
				a[indexMerged] = a[indexA]; //copy element
				indexA--;
			}else{
				a[indexMerged] = b[indexB]; //copy element
				indexB--;
			}
			indexMerged--; //move indices
		}
	}
}
