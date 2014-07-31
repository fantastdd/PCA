package analysis;

import java.io.IOException;
import java.util.LinkedList;

import tool.GQRGraphReader;
import deprecated.Fun;
import essential.PCGraph;

public class RetainChanges {
public static PCGraph retainChanges(PCGraph og, PCGraph ng)
{
	PCGraph graph = ng.clone();
	for (int i = 0;i< og.getNumOfNodes()-1;i++)
		for (int j = i+1;j<og.getNumOfNodes();j++)
		{
			//if (og.getNodes()[i][j] == 255 || (Integer.bitCount(og.getNodes()[i][j])> Integer.bitCount(ng.getNodes()[i][j])))
			if (og.getEdges()[i][j] == 255 || (Integer.bitCount(og.getEdges()[i][j]) > Integer.bitCount(ng.getEdges()[i][j])))
			{
			    /*System.out.println("@@@@ "+BinaryRelations.translateToString(og.getNodes()[5][23]));
			    System.out.println("@@@@ "+BinaryRelations.translateToString(ng.getNodes()[5][23]));*/
				/*if(i == 5 && j == 32)
				{	System.out.println("@@@@ "+BinaryRelations.translateToString(og.getNodes()[5][32]));
				   System.out.println("#### "+BinaryRelations.translateToString(ng.getNodes()[5][32]));
				   }*/
				graph.getEdges()[i][j] = 255;
				graph.getEdges()[j][i] = 255;
			}
		}
   return graph;
}

/**
 * OG' = PC(OG)
 * For i < nd do
 * Replace (R@OG') = OG''
 * PC(OG'') = OG'''
 * For each R' in OG'''
 * Compare R and R'
 * If R < R', R might be a key. Non-Redundant
 * @throws IOException 
 * */
public static PCGraph buildGraphFromNonRedunantConstraints(PCGraph PCOG,LinkedList<PCGraph> finalOGs) throws IOException
{
	
	PCGraph newGraph = new PCGraph(60,14,4);
	newGraph.setName("Retain Non-Reduancy");
	
    for (PCGraph graph:finalOGs)
    {
    	StructureDifference.shootNonRedundancy(PCOG, graph,newGraph);
    }
	
return newGraph;


} 

public static void main(String args[]) throws IOException
{
	PCGraph OG = PCGraph.loadGraph("./Target/H_60_14_4_0"); 
	PCGraph PCOG = GQRGraphReader.readGraphFromGQR("./Shoot/PC_H601440_*");
	PCOG.setName("PCOG");
	PCGraph rtPCOG = RetainChanges.retainChanges(OG, PCOG);
	rtPCOG.setName("rtPCOG");
	LinkedList<PCGraph> finalOGs = GQRGraphReader.readGraphsFromGQR("./Shoot/PC_601440RU_*");
	int count = 0;
	for(PCGraph og:finalOGs)
	{
		System.out.println(++count); 
		StructureDifference.printDifference(PCOG, og);
		og = RetainChanges.retainChanges(OG, og);
		
	
	
	}
	PCGraph graph = RetainChanges.buildGraphFromNonRedunantConstraints(rtPCOG,finalOGs);
 //   graph.printGraph();
  //  Difference.printDifference(PCOG, rtPCOG);
    StructureDifference.printDifference(rtPCOG, graph);
  Fun fun = new Fun(graph);
   Fun fun1 = new Fun(rtPCOG);
   Fun fun2 = new Fun(PCOG);

}



}
