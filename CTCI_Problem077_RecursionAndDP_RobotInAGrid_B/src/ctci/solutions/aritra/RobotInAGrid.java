package ctci.solutions.aritra;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Question: Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only move in two directions, right and down, 
 * but certain cells are off limits such that the robot cannot step on them. Design an algorithm to find a path for the robot from the top left
 * to the bottom right.
 * 
 * Solution: If we walk through the algorithm, we'll see that we are visiting squares multiple times. In fact, we visit each square many, many times. 
 * After all, we have rc squares but we're doing O(2^r+c) work. If we were only visiting each square once, we would probably have an algorithm
 * that was O(rc)
 * 
 * How does our current algorithm work? To find a path to (r,c), we look for a path to an adjacent coordinate: (r-1,c) or (r,c-1). Of course, if one of those squares is offlimits,
 * we ignore it. Then'we look at their adjacent coordinates: (r-2, c), (r-1, c-1), (r-1,c-1), and (r,c-2). The spot (r-1,c-1) appears twice, which means
 * that we're duplicating effort. Ideally we should remember that we already visited (r-1, c-1) so that we don't waste our time.
 * 
 * This is what the dynamic programming algorithm below does
 */

public class RobotInAGrid {
	public static void main(String[] args) {
		boolean[][] maze = {
				{true,	true,	false,	false},
				{true,	true,	false,	false},
				{true,	true,	false,	false},
				{false,	true,	true,	true},
				{true,	false,	false,	true},
				{true,	true,	false,	true}
		};
		
		ArrayList<Point> path = new RobotInAGrid().getPath(maze);
		
		Iterator<Point> iter = path.iterator();
		while(iter.hasNext()){
			Point p = iter.next();
			System.out.print("("+p.x + ", " + p.y + ") -> ");
		}
	}
	
	ArrayList<Point> getPath(boolean[][] maze){
		if(maze == null || maze.length == 0)
			return null;
		ArrayList<Point> path = new ArrayList<Point>();
		HashMap<Point, Boolean> cache = new HashMap<Point, Boolean>();
		int lastRow = maze.length - 1;
		int lastCol = maze[0].length - 1;
		if(getPath(maze,lastRow, lastCol, path, cache)){
			return path;
		}
		return null;
	}
	
	boolean getPath(boolean[][]maze, int row, int col, ArrayList<Point> path, HashMap<Point, Boolean> cache){
		//If out of bounds or not available, return
		if(col < 0 || row < 0 || !maze[row][col]){
			return false;
		}
		
		Point p = new Point(row,col);
		
		//If we've already visited this cell, return
		if(cache.containsKey(p)){
			return cache.get(p);
		}
		
		boolean isAtOrigin = (row == 0) && (col == 0);
		boolean success = false;
		
		//If there's a path from the start to my current location, add my location
		if(isAtOrigin || getPath(maze, row, col - 1, path, cache) || getPath(maze, row - 1, col, path, cache)){
			path.add(p);
			success = true;
		}
		
		cache.put(p,  success); //cache result
		return success;
	}
}
