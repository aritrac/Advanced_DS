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
 * Solution: Let's again imagine the string thisismikesfavoritefood. Again, imagine that everything is a valid word.
 * In this case, we attempt to insert the first space after t as well as after th (and many other choices). Think about what the next choice is.
 * 
 * split(thisismikesfavoritefood) ->
 * 		t + split(hisismikesfavoritefood)
 * 	 OR th + split(isismikesfavoritefood)
 *   OR ..
 *   
 * split(hisismikesfavoritefood) ->
 * 		h + split(isismikesfavoritefood)
 *   OR ...
 *   
 * ...
 * 
 * Adding a space after t and h leads to the same recursive path as inserting a space after th. There's no sense in computing split(isismikesfavoritefood) twice when it will lead to the
 * same result.
 * We should instead cache the result. We do this using a hashtable which maps from the current substring to the ParseResult object.
 * We don't actually need to make the current substring a key. The start index in the string sufficiently represents the substring. After all, if we were to use the substring, we'd really be using
 * sentence.substring(start, sentence.length). This hashtable will map from a start index to the best parsing from that index to the end of the string.
 * And, since the start index is the key, we don't need a true hash table at all. We can just use an array of ParseResult objects. This will also serve the purpose of mapping
 * from an index to an object.
 * 
 * The code is essentially identical to the earlier function, but now takes in a memo table(a cache). We look up when we first call the function and set it when we return.
 */

public class ReSpace {
	String bestSplit(HashSet<String> dictionary, String sentence){
		ParseResult[] memo = new ParseResult[sentence.length()];
		ParseResult r = split(dictionary, sentence, 0, memo);
		return r == null ? null : r.parsed;
	}
	
	ParseResult split(HashSet<String> dictionary, String sentence, int start, ParseResult[] memo){
		if(start >= sentence.length()){
			return new ParseResult(0,"");
		}
		if(memo[start] != null){
			return memo[start];
		}
		
		int bestInvalid = Integer.MAX_VALUE;
		String bestParsing = null;
		String partial = "";
		int index = start;
		while(index < sentence.length()){
			char c = sentence.charAt(index);
			partial += c;
			int invalid = dictionary.contains(partial) ? 0 : partial.length();
			if(invalid < bestInvalid){ //Short circuit
				//Recurse, putting a space afte this character. If this is better than the current best option, replace the best option.
				ParseResult result = split(dictionary, sentence, index + 1, memo);
				if(invalid + result.invalid < bestInvalid){
					bestInvalid = invalid + result.invalid;
					bestParsing = partial + " " + result.parsed;
					if(bestInvalid == 0) //Short circuit
						break;
				}
			}
			index++;
		}
		memo[start] = new ParseResult(bestInvalid, bestParsing);
		return memo[start];
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
