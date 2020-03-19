package ctci.solutions.aritra;

/*
 * Question: Given two straight line segments (represented as a start point and an end point), compute the point of intersection, if any.
 * 
 * Solution: We first need to think about what it means for two line segments to intersect.
 * For two infinite lines to intersect, they only have to have different slopes. If they have the same slope, then they must be exact same line(same y intercept). That is:
 * slope1 != slope2
 * OR
 * slope1 == slope2 and intersect1 == intersect2
 * 
 * For two straight lines to intersect, the condition above must be true, plus the point of intersection must be within the ranges of each line segments
 * 
 * extended infinite segments intersect
 * AND
 * intersection is within line segment 1 (x and y coordinates)
 * AND
 * intersection is within line segment 2 (x and y coordinates)
 * 
 * What if the two segments represent the same infinite line? In this case, we have to ensure that some portion of their segments overlap. If we order the line segments
 * by their x locations (start is before end, point 1 is before point2), then an intersection occurs only if:
 * 
 * Assume: start1.x < start2.x && start1.x < end1.x && start2.x < end2.x
 * Then intersection occurs if: start2 is between start1 and end1
 * 
 * The following code implements the same
 */

public class Intersection {
	Point intersection(Point start1, Point end1, Point start2, Point end2){
		//Rearranging these so that, in order of x values: start is before end and point1 is before point2. This will make some of the later logic simpler
		if(start1.x > end1.x)
			swap(start1, end1);
		if(start2.x > end2.x)
			swap(start2, end2);
		if(start1.x > start2.x){
			swap(start1,start2);
			swap(end1,end2);
		}
		//Compute lines (including slope and y-intercept)
		Line line1 = new Line(start1, end1);
		Line line2 = new Line(start2, end2);
		
		//If the lines are parallel, they intercept only if they have the same y intercept and start2 is on line 1
		if(line1.slope == line2.slope){
			if(line1.yIntercept == line2.yIntercept && isBetween(start1,start2,end1)){
				return start2;
			}
			return null;
		}
		
		//Get intersection coordinates
		double x = (line2.yIntercept - line1.yIntercept)/ (line1.slope - line2.slope);
		double y = x * line1.slope + line1.yIntercept;
		Point intersection = new Point(x,y);
		
		//Check if within line segment range
		if(isBetween(start1, intersection, end1) && isBetween(start2, intersection, end2)){
			return intersection;
		}
		return null;
	}
	
	//Checks if middle is between start and end
	boolean isBetween(double start, double middle, double end){
		if(start > end){
			return end <= middle && middle <= start;
		}else{
			return start <= middle && middle <= end;
		}
	}
	
	//Checks if middle is between start and end
	boolean isBetween(Point start, Point middle, Point end){
		return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y);
	}
	
	//Swaps coordinate of point one and two
	void swap(Point one, Point two){
		double x = one.x;
		double y = one.y;
		one.setLocation(two.x, two.y);
		two.setLocation(x, y);
	}
}

class Line{
	public double slope, yIntercept;
	
	public Line(Point start, Point end){
		double deltaY = end.y - start.y;
		double deltaX = end.x - start.x;
		slope = deltaY / deltaX; //Will be Infinity (not exception) when deltaX = 0
		yIntercept = end.y - slope * end.x; //y = mx + c so c = y - mx
	}
}

class Point{
	public double x, y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(double x, double y){
		this.x = x;
		this.y = y;
	}
}
