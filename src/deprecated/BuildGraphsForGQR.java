package deprecated;

import java.io.IOException;
import java.util.LinkedList;

import analysis.EntropyConstraint;
import essential.GQR;
import essential.PCGraph;

public class BuildGraphsForGQR {
	public static void printGraph(PCGraph graph)
	{
		for (int i = 0; i < graph.getNumOfNodes()-1;i++)
			for (int j = i+1; j <graph.getNumOfNodes();j++)
			{
				if(graph.getEdges()[i][j] !=255)
					System.out.println("scsp.setConstraint("+i+","+j+","+"1);");
			
			}
		
		
		
		
	}
    public static void remove_Entropy(PCGraph graph,LinkedList<EntropyConstraint> entropy)
    {
        int count = 0;
         for (EntropyConstraint ec : entropy)
         {
             if(ec.getEntropy() > 0)
             {
                 count++;
                 graph.getEdges()[ec.getI()][ec.getJ()] = 255;
         System.out.println("Consttraint "+ ec.getI() +"  "+ ec.getJ()
                     +" entropy: " + ec.getEntropy() + " has been removed ");
            
             }
         }
         System.out.println("totally " + count + " constarints have been removed from " + entropy.size());
    }
         
	
public static void main(String args[]) throws IOException
{
	
  // GQR.generateGraphGQR(256, 20, 4, "NP8", 100, "H_256_20_4_100instances");
	 GQR.generateGraphGQR(80, 14, 4, "NP8", 100, "H_1th_cluster_80_14_4");	 
	//GQR.generateGraphGQR(10, 3, 4, "NP8", 1, "Test_Graph");
	//Graph graph = GQRReader.readGraphFromGQR("/home/users/xiaoyuge/Emp/GNI/H_50th_220_20_4.csp");
	// BuildGraphsForGQR.printGraph(graph);

}
}
