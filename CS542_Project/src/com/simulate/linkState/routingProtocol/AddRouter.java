package com.simulate.linkState.routingProtocol;

import java.util.Scanner;

/**
 * AddRouter class used to add router in network topology
 * 
 * @author sagar m
 * @version 1.0
 * @since 13-November-2016
 * 
 */
public class AddRouter {
	/**
	 * addRouter method used to add router in current network topology
	 * 
	 * @param networkTopology
	 *            This is current network topology
	 * @return int[][] This return new network topology
	 */
	static int newNetworkTopology[][];

	public static int[][] addRouter(int networkTopology[][]) {
		newNetworkTopology = new int[networkTopology.length + 1][networkTopology.length + 1];

		for (int i = 0; i < networkTopology.length; i++)
			for (int j = 0; j < networkTopology.length; j++)
				newNetworkTopology[i][j] = networkTopology[i][j];
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new Router Number");
		int newRouter = sc.nextInt();
		while (Main.Routerlist.contains(newRouter)) {
			System.out
					.println("Router already prsent.\nEnter new Router Number");
			newRouter = sc.nextInt();
		}
		Main.Routerlist.add(newRouter);
		for (int i = 0; i < networkTopology.length; i++) {
			System.out.println("Enter weight between Router "
					+ Main.Routerlist.get(i) + " and " + newRouter);
			int newRouterWeight = sc.nextInt();
			newNetworkTopology[i][networkTopology.length] = newRouterWeight;
		}
		for (int i = 0; i < networkTopology.length; i++) {
			System.out.println("Enter weight between Router " + newRouter
					+ " and " + Main.Routerlist.get(i));
			int newRouterWeight = sc.nextInt();
			newNetworkTopology[networkTopology.length][i] = newRouterWeight;
		}
		System.out.println();
		newNetworkTopology[networkTopology.length][networkTopology.length] = 0;

		return newNetworkTopology;
	}

	/**
	 * This method display network topology
	 */
	public void display() {

		int sourceRouter = Main.sourceRouter;
		if (sourceRouter != Integer.MAX_VALUE) {
			System.out.println("Review original topology matrix:");
			ShortestPath sp = new ShortestPath();
			sp.dijkstra(newNetworkTopology, sourceRouter);
			int parent[] = ShortestPath.parent;
			int cost[] = ShortestPath.minDist;
			System.out.println("=============================");
			System.out.println("Router " + Main.Routerlist.get(sourceRouter)
					+ " Connection Table");
			System.out.println("Destination \t Interface");
			System.out.println("=============================");
			for (int i = 0; i < newNetworkTopology.length; i++) {
				if (cost[i] == 0 || cost[i] == Integer.MAX_VALUE) {
					System.out.printf("     " + Main.Routerlist.get(i)
							+ "\t\t  None");
					System.out.println();
				} else {
					System.out.print("     " + Main.Routerlist.get(i) + "\t\t");
					sp.printNextVertex(parent, i, sourceRouter);
					System.out.println();
				}
			}
		}

	}
}
