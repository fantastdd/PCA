package newstory;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import tool.GQRGraphGenerator;
import tool.Log;
import essential.PCGraph;

public class GenerateGraph {
//List<PCGraph> gOGraphs = new LinkedList<PCGraph>();//output graphs
StringBuilder sb = new StringBuilder();
private int k;
public void generateCompleteGraph(int k)
{
	this.k = k;
	int[][] edges = new int[k][k];
		int numOfEdges = k * (k - 1)/2;
	sb.ensureCapacity((int) Math.pow(50, numOfEdges));
	assign(0, 1, 0, edges);
	if (sb.length() != 0)
	{
		Log.echo(" Generated "  + gCounter + " graphs ");
		GQRGraphGenerator.writeGraphGQR(sb, "comp" + k);
	}
	//GQRGraphGenerator.parseGraphGQR(gOGraphs, "comp3");
}
public void generateRealComplete3Graph_Atomic()
{
	int[][] edges = new int[3][3];
	int numOfEdges = 3;
	sb.ensureCapacity((int) Math.pow(50, numOfEdges));
	
	ICombinatoricsVector<Integer> initialVector = Factory.createVector(new Integer[]{1, 2, 4, 8, 16, 32, 64, 128});
	// Create a multi-combination generator to generate 3-combinations of
	   // the initial vector
	   Generator<Integer> gen = Factory.createMultiCombinationGenerator(initialVector, 3);
	   
	   Log.echo(" Generated "  + gen.generateAllObjects().size() + " combinations ");
	   for (ICombinatoricsVector<Integer> combination : gen) 
	   {
		   //Get unique order of the labels.
		   
		   	  edges[0][1] = combination.getValue(0);    
		      edges[0][2] = combination.getValue(1);
		      edges[1][2] = combination.getValue(2);
	   }
}
public void generateComplete3Graph()
{
	
	int[][] edges = new int[3][3];
	int numOfEdges = 3;
	sb.ensureCapacity((int) Math.pow(50, numOfEdges));

	Integer[] allRels = new Integer[254];
	for (int i = 0; i < allRels.length; i++)
	{
		allRels[i] = i + 1;
	}
	// Create the initial vector of (apple, orange)
	   ICombinatoricsVector<Integer> initialVector = Factory.createVector(
	      allRels );

	   // Create a multi-combination generator to generate 3-combinations of
	   // the initial vector
	   Generator<Integer> gen = Factory.createMultiCombinationGenerator(initialVector, 3);
	  
	   Log.echo(" Generated "  + gen.generateAllObjects().size() + " graphs ");
	   for (ICombinatoricsVector<Integer> combination : gen) {
	    
	      edges[0][1] = combination.getValue(0);    
	      edges[0][2] = combination.getValue(1);
	      edges[1][2] = combination.getValue(2);
	      PCGraph graph = new PCGraph(edges);
		  GQRGraphGenerator.parseGraphGQR(graph, sb);
		  gCounter ++;
		  if (gCounter % 1000000 == 0)
		  {
				Log.echo(" Generated "  + gCounter + " graphs ");
				GQRGraphGenerator.writeGraphGQR(sb, "comp3");
				sb = new StringBuilder();
			}
	   }
	   if (sb.length() != 0)
	   {
		   Log.echo(" Generated the remaining graphs ");
		   GQRGraphGenerator.writeGraphGQR(sb, "comp3");
	   }
}
int gCounter = 0;
public void assign(int i, int j, int label, int[][] edges )
{
	while (label < 254)
	{
		edges[i][j] =  ++label;
		//assign labels to constraint i, j +1
		if (j + 1 < edges.length)
			assign(i, j + 1, 0, edges);
		else
			//assign labels to constraint  i + 1, i + 2
			if (i < edges.length - 2)
				assign(i + 1, i + 2, 0, edges);
			else
			//reach the terminate node
			{
				//write graph;
				//Log.echo("i j " + i + " " + j + " " + label);
				gCounter ++;
				PCGraph graph = new PCGraph(edges);
				//Log.echo(graph.printGraph());
				GQRGraphGenerator.parseGraphGQR(graph, sb);
				//gOGraphs.add(graph);
				if (gCounter % 1000000 == 0)
				{
					Log.echo(" Generated "  + gCounter + " graphs ");
					GQRGraphGenerator.writeGraphGQR(sb, "comp" + k);
					sb = new StringBuilder();
				}
			}
			
				
	}
	return ;
}
public static void main(String args[])
{
	/*PCGraph graph = new PCGraph(500, 40, 4, false ,"NP8");
	String filename = "H_1st_500_40_4";
	TriangleFreeGraph tfg = new TriangleFreeGraph(graph);
	File file = new File("TFG_" + filename + ".csp");
	if (file.exists())
		file.delete();
	GQRGraphParser.generateGraphGQR(graph, "TFG_" + filename);*/
	GenerateGraph gg = new GenerateGraph();
	//gg.generateCompleteGraph(4);
	gg.generateComplete3Graph();
	//Log.echo("Number of graphs  " + gg.gCounter);
}

}
