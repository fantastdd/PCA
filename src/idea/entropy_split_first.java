package idea;

import java.io.IOException;
import java.util.LinkedList;

import tool.GQRGraphReader;
import analysis.StructureDifference;
import analysis.Entropy;
import essential.GQR;
import essential.PCGraph;
import essential.Constraint;

public class entropy_split_first {
public static void main(String args[]) throws IOException
{
//	Graph graph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H_NEW_60_14_4_1.csp");
	PCGraph graph = GQRGraphReader.readGraphFromGQR("./JAVAJNI/H_60_14_4_1.csp");
    graph.setName("Oringial");
//    Graph pcgraph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H_NEW_60_14_4_1_PC.csp");
	PCGraph pcgraph = GQRGraphReader.readGraphFromGQR("./JAVAJNI/H_60_14_4_1_PC.csp");
    pcgraph.setName("PC");
	
	//Entropy.calEntropy(graph);
//	Entropy.produceQueueWithEntropySorted(graph);
//  Entropy.analysis(graph);
 //   Entropy.entropyWithRecords(graph, 5, 23, new LinkedList<biConstraint<Integer>>());
	PCGraph clone = new PCGraph(60,14,4);
	clone.setName("Min");
    Entropy.allInvolvedEdges(graph,pcgraph,clone,5,23,true);
    Entropy.allInvolvedEdges(graph,pcgraph,clone,5,32,true);
    Entropy.allInvolvedEdges(graph,pcgraph,clone,20,41,true);
    Entropy.allInvolvedEdges(graph,pcgraph,clone,30,41,false);
    
	
/* 
 * Sample 26
 * 
 * */
	
/*    	Entropy.allInvolvedEdges(graph,pcgraph,clone,0,20,false);
    	Entropy.allInvolvedEdges(graph,pcgraph,clone,0,36,false);
    	Entropy.allInvolvedEdges(graph,pcgraph,clone,0,42,false);
      	Entropy.allInvolvedEdges(graph,pcgraph,clone,1,42,true);
     	Entropy.allInvolvedEdges(graph,pcgraph,clone,2,11,true);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,2,26,false);
  		Entropy.allInvolvedEdges(graph,pcgraph,clone,2,36,false);
 		Entropy.allInvolvedEdges(graph,pcgraph,clone,2,37,false);
  		Entropy.allInvolvedEdges(graph,pcgraph,clone,11,22,false);
  		Entropy.allInvolvedEdges(graph,pcgraph,clone,11,36,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,11,37,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,17,33,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,18,26,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,18,42,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,20,22,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,20,36,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,26,33,false);
    	Entropy.allInvolvedEdges(graph,pcgraph,clone,26,36,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,26,59,false);
    	Entropy.allInvolvedEdges(graph,pcgraph,clone,27,59,false);
      	Entropy.allInvolvedEdges(graph,pcgraph,clone,30,33,false);
    	Entropy.allInvolvedEdges(graph,pcgraph,clone,30,59,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,33,59,false);
    	Entropy.allInvolvedEdges(graph,pcgraph,clone,37,52,false);
  		Entropy.allInvolvedEdges(graph,pcgraph,clone,37,53,false);
   		Entropy.allInvolvedEdges(graph,pcgraph,clone,52,53,false);
*/
 //   GQR.generateGraphGQR(clone,"./JAVAGNI/MinNew");
     GQR.generateGraphGQR(clone,"./JAVAGNI/Min");
    StructureDifference.printDifference(graph, clone);

}
}
