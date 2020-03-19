package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Question: You have an integer matrix representing a plot of land, where the value at that location represents the height above sea level. A value of 0 indicates water.
 * A pond is a region of water connected vertically, horizontally, or diagonally. The size of the pond is the total number of connected water cells.
 * Write a method to compute the sizes of all ponds in the matrix.
 * 
 * Example:
 * Input:
 * 0 2 1 0
 * 0 1 0 1
 * 1 1 0 1
 * 0 1 0 1
 * 
 * Output: 2,4,1 (in any order)
 * 
 * Solution: As in the previous solution,if we don't like modifying the input matrix, you can create a secondary visited matrix as shown
 */

public class PondSizes {
	
	ArrayList<Integer> computePondSizes(int[][] land){
		boolean[][] visited = new boolean[land.length][land[0].length];
		ArrayList<Integer> pondSizes = new ArrayList<Integer>();
		for(int r = 0; r < land.length; r++){
			for(int c = 0; c < land[r].length; c++){
				int size = computeSize(land, visited, r, c);
				if(size > 0){
					pondSizes.add(size);
				}
			}
		}
		return pondSizes;
	}
	
	int computeSize(int[][] land, boolean[][] visited, int row, int col){
		//if out of bounds or already visited
		if(row < 0 || col < 0 || row >= land.length || col >= land[row].length || visited[row][col] || land[row][col] != 0){ //This has an extra condition if visited then return which makes it efficient
			return 0;
		}
		int size = 1;
		visited[row][col] = true;
		for(int dr = -1; dr <= 1; dr++){
			for(int dc = -1; dc <= 1; dc++){
				size += computeSize(land, visited, row + dr, col + dc);
			}
		}
		return size;
	}
	
	public static void main(String[] args) {
		int[][] arr = {{0,2,1,0},
					   {0,1,0,1},
					   {1,1,0,1},
					   {0,1,0,1}};
		ArrayList<Integer> sizes = new PondSizes().computePondSizes(arr);
		Iterator<Integer> iter = sizes.iterator();
		
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
