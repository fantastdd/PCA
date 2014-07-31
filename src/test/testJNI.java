package test;

import java.io.IOException;

import tool.GQRGraphReader;
import analysis.StructureDifference;
import essential.GQR;
import essential.PCGraph;

public class testJNI {
 public static void main(String args[]) throws IOException
 {
	

	 PCGraph originalGraph = GQRGraphReader.readGraphFromGQR("./JAVAJNI/H_NEW_60_14_4_1.csp");
	 //  Graph originalGraph = ReadGraphFromGQR.readGraphFromGQR("./JNI/H601442_MTS.csp");
	 originalGraph.setName("Original");
	 //Graph solutionGraph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H601441_MTS_S");
     //solutionGraph.setName("Solution");
	 //double probabiliy = Difference.similarity(originalGraph, solutionGraph);
	 //System.out.println(probabiliy);
	 GQR.generateGraphGQR(originalGraph, "./JAVAJNI/H_NEW_60_14_4_1_new");
 }
}
