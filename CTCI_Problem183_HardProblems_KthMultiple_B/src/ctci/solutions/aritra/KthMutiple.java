package ctci.solutions.aritra;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Question: Design an algorithm to find the kth number such that only prime factors are 3,5 and 7.Note that 3,5 and 7 do not have to be factors, but it should not
 * have any other prime factors. For example, the first seven multiples would be (in order) 1,3,5,7,9,15,21
 * 
 * Solution: The question is: what is the next value in the list? The next value will be one of these:
 * 1) 3 * (some previous number in list)
 * 2) 5 * (some previous number in list)
 * 3) 7 * (some previous number in list)
 * 
 * If this doesn't immediately jump to you, think about it this way: whatever the next value (let's call it nv) is, divide it by 3. Will that number have already appeared? As
 * long as nv has factors of 3 in it, yes. The same can be said for dividing it by 5 and 7.
 * So, we know Ak can be expressed as (3*5*7) * (some value in {a1,...,Ak-1}). We also know that Ak is by definition, the next number in the list. Therefore, Ak will be the smallest
 * "new" number (a number that is already in {A1,...,Ak-1} that can be formed by multiplying each value in the list by 3,5,7)
 * How would we find Ak? Well, we could actually multiply each number in the list by 3,5 and 7 and find the smallest element that has not yet been added to our list.
 * This solution is O(k^2). Not bad, but I think we can do better.
 * 
 * Rather than Ak trying to pull from a previous element in the list(by multiplying all of them by 3,5, and 7) we can think about each previous value in the list as pushing out three subsequent
 * values in the list. That is, each number Ai will eventually be used later in the list in the following forms:
 * 3 * Ai
 * 5 * Ai
 * 7 * Ai
 * 
 * We can use this thought to plan in advance. Each time we add a number Ai to the list, we hold on to the values 3Ai, 5Ai and 7Ai
 * in some sort of temporary list, To generate Ai+1, we search through this temporary list to find the smallest value.
 */

public class KthMutiple {
	int removeMin(Queue<Integer> q){
		int min = q.peek();
		for(Integer v : q){
			if(min > v){
				min = v;
			}
		}
		while(q.contains(min)){
			q.remove(min);
		}
		return min;
	}
	
	void addProducts(Queue<Integer> q, int v){
		q.add(v * 3);
		q.add(v * 5);
		q.add(v * 7);
	}
	
	int getKthMagicNumber(int k){
		if(k < 0)
			return 0;
		int val = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		addProducts(q,1);
		for(int i = 0; i < k; i++){
			val = removeMin(q);
			addProducts(q,val);
		}
		return val;
	}
}
