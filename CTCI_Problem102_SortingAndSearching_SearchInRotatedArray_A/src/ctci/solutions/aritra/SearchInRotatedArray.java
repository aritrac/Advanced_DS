package ctci.solutions.aritra;

/*
 * Question: Given a sorted array of n integers that has been rotated an unknown number of times, write code to find an element in the array. You may assume
 * that the array was originally sorted in increasing order.
 * 
 * Solution: This is an implementation of binary search. In classic binary search, we compare x with the midpoint to figure out if x belongs on the left or the right side.
 * The complication here is that the array is rotated and may have an inflection point. Consider for example, the following two arrays
 * 
 *  Array1 : {10,15,20,0,5}
 *  Array2 : {50,5,20,30,40}
 *  
 *  Note that both arrays have a mid point of 20, but 5 appears on left side of one and on the right side of the other. Therefore comparing x with the midpoint is insufficient.
 *  
 *  However if we look a bit deeper, we can see that one half of the array must be ordered normally(in increasing order). We can therefore look at the normally ordered half to determine 
 *  whether we should search the left or right half.
 *  
 *  For example, if we are searching for 5 in Array1, we can look at the left element(10) and middle element(20). Since 10 < 20, the left half must be ordered normally.
 *  And since 5 is not between those, we know that we must search the right half.
 *  
 *  In Array2, we can see that since 50 > 20, the right half must be ordered normally. We turn to the middle(20) and right (40) element to check if 5 would fall between them.
 *  The value 5 would not; therefore we search the left half.
 *  
 *  The tricky condition is if the left and middle element are identical, as in the example array {2,2,2,3,4,2}. In this case, we can check if the rightmost element is different.
 *  If it is, we can search just the right side. Otherwise, we have no choice but to search both halves.
 */

public class SearchInRotatedArray {
	
	public static void main(String[] args) {
		SearchInRotatedArray srch = new SearchInRotatedArray();
		int[] arr1 = {10,15,20,0,5};
		int[] arr2 = {50,5,20,30,40};
		
		System.out.println("location of 5 in arr1 = " + srch.search(arr1, 0, 4, 5));
		System.out.println("location of 30 in arr2 = " + srch.search(arr2, 0, 4, 30));
		
	}
	
	int search(int[] a, int left, int right, int x){
		int mid = (left + right) / 2;
		if(x == a[mid]){ //Found element
			return mid;
		}
		if(right < left){
			return -1;
		}
		
		/*
		 * Either the left or right half must be normally ordered. Find out which side is normally ordered, then use the normally ordered half to figure out which
		 * side to search to find x.
		 */
		
		if(a[left] < a[mid]){ //Left is normally ordered
			if(x >= a[left] && x < a[mid]){
				return search(a, left, mid - 1, x); //Search left
			}else{
				return search(a, mid + 1, right, x); //Search right
			}
		}else if(a[mid] < a[left]){//Right is normally ordered
			if(x > a[mid] && x <= a[right]){
				return search(a, mid + 1, right, x); //Search right
			}else{
				return search(a,left, mid - 1, x); //Search left
			}
		}else if(a[left] == a[mid]){ //Left half or right half is all repeats
			if(a[mid] != a[right]){ //If right is different, search it
				return search(a, mid + 1, right, x); //search right
			}else{ //we have to search both halves
				int result = search(a,left,mid - 1, x); //Search left
				if(result == -1){
					return search(a,mid + 1, right,x); //Search right
				}else{
					return result;
				}
			}
		}
		return -1;
	}
}
