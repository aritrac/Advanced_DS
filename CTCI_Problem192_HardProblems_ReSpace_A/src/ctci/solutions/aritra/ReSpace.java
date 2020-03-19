package ctci.solutions.aritra;

import java.util.HashSet;

/*
 * Question: You have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. A sentence like 'I reset the computer. It still didn't boot!' became
 * 'iresetthecomputeritstilldidntboot'. You'll deal with the punctuation and capitalization later, right now you need to insert the spaces. Most of the words are
 * in a dictionary but a few are not. Given a dictionary (a list of strings) and the document (a string), design an algorithm to unconcatenate the document in a way that minimizes
 * the number of unrecognized characters.
 * 
 * Example:
 * Input: jesslookedjustliketimherbrother
 * Output: [jess] looked just like [tim] her brother (7 unrecognized characters)
 * 
 * Solution: The key to this problem is finding a way to define the solution(that is, parsed string) in terms of its subproblems. One way to do this is recursing
 * through the string.
 * The very first choice we make is where to insert the first space. After the first character? Second character? Third character?
 * Let's imagine this in terms of a string like thisismikesfavouritefood. What is the first space we insert?
 * >>If we insert a space after t, this gives us one invalid character
 * >>After th is two invalid characters
 * >>After thi is three invalid characters
 * >>At this we have a complete word. This is zero invalid characters.
 * >>At thisi is five invalid characters
 * >>... and so on
 * After we choose the first space, we can recursively pick the second space, then the third space, and so on, until we are done with the string.
 * We take the best (fewest invalid characters) out of all these choices and return.
 * What should the function return? We need both the number of invalid characters in the recursive path as well as the actual parsing. Therefore, we just return both by using a custom-built
 * ParseResult class.
 */

public class ReSpace {
	String bestSplit(HashSet<String> dictionary, String sentence){
		ParseResult r = split(dictionary, sentence, 0);
		return r == null ? null : r.parsed;
	}
	
	ParseResult split(HashSet<String> dictionary, String sentence, int start){
		if(start >= sentence.length()){
			return new ParseResult(0,"");
		}
		int bestInvalid = Integer.MAX_VALUE;
		String bestParsing = null;
		String partial = "";
		int index = start;
		while(index < sentence.length()){
			char c = sentence.charAt(index);
			partial += c;
			int invalid = dictionary.contains(partial) ? 0 : partial.length();
			if(invalid < bestInvalid){//Short circuit
				//Recurse, putting a space after this character. If this is better than the current best option, replace the best option
				ParseResult result = split(dictionary, sentence, index + 1);
				if(invalid + result.invalid < bestInvalid){
					bestInvalid = invalid + result.invalid;
					bestParsing = partial + " " + result.parsed;
					if(bestInvalid == 0) //Short circuit
						break;
				}
				
			}
			index++;
		}
		return new ParseResult(bestInvalid, bestParsing);
	}
}

class ParseResult{
	public int invalid = Integer.MAX_VALUE;
	public String parsed = " " ;
	public ParseResult(int inv, String p){
		invalid = inv;
		parsed = p;
	}
}
