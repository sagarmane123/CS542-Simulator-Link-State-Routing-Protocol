package com.simulate.linkState.routingProtocol;

import java.util.Scanner;

/**
 * <H1>Remove Router</H1> RemoveRouter class used for removing router
 * 
 * @author sagar m
 * @version 1.0
 * @since 13-November-2016
 * 
 */
public class RemoveRouter {

	static int destinationArray[][];

	/**
	 * 
	 * @param matrix
	 *            This is first parameter, 2d array
	 * @param x
	 *            This is router number to removed.
	 * @return int[][] This is new matrix after removing router.
	 */
	public int[][] remove(int matrix[][], int x) {
		destinationArray = new int[matrix.length - 1][matrix.length - 1];

		int p = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (i == x)
				continue;

			int q = 0;
			for (int j = 0; j < matrix.length; j++) {
				if (j == x)
					continue;

				destinationArray[p][q] = matrix[i][j];
				q++;
			}

			p++;
		}
		return destinationArray;
	}

	/**
	 * This method display network topology
	 */
	public void display() {
		int sourceRouter = Main.sourceRouter;
		if (sourceRouter != Integer.MAX_VALUE) {
			System.out.println("Review original topology matrix:");
			ShortestPath sp = new ShortestPath();
			sp.dijkstra(destinationArray, sourceRouter);
			int parent[] = ShortestPath.parent;
			int cost[] = ShortestPath.minDist;
			System.out.println("=============================");
			System.out.println("Router " + Main.Routerlist.get(sourceRouter)
					+ " Connection Table");
			System.out.println("Destination \t Interface");
			System.out.println("=============================");
			for (int i = 0; i < destinationArray.length; i++) {
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
