package ctci.solutions.aritra;

/*
 * Question: Write a method to shuffle a deck of cards. If must be a perfect shuffle - in other words, each of the 52! permutations of the deck has to be equally likely.
 * Assume that you are given a random number generator which is perfect.
 * 
 * Solution: We would first shuffle the first n - 1 elements. Then, we would take the nth element and randomly swap it with an element in the array. That's it.
 * Iteratively, the algorithm looks like this
 */

public class Shuffle {

	// Random number between lower and higher, inclusive
	int rand(int lower, int higher) {
		return lower + (int) (Math.random() * (higher - lower + 1));
	}
	
	void shuffleArrayIteratively(int[] cards){
		for(int i = 0; i < cards.length; i++){
			int k = rand(0,i);
			int temp = cards[k];
			cards[k] = cards[i];
			cards[i] = temp;
		}
	}
}
