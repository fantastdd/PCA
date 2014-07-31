package test;

import java.io.IOException;

import analysis.StructureDifference;

import tool.GQRGraphReader;
import essential.PCGraph;

public class testGNI {
 public static void main(String args[]) throws IOException
 {
	 PCGraph graph1 = GQRGraphReader.readGraphFromGQR("./JAVAGNI/H601441_PC");
	 PCGraph graph2 = GQRGraphReader.readGraphFromGQR("./JAVAGNI/H601441_C532");	 
	// Difference.printDifference(graph1, graph2);
	StructureDifference.similarity(graph1, graph2);
	
	 
	 
	 
 }
}
