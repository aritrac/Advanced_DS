package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/*
 * Question: You are given a list of projects and a list of dependencies (which is a list of projects, where the second project is dependent on the first project.)
 * All of a project's dependencies must be built before the project is. Find a build order that will allow the projects to be built. If there is no
 * valid build order, return an error.
 * 
 * Example:
 * Input:
 * projects: a, b, c, d, e, f
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * 
 * Output: f, e, a, b, d, c
 * 
 * Solution:
 * We can do the same thing we did in the previous problem using DFS. This is called Topological Sorting, linearly ordering the vertices in a graph such that for every edge 
 * (a, b), a appears before b in the linear order.
 * 
 * Suppose we picked an arbitrary node say b and performed a depth first on it. When we get to the end of a path and can't go any further
 * which will happen at leaf nodes, we know that those terminating nodes can be the last projects to be built.No projects depend on them.
 * Now, consider what happens at node a when we return from the DFS of e(leaf node). We know a's children need to appear after a in the
 * build order. So, once we return from searching a's children and therefore they have been added, we can choose to add a to the front of the build order
 * 
 * Once we return from a, and complete the DFS of b's other children, then everything that must appear after b is in the list. Add b to the front.
 * Now what? We can start with any old node again doing a DFS on it and then adding the node to the front of the build queue when the DFS is completed.
 * 
 * In this algorithm, we should think about the issue of cycles. There is no possible build order if there is a cycle. But still, we
 * don't want to get stuck in an infinite loop.So each node will have 3 states COMPLETED, PARTIAL and BLANK to detect a loop if one exists
 * 
 * The algorithm below implements the same
 */

public class BuildOrder {
	public static void main(String[] args) {
		BuildOrder bo = new BuildOrder();
		String[] projects = {"a","b","c","d","e","f","g"};
		String[][] dependencies = {
				{"f","c"},
				{"f","b"},
				{"c","a"},
				{"f","a"},
				{"b","a"},
				{"a","e"},
				{"b","e"},
				{"d","g"}
		};
		
		Stack<Project> order = bo.findBuildOrder(projects, dependencies);
		
		while(!order.isEmpty()){
			System.out.print(order.pop().getName() + ",");
		}
	}
	
	Stack<Project> findBuildOrder(String[] projects, String[][] dependencies){
		Graph graph = buildGraph(projects,dependencies);
		return orderProjects(graph.getNodes());
	}
	
	Stack<Project> orderProjects(ArrayList<Project> projects){
		Stack<Project> stack = new Stack<Project>();
		for(Project project : projects){
			if(project.getState() == State.BLANK){
				if(!doDFS(project,stack)){
					return null;
				}
			}
		}
		return stack;
	}
	
	boolean doDFS(Project project, Stack<Project> stack){
		if(project.getState() == State.PARTIAL){
			return false; //Cycle
		}
		
		if(project.getState() == State.BLANK){
			project.setState(State.PARTIAL);
			ArrayList<Project> children = project.getChildren();
			for(Project child : children){
				if(!doDFS(child,stack)){
					return false;
				}
			}
			project.setState(State.COMPLETE);
			stack.push(project);
		}
		return true;
	}
	
	/*
	 * Build the graph, adding edge (a, b) if b depends on a. Assumes a pair is listed in "build order".
	 * The pair (a, b) in dependencies indicates that b depends on a and a must be built before b.
	 */
	
	Graph buildGraph(String[] projects, String[][] dependencies){
		Graph graph = new Graph();
		for(String project : projects){
			graph.getOrCreateNode(project);
		}
		
		for(String[] dependency : dependencies){
			String first = dependency[0];
			String second = dependency[1];
			graph.addEdge(first, second);
		}
		
		return graph;
	}
	
	enum State {COMPLETE, PARTIAL, BLANK};

	class Project {
		private State state = State.BLANK;
		public State getState(){
			return state;
		}
		public void setState(State st){
			state = st;
		}
		private ArrayList<Project> children = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();
		private String name;
		private int dependencies = 0;

		public Project(String n) {
			this.name = n;
		}

		public void addNeighbor(Project node) {
			if (!map.containsKey(node.getName())) {
				children.add(node);
				map.put(node.getName(), node);
				node.incrementDependencies();
			}
		}

		public void incrementDependencies() {
			dependencies++;
		}

		public void decrementDependencies() {
			dependencies--;
		}

		public String getName() {
			return name;
		}

		public ArrayList<Project> getChildren() {
			return children;
		}

		public int getNumberDependencies() {
			return dependencies;
		}
	}

	class Graph {
		private ArrayList<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();

		public Project getOrCreateNode(String name) {
			if (!map.containsKey(name)) {
				Project node = new Project(name);
				nodes.add(node);
				map.put(name, node);
			}

			return map.get(name);
		}

		public void addEdge(String startName, String endName) {
			Project start = getOrCreateNode(startName);
			Project end = getOrCreateNode(endName);
			start.addNeighbor(end);
		}

		public ArrayList<Project> getNodes() {
			return nodes;
		}
	}
}
