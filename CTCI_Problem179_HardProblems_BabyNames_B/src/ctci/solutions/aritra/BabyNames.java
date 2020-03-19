package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/*
 * Question: Each year, the government releaes a list of the 10,000 most common baby names and their frequencies(the number of babies with that name). The only problem with this
 * is that some names have multiple spellings. For example, "John" and "Jon" are essentially the same name but would be listed separately in the list. Give ntwo lists, one of names
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
 * Solution: We will do the following approach using a graph. We just need to group the names by their component, sum up their frequencies, and return a list with one arbitrarily
 * chosen name from each group.
 * In practice, how does this work? We could pick a name and do a depth-first (or breadth-first) search to sum the frequencies of all the names in one component. We would
 * have to make sure that we hit each component exactly once. That's easy enough to achieve: mark a node as visited after it's discovered in the graph search,
 * and only start the search for nodes where visited is false.
 */

public class BabyNames {
	HashMap<String, Integer> trulyMostPopular(HashMap<String, Integer> names, String[][] synonyms){
		//Create data
		Graph graph = constructGraph(names);
		connectEdges(graph, synonyms);
		
		//find components
		HashMap<String, Integer> rootNames = getTrueFrequencies(graph);
		return rootNames;
	}
	
	//Add all names to graph as nodes
	Graph constructGraph(HashMap<String, Integer> names){
		Graph graph = new Graph();
		for(Entry<String, Integer> entry : names.entrySet()){
			String name = entry.getKey();
			int frequency = entry.getValue();
			graph.createNode(name, frequency);
		}
		return graph;
	}
	
	//Connect synonymous spellings
	void connectEdges(Graph graph, String[][] synonyms){
		for(String[] entry : synonyms){
			String name1 = entry[0];
			String name2 = entry[1];
			graph.addEdge(name1, name2);
		}
	}
	
	//Do DFS of each component. If a node has been visited before, then its component has already been computed.
	HashMap<String, Integer> getTrueFrequencies(Graph graph){
		HashMap<String, Integer> rootNames = new HashMap<String, Integer>();
		for(GraphNode node : graph.getNodes()){
			if(!node.isVisited()){//Already visited this component
				int frequency = getComponentFrequency(node);
				String name = node.getName();
				rootNames.put(name, frequency);
			}
		}
		return rootNames;
	}
	
	//Do DFS to find the total frequency of this component, and mark each node as visited
	int getComponentFrequency(GraphNode node){
		if(node.isVisited())
			return 0; //Already visited
		node.setIsVisited(true);
		int sum = node.getFrequency();
		for(GraphNode child : node.getNeighbors()){
			sum += getComponentFrequency(child);
		}
		return sum;
	}
}

class Graph {
	private ArrayList<GraphNode> nodes;
	private HashMap<String, GraphNode> map;
	
	public Graph() {
		map = new HashMap<String, GraphNode>();
		nodes = new ArrayList<GraphNode>();
	}
	
	public boolean hasNode(String name) {
		return map.containsKey(name);
	}	
	
	public GraphNode createNode(String name, int freq) {
		if (map.containsKey(name)) { 
			return getNode(name);
		}
		
		GraphNode node = new GraphNode(name, freq);
		nodes.add(node);
		map.put(name, node);
		return node;
	}
	
	private GraphNode getNode(String name) {
		return map.get(name);
	}
	
	public ArrayList<GraphNode> getNodes() {
		return nodes;
	}
	
	public void addEdge(String startName, String endName) {
		GraphNode start = getNode(startName);
		GraphNode end = getNode(endName);
		if (start != null && end != null) {
			start.addNeighbor(end);
			end.addNeighbor(start);
		}
	}
}


class GraphNode {
	private ArrayList<GraphNode> neighbors;
	private HashMap<String, GraphNode> map;
	private String name;
	private int frequency;
	private boolean visited = false;
	
	public GraphNode(String nm, int freq) {
		name = nm;
		frequency = freq;
		neighbors = new ArrayList<GraphNode>();
		map = new HashMap<String, GraphNode>();
	}

	public String getName() {
		return name;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public boolean addNeighbor(GraphNode node) {
		if (map.containsKey(node.getName())) {
			return false;
		}
		neighbors.add(node);
		map.put(node.getName(), node);
		return true;
	}
	
	public ArrayList<GraphNode> getNeighbors() {
		return neighbors;
	}
		
	public boolean isVisited() {
		return visited;
	}
	
	public void setIsVisited(boolean v) {
		visited = v;
	}
}