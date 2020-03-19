package ctci.solutions.aritra;

/*
 * Question: Given an M x N matrix in which each row and each column is sorted in ascending order, write a method to find an element.
 * 
 * Solution: 
 * 
 * 15 20  70  85
 * 20 35  80  95
 * 30 55  95 105
 * 40 80 100 120
 * 
 * Alternatively we can apply a solution that more directly looks like binary search. The code is more considerably complicated, but it applies
 * many of the same learnings.
 * We want to leverage the sorting property to more efficiently find an element. So, we might ask ourselves, what does the unique ordering property of this matrix
 * imply about where an element might be located?
 * We are told that every row and column is sorted. This means that element a[i][j] will be greater than the elements in row i between columns 0 and j - 1 and the elements
 * in column j between rows 0 and i - 1.
 * 
 * We notice a property in this rectangle which goes as follows. For any rectangle we draw in the matrix, the bottom right hand corner will always be the biggest.
 * Likewise, the top left hand corner will always be the smallest. The colors below indicate what we know about the ordering of the elements.
 * 
 * 85 can't be in the black area, since 95 is in the upper left hand corner and is therefore the smallest element in that square.
 * 85 can't be in the light area either, since 35 is in the lower right hand corner of that square.
 * 85 must be in one of the two white areas.
 * 
 * So we partition our grid into four quadrants and recursively search the lower left quadrant and the upper irght quadrant. These, too, will get divided into quadrants and searched
 * Observe that since the diagonal is sorted, we can efficiently search it using binary search.
 */

public class SortedMatrixSearch {
	public static void main(String[] args) {
		int[][] matrix = {
				{15,20,70,85},
				{20,35,80,95},
				{30,55,95,105},
				{40,80,100,120}
		};
		int elem = 55;
		System.out.println("Is 55 present in the matrix?" + new SortedMatrixSearch().findElement(matrix, elem));
	}
	
	Coordinate findElement(int[][] matrix, Coordinate origin, Coordinate dest, int x){
		if(!origin.inbounds(matrix) || !dest.inbounds(matrix)){
			return null;
		}
		if(matrix[origin.row][origin.column] == x){
			return origin;
		}else if(!origin.isBefore(dest)){
			return null;
		}
		
		/*
		 * Set start to start of diagonal and end to the end of the diagonal. Since the grid may not be square, the end of the diagonal may not equal dest.
		 */
		Coordinate start = (Coordinate)origin.clone();
		int diagDist = Math.min(dest.row - origin.row, dest.column - origin.column);
		Coordinate end = new Coordinate(start.row + diagDist,start.column + diagDist);
		Coordinate p = new Coordinate(0,0);
		
		//Do binary search on the diagonal, looking for the first element > x
		while(start.isBefore(end)){
			p.setToAverage(start, end);
			if(x > matrix[p.row][p.column]){
				start.row = p.row + 1;
				start.column = p.column + 1;
			}else{
				end.row = p.row - 1;
				end.column = p.column - 1;
			}
		}
		
		//Split the grid into quadrants. Search the bottom left and the top right
		return partitionAndSearch(matrix, origin, dest, start, x);
	}
	
	Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivot, int x){
		Coordinate lowerLeftOrigin = new Coordinate(pivot.row, origin.column);
		Coordinate lowerLeftDest = new Coordinate(dest.row, pivot.column - 1);
		Coordinate upperRightOrigin = new Coordinate(origin.row, pivot.column);
		Coordinate upperRightDest = new Coordinate(pivot.row - 1, dest.column);
		
		Coordinate lowerLeft = findElement(matrix, lowerLeftOrigin, lowerLeftDest, x);
		if(lowerLeft == null){
			return findElement(matrix, upperRightOrigin, upperRightDest, x);
		}
		return lowerLeft;
	}
	
	Coordinate findElement(int[][] matrix, int x){
		Coordinate origin = new Coordinate(0, 0);
		Coordinate dest = new Coordinate(matrix.length - 1, matrix[0].length - 1);
		return findElement(matrix, origin, dest, x);
	}
}

class Coordinate implements Cloneable{
	public int row, column;
	
	public Coordinate(int r, int c){
		row = r;
		column = c;
	}
	
	public boolean inbounds(int[][] matrix){
		return row >= 0 && column >= 0 && row < matrix.length && column < matrix[0].length;
	}
	
	public boolean isBefore(Coordinate p){
		return row <= p.row && column <= p.column;
	}
	
	public Object clone(){
		return new Coordinate(row,column);
	}
	
	public void setToAverage(Coordinate min, Coordinate max){
		row = (min.row + max.row) / 2;
		column = (min.column + max.column) / 2;
	}
}