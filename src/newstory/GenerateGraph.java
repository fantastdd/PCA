package newstory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import tool.GQRGraphParser;
import tool.Log;
import essential.PCGraph;

public class GenerateGraph {
List<PCGraph> generatedGraphs = new LinkedList<PCGraph>();
public void generateCompleteGraph(int k)
{
	int[][] edges = new int[k][k];
	assign(0, 1, 0, edges);
	GQRGraphParser.parseGraphGQR(generatedGraphs, "comp3");
}
public void assign(int i, int j, int label, int[][] edges )
{
	while (label < 254)
	{
		edges[i][j] =  ++label;
		//assign labels to constraint i, j +1
		if (j + 1 < edges.length)
		{ 
			/*int mlabel = assign(i, j + 1, 0, edges);
			while(mlabel < 254)
			{
				mlabel = assign(i, j + 1, mlabel, edges);
			}*/
			assign(i, j + 1, 0, edges);
		}
		else
			//assign labels to constraint  i + 1, i + 2
			if (i < edges.length - 2)
			{
				/*int mlabel = assign(i + 1, i + 2, 0, edges);
				while(mlabel < 254)
				{
					mlabel = assign(i + 1, i + 2, mlabel, edges);
				}	*/
				assign(i + 1, i + 2, 0, edges);
			}
			else
			//reach the terminate node
			{
				//write graph;
				//Log.echo("i j " + i + " " + j + " " + label);
				PCGraph graph = new PCGraph(edges);
				//Log.echo(graph.printGraph());
				generatedGraphs.add(graph);
				if (generatedGraphs.size() % 10000 == 0)
					Log.echo(" Generated " + generatedGraphs.size() + " graphs ");
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
	gg.generateCompleteGraph(3);
	Log.echo("Number of graphs  " + gg.generatedGraphs.size());
}

}
