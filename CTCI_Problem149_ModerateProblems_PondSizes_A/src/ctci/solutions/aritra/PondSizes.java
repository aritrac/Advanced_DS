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
 * Solution: Iterative apporach
 * The first thing we can try is just walking through the array. It's easy enough to find water: when it's a zero, that's water
 * 
 * Given a water cell, how can we compute the amount of water nearby? If the cell is not adjacent to any zero cells, then the size of this pond is 1. If it is, then we need
 * to add in the adjacent cells, plus any water cells adjacent to those cells. We need to, of course, be careful to not recount any cells. We can do this with a modified breadth-first
 * search or depth first search. Once we visit a cell, we permanently mark it as visited.
 * 
 * For each cell, we need to check eight adjacent cells. We could do this by writing in lines to check up, down, left, right, and each of the four diagonal cells. It's even easier, though,
 * to do this with a loop.
 */

public class PondSizes {
	ArrayList<Integer> computePondSizes(int[][] land){
		ArrayList<Integer> pondSizes = new ArrayList<Integer>();
		for(int r = 0; r < land.length; r++){
			for(int c = 0; c < land[r].length; c++){
				if(land[r][c] == 0){ //If we discovered a pond location
					int size = computeSize(land,r,c);
					pondSizes.add(size);
				}
			}
		}
		return pondSizes;
	}
	
	
	//Recursively compute the size of the pond
	int computeSize(int[][] land, int row, int col){
		//If out of bounds or already visited
		if(row < 0 || col < 0 || row >= land.length || col >= land[row].length || land[row][col] != 0){	//Visited or not water
			return 0;
		}
		int size = 1;
		land[row][col] = -1; //Mark visited
		for(int dr = -1; dr <= 1; dr++){
			for(int dc = -1; dc <= 1; dc++){
				size += computeSize(land, row + dr, col + dc); //Go to neighboring surrounding 8 cells
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
