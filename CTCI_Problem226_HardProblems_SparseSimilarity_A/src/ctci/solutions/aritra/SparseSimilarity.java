package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
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
 * Solution: A brute force algorithm is as simple as just comparing all arrays to all other arrays. At each comparison, we compute the size of the intersection and size of the union of the two
 * arrays. Note that we only want to print this pair if the similarity is greater than 0. The union of two arrays can never be zero (unless both arrays are empty, in which case we don't
 * want them printed anyway). Therefore, we are really just printing the similarity if the intersection is greater than 0. How do we compute the size of the intersection and the union?
 * The intersection means the number of elements in common. Therefore, we can just iterate through the first array (A) and check if each element is in the second array (B).If it is, increment
 * an intersection variable.
 * To compute union, we need to be sure that we don't double count elements that are in both. One way to do this is to count up all the elements in A that are not in B. Then,
 * add in all the elements in B. This will avoid double counting as the duplicate elements are only counted with B.
 * Alternatively we can think about it this way. If we did double count elements, it would mean that elements in the intersection (in both A and B) were counted twice. therefore, the easy fix is to just
 * remove these duplicate elements.
 * 		union(A, B) = A + B - intersection(A, B)
 * This means that all we really need to do is to compute the intersection. We can derive the union, and therefore similarity, from that immediately.
 * 
 * As a quick win we can optimize the computation for the similarity of two arrays. Specifically, we need to optimize the intersection computation. We need to know the number of elements
 * in common between the two arrays. We can throw all of A's elements into a hash table. Then we iterate through B, incrementing intersection everytime we find an element in A.
 * 
 * We'll need to return a list of document pairs and their similarities. We'll use a DocPair class for this. The exact return type will be a hashtable that maps
 * from DocPair to a double representing the similarity.
 *   
 * It will also be useful to have a class that represents the documents.
 * 
 * Strictly speaking, we don't need any of this. However, readability is important, and it's a lot easier to read ArrayList<Document> than ArrayList<ArrayList<Integer>>
 * 
 */

public class SparseSimilarity {
	HashMap<DocPair, Double> computeSimilarities(ArrayList<Document> documents){
		HashMap<DocPair, Double> similarities = new HashMap<DocPair, Double>();
		for(int i = 0; i < documents.size(); i++){
			for(int j = i + 1; j < documents.size(); j++){
				Document doc1 = documents.get(i);
				Document doc2 = documents.get(j);
				double sim = computeSimilarity(doc1, doc2);
				if(sim > 0){
					DocPair pair = new DocPair(doc1.getId(), doc2.getId());
					similarities.put(pair, sim);
				}
			}
		}
		return similarities;
	}
	
	double computeSimilarity(Document doc1, Document doc2){
		int intersection = 0;
		HashSet<Integer> set1 = new HashSet<Integer>();
		set1.addAll(doc1.getWords());
		
		for(int word : doc2.getWords()){
			if(set1.contains(word)){
				intersection++;
			}
		}
		
		double union = doc1.size() + doc2.size() - intersection;
		return intersection / union;
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
