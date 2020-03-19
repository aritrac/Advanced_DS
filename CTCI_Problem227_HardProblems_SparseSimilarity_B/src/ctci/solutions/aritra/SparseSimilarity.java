package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/*
 * /*
 *	Question: The similarity of two documents (each with distinct words) is defined to be the size of the intersection divided by the size of the union. For example,
 *	if the documents consists of integers, the similarity of {1,5,3} and {1,7,2,3} is 0.4, because the intersection has size 2 and the union has size 5.
 *	We have a long list of documents(with distinct values and each with an associated ID) where the similarity is believed to be 'sparse'. That is, any two arbitrarily selected documents
 *	are very likely to have similarity 0. Design an algorithm that returns a list of pairs of document ids and the associated similarity.
 *	Print only the pairs with similarity greater than 0. Empty documents should not be printed at all. For simplicity, you may assume each document is represented as an array of distinct
 *	integers.
 *
 *  Example:
 *  Input:
 *  	13: {14,15,100,9,3}
 *  	16: {32,1,9,3,5}
 *  	19: {15,29,2,6,8,7}
 *  	24: {7,10}
 *  
 *  Output:
 *  	ID1, ID2 : SIMILARITY
 *  	 13, 19  : 0.1
 *  	 13, 16  : 0.25
 *  	 19, 24  : 0.14285714285714285
 *  
 *  Solution: Suppose DocA is {14,15,100,9,3}. For a document to have similarity > 0, it needs to have a 14, a 15, a 100, a 9, a 3. How can we quickly gather a list of all documents with one
 *  of those elements? If we build a hash table that maps from a word to all documents that contain that word, we can very quickly know the documents that overlap with docA
 *  
 *  1 -> 16
 *  2 -> 19
 *  3 -> 13,16,24
 *  5 -> 16
 *  6 -> 19
 *  7 -> 19,24
 *  8 -> 19
 *  9 -> 13,16
 *  ...
 *  
 *  One way to tackle this is to analyze what information the hash table gives us. Consider this list of documents.
 *  12 : {1,5,9}
 *  13 : {5,3,1,8}
 *  14 : {4,3,2}
 *  15 : {1,5,9,8}
 *  17 : {1,6}
 *  
 *  If we look up document 12's elements in a hash table for this document, we'll get
 *  1 -> {12,13,15,17}
 *  5 -> {12,13,15}
 *  9 -> {12,15}
 *  
 *  We could go through each document, look up the items in the hashtable, and then count how many times each document appears in each item's lists. There's more direct way to do it.
 *  1. As before, build a hash table for a list of documents
 *  2. Create a new hash table that maps from a document pair to an integer (which will indicate the size of the intersection)
 *  3. Read the first hash table by iterating through each list of documents.
 *  4. For each list of documents, iterate through the pairs in that list. Increment the intersection count for each pair.
 */

public class SparseSimilarity {
	HashMap<DocPair, Double> computeSimilarities(HashMap<Integer, Document> documents){
		HashMapList<Integer, Integer> wordToDocs = groupWords(documents);
		HashMap<DocPair, Double> similarities = computeIntersections(wordToDocs);
		adjustToSimilarities(documents, similarities);
		return similarities;
	}
	
	//Create hash table from each word to where it appears
	HashMapList<Integer, Integer> groupWords(HashMap<Integer,Document> documents){
		HashMapList<Integer, Integer> wordToDocs = new HashMapList<Integer, Integer>();
		
		for(Document doc : documents.values()){
			ArrayList<Integer> words = doc.getWords();
			for(int word : words){
				wordToDocs.put(word, doc.getId());
			}
		}
		return wordToDocs;
	}
	
	//Compute intersections of documents. Iterate through each list of documents and then each pair within that list, incrementing the intersection of each page
	HashMap<DocPair, Double> computeIntersections(HashMapList<Integer, Integer> wordToDocs){
		HashMap<DocPair, Double> similarities = new HashMap<DocPair, Double>();
		Set<Integer> words = wordToDocs.keySet();
		for(int word : words){
			ArrayList<Integer> docs = wordToDocs.get(word);
			Collections.sort(docs);
			for(int i = 0; i < docs.size(); i++){
				for(int j = i + 1; j < docs.size(); j++){
					increment(similarities, docs.get(i),docs.get(j));
				}
			}
		}
		return similarities;
	}
	
	//Increment the intersection size of each document pair.
	void increment(HashMap<DocPair, Double> similarities, int doc1, int doc2){
		DocPair pair = new DocPair(doc1, doc2);
		if(!similarities.containsKey(pair)){
			similarities.put(pair, 1.0);
		}else{
			similarities.put(pair, similarities.get(pair) + 1);
		}
	}
	
	//Adjust the intersection value to become the similarity
	void adjustToSimilarities(HashMap<Integer, Document> documents, HashMap<DocPair, Double> similarities){
		for(Entry<DocPair, Double> entry : similarities.entrySet()){
			DocPair pair = entry.getKey();
			Double intersection = entry.getValue();
			Document doc1 = documents.get(pair.doc1);
			Document doc2 = documents.get(pair.doc2);
			double union = (double)doc1.size() + doc2.size() - intersection;
			entry.setValue(intersection/union);
		}
	}
}

class DocPair{
	public int doc1, doc2;
	
	public DocPair(int d1, int d2){
		doc1 = d1;
		doc2 = d2;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof DocPair){
			DocPair p = (DocPair)o;
			return p.doc1 == doc1 && p.doc2 == doc2;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return (doc1 * 31) ^ doc2;
	}
}

class Document{
	private ArrayList<Integer> words;
	private int docId;
	
	public Document(int id, ArrayList<Integer> w){
		docId = id;
		words = w;
	}
	
	public ArrayList<Integer> getWords(){
		return words;
	}
	
	public int getId(){
		return docId;
	}
	
	public int size(){
		return words == null ? 0 : words.size();
	}
}

class HashMapList<T,E>{
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();
	
	//Insert item into list at key.
	public void put(T key, E item){
		if(!map.containsKey(key)){
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}
	
	//Insert list of items at key
	public void put(T key, ArrayList<E> items){
		map.put(key, items);
	}
	
	//Get list of items at key
	public ArrayList<E> get(T key){
		return map.get(key);
	}
	
	//Check if hashmaplist contains key
	public boolean containsKey(T key){
		return map.containsKey(key);
	}
	
	//Check if list at key contains value
	public boolean containsKeyValue(T key, E value){
		ArrayList<E> list = get(key);
		if(list == null)
			return false;
		return list.contains(value);
	}
	
	//Get the list of keys
	public Set<T> keySet(){
		return map.keySet();
	}
	
	@Override
	public String toString(){
		return map.toString();
	}
}

