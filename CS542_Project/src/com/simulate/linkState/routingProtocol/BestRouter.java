package com.simulate.linkState.routingProtocol;

import java.util.Arrays;

/**
 * <h1>Best Router</h1> 
 * BestRouter class used to find best router in network
 * topology. Best router calculated by calculating minimum of sum of distance
 * from each router to all router.
 * 
 * @author sagar m
 * @version 1.0
 * @since 13-November-2016
 */
public class BestRouter {
	/**
	 * 
	 * @param networkTopology
	 *            This 2d matrix input of Network topology
	 * @return int This return best router number
	 */
	static int shortestPathCost;

	public int bestRouter(int[][] networkTopology) {
		int best = 0;
		ShortestPath sp = new ShortestPath();
		shortestPathCost = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < networkTopology.length; i++) {
			sum = 0;
			sp.dijkstra(networkTopology, i);
			int cost[] = ShortestPath.minDist;
			for (int j : cost)
				sum += j;
			// System.out.println("Router"+(i+1) +" sum is "+ sum );
			if (sum < shortestPathCost) {
				shortestPathCost = sum;
				best = i;
			}
		}
		return Main.Routerlist.get(best);
	}
}
