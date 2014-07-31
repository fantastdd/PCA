package test;

import essential.FastSimulator;
import essential.PCGraph;
import essential.SetOperators;

public class HitIt {
public static void main(String args[]) throws Exception
{
 PCGraph graph = PCGraph.loadGraph("Graph_5_4_3_ALL_0.0.obj");	
 PCGraph clone = PCGraph.loadGraph("Graph_5_4_3_ALL_0.0.obj");	
 graph.getEdges()[0][1] = 72;
 graph.getEdges()[1][0] = SetOperators.inverse(graph.getEdges()[0][1]);
 graph.printGraph();
 FastSimulator fs = new FastSimulator();
 clone.getEdges()[0][1] = 72;
 clone.getEdges()[1][0] = SetOperators.inverse(graph.getEdges()[0][1]);
// System.out.println(fs.Path_Consistency(graph));
 for (int i = 0;i<graph.getNumOfNodes()-1;i++)
 {
	 for (int j = i+1;j<graph.getNumOfNodes();j++)
	 {
		 
		 for (int k = 0;k<8;k++)
		 {
			 
			 graph.getEdges()[i][j]|= 1<<k;
			 graph.getEdges()[j][i] = SetOperators.inverse(graph.getEdges()[i][j]);
			 if (!fs.Path_Consistency(graph))
				 graph.printGraph();
			 else
			 {	 graph.getEdges()[i][j]= clone.getEdges()[i][j];
			   graph.getEdges()[j][i] = SetOperators.inverse(graph.getEdges()[i][j]);
			 }
		 }
		 
		 
		 
	 }
	 
	 
	 
	 
 }
 for (int i = 0;i<graph.getNumOfNodes()-1;i++)
 {
	 for (int j = i+1;j<graph.getNumOfNodes();j++)
	 {
		 
		 for (int k = 0;k<8;k++)
		 {
			 for (int t = 0;t<8;t++)
			 { 
			  graph.getEdges()[i][j]|= 1<<k;
			  graph.getEdges()[i][j]|= 1<<t;
			 graph.getEdges()[j][i] = SetOperators.inverse(graph.getEdges()[i][j]);
			 if (!fs.Path_Consistency(graph))
				 graph.printGraph();
			 else
			 {	 graph.getEdges()[i][j]= clone.getEdges()[i][j];
			   graph.getEdges()[j][i] = SetOperators.inverse(graph.getEdges()[i][j]);
			 }
		 }
		 }
		 
		 
		 
	 }



}
	 
	 
	 
 
for (int rel = 1; rel<=255;rel++)
{
	 graph.getEdges()[0][1] = rel;
	 graph.getEdges()[1][0] = SetOperators.inverse(graph.getEdges()[0][1]);
	 if (!fs.Path_Consistency(graph))
	 { 
		 graph.printGraph();
		 graph.getEdges()[0][1]= clone.getEdges()[0][1];
		 graph.getEdges()[1][0] = SetOperators.inverse(graph.getEdges()[0][1]);
	 
	 }
	 else
	 {
		 graph.getEdges()[0][1]= clone.getEdges()[0][1];
		 graph.getEdges()[1][0] = SetOperators.inverse(graph.getEdges()[0][1]);
	 }

}
}
}
