package ctci.solutions.aritra;

/*
 * Question: Imagine a histogram (bar graph). Please open Histogram.png in the project to have a look at it. We need to design an algorithm to compute the volume of water it could
 * hold if someone poured water across the top. You can assume that each histogram bar has width 1.
 * 
 * Example
 * Input: {0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0} Have a look at the image attached in the project.
 * Output: 26
 * 
 * Solution: To optimize the previous algorithm, let's think about the exact cause of the inefficiency of the prior algorithm. The root cause is the perpetual calls to findIndexOfMax.
 * This suggests that it should be our focus for optimizing.
 * 
 * One thing we should notice is that we don't pass in arbitrary ranges into the findIndexOfMax function. It's actually always finding the max from one point to an edge
 * (either the right edge or the left edge). Is there a quicker way we could know what the max height is from a given point to each edge?
 * 
 * In two sweeps through the histogram (one moving right to left and the other moving left to right), we can create a table that tells us, from any index i, the location of
 * the max index on the right and the max index on the left.
 * 
 * The rest of the algorithm proceeds essentially the same way.
 * 
 * Example:
 * INDEX:						0	1	2	3	4	5	6	7	8	9
 * HEIGHT:						3	1	4	0	0	6	0	3	0	2
 * INDEX LEFT MAX:				0	0	2	2	2	5	5	5	5	5
 * INDEX RIGHT MAX:				5	5	5	5	5	5	7	7	9	9
 */

public class VolumeOfHistogram {
	int computeHistogramVolume(int[] histogram){
		int start = 0;
		int end = histogram.length - 1;
		
		HistogramData[] data = createHistogramData(histogram);
		
		int max = data[0].getRightMaxIndex();	//Get overall max which is 5 in the example above
		int leftVolume = subgraphVolume(data, start, max, true);
		int rightVolume = subgraphVolume(data, max, end, false);
		
		return leftVolume + rightVolume;
	}
	
	HistogramData[] createHistogramData(int[] histo){
		HistogramData[] histogram = new HistogramData[histo.length];
		for(int i = 0; i < histo.length; i++){
			histogram[i] = new HistogramData(histo[i]);
		}
		
		//Set left max index
		int maxIndex = 0;
		for(int i = 0; i < histo.length; i++){
			if(histo[maxIndex] < histo[i]){
				maxIndex = i;
			}
			histogram[i].setLeftMaxIndex(maxIndex);
		}
		
		//Set right max index
		maxIndex = histogram.length - 1;
		for(int i = histogram.length - 1; i >= 0; i--){
			if(histo[maxIndex] < histo[i]){
				maxIndex = i;
			}
			histogram[i].setRightMaxIndex(maxIndex);
		}
		return histogram;
	}
	
	//Compute the volume of a subgraph of the histogram. One max is at either start or end (depending on isLeft). Find second tallest, then compute volume between tallest and second tallest.
	//Then compute volume of subgraph.
	int subgraphVolume(HistogramData[] histogram, int start, int end, boolean isLeft){
		if(start >= end)
			return 0;
		int sum = 0;
		if(isLeft){
			int max = histogram[end - 1].getLeftMaxIndex();
			sum += borderedVolume(histogram,max,end);
			sum += subgraphVolume(histogram,start,max,isLeft);
		}else{
			int max = histogram[start + 1].getRightMaxIndex();
			sum += borderedVolume(histogram, start, max);
			sum += subgraphVolume(histogram, max, end, isLeft);
		}
		return sum;
	}
	
	//Compute volume between start and end. Assumes tallest bar is at start and 2nd tallest is at end.
	int borderedVolume(HistogramData[] data, int start, int end){
		if(start >= end)
			return 0;
		int min = Math.min(data[start].getHeight(), data[end].getHeight());
		int sum = 0;
		for(int i = start + 1; i < end; i++){
			sum += min - data[i].getHeight();
		}
		return sum;
	}
}

class HistogramData{
	private int height;
	private int leftMaxIndex = -1;
	private int rightMaxIndex = -1;
	
	public HistogramData(int v){
		height = v;
	}
	public int getHeight(){
		return height;
	}
	public int getLeftMaxIndex(){
		return leftMaxIndex;
	}
	public void setLeftMaxIndex(int idx){
		leftMaxIndex = idx;
	}
	public int getRightMaxIndex(){
		return rightMaxIndex;
	}
	public void setRightMaxIndex(int idx){
		rightMaxIndex = idx;
	}
}
