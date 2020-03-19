package ctci.solutions.aritra;

/*
 * Question: Given an NxN matrix of positive and negative integers, write code to find the submatrix with the largest possible sum.
 * 
 * Solution: Picture a submatrix:
 * 
 * 											ROW START
 * 								9		-8		1		3		-2
 * 							   -3		 7		6	   -2		 4
 * 							    6		-4	   -4		8		-7
 * 			Partial Sums	=  12		-5		3		9		-5
 * 											ROW  END
 * Given a row start and a row end, we want to find the colStart and colEnd that give us the highest possible sum. To do this, we can sum up each column and then apply the maximumSubArray
 * function.
 * 
 * For the example shown above, the maximum subarray is the first to fourth columns. This means that the maximum submatrix is (rowStart,firstColumn) through (rowEnd, fourthColumn).
 * 
 * We now have the pseudocode like this.
 * maxSum = 0
 * foreach rowStart in rows
 * 		foreach rowEnd in rows
 * 			foreach col in columns
 * 				partialSum[col] = sum of matrix[rowStart,col] through matrix[rowEnd, col]
 * 			runningMaxSum = maxSubArray(partialSum)
 * 			maxSum = max(runningMaxSum, maxSum)
 * return maxSum
 * 
 * While calculating the sum of the column for the partialSum, we are adding them from scratch, even though in the previous iteration of the outer for loop,
 * we already added up a[0]...a[i-1]. Let's cut out this duplicated effort.
 * 
 * maxSum = 0
 * foreach rowStart in rows
 * 		clear array partialSum
 * 		foreach rowEnd in rows
 * 			partialSum[col] += matrix[rowEnd, col]
 * 		runningMaxSum = maxSubArray(partialSum)
 * 		maxSum = max(runningMaxSum, maxSum)
 * return maxSum
 * 
 * Our full code looks like this		
 */

public class MaxSubMatrix {
	Submatrix getMaxMatrix(int[][] matrix){
		int rowCount = matrix.length;
		int colCount = matrix[0].length;
		Submatrix best = null;
		
		//Taking each pair of rows possible
		//row1 with row1,row2,row3,row4
		//row2 with row2,row3,row4
		//row3 with row3,row4
		//row4 with row4
		for(int rowStart = 0; rowStart < rowCount; rowStart++){
			//partialSum[i] stores the entire ith column sum right from rowStart to rowEnd
			int[] partialSum = new int[colCount];
			for(int rowEnd = rowStart; rowEnd < rowCount; rowEnd++){
				//Add all values at row rowEnd
				for(int i = 0; i < colCount; i++){
					partialSum[i] += matrix[rowEnd][i];
				}
				//For the rowStart and rowEnd row finding the best Range of columns that gives highest sum
				Range bestRange = maxSubArray(partialSum, colCount);
				if(best == null || best.getSum() < bestRange.sum){
					best = new Submatrix(rowStart, bestRange.start, rowEnd, bestRange.end, bestRange.sum);
				}
			}
		}
		return best;
	}
	
	//Finding the highest sum range with start and ith index in the partialSum array
	Range maxSubArray(int[] partialSum, int colCount){
		Range best = null;
		int start = 0;
		int sum = 0;
		for(int i = 0; i < colCount; i++){
			sum += partialSum[i];
			if(best == null || sum > best.sum){
				best = new Range(start, i, sum);
			}
			
			//If running_sum is < 0 no point in trying to continue the series. Rest
			if(sum < 0){
				//If any best solution was there before i, that would already be there in best variable
				//Hence making start point to i + 1
				start = i + 1;
				sum = 0;
			}
		}
		return best;
	}
}

class Submatrix{
	private int row1, row2, col1, col2, sum;
	public Submatrix(int r1, int c1, int r2, int c2, int sm){
		row1 = r1;
		col1 = c1;
		row2 = r2;
		col2 = c2;
		sum = sm;
	}
	
	public int getSum(){
		return sum;
	}
}

class Range{
	public int start, end, sum;
	public Range(int start, int end, int sum){
		this.start = start;
		this.end = end;
		this.sum = sum;
	}
}
