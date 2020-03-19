package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Question: Given two words of equal length that are in a dictionary, write a method to transform one word into another word by changing only one letter at a time. The new word
 * you get in each step must be in the dictionary
 * 
 * Example:
 * INPUT: 	DAMP,LIKE
 * OUTPUT:	DAMP -> LAMP -> LIMP -> LIME -> LIKE
 * 
 * Solution: One optimization is to switch from depth-first search to breadth-first search. If there are zero paths or one path, the algorithms are equivalent speeds. However,
 * if there are multiple paths, breadth-first search may run faster.
 * 
 * Breadth-first search finds the shortest path between two nodes, whereas depth-first search finds any path. This means that depth first search might take a very long, windy path
 * in order to find a connection when, in fact, the nodes were quite close.
 * 
 * As noted earlier, we can optimize this using breadth-first search. Is this as fast as we can make it? Not quite.
 * Imagine that the path between two nodes has length 4. With breadth-first search, we will visit about 15^4 nodes to find them.
 * Breadth-first search spans out very quickly.
 * Instead what if we searched out from the source and destination nodes simultaneously? In this case, the breadth-first searches would collide after each had done about two levels.
 * >>Nodes travelled from source : 15^2
 * >>Nodes travelled from destination : 15^2
 * >>Total Nodes: 15^2 + 15^2
 * This is much better than the traditional breadth-first search.
 * We will need to track the path that we've travelled at each node.
 * To implement this approach we've used an additional class BFSData. BFSData helps us keep things a bit clearer, and allows us
 * to keep a similar framework for the two simultaneous breadth-first searches. The alternative is to keep passing around a bunch of separate variables.
 */

public class WordTransformer {
	
	LinkedList<String> transform(String startWord, String stopWord, String[] words){
		HashMapList<String, String> wildcardToWordList = createWildcardToWordMap(words);
		
		BFSData sourceData = new BFSData(startWord);
		BFSData destData = new BFSData(stopWord);
		
		while(!sourceData.isFinished() && !destData.isFinished()){
			//Search out from source
			String collision = searchLevel(wildcardToWordList, sourceData, destData);
			if(collision != null){
				return mergePaths(sourceData, destData, collision);
			}
			
			//Search out from destination
			collision = searchLevel(wildcardToWordList, destData, sourceData);
			if(collision != null){
				return mergePaths(sourceData, destData, collision);
			}
		}
		return null;
	}
	
	//Search one level and return collision, if any
	String searchLevel(HashMapList<String, String> wildcardToWordList, BFSData primary, BFSData secondary){
		//We only want to search one level at a time. Count how many nodes are currently in the primary's level and only do that many nodes. We'll continue to add nodes to the end
		int count = primary.toVisit.size();
		for(int i = 0; i < count; i++){
			//Pull out first node
			PathNode pathNode = primary.toVisit.poll();
			String word = pathNode.getWord();
			
			//Check if it's already been visited
			if(secondary.visited.containsKey(word)){
				return pathNode.getWord();
			}
			
			//Add friends to queue
			ArrayList<String> words = getValidLinkedWords(word, wildcardToWordList);
			
			for(String w : words){
				if(!primary.visited.containsKey(w)){
					PathNode next = new PathNode(w, pathNode);
					primary.visited.put(w, next);
					primary.toVisit.add(next);
				}
			}
		}
		return null;
	}
	
	LinkedList<String> mergePaths(BFSData bfs1, BFSData bfs2, String connection){
		PathNode end1 = bfs1.visited.get(connection); //end1 -> source
		PathNode end2 = bfs2.visited.get(connection); //end2 -> dest
		LinkedList<String> pathOne = end1.collapse(false); //forward
		LinkedList<String> pathTwo = end2.collapse(true); //reverse
		pathTwo.removeFirst(); //remove connection
		pathOne.addAll(pathTwo); //add second path
		return pathOne;
	}
	
	// Get list of wildcards associated with word
	ArrayList<String> getWildcardRoots(String w) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < w.length(); i++) {
			String word = w.substring(0, i) + "_" + w.substring(i + 1);
			words.add(word);
		}
		return words;
	}

	// Insert words in dictionary into mapping from wildcard form -> word.
	HashMapList<String, String> createWildcardToWordMap(String[] words) {
		HashMapList<String, String> wildcardToWords = new HashMapList<String, String>();
		for (String word : words) {
			ArrayList<String> linked = getWildcardRoots(word);
			for (String linkedWord : linked) {
				wildcardToWords.put(linkedWord, word);
			}
		}
		return wildcardToWords;
	}

	// Return words that are one edit away
	ArrayList<String> getValidLinkedWords(String word,
			HashMapList<String, String> wildcardToWords) {
		ArrayList<String> wildcards = getWildcardRoots(word);
		ArrayList<String> linkedWords = new ArrayList<String>();
		for (String wildcard : wildcards) {
			ArrayList<String> words = wildcardToWords.get(wildcard);
			for (String linkedWord : words) {
				if (!linkedWord.equals(word)) {
					linkedWords.add(linkedWord);
				}
			}
		}
		return linkedWords;
	}
}

class BFSData {
	public Queue<PathNode> toVisit = new LinkedList<PathNode>();
	public HashMap<String, PathNode> visited = new HashMap<String, PathNode>();

	public BFSData(String root) {
		PathNode sourcePath = new PathNode(root, null);
		toVisit.add(sourcePath);
		visited.put(root, sourcePath);
	}

	public boolean isFinished() {
		return toVisit.isEmpty();
	}
}

class PathNode {
	private String word = null;
	private PathNode previousNode = null;

	public PathNode(String word, PathNode previous) {
		this.word = word;
		previousNode = previous;
	}

	public String getWord() {
		return word;
	}

	// Traverse path and return linked list of nodes
	public LinkedList<String> collapse(boolean startsWithRoot) {
		LinkedList<String> path = new LinkedList<String>();
		PathNode node = this;
		while (node != null) {
			if (startsWithRoot) {
				path.addLast(node.word);
			} else {
				path.addFirst(node.word);
			}
			node = node.previousNode;
		}
		return path;
	}
}

class HashMapList<T, E> {
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();

	// Insert item into list at key.
	public void put(T key, E item) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}

	// Insert list of items at key
	public void put(T key, ArrayList<E> items) {
		map.put(key, items);
	}

	// Get list of items at key
	public ArrayList<E> get(T key) {
		return map.get(key);
	}

	// Check if hashmaplist contains key
	public boolean containsKey(T key) {
		return map.containsKey(key);
	}

	// Check if list at key contains value
	public boolean containsKeyValue(T key, E value) {
		ArrayList<E> list = get(key);
		if (list == null)
			return false;
		return list.contains(value);
	}

	// Get the list of keys
	public Set<T> keySet() {
		return map.keySet();
	}

	@Override
	public String toString() {
		return map.toString();
	}
}