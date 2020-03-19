package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Question: You have a stack of n boxes with widths wi, heights hi and depths di. The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is 
 * strictly larger than the box above it in width, height, and depth. Implement a method to compute the height of the tallest possible stack.
 * The height of a stack is the sum of the heights of each box.
 * 
 * Solution: Alternatively, we can think about the recursive algorithm as making a choice, at each step, whether to put a particular box in the stack. (We will again sort our boxes
 * indescending order by a dimension, such as height).
 * First, we choose whether or not to put box 0 in the stack. Take one recursive path with box 0 at the bottom and one recursive pathwithout box 0. Return the better of the two options
 * 
 * Then we choose whether or not to put box 1 in the stack. Take one recursive path with box 1 at the bottom and one path without box 1. Return the better of the two options
 * 
 * We will again use memoization to cache the height of the tallest stack with a particular bottom.
 */

public class StackOfBoxes {
	int createStack(ArrayList<Box> boxes){
		Collections.sort(boxes, new BoxComparator());
		int[] stackMap = new int[boxes.size()];
		return createStack(boxes, null, 0, stackMap);
	}
	
	int createStack(ArrayList<Box> boxes, Box bottom, int offSet, int[] stackMap){
		if(offSet >= boxes.size())
			return 0; //Base case
		
		//Height with this bottom
		Box newBottom = boxes.get(offSet);
		int heightWithBottom = 0;
		if(bottom == null || newBottom.canBeAbove(bottom)){
			if(stackMap[offSet] == 0){
				stackMap[offSet] = createStack(boxes, newBottom, offSet + 1, stackMap);
				stackMap[offSet] += newBottom.height;
			}
			heightWithBottom = stackMap[offSet];
		}
		
		//without this bottom
		int heightWithoutBottom = createStack(boxes, bottom, offSet + 1, stackMap);
		
		//Return better of two options
		return Math.max(heightWithBottom, heightWithoutBottom);
	}
}

class Box{
	int width;
	int height;
	int depth;
	
	public Box(int width, int height, int depth){
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public boolean canBeAbove(Box bottom){
		return bottom.height > this.height;
	}
}

class BoxComparator implements Comparator<Box>{
	@Override
	public int compare(Box x, Box y){
		return y.height - x.height;
	}
}
