package deprecated;

import java.io.IOException;

import tool.GQRGraphReader;
import analysis.StructureDifference;
import essential.PCGraph;

public class testDifference {
public static void main(String args[]) throws NumberFormatException, IOException
{
	/*  Graph graph0 = Graph.loadGraph("./Target/H_60_14_4_0"); 
	  Graph graph1 = Graph.loadGraph("./Target/H_60_14_4_0_PC");
	  Graph graph2 = Graph.loadGraph("./Target/H601440RU_#128");
	  Graph graph3 = Graph.loadGraph("./Target/PC_601440RU_#128");
	
	  Difference.printDifference(graph0, graph1);
	  System.out.println("---------------------------------------------------------");
	  Difference.printDifference(graph1, graph2);
	  System.out.println("---------------------------------------------------------");
	 
	  Difference.printDifference(graph3, graph1);

	  Graph graph0 = Graph.loadGraph("./Target/H_60_14_4_0"); 
	  graph0.printGraph();
	  Graph graph1 = Graph.loadGraph("./Target/H_60_14_4_0_PC");
	  
	  Graph graph2 = Graph.loadGraph("./Target/H601440RU_#133");
	  Graph graph3 = Graph.loadGraph("./Target/PC_601440RU_#133");
	
	
	  Graph graph4 = Graph.loadGraph("./Target/H601440RU_#128");
	  Graph graph5 = Graph.loadGraph("./Target/PC_601440RU_#128");
	  
	  Graph graph6 = Graph.loadGraph("./Target/H601440RU_#396");
	  Graph graph7 = Graph.loadGraph("./Target/PC_601440RU_#396");
	  
	  Graph graph8 = Graph.loadGraph("./Target/H601440RU_#522");
	  Graph graph9 = Graph.loadGraph("./Target/PC_601440RU_#522");
	 
	  Difference.printDifference(graph0, graph1);
	  System.out.println("---------------------------------------------------------");
	  Difference.printDifference(graph1, graph3);
	  System.out.println("---------------------------------------------------------");
	   Difference.printDifference(graph1, graph5);
	  System.out.println("---------------------------------------------------------");
		 
	  Difference.printDifference(graph3, graph7);
	  System.out.println("---------------------------------------------------------");
	  Difference.printDifference(graph5, graph7);
	  
	  System.out.println("---------------------------------------------------------");
	  Difference.printDifference(graph8, graph9);
	  
	  
//	  Fun fun = new Fun(graph0,"./Target/H_60_14_4_0");
//	  
//	  Fun fun1 = new Fun(graph1,"./Target/H_60_14_4_0_PC");
//	  
	  Fun fun2 = new Fun(graph3,"./Target/PC_601440RU_#133");
//
//	  Fun fun5 = new Fun(graph6,"./Target/PC_601440RU_#128");
//	  
//	  Fun fun6 = new Fun(graph6,"./Target/PC_601440RU_#396");
//	
*/
	PCGraph ogGraph =  GQRGraphReader.readGraphFromGQR("./JAVAGNI/H601441_PC");	
//	Graph ogGraph =  ReadGraphFromGQR.readGraphFromGQR("./JAVAGNI/H601441_PC");	
	ogGraph.setName("ogGraph");
	PCGraph pcGraph = GQRGraphReader.readGraphFromGQR("./JAVAGNI/H601441_PC523PC");
    pcGraph.setName("pcGraph");
	//LinkedList<biConstraint> constraints =	GraphPolymorphsim.findoutKeyConstraints(pcGraph,GraphPolymorphsim.keyConstraint("./Target/H_NEW_60_14_4_1_RU_C"));	
    //Fun fun =  new Fun(graph,"./Target/H_NEW_60_14_4_1_PC",constraints);
//    /Fun fun1 =  new Fun(pcGraph,"./Target/H_NEW_60_14_4_1_PC",constraints);
  //  Difference.similarity(ogGraph, pcGraph);
    StructureDifference.printDifference(ogGraph, pcGraph);

}
}
