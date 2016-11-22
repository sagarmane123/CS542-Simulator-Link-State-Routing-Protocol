package com.simulate.linkState.routingProtocol;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * <h1>Dijkstra Shortest Path</h1> ShortestPath class used to find shortest path
 * between source and destination using Dijkstra algoritham
 * 
 * @author sagar m
 * @version 1.0
 * @since 13-November-2016
 * 
 */
public class ShortestPath {

	static int V;
	static int parent[];
	static Boolean Visited[];
	static int minDist[];
	static LinkedList<Integer> linkedlist;
	static boolean flag = false;

	/**
	 * minimumDistance method find the minimum Distance vertex from the set of
	 * vertices not yet processed
	 * 
	 * @param minDist
	 *            This is an array of minimum distance
	 * @param Visited
	 *            This is an array of visited node
	 * @return int This return index value of minimum distance vertex
	 */
	int minimumDistance(int minDist[], Boolean Visited[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (Visited[v] == false && minDist[v] <= min) {
				min = minDist[v];
				min_index = v;
			}
		return min_index;
	}

	/**
	 * printPath method used to print shortest path to destination vertex.
	 * 
	 * @param parent
	 *            This is an array of shortest path
	 * @param j
	 *            This is a destination node value
	 */
	void printPath(int parent[], int j) {
		if (parent[j] == -1)
			return;

		printPath(parent, parent[j]);
		System.out.print("-->"+Main.Routerlist.get(j));
	}

	/**
	 * printNextVertex method used to find interface between source and
	 * destination
	 * 
	 * @param parent
	 *            This is an array of shortest path
	 * @param src
	 *            This is source vertex
	 * @param dest
	 *            This is destination vertex
	 */
	void printNextVertex(int parent[], int dest, int src) {
		if (parent[dest] == -1) {
			return;
		}
		printNextVertex(parent, parent[dest], src);
		if (parent[dest] == src)
			// System.out.printf("%5d",dest+1 );
			System.out.printf("%5d", Main.Routerlist.get(dest));
	}

	/**
	 * dijkstra method used to implement Dijkstra shortest path algorithm
	 * 
	 * @param graph
	 *            This is adjacency matrix
	 * @param src
	 *            This is source vertex
	 */
	void dijkstra(int graph[][], int src) {
		V = graph.length;
		// minDist hold the shortest distance from source to destination
		minDist = new int[V];
		// Visited keep track of vertex is Visited or not
		Visited = new Boolean[V];
		// Parent array to store shortest path tree
		parent = new int[V];
		// Initialize all minDistances as INFINITE and Visited[] as false and
		// parent[]=-1
		for (int i = 0; i < V; i++) {
			minDist[i] = Integer.MAX_VALUE;
			Visited[i] = false;
			parent[src] = -1;
		}
		minDist[src] = 0;
		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			// Find minimum Distance vertex from the set of vertices
			int u = minimumDistance(minDist, Visited);
			// Mark the selected vertex as visited
			Visited[u] = true;
			// Update minimum distance value of the adjacent vertices of the
			// selected vertex
			for (int v = 0; v < V; v++) {
				if (graph[u][v] == -1)
					graph[u][v] = 0;
				if (!Visited[v] && graph[u][v] != 0
						&& minDist[u] != Integer.MAX_VALUE
						&& minDist[u] + graph[u][v] < minDist[v]) {
					minDist[v] = minDist[u] + graph[u][v];
					parent[v] = u;
				}
			}
		}
	}
}