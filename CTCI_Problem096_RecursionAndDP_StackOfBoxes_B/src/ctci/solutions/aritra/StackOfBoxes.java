package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Question: You have a stack of n boxes with widths wi, heights hi and depths di. The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is 
 * strictly larger than the box above it in width, height, and depth. Implement a method to compute the height of the tallest possible stack.
 * The height of a stack is the sum of the heights of each box.
 * 
 * Solution: The previous solution gets very inefficient. We try to find the best solution that looks like {b3, b4, ...} even though we may have already found the best solution
 * with b4 at the bottom. Instead of generating these solutions from scratch, we can cache these results using memoization.
 */

public class StackOfBoxes {
	int createStack(ArrayList<Box> boxes){
		Collections.sort(boxes, new BoxComparator());
		int maxHeight = 0;
		int[] stackMap = new int[boxes.size()];
		for(int i = 0; i < boxes.size(); i++){
			int height = createStack(boxes, i, stackMap);
			maxHeight = Math.max(maxHeight, height);
		}
		return maxHeight;
	}
	
	int createStack(ArrayList<Box> boxes, int bottomIndex, int[] stackMap){
		if(bottomIndex < boxes.size() && stackMap[bottomIndex] > 0){
			return stackMap[bottomIndex];
		}
		
		Box bottom = boxes.get(bottomIndex);
		int maxHeight = 0;
		for(int i = bottomIndex + 1; i < boxes.size(); i++){
			if(boxes.get(i).canBeAbove(bottom)){
				int height = createStack(boxes,i,stackMap);
				maxHeight = Math.max(height, maxHeight);
			}
		}
		maxHeight += bottom.height;
		stackMap[bottomIndex] = maxHeight;
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
