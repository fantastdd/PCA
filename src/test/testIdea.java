package test;

import analysis.Entropy;
import essential.GQR;
import essential.PCGraph;

public class testIdea {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         PCGraph graph = new PCGraph();
/*         for (int i = 10000;i>0;i--)
         {
        	 graph = graph.buildEntropyO(7,3);
          GQR.generateGraphGQR(graph, "./GNI/SNOI");	 
         }*/
         
 //        Graph graph = new Graph();
         for (int i = 1000;i>0;i--)
         {
        	 graph = graph.buildEntropyO_Seven(3);
          GQR.generateGraphGQR(graph, "./GNI/SNOI8");	 
         }
         
         
       /*  for (int i = 1000;i>0;i--)
         {
        	 Graph graphNormal = new Graph(7,6,3,false,"NP8");
             GQR.generateGraphGQR(graphNormal, "./GNI/SNOI");	 
         }*/
  
  
	}

}
