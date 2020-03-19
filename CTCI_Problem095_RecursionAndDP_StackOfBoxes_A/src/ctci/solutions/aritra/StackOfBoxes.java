package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Question: You have a stack of n boxes with widths wi, heights hi and depths di. The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is 
 * strictly larger than the box above it in width, height, and depth. Implement a method to compute the height of the tallest possible stack.
 * The height of a stack is the sum of the heights of each box.
 * 
 * Solution: Imagine we had the following boxes: b1, b2, ...., bn. The biggest stack that we can build with all the boxes equals the max of
 * (biggest stack with bottom b1, biggest stack with bottom b2, ... biggest stack with bottom bn). That is if we experimented with each box as a bottom,
 * and built the biggest stack possible with each. We would find the biggest stack possible.
 * 
 * But how would we find the biggest stack with a particular bottom? Essentially the same way. We experiment with different boxes for the second level and so on for each level.
 * 
 * Of course, we can only experiment with valid boxes. If b5 is bigger than b1, then there is no point in trying to build a stack that looks like {b1, b5, ...}. We already know
 * b1 can't be below b5.
 * 
 * We can perform a small optimization here. The requirements of this problem stipulate that the lower boxes must be strictly greater than the higher boxes in all dimensions.
 * Therefore, if we sort(descending order) the boxes on a dimension - any dimension- then we know we don't have to look backwards in the list. The box b1 cannot be on top of box b5.
 * Since its height(or whatever dimension we sorted on) is greater than b5's height.
 */

public class StackOfBoxes {
	int createStack(ArrayList<Box> boxes){
		//Sort in descending order by height
		Collections.sort(boxes, new BoxComparator());
		int maxHeight = 0;
		for(int i = 0; i < boxes.size(); i++){
			int height = createStack(boxes,i);
			maxHeight = Math.max(maxHeight, height);
		}
		return maxHeight;
	}
	
	int createStack(ArrayList<Box> boxes, int bottomIndex){
		Box bottom = boxes.get(bottomIndex);
		int maxHeight = 0;
		for(int i = bottomIndex + 1; i < boxes.size(); i++){
			if(boxes.get(i).canBeAbove(bottom)){
				int height = createStack(boxes,i);
				maxHeight = Math.max(height, maxHeight);
			}
		}
		maxHeight += bottom.height;
		return maxHeight;
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
