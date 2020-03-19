package ctci.solutions.aritra;

import java.util.LinkedList;

/*
 * Question: Given a directed graph, design an algorithm to find out whether there is a route between two nodes
 * 
 * Solution: This problem can be solved by just simple graph traversal, such as depth-first search or breadth-first search. We start with one of
 * the two nodes and during traversal, check if the other node is found. We should mark any node found in the course of the algorithm as 'already visited'
 * to avoid cycles and repetition of the nodes. The code below provides an iterative implementation of breadth-first search.
 */

public class RouteBetweenNodes {
	enum State {
		Unvisited, Visited, Visiting;
	}

	class Node {
		State state;
		Node[] adjacent;
		String name;

		public Node[] getAdjacent() {
			return adjacent;
		}

		public Node(String name, State state) {
			this.name = name;
			this.state = state;
		}
	}

	class Graph {
		Node A;
		Node B;
		Node C;
		Node D;
		Node E;
		Node F;
		Node G;

		public Graph() {
			this.A = new Node("A", State.Unvisited);
			this.B = new Node("B", State.Unvisited);
			this.C = new Node("C", State.Unvisited);
			this.D = new Node("D", State.Unvisited);
			this.E = new Node("E", State.Unvisited);
			this.F = new Node("F", State.Unvisited);
			this.G = new Node("G", State.Unvisited);

			this.A.adjacent = new Node[] { C };
			this.B.adjacent = new Node[] { C };
			this.C.adjacent = new Node[] { D };
			this.D.adjacent = new Node[] { E };
			this.E.adjacent = new Node[] { F, G };
			this.G.adjacent = new Node[] { C };
		}

		public Node[] getNodes() {
			return new Node[] { A, B, C, D, E, F, G };
		}
	}

	public static void main(String[] args) {
		RouteBetweenNodes rbn = new RouteBetweenNodes();
		Graph G = rbn.new Graph();
		System.out.println("Is there a path between A and G ? "
				+ rbn.search(G, G.A, G.G));
		System.out.println("Is there a path between G and C ? "
				+ rbn.search(G, G.G, G.C));
		System.out.println("Is there a path between G and D ? "
				+ rbn.search(G, G.G, G.D));
		System.out.println("Is there a path between D and A ? "
				+ rbn.search(G, G.D, G.A));
	}

	boolean search(Graph G, Node start, Node end) {
		if (start == end)
			return true;

		// Operates as queue
		LinkedList<RouteBetweenNodes.Node> q = new LinkedList<RouteBetweenNodes.Node>();

		for (Node u : G.getNodes()) {
			u.state = State.Unvisited;
		}

		start.state = State.Visiting;
		q.add(start);

		Node u;
		while (!q.isEmpty()) {
			u = q.removeFirst(); // i.e. dequeue
			if (u != null) {
				if (u.getAdjacent() != null) {
					for (Node v : u.getAdjacent()) {
						if (v.state == State.Unvisited) {
							if (v == end) {
								return true;
							} else {
								v.state = State.Visiting;
								q.add(v);
							}
						}
					}
				}
				u.state = State.Visited;
			}
		}
		return false;
	}
}
