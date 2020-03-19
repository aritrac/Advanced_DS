package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/*
 * Question: Given a two dimensional graph with points on it, find a line which passes the most number of lines
 * 
 * Solution: This solution seems quite straightforward at first. And it is - sort of.
 * We just "draw" an infinite line (that is, not a line segment) between every two points and using a hashtable, track which line is the most common.This will take O(N^2) time,
 * since there are N^2 line segments. We will represent a line as a slope and y-intercept(as opposed to a pair of points), which allows us to easily check to see if the line
 * from (x1, y1) to (x2, y2) is equivalent to the line from (x3,y3) to (x4,y4)
 * 
 * To find the most common line then, we just need to iterate through all line segments, using a hash table to count the number of times we've seen each line. Easy enough!
 * However, there's one little complication. We're defining two lines to be equal if the lines have the same slope and y-intercept. We are then, furthermore, hashing 
 * the lines based on these values (specifically, based on the slope). The problem is that floating point numbers cannot always be represented accurately in binary. We
 * resolve this by checking if two floating point numbers are within an epsilon value of each other.
 * 
 * What does this mean for our hash table? It means the two lines with equal slopes may not be hashed to the same value. To solve this, we will round the slope down
 * to the next epsilon and use this flooredScope as the hash key. Then to retrieve all lines that are potentially equal, we will search the hash table at three spots:
 * flooredScope, flooredScope - epsilon, and flooredScope + epsilon. This will ensure that we've checked out all lines that might be equal.
 */

public class BestLine {
	//Find line that goes through most number of points
	Line findBestLine(GraphPoint[] points){ //Starts here
		HashMapList<Double,Line> linesBySlope = getListOfLines(points);
		return getBestLine(linesBySlope);
	}
	
	//Add each pair of points as a line to the list
	HashMapList<Double, Line> getListOfLines(GraphPoint[] points){
		HashMapList<Double, Line> linesBySlope = new HashMapList<Double, Line>();
		for(int i = 0; i < points.length; i++){
			for(int j = i + 1; j < points.length; j++){
				Line line = new Line(points[i], points[j]);
				double key = Line.floorToNearestEpsilon(line.slope);
				linesBySlope.put(key, line);
			}
		}
		return linesBySlope;
	}
	
	//Return the line with the most equivalent other lines
	Line getBestLine(HashMapList<Double,Line> linesBySlope){
		Line bestLine = null;
		int bestCount = 0;
		
		Set<Double> slopes = linesBySlope.keySet(); //Get list of all slopes
		
		for(double slope : slopes){
			ArrayList<Line> lines = linesBySlope.get(slope);
			for(Line line : lines){
				//Count lines that are equivalent to current line
				int count = countEquivalentLines(linesBySlope, line);
				
				//If better than current line, replace it
				if(count > bestCount){
					bestLine = line;
					bestCount = count;
					bestLine.Print();
					System.out.println(bestCount);
				}
			}
		}
		return bestLine;
	}
	
	//Check hashmap for equivalent lines. Note we need to check one epsilon above and below the actual slope since we're defining two lines as equivalent if they're within an 
	//epsilon of each other.
	int countEquivalentLines(HashMapList<Double, Line> linesBySlope, Line line){
		double key = Line.floorToNearestEpsilon(line.slope);
		int count = countEquivalentLines(linesBySlope.get(key), line);
		count += countEquivalentLines(linesBySlope.get(key - Line.epsilon), line);
		count += countEquivalentLines(linesBySlope.get(key + Line.epsilon), line);
		return count;
	}
	
	//Count lines within an array of lines which are "equivalent" (slope and y-intercept are within an epsilon value) to a given line
	int countEquivalentLines(ArrayList<Line> lines, Line line){
		if(lines == null)
			return 0;
		int count = 0;
		for(Line parallelLine : lines){
			if(parallelLine.isEquivalent(line)){
				count++;
			}
		}
		return count;
	}
}

class Line {
	public static double epsilon = .0001;
	public double slope;
	public double intercept;
	
	private boolean infinite_slope = false;
	
	public Line(GraphPoint p, GraphPoint q) {
		if (Math.abs(p.x - q.x) > epsilon) { // if x’s are different
			slope = (p.y - q.y) / (p.x - q.x); // compute slope
			intercept = p.y - slope * p.x; // y intercept from y=mx+b
		} else {
			infinite_slope = true;
			intercept = p.x; // x-intercept, since slope is infinite
		}
	}
	
	public boolean isEquivalent(double a, double b) {
		return (Math.abs(a - b) < epsilon);
	}
	
	public void Print() {
		System.out.println("y = " + slope + "x + " + intercept);
	}
		
	public static double floorToNearestEpsilon(double d) {
		int r = (int) (d / epsilon);
		return ((double) r) * epsilon;
	}
    
	public boolean isEquivalent(Object o) {  
		Line l = (Line) o;
    	if (isEquivalent(l.slope, slope) && isEquivalent(l.intercept, intercept) && (infinite_slope == l.infinite_slope)) {
    		return true;
    	}
        return false;
    }      
}

class GraphPoint {
	public double x;
	public double y;
	public GraphPoint(double x1, double y1) {
		x = x1;
		y = y1;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}


class HashMapList<T, E> {
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();
	
	/* Insert item into list at key. */
	public void put(T key, E item) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}
	
	/* Insert list of items at key. */
	public void put(T key, ArrayList<E> items) {
		map.put(key, items);
	}
	
	/* Get list of items at key. */
	public ArrayList<E> get(T key) {
		return map.get(key);
	}
	
	/* Check if hashmaplist contains key. */
	public boolean containsKey(T key) {
		return map.containsKey(key);
	}
	
	/* Check if list at key contains value. */
	public boolean containsKeyValue(T key, E value) {
		ArrayList<E> list = get(key);
		if (list == null) return false;
		return list.contains(value);
	}
	
	/* Get the list of keys. */
	public Set<T> keySet() {
		return map.keySet();
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}