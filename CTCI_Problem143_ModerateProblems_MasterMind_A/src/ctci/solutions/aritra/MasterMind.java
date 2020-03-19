package ctci.solutions.aritra;

/*
 * Question: The Game of Master Mind is played as follows
 * The computer has four slots, and each slot will contain a ball that is red(R), yellow(Y),green(G) or blue(B). For example the computer might have RGGB
 * (slot #1 is red, Slot #2 and #3 is green, slot #4 is blue)
 * You, the user, are trying to guess the solution. You might, for example, guess YRGB.
 * When you guess the correct color for the correct slot, you get a hit. If you guess a color that exists but is in the wrong slot, you get a 'pseudo' hit.
 * Note that a slot that is a hit can never count as a pseudo-hit
 * For example, if the actual solution is RGBY and you guess GGRR, you have one hit and one pseudo-hit.
 * Write a method that, given a guess and a solution, returns the number of hits and pseudo-hits
 * 
 * Solution: This problem is very straight forward, but it's surprisingly easy to make little mistakes. You should check your code extremely thoroughly,
 * on a variety of test cases.
 * We'll implement this code by first creating a frequency array which stores how many times each character occurs in solution excluding times when the slot
 * is a 'hit'. Then, we iterate through guess to count the number of pseudo-hits
 * The code below implements this algorithm
 */

public class MasterMind {
	
	int MAX_COLORS = 4;
	
	int code(char c){
		switch(c){
			case 'B':
				return 0;
			case 'G':
				return 1;
			case 'R':
				return 2;
			case 'Y':
				return 3;
			default:
				return -1;
		}
	}
	
	Result estimate(String guess, String solution){
		if(guess.length() != solution.length())
			return null;
		Result res = new Result();
		int[] frequencies = new int[MAX_COLORS];
		
		//Compute hits and build frequency table
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) == solution.charAt(i)){
				res.hits++;
			}else{
				//Only increment the frequency table (which will be used for the pseudo-hits) if it's not a hit. If it's a hit, the slot has already been 'used'
				int code = code(solution.charAt(i));
				frequencies[code]++;
			}
		}
		//Compute pseudo hits
		for(int i = 0; i < guess.length(); i++){
			int code = code(guess.charAt(i));
			if(code >= 0 && frequencies[code] > 0 && guess.charAt(i) != solution.charAt(i)){
				res.pseudoHits++;
				frequencies[code]--;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String guess = "GGRR";
		String solution = "RGBY";
		Result res = new MasterMind().estimate(guess, solution);
		System.out.println(res);
	}
}

class Result{
	public int hits = 0;
	public int pseudoHits = 0;
	
	public String toString(){
		return "(" + hits + ", " + pseudoHits + ")";
	}
}
