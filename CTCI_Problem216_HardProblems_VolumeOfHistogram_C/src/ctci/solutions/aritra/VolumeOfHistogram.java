package ctci.solutions.aritra;

/*
 * Question: Imagine a histogram (bar graph). Please open Histogram.png in the project to have a look at it. We need to design an algorithm to compute the vloume of water it could
 * hold if someone poured water across the top. You can assume that each histogram bar has width 1.
 * 
 * Example
 * Input: {0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0} Have a look at the image attached in the project.
 * Output: 26
 * 
 * Solution: As we've seen, the volume of water in a particular area is determined by the tallest bar to the left and to the right (specifically, by the shorter of the two tallest
 * bars on the left and the tallest bar on the right.)For example, water fills in the area between the bar with height 6 and the bar with height 8, up to a height of 6.
 * It's the second tallest, therefore, that determines the height.
 * The total volume of water is the volume of water above each histogram bar. Can we efficiently compute how much water is above each histogram bar?
 * Yes, in the previous solution, we were able to precompute the height of the tallest bar on the left and right of each index. The minimums of these will indicate the 
 * 'water level' at a bar. The difference between the water level and the height of this bar will be the volume of water.
 * 
 * HEIGHT:				0	0	4	0	0	6	0	0	3	0	8	0	2	0	5	2	0	3	0	0
 * LEFT MAX:			0	0	4	4	4	6	6	6	6	6	8	8	8	8	8	8	8	8	8	8
 * RIGHT MAX:			8	8	8	8	8	8	8	8	8	8	8	5	5	5	5	3	3	3	0	0
 * MIN:					0	0	4	4	4	6	6	6	6	6	8	5	5	5	5	3	3	3	0	0
 * DELTA(MIN-HEIGHT):	0	0	0	4	4	0	6	6	3	6	0	5	3	5	0	1	3	0	0	0
 * 
 * Our algorithm now runs in a few simple steps:
 * 1. Sweep left to right, tracking the max height you've seen and setting left max
 * 2. Sweep right to left, tracking the max height you've seen and setting right max
 * 3. Sweep across the histogram, computing the minimum of the left max and right max for each index.
 * 4. Sweep across the histogram, computing the delta between each minimum and the bar. Sum these deltas.
 * 
 * In the actual implementation, we don't need to keep so much data around. Steps 2,3 and 4 can be merged into the same sweep. First, compute the left maxes in one sweep. Then
 * sweep through in reverse, tracking the right max as you go. At each element, calculate the min of the left and right max and then the delta between that (the 'min of maxes') and
 * the bar height. Add this to the sum.
 */

public class VolumeOfHistogram {
	//Go through each bar and compute the volume of water above it.
	//Volume of water at a bar = height - min(tallest bar on left,tallest bar on right)
	//	[where above equation is positive]
	//Compute the left max in the first sweep, then sweep again to compute the right max, minimum of the bar heights, and the delta
	int computeHistogramVolume(int[] histo){
		//Get left max
		int[] leftMaxes = new int[histo.length];
		int leftMax = histo[0];
		for(int i = 0; i < histo.length; i++){
			leftMax = Math.max(leftMax, histo[i]);
			leftMaxes[i] = leftMax;
		}
		
		int sum = 0;
		
		//Get right max
		int rightMax = histo[histo.length - 1];
		for(int i = histo.length - 1; i >= 0; i--){
			rightMax = Math.max(rightMax, histo[i]);
			int secondTallest = Math.min(rightMax, leftMaxes[i]);
			//If there are taller things on the left and right side, then there is water above this bar. Compute the volume and add to the sum.
			if(secondTallest > histo[i]){
				sum += secondTallest - histo[i];
			}
		}
		return sum;
	}
}
