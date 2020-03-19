package ctci.solution.aritra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Question: Each year, the government releases a list of the 10,000 most common baby names and their frequencies(the number of babies with that name). The only problem with this
 * is that some names have multiple spellings. For example, "John" and "Jon" are essentially the same name but would be listed separately in the list. Give n two lists, one of names
 * /frequencies and the other of pairs of equivalent names, write an algorithm to print a new list of the true frequency of each name. Note that if John and Jon are synonyms and Jon and Johnny
 * are synonyms. (It is both transitive and symmetric) In the final list, any name can be used as the real name.
 * 
 * 			NAME				COUNT
 * 		    John				 10
 * 			Jon					 3
 * 			Davis				 2
 * 			Kari				 3
 * 			Johnny				 11
 * 			Carlton				 8
 * 			Carleton			 2
 * 			Jonathan			 9
 * 			Carrie				 5
 * 
 * 
 * 			NAME				ALTERNATE
 * 			Jonathan			John
 * 			Jon					Johnny
 * 			Johnny				John
 * 			Kari				Carrie
 * 			Carleton			Carlton
 * 
 * Example:
 * Input:
 * Names: John(15), Jon(12), Chris(13), Kris(4), Christopher(19)
 * Synonyms: (Jon,John), (John,Johnny), (Chris,Kris), (Chris, Christopher)
 * 
 * Output: John(27), Kris(36)
 * 
 * Solution: We can think of these names as "equivalence classes". When we find a pair (Jonathan, John), we put these in the same set (or equivalence classes). Each
 * name maps to its equivalence class. All items in the set map to the same instance of the set. If we need to merge two sets, then we copy one set
 * into the other and update the hash table to point to the new set.
 * 
 * READ(Jonathan, John)
 * 		CREATE Set1 = Jonathan, John
 * 		L1.ADD Jonathan -> Set1
 * 		L1.ADD John -> Set1
 * 
 * READ(Jon, Johnny)
 * 		CREATE Set2 = Jon, Johnny
 * 		L1.ADD Jon -> Set2
 * 		L1.ADD Johnny -> Set2
 * 
 * READ(Johnny, John)
 * 		COPY Set2 into Set1
 * 			Set1 = Jonathan, John, Jon, Johnny
 * 		L1.UPDATE Jon -> Set1
 * 		L1.UPDATE Johnny -> Set1
 * 
 * In the last step above, we iterated through all items in Set2 and updated the reference to point to Set1. As we do this, we keep track of the total frequency of names
 */

public class BabyNames {
	HashMap<String, Integer> trulyMostPopular(HashMap<String, Integer> names, String[][] synonyms){
		//Parse list and initialize equivalence classes
		HashMap<String, NameSet> groups = constructGroups(names);
		
		//Merge equivalence classes together.
		mergeClasses(groups, synonyms);
		
		//Convert back to hash map
		return convertToMap(groups);
	}
	
	//This is the core of the algorithm. Read through each pair. Merge their equivalence classes and update the mapping of the secondary class to point to the first set
	void mergeClasses(HashMap<String, NameSet> groups, String[][] synonyms){
		for(String[] entry : synonyms){
			String name1 = entry[0];
			String name2 = entry[1];
			NameSet set1 = groups.get(name1);
			NameSet set2 = groups.get(name2);
			if(set1 != set2){
				//Always merge the smaller set into the bigger one
				NameSet smaller = set2.size() < set1.size() ? set2 : set1;
				NameSet bigger = set2.size() < set1.size() ? set1 : set2;
				
				//Merge lists
				Set<String> otherNames = smaller.getNames();
				int frequency = smaller.getFrequency();
				bigger.copyNamesWithFrequency(otherNames, frequency);
				
				//Update mapping
				for(String name : otherNames){
					groups.put(name, bigger);
				}
			}
		}
	}
	
	//Read (name, frequency) pairs and initialize a mapping of names to NameSets (equivalence classes).
	HashMap<String, NameSet> constructGroups(HashMap<String, Integer> names){
		HashMap<String, NameSet> groups = new HashMap<String, NameSet>();
		for(Entry<String, Integer> entry : names.entrySet()){
			String name = entry.getKey();
			int frequency = entry.getValue();
			NameSet group = new NameSet(name, frequency);
			groups.put(name, group);
		}
		return groups;
	}
	
	HashMap<String, Integer> convertToMap(HashMap<String, NameSet> groups){
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		for(NameSet group : groups.values()){
			list.put(group.getRootName(), group.getFrequency());
		}
		return list;
	}
}

class NameSet{
	private Set<String> names = new HashSet<String>();
	private int frequency = 0;
	private String rootName;
	
	public NameSet(String name, int freq){
		names.add(name);
		frequency = freq;
		rootName = name;
	}
	
	public void copyNamesWithFrequency(Set<String> more, int freq){
		names.addAll(more);
		frequency += freq;
	}
	
	public Set<String> getNames(){
		return names;
	}
	
	public String getRootName(){
		return rootName;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public int size(){
		return names.size();
	}
}
