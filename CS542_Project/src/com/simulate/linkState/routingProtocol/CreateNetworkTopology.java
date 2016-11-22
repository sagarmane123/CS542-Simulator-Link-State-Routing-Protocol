package com.simulate.linkState.routingProtocol;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <h1>Create Network Topology</h1> CreateNetworkTopology class contains
 * function createMatrixFromFile which generated 2d matrix from txt file
 * 
 * @author sagar m
 * @version 1.0
 * @since 13-November-2016
 * 
 */
public class CreateNetworkTopology {
	/**
	 * createMatrixFromFile method is used to import txt file from and convert
	 * file data into 2d matrix.
	 * 
	 * @param filename
	 *            this parameter is file path
	 * @return int[][] This return 2d matrix.
	 * @throws IOException
	 */
	public static int[][] createMatrixFromFile(String filename)
			throws IOException {
		try {
			Path p = Paths.get(filename);
			List<String> FileLine = Files.readAllLines(p);
			// Remove any blank lines
			for (int i = FileLine.size() - 1; i >= 0; i--) {
				if (FileLine.get(i).isEmpty()) {
					FileLine.remove(i);
				}
			}
			// Declare 2d array
			int[][] matrix = new int[FileLine.size()][];
			// Iterate through each row to determine the number of columns
			for (int i = 0; i < FileLine.size(); i++) {
				// Split the line by spaces
				String[] splitLine = FileLine.get(i).split("\\s");
				// Declare the number of columns in the row from the split
				matrix[i] = new int[splitLine.length];
				for (int j = 0; j < splitLine.length; j++) {
					// Convert String element to an integer
					matrix[i][j] = Integer.parseInt(splitLine[j]);
				}
			}
			return matrix;
		} catch (FileNotFoundException error) {
			throw new FileNotFoundException();
		}
	}
}
