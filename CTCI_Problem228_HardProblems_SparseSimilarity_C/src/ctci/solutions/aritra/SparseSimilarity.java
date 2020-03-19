package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

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
 *  Solution: Recall our earlier algorithm that computed the similarity between two documents by sorting them. We can extend this approach to multiple documents.
 *  Imagine we took all of the words, tagged them by their original document, and then sorted them. The prior list of documents would look like this
 *  
 *  1[12], 1[13], 1[15], 1[16], 2[14], 3[13], 3[14], 4[14], 5[12], 5[13], 5[15], 6[16], 8[13], 8[15], 9[12], 9[15]
 *  Now, we have essentially the same approach as before. We iterate through this list of elements. For each sequence of identical elements, we increment the intersection counts for
 *  the corresponding pair of documents.
 *  We will use Element class to group together documents and words. When we sort the list, we will sort the list, we will sort first on the word but break ties on the document ID.
 */

public class SparseSimilarity {
	
	HashMap<DocPair, Double> computeSimilarities(HashMap<Integer, Document> documents){
		ArrayList<Element> elements = sortWords(documents);
		HashMap<DocPair, Double> similarities = computeIntersections(elements);
		adjustToSimilarities(documents, similarities);
		return similarities;
	}
	
	//Throw all words into one list, sorting by the word and then the document
	ArrayList<Element> sortWords(HashMap<Integer, Document> docs){
		ArrayList<Element> elements = new ArrayList<Element>();
		for(Document doc : docs.values()){
			ArrayList<Integer> words = doc.getWords();
			for(int word : words){
				elements.add(new Element(word, doc.getId()));
			}
		}
		Collections.sort(elements);
		return elements;
	}
	
	//Increment the intersection size of each document pair
	void increment(HashMap<DocPair, Double> similarities, int doc1, int doc2){
		DocPair pair = new DocPair(doc1, doc2);
		if(!similarities.containsKey(pair)){
			similarities.put(pair, 1.0);
		}else{
			similarities.put(pair, similarities.get(pair) + 1);
		}
	}
	
	//Adjust the intersection value to become the similarity
	HashMap<DocPair, Double> computeIntersections(ArrayList<Element> elements){
		HashMap<DocPair, Double> similarities = new HashMap<DocPair, Double>();
		
		for(int i = 0; i < elements.size(); i++){
			Element left = elements.get(i);
			for(int j = i + 1; j < elements.size(); j++){
				Element right = elements.get(j);
				if(left.word != right.word){
					break;
				}
				increment(similarities, left.document, right.document);
			}
		}
		return similarities;
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

class Element implements Comparable<Element>{
	public int word, document;
	public Element(int w, int d){
		word = w;
		document = d;
	}
	
	//When we sort the words, this function will be used to compare the words
	public int compareTo(Element e){
		if(word == e.word){
			return document - e.document;
		}
		return word - e.word;
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
