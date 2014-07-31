package deprecated;

import java.io.IOException;

import tool.GQRGraphReader;
import analysis.Entropy;
import essential.GQR;
import essential.PCGraph;

public class testEntropy {


	public static void main(String[] args) throws IOException {
		
		
		
		
		PCGraph graph = GQRGraphReader.readGraphFromGQR("/home/users/xiaoyuge/Emp/GNI/H_14th_200_20_4.csp");

	//	Graph pcgraph = GQRReader.readGraphFromGQR("./JAVAGNI/H_60_14_4_1_PC.csp");
	 //   pcgraph.setName("PC");
		Entropy.calEntropy(graph);
	    BuildGraphsForGQR.remove_Entropy(graph, Entropy.entropy);
	    GQR.generateGraphGQR(graph, "/home/users/xiaoyuge/Emp/GNI/H_14th_min");
	//	Graph pcgraph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H_NEW_60_14_4_1_PC.csp");
	//   pcgraph.setName("PC");
	//	Entropy.calEntropy(pcgraph);
	  
	//	Graph graph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H_60_14_4_1.csp");
	//    graph.setName("Oringial");
	//	Entropy.calEntropy(graph);
		
		
		
		
		
		
		// TODO Auto-generated method stub
		// Graph graph2 = Graph.loadGraph("./Target/H601440RU_#133");
		
	//	  Graph graph0 = Graph.loadGraph("./Target/H_60_14_4_0"); 
		//  Graph graph1 = Graph.loadGraph("./Target/H_60_14_4_0_PC");
		 //Graph graph2 = RetainChanges.retainChanges(graph0, graph1);

	
		/*for (int i = 0; i < graph2.getNumOfRegions()-1;i++)
		for (int j = i+1; j < graph2.getNumOfRegions();j++)
    System.out.println(" "+i+"   "+j+" "+CommonEdge.CommonEdge(graph2, i, j));*/
	/*	Graph pcGraph = ReadGraphFromGQR.readGraphFromGQR("./Target/H_NEW_60_14_4_1_PC");
		Graph originalGraph = Graph.loadGraph("./Target/H_NEW_60_14_4_1"); 
		originalGraph.setName("OG");
		Graph nonRedundacyGraph = RetainChanges.retainChanges(originalGraph, pcGraph);
		nonRedundacyGraph.setName("NONOG");
		pcGraph.setName("H_NEW_60_14_4_1_PC");*/
	    
	//	Entropy.tryMinNetwork(pcGraph);
	
	//		  Fun fun = new Fun(findMinGraph);
////			  Fun fun1 = new Fun(pcGraph);
		
		
 //       LinkedList<biConstraint<Integer>> cons = new LinkedList<biConstraint<Integer>>();
//        Entropy.entropyWithRecords(pcGraph, 0, 20, cons);
    
/*        Entropy.entropyWithRecords(pcGraph, 0, 36, cons);
        Entropy.entropyWithRecords(pcGraph, 0, 42, cons);
        Entropy.entropyWithRecords(pcGraph, 1, 42, cons);
        Entropy.entropyWithRecords(pcGraph, 2, 11, cons);
        Entropy.entropyWithRecords(pcGraph, 2, 26, cons);
        Entropy.entropyWithRecords(pcGraph, 2, 36, cons);
        Entropy.entropyWithRecords(pcGraph, 2, 37, cons);
        Entropy.entropyWithRecords(pcGraph, 11, 22, cons);
        Entropy.entropyWithRecords(pcGraph, 11, 36, cons);
        Entropy.entropyWithRecords(pcGraph, 11, 37, cons);
        Entropy.entropyWithRecords(pcGraph, 17, 33, cons);
        Entropy.entropyWithRecords(pcGraph, 18, 26, cons);
        Entropy.entropyWithRecords(pcGraph, 18, 42, cons);
        Entropy.entropyWithRecords(pcGraph, 20, 22, cons);
        Entropy.entropyWithRecords(pcGraph, 20, 36, cons);
        Entropy.entropyWithRecords(pcGraph, 26, 33, cons);
    
        Entropy.entropyWithRecords(pcGraph, 26, 36, cons);
        Entropy.entropyWithRecords(pcGraph, 26, 59, cons);
        Entropy.entropyWithRecords(pcGraph, 27, 59, cons);
        Entropy.entropyWithRecords(pcGraph, 30, 33, cons);
        Entropy.entropyWithRecords(pcGraph, 30, 59, cons);
        Entropy.entropyWithRecords(pcGraph, 33, 59, cons);
        Entropy.entropyWithRecords(pcGraph, 37, 52, cons);
        Entropy.entropyWithRecords(pcGraph, 37, 53, cons);
        Entropy.entropyWithRecords(pcGraph, 52, 53, cons);
 */
   //  	Entropy.tryMinNetwork(pcGraph);
	//	Graph findMinGraph = ReadGraphFromGQR.readGraphFromGQR("./Target/tryMinNetworkH_NEW_60_14_4_1_PC.csp");
	//	Entropy.tryMinNetwork(pcGraph);
	/*	Graph findMinGraph = ReadGraphFromGQR.readGraphFromGQR("./Target/FindMin.csp");
		findMinGraph.setName("Min Graph");
//		Graph tryMinGraph =  ReadGraphFromGQR.readGraphFromGQR("./Target/tryMinNetworkH_NEW_60_14_4_1_PC.csp");
		
	
		LinkedList<biConstraint> cons = GraphPolymorphsim.findoutKeyConstraints(pcGraph,GraphPolymorphsim.keyConstraint("./Target/H_NEW_60_14_4_1_RU_C"));		
	  
	//	tryMinGraph.setName("TryMinGraph");
	//    Entropy.calEntropy(pcGraph);
		Entropy.calEntropy(findMinGraph);
		findMinGraph.printGraph(Entropy.entropy);
		  for (biConstraint c:cons)
		    	System.out.println(c);*/
	//	Difference.properSubSet(findMinGraph,pcGraph);
	//	Difference.properSubSet(findMinGraph,originalGraph);
	
//	//	Difference.properSubSet(orginalGraph,findMinGraph);
	//	 Fun fun = new Fun(findMinGraph,"Minimum",cons);
//	     Fun fun1 = new Fun(originalGraph,"Original Graph",cons);
//			 Fun fun2 = new Fun(nonRedundacyGraph,"NONOR Graph");
	//	 Difference.printDifference(findMinGraph, tryMinGraph);
		
	 //     Difference.printDifference(findMinGraph, pcGraph);
        
    //	Entropy.tryMinNetwork(pcGraph,cons);
	}

}
