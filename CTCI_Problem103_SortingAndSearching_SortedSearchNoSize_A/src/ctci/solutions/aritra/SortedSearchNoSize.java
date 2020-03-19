package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: You are given an array-like data struture Listy which lacks a size method. It does however have an elementAt(i) method that returns the element at index i in O(1)
 * time. If i is beyond the bounds of the data structure, it returns -1(For this reason, the data structure only supports positive integers.) Given a Listy which contains
 * sorted positive integers, find the index at which an element x occurs. If x occurs multiple times, yo umay return any index.
 * 
 * Solution: We know that elementAt will return -1 when i is too large. We can therefore just try bigger and bigger values until we exceed the size of the list.
 * But how much bigger?We can do it exponentially. Try 1, then 2, then 4, then 8, then 16 and so on. This ensures that, if the list has length n, we'll
 * find the length in at most O(log n) time.
 * 
 * Once we find the length, we just perform a (mostly) normal binary search. I say "mostly" because we need to make one small tweak. If the midpoint is -1,
 * we need to treat this as a "too big" value and search left.
 * 
 * There is one more tweak. Recall that the way we figure out the length is by calling elementAt and comparing it to -1. If, in
 * the process, the element is bigger than the value x (the one we are searching for), we'll jump over to the binary search part early.
 */

public class SortedSearchNoSize {
	public static void main(String[] args) {
		Listy list = new Listy();
		list.add(3);
		list.add(8);
		list.add(24);
		list.add(31);
		list.add(34);
		list.add(39);
		list.add(45);
		list.add(46);
		list.add(47);
		list.add(50);
		list.add(51);
		
		System.out.println("Element 50 is at " + new SortedSearchNoSize().search(list, 50));
		
		System.out.println("Element 31 is at " + new SortedSearchNoSize().search(list, 31));
	}
	
	int search(Listy list, int value){
		int index = 1;
		while(list.elementAt(index) != -1 && list.elementAt(index) < value){
			index *= 2;
		}
		
		return binarySearch(list, value, index/2, index);
	}
	
	int binarySearch(Listy list, int value, int low, int high){
		int mid;
		
		while(low <= high){
			mid = (low + high) / 2;
			int middle = list.elementAt(mid);
			if(middle > value || middle == -1){
				high = mid - 1;
			}else if(middle < value){
				low = mid + 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
}

class Listy{
	ArrayList<Integer> list;
	
	public Listy(){
		list = new ArrayList<Integer>();
	}
	
	public Integer elementAt(int i){
		if( i < list.size()){
			return list.get(i);
		}else{
			return -1;
		}
	}
	
	public void add(Integer i){
		list.add(i);
	}
}