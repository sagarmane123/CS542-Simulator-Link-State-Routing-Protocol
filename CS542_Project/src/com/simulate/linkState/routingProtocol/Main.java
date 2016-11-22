package com.simulate.linkState.routingProtocol;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This main class used to performed different operations like Create Network
 * Topology Build Connection Table Shortest Path to destination Modify Network
 * Topology Best Router to Broadcast Add Router
 * 
 * @author sagar m
 * @version 1.0
 * @since 13-November-2016
 * 
 */
public class Main {

	static int intArray[][];
	static int sourceRouter = Integer.MAX_VALUE;
	static int destinationRouter = Integer.MAX_VALUE;
	static int removeRouter = Integer.MAX_VALUE;
	static List<Integer> Routerlist = new ArrayList<Integer>();
	static int[] cost;

	public static void main(String[] args) throws Exception {

		try {
			int masterCommand = 0;
			Scanner sc = new Scanner(System.in);
			System.out.println("******************************************\n");
			System.out.println("CS542 Simulate Link State Routing Protocol\n");
			System.out.println("******************************************\n");
			do {
				System.out.println();
				System.out.println("Enter Master Command");
				System.out.println();
				System.out.println("(1) Create a Network Topology");
				System.out.println("(2) Build a Connection Table");
				System.out.println("(3) Shortest Path to Destination Router");
				System.out.println("(4) Modify a Topology");
				System.out.println("(5) Best Router for Broadcast");
				System.out.println("(6) Add Router");
				System.out.println("(7) Exit");
				System.out.println();
				System.out.println("Master Command:");
				masterCommand = sc.nextInt();

				switch (masterCommand) {
				case 1:
					//Creating Network Topology
					System.out
							.println("Input original network topology matrix data file:");
					String fileName = sc.next();
					//Boolean for comparing given file exists or not
					boolean f = new File(fileName).isFile();
					if (f) {
						Routerlist.clear();
						CreateNetworkTopology cnt = new CreateNetworkTopology();
						intArray = cnt.createMatrixFromFile(fileName);
						for (int i = 1; i <= intArray.length; i++)
							Routerlist.add(i);
						System.out.println("Review original topology matrix:");
						// Print the integer array
						for (int[] row : intArray) {
							for (int col : row) {
								System.out.printf("%2d ", col);
							}
							System.out.println();
						}
					} else {
						System.err.println("Error!!!\nFile does not Exits !!!");
					}
					System.out.println();
					break;
				case 2:
					//Generating Connection Table
					if (intArray.length != 0) {
						System.out.println("Enter a source router:");
						sourceRouter = sc.nextInt();
						if (!Routerlist.contains(sourceRouter)) {
							System.out.println("Enter different source router");
							System.out.println();
							break;
						}
						ShortestPath sp = new ShortestPath();
						for (int i = 0; i < Routerlist.size(); i++) {
							if (Routerlist.get(i) == sourceRouter)
								sourceRouter = i;
						}
						sp.dijkstra(intArray, sourceRouter);
						int parent[] = ShortestPath.parent;
						cost = ShortestPath.minDist;
						System.out.println("=============================");
						System.out.println("Router "
								+ Routerlist.get(sourceRouter)
								+ " Connection Table");
						System.out.println("Destination \t Interface");
						System.out.println("=============================");
						for (int i = 0; i < intArray.length; i++) {
							if (i == sourceRouter) {
								System.out.printf("     " + Routerlist.get(i)
										+ "\t\t  None");
								System.out.println();
							} else {
								if (cost[i] == 0
										|| cost[i] == Integer.MAX_VALUE) {
									System.out.printf("     "
											+ Routerlist.get(i) + "\t\t  None");
									System.out.println();
								} else {
									System.out.print("     "
											+ Routerlist.get(i) + "\t\t");
									sp.printNextVertex(parent, i, sourceRouter);
									System.out.println();
								}
							}
						}
					} else {
						System.out.println("Please Create Network Topology ");
					}
					System.out.println();
					break;
				case 3:
					//Finding Optimal Shortest path from source to destination
					if (intArray.length != 0
							|| sourceRouter != Integer.MAX_VALUE) {
						if (sourceRouter == Integer.MAX_VALUE) {
							System.out.println("Enter a source router:");
							sourceRouter = sc.nextInt();
							ShortestPath s = new ShortestPath();
							for (int i = 0; i < Routerlist.size(); i++) {
								if (Routerlist.get(i) == sourceRouter)
									sourceRouter = i;
							}
							s.dijkstra(intArray, sourceRouter);
						}
						System.out.println("Enter a destination router:");
						destinationRouter = sc.nextInt();
						if (!Routerlist.contains(destinationRouter)) {
							System.out
									.println("Enter different router for destination");
							System.out.println();
							break;
						}
						for (int i = 0; i < Routerlist.size(); i++) {
							if (Routerlist.get(i) == destinationRouter)
								destinationRouter = i;
						}
						int part[] = ShortestPath.parent;
						cost = ShortestPath.minDist;
						boolean flag = true;
						ShortestPath sp1 = new ShortestPath();
						if (cost[destinationRouter] > 0
								&& cost[destinationRouter] != Integer.MAX_VALUE) {
							System.out.print("Shortest path from "
									+ Routerlist.get(sourceRouter) + " to "
									+ Routerlist.get(destinationRouter)
									+ " is ");
							System.out.print(Routerlist.get(sourceRouter));
							sp1.printPath(part, destinationRouter);
							System.out.println("\nCost is "
									+ cost[destinationRouter]);
						} else
							System.err.println("No Path Exits !!!");
					} else {
						System.out.println("Please Create Network Topology ");
					}
					System.out.println();
					break;
				case 4:
					//Modify Network Topolgy
					System.out.println("Enter a router to removed:");
					removeRouter = sc.nextInt();
					for (int i = 0; i < Routerlist.size(); i++) {
						if (Routerlist.get(i) == removeRouter)
							removeRouter = i;
					}
					while (!
							Main.Routerlist.contains(removeRouter)) {
						System.out
								.println("Please enter valid Router number");
						removeRouter = sc.nextInt();
					}
					Routerlist.remove(removeRouter);
				//	if (removeRouter <= intArray.length) {
						RemoveRouter rr = new RemoveRouter();
						intArray = rr.remove(intArray, removeRouter);
						if (removeRouter != sourceRouter && sourceRouter!= Integer.MAX_VALUE )
							rr.display();
//					} else {
//						System.out
//								.println("Error!!! \nPlease select different router");
//					}
					System.out.println();
					break;
				case 5:
					//Best Router to Broadcast
					BestRouter br = new BestRouter();
					int bestRouter = br.bestRouter(intArray);
					if(br.shortestPathCost>0 && br.shortestPathCost != Integer.MAX_VALUE)
					{
					System.out.println("Best Router is " + bestRouter);
					System.out.println("Total cost is " + br.shortestPathCost);
					}
					else
					{
						System.out.println("There is no any best router");
					}
					System.out.println();
					break;
				case 6:
					//Add New Router
					AddRouter ar = new AddRouter();
					intArray = ar.addRouter(intArray);
					ar.display();
					break;
				case 7:
					System.out.println("Good By CS542!!!");
					System.out.println();
					break;
				}
			} while (masterCommand != 7);
		} catch (Exception e) {
			System.out.println("Somethins goes wrong.\nTry again");
		}
	}
}
