package ctci.solution.aritra;

/*
 * Question: You have a large text file containing words. Given any two words, find the shortest distance (in terms of number of words) between them in the file.
 * If the operation will be repeated many times for the same file (but different pairs of words), can you optimize your solution?
 * 
 * Solution: To solve this problem, we can traverse the file just once. We remember throughout our traversal where we've last seen word1 and word2, storing the locations in location1
 * and location2. If the current locations are better than our best known location, we update the best locations.
 * 
 * The code below implements the algorithm
 */

public class WordDistance {
	LocationPair findClosest(String[] words, String word1, String word2){
		LocationPair best = new LocationPair(-1,-1);
		LocationPair current = new LocationPair(-1,-1);
		for(int i = 0; i < words.length; i++){
			String word = words[i];
			if(word.equals(word1)){
				current.location1 = i;
				best.updateWithMin(current);
			}else if(word.equals(word2)){
				current.location2 = i;
				best.updateWithMin(current); //If shorter, update values
			}
		}
		return best;
	}
}

class LocationPair{
	public int location1, location2;
	
	public LocationPair(int first, int second){
		setLocations(first, second);
	}
	
	public void setLocations(int first, int second){
		this.location1 = first;
		this.location2 = second;
	}
	
	public void setLocations(LocationPair loc){
		setLocations(loc.location1, loc.location2);
	}
	
	public int distance(){
		return Math.abs(location1 - location2);
	}
	
	public boolean isValid(){
		return location1 >= 0 && location2 >= 0;
	}
	
	public void updateWithMin(LocationPair loc){
		if(!isValid()||loc.distance() < distance()){
			setLocations(loc);
		}
	}
}
