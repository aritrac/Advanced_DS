package ctci.solution.aritra;

/*
 * Question: Design a method to find the frequency of occurrences of any given word in a book. What if we were running this algorithm multiple times?
 * 
 * Solution: In this case, we can simply go through the book word by word and count the number of times that a word appears. This will take O(n) time.
 * We know we can't do better than that since we must look at every word in the book.
 */

public class WordFrequencies {
	int getFrequency(String[] book, String word){
		word = word.trim().toLowerCase();
		int count = 0;
		for(String w : book){
			if(w.trim().toLowerCase().equals(word)){
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		String[] book = "I was alone in the jungle when it happened and then again we forgot most of it but those eyes I still remember those eyes".split(" ");
		WordFrequencies wf = new WordFrequencies();
		System.out.println("The number of times 'it' word occurs = " + wf.getFrequency(book, "it"));
	}
}
