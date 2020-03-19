package ctci.solutions.aritra;

/*
 * Question: Given a sorted array of strings that is interspersed with empty strings, write a method to find the location if a given string.
 * 
 * Example
 * Input: ball, {"at","","","","ball","","","car","","","dad","",""}
 * Output: 4
 * 
 * Solution: If it weren't for the empty strings, we could simply use binary search. We would compare the string to be found, str, with the
 * midpoint of the array. and go from there
 * With empty string interspersed, we can implement a simple modification of binary search. All we need to do is fix the comparison
 * against mid, in case mid is an empty string. We simply move mid to the closest non empty string.
 * 
 * The recursive code below to solve this problem can easily be modified to be iterative.
 * 
 * 
 */

public class SparseSearch {
	public static void main(String[] args) {
		String[] strings = {"at","","","","ball","","","car","","","dad","",""};
		String str = "ball";
		
		System.out.println("Position of 'ball' in the array = " + new SparseSearch().search(strings, str));
	}
	
	int search(String[] strings, String str, int first, int last){
		if(first > last)
			return -1;
		//move mid to the middle
		int mid = (last + first) / 2;
		
		//If mid is empty, find closest non-empty string.
		if(strings[mid].isEmpty()){
			int left = mid - 1;
			int right = mid + 1;
			while(true){
				if(left < first && right > last){
					return -1;
				}else if(right <= last && !strings[right].isEmpty()){
					mid = right;
					break;
				}else if(left >= first && !strings[left].isEmpty()){
					mid = left;
					break;
				}
				right++;
				left--;
			}
		}
		
		//Check for string and recurse if necessary
		if(str.equals(strings[mid])){//Found it!
			return mid;
		}else if(strings[mid].compareTo(str) < 0){ //Search right
			return search(strings, str, mid + 1, last);
		}else{ //Search left
			return search(strings, str, first, mid - 1);
		}
	}
	
	int search(String[] strings, String str){
		if(strings == null || str == null || str == ""){
			return -1;
		}
		return search(strings, str, 0, strings.length - 1);
	}
}
