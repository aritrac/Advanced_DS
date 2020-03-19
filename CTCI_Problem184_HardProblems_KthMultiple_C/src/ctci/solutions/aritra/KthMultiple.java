package ctci.solutions.aritra;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Question: Design an algorithm to find the kth number such that only prime factors are 3,5 and 7.Note that 3,5 and 7 do not have to be factors, but it should not
 * have any other prime factors. For example, the first seven multiples would be (in order) 1,3,5,7,9,15,21
 * 
 * Solution: In continuation of the previous problem, we will use the following algorithm as shown below
 * 
 * 1.Initialize array and queues Q3, Q5 and Q7.
 * 2.Insert 1 into array
 * 3.Insert 1*3, 1*5 and 1*7 into Q3, Q5 and Q7 respectively
 * 4.Let x be the minimum element in Q3, Q5 and Q7. Append x to magic
 * 5.If x was found in:
 * 	Q3-> append x*3,x*5 and x*7 to Q3,Q5 and Q7. Remove x from Q3
 *  Q5-> append x*5 and x*7 to Q5 and Q7. Remove x from Q5
 *  Q7-> append x*7 to Q7. Remove x from Q7
 *  
 *  Suppose 15 is the minimum we got from Q5, that means the minimum of Q3 > 15 though 15 is a multiple of 3, we have crossed it before in Q3 and removed it, so no need to add it again to Q3
 *  as we have moved to higher multiples of Q3
 *  Same goes for Q7. Suppose min from Q7 is 35 and we know 35 is a multiple of 5, but Q5 min is greater than 35, which means Q5 removed this element long back, so no need to add it again to Q5.
 *  and also we are not going to insert these already crossed values into the queues once again, and hence we remove it from the queue that we found these obsolete values , but add their multiples
 *  to it.
 */

public class KthMultiple {
	int getKthMagicNumber(int k){
		if(k < 0){
			return 0;
		}
		int val = 0;
		Queue<Integer> queue3 = new LinkedList<Integer>();
		Queue<Integer> queue5 = new LinkedList<Integer>();
		Queue<Integer> queue7 = new LinkedList<Integer>();
		
		queue3.add(1);
		
		//Include 0th through kth iteration
		for(int i = 0; i <= k; i++){
			int v3 = queue3.size() > 0? queue3.peek() : Integer.MAX_VALUE;
			int v5 = queue5.size() > 0? queue5.peek() : Integer.MAX_VALUE;
			int v7 = queue7.size() > 0? queue7.peek() : Integer.MAX_VALUE;
			val = Math.min(v3, Math.min(v5, v7));
			if(val == v3){ //enqueue into queue 3,5 and 7
				queue3.remove();
				queue3.add(3*val);
				queue5.add(5*val);
			}else if(val == v5){	//enqueue into 5 and 7
				queue5.remove();
				queue5.add(5 * val);
			}else if(val == v7){	//enqueue into q7
				queue7.remove();
			}
			queue7.add(7 * val);	//Always enqueue into q7
		}
		return val;
	}
}
