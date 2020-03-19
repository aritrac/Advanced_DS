package ctci.solutions.aritra;

/*
 * Question: given a list of millions of words, design an algorithm to create the largest possible rectangle of letters such that every row forms a word (reading left to right) and
 * every column forms a word (reading top to bottom). The words need not be chosen consecutively from the list, but all rows must be the same length and all columns must be the same height.
 * 
 * Solution: If we're going to create a rectangle of words, we know that each row must be the same length and each column must be the same length. So let's group the words of the dictionary
 * based on their sizes. Let's call this grouping D, where D[i] contains the list of words of length i.
 * Next, observe that we're looking for the largest rectangle. What is the largest rectangle that could be formed? It's length (largest_word)^2
 * 
 * By iterating from the biggest possible rectangle to the smallest, we ensure that the first valid rectangle we find will be the largest possible one.
 * 
 * Now, for the hard part: makeRectangle(int l, int h). This method attempts to build a rectangle of words which has length l and height h.
 * One way to do this is to iterate through all (ordered) sets of h words and then check if the columns are also valid words. This will work, but it's rather inefficient.
 * 
 * Image that we are trying to build a 6x5 rectangle and the first few rows are:
 * 
 * there
 * queen
 * pizza
 * .....
 * 
 * At this point, we know that the first column starts with tqp. We know there is no dictionary word that starts with tqp. So why bother continuing to build a rectangle when we know
 * we'll fail to create a valid one in the end?
 * 
 * This leads us to a more optimal solution. We can build a trie to easily lookup if a substring is a prefix of a word in the dictionary. Then we will build our rectangle, row by row,
 * we check to see if the columns are all valid prefixes. If not, we fail immediately, rather than continue to try to build this rectangle.
 * The code below implements the following algorithm.
 * 
 * The steps in the code are as follows:
 * 
 * 1.First, we do some pre-processing to group words by their lengths. We create an array of tries(one for each word length) but hold off on building the tries until we need them.
 * 
 * 2.The maxRectangle method is the main part of our code.It starts with the biggest possible rectangle area(which is maxWordLength^2 and tries to build a rectangle of that size.If it fails,
 * it subtracts one from the area and attempts this new, smaller size. The first rectangle that can be successfully built is guaranteed to be the biggest)
 * 
 * 3.The makeRectangle method is called by maxRectangle and tries to build a rectangle of a specific length and height.
 * 
 * 4.The makePartialRectangle method is where the action happens. It is passed in the intended final length and height and a partially formed rectangle. If the rectangle is already of the final
 * height, then we just need to check if the column form valid, complete words and return.
 * Otherwise, we check to see if the columns form valid prefixes. If they do not, then we immediately break since there is no way to build a valid rectangle off of this partial one.
 * But, if everything is okay so far, and all the columns are valid prefixes of words, then we search through all the words of the right length, append each to the current rectangle,
 * and recursively try to build a rectangle off of {current rectangle with new word appended}
 * 
 * 5.The Rectangle class represents a partially or fully formed rectangle of words. The method isPartialOk can be called to check if the rectangle is, thus far, a valid one
 * (that is, all the columns are prefixes of words). The method isComplete serves a similar function, but checks if each of the columns makes a full word.
 * 
 * 6.The WordGroup class is a simple container for all words of a specific length. For easy lookup, we store the words in a HashTable as well as in an ArrayList. The list
 * in WordGroup are created through a static method called createWordGroups.
 */

import java.util.ArrayList;

public class WordRectangle {
	private int maxWordLength;
	private WordGroup[] groupList ;
	private Trie trieList[];

	public WordRectangle(String[] list) {
		groupList = WordGroup.createWordGroups(list);
		maxWordLength = groupList.length;
		// Initialize trieList to store trie of groupList[i] at ith position.
		trieList = new Trie[maxWordLength];
	}

	/* This function finds a rectangle of letters of the largest 
	 * possible area (length x breadth) such that every row forms a 
	 * word (reading left to right) from the list and every column 
	 * forms a word (reading top to bottom) from the list. 
	 */
	public Rectangle maxRectangle() {
		// The dimensions of the largest possible rectangle.
		int maxSize = maxWordLength * maxWordLength; 

		for (int z = maxSize; z > 0; z--) {
			// Find out all pairs i,j less than maxWordLength 
			// such that i * j = z
			for (int i = 1; i <= maxWordLength; i ++ ) {
				if (z % i == 0) {
					int j = z / i;
					if (j <= maxWordLength) {
						// Check if a Rectangle of length i and height 
						// j can be created. 
						Rectangle rectangle = makeRectangle(i,j);
						if (rectangle != null) {
							return rectangle;
						}
					}
				}
			}
		}
		return null;
	}

	/* This function takes the length and height of a rectangle as
	 * arguments. It tries to form a rectangle of the given length and 
	 * height using words of the specified length as its rows, in which 
	 * words whose length is the specified height form the columns. It 
	 * returns the rectangle so formed, and null if such a rectangle 
	 * cannot be formed.
	 */
	private Rectangle makeRectangle(int length, int height) {
		if (groupList[length - 1] == null || groupList[height - 1] == null) {
			return null;
		}
		if (trieList[height - 1] == null) {
			ArrayList<String> words = groupList[height - 1].getWords();
			trieList[height - 1] = new Trie(words); 
		}
		return makePartialRectangle(length, height, new Rectangle(length));
	}


	/* This function recursively tries to form a rectangle with words
	 * of length l from the dictionary as rows and words of length h
	 * from the dictionary as columns. To do so, we start with an empty
	 * rectangle and add in a word with length l as the first row. We
	 * then check the trie of words of length h to see if each partial
	 * column is a prefix of a word with length h. If so we branch
	 * recursively and check the next word till we've formed a complete
	 * rectangle. When we have a complete rectangle check if every
	 * column is a word in the dictionary.
	 */
	private Rectangle makePartialRectangle(int l, int h, Rectangle rectangle) {

		// Check if we have formed a complete rectangle by seeing if each column
		// is in the dictionary
		if (rectangle.height == h) {
			if (rectangle.isComplete(l, h, groupList[h - 1])) {
				return rectangle;
			} else {
				return null;
			}
		}

		// If the rectangle is not empty, validate that each column is a
		// substring of a word of length h in the dictionary using the
		// trie of words of length h.
		if (!rectangle.isPartialOK(l, trieList[h - 1])) {
			return null;
		}
		
		// For each word of length l, try to make a new rectangle by adding
		// the word to the existing rectangle.
		for (int i = 0; i < groupList[l-1].length(); i++) {
			Rectangle orgPlus = rectangle.append(groupList[l-1].getWord(i));
			Rectangle rect = makePartialRectangle(l, h, orgPlus);
			if (rect != null) {
				return rect;
			}
		}
		return null;
	}

	// Test harness.
	public static void main(String[] args) {
		WordRectangle dict = new WordRectangle(AssortedMethods.getListOfWords());
		Rectangle rect = dict.maxRectangle();
		if (rect != null) {
			rect.print();
		} else {
			System.out.println ("No rectangle exists");
		}
	}


}

