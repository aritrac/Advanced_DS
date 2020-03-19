package ctci.solutions.aritra;

/*
 * Question: Imagine a histogram (bar graph). Please open Histogram.png in the project to have a look at it. We need to design an algorithm to compute the volume of water it could
 * hold if someone poured water across the top. You can assume that each histogram bar has width 1.
 * 
 * Example
 * Input: {0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0} Have a look at the image attached in the project.
 * Output: 26
 * 
 * Solution: This is a difficult problem, so let's come up with a good example to help us solve it. Have a look at Histogram2.png in the project to see this example.
 * We should study this example to see what we can learn from it. What exactly dictates how big those gray areas are?
 * 
 * Let's look at the tallest bar, which has size 8. What role does that bar play? It plays an important role for being the highest, but it
 * actually wouldn't matter if that bar instead had height 100. It wouldn't affect the volume. The tallest bar forms a barrier for water on its left and right. But
 * the volume of water is actually controlled by the next highest bar on the left and right.
 * 
 * Water on immediate left of tallest bar: The next tallest bar on the left has height 6. We can fill up the area in between with water, but we have to deduct the height of each
 * histogram between the tallest and next tallest. This gives volume on the immediate left of: (6-0) + (6-0) + (6-3) + (6-0) = 21.
 * 
 * Water on immediate right of tallest bar: The next tallest bar on the right has height 5. We can now compute the volume:
 * (5-0) + (5-2) + (5-0) = 13
 * This just tells us part of the volume. What about the rest?
 * We have essentially two subgraphs, one on the left and one on the right. To find the volume there, we repeat a very similar process.
 * 1. Find the max(Actually, this is given to us. The highest on the left subgraph is the right border(6) and the highest on the right subgraph is the left border(5).)
 * 2. Find the second tallest in each subgraph. In the left subgraph, this is 4. In the right subgraph, this is 3.
 * 3. Compute the volume between the tallest and the second tallest.
 * 4. Recurse on the edge of the graph.
 * The code below implements this algorithm.
 */

public class VolumeOfHistogram {
	int computeHistogramVolume(int[] histogram){
		int start = 0;
		int end = histogram.length - 1;
		
		int max = findIndexOfMax(histogram, start, end);
		int leftVolume = subgraphVolume(histogram, start, max, true);
		int rightVolume = subgraphVolume(histogram, max, end, false);
		
		return leftVolume + rightVolume;
	}
	
	//Compute the volume of a subgraph of the histogram. One max is at either start or end (depending on isLeft). Find the second tallest, then compute volume between tallest
	//and second tallest. Then compute volume of the subgraph.
	int subgraphVolume(int[] histogram, int start, int end, boolean isLeft){
		if(start >= end)
			return 0;
		int sum = 0;
		if(isLeft){
			int max = findIndexOfMax(histogram,start,end-1);
			sum += borderedVolume(histogram, max,end);
			sum += subgraphVolume(histogram, start, max, isLeft);
		}else{
			int max = findIndexOfMax(histogram, start + 1, end);
			sum += borderedVolume(histogram, start, max);
			sum += subgraphVolume(histogram, max, end, isLeft);
		}
		return sum;
	}
	
	//Find tallest bar in histogram between start and end
	int findIndexOfMax(int[] histogram, int start, int end){
		int indexOfMax = start;
		for(int i = start + 1; i <= end; i++){
			if(histogram[i] > histogram[indexOfMax]){
				indexOfMax = i;
			}
		}
		return indexOfMax;
	}
	
	//Compute volume between start and end. Assumes tallest bar is at start and 2nd tallest is at end
	int borderedVolume(int[] histogram, int start, int end){
		if(start >= end)
			return 0;
		int min = Math.min(histogram[start], histogram[end]);
		int sum = 0;
		for(int i = start + 1; i < end; i++){
			sum += min - histogram[i];
		}
		return sum;
	}
}
