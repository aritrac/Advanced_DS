package ctci.solutions.aritra;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/*
 * Question: Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only move in two directions, right and down, 
 * but certain cells are off limits such that the robot cannot step on them. Design an algorithm to find a path for the robot from the top left
 * to the bottom right.
 * 
 * Solution: If we picture this grid, the only way to move to spot(r,c) is by moving to one of the adjacent spots: (r-1, c) or (r,c-1). So,
 * we need to find a path to either (r-1,c) or (r, c-1).
 * How do we find a path to those spots? To find a path to (r-1,c) or (r,c-1), we need to move to one of its adjacent cells. So, we need to find a path to a spot adjacent to
 * (r-1,c), which are coordinates (r-2, c) and (r-1, c- 1), or a spot adjacent to (r,c-1), which are spots (r-1, c-1) and (r,c-2). Observe that we list the point
 * (r-1,c-1) twice, we'll discuss that issue later
 * 
 * So then to find a path from the origin we just work backwards like this. Starting from the last cell, we try to find a path to each of its adjacent cells. The recursive code
 * below implements this algorithm.
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
		HashSet<Point> failedPoints = new HashSet<Point>();
		
		if(getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)){
			return path;
		}
		return null;
	}
	
	boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints){
		//If out of bounds or not available, return
		if(col < 0 || row < 0 || !maze[row][col]){
			return false;
		}
		
		Point p = new Point(row,col);
		
		//If we've already visited this cell, return
		if(failedPoints.contains(p)){
			return false;
		}
		
		boolean isAtOrigin = (row == 0) && (col == 0);
		
		//if there's a path from start to my current location, add my location
		if(isAtOrigin || getPath(maze,row,col-1, path, failedPoints) || (getPath(maze,row-1,col,path,failedPoints))){
			path.add(p);
			return true;
		}
		
		failedPoints.add(p); //Cache results
		return false;
	}
}
