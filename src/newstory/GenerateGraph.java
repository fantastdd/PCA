package newstory;
import tool.GQRGraphGenerator;
import tool.Log;
import essential.PCGraph;

public class GenerateGraph {
//List<PCGraph> gOGraphs = new LinkedList<PCGraph>();//output graphs
StringBuilder sb = new StringBuilder();
public void generateCompleteGraph(int k)
{
	int[][] edges = new int[k][k];
		int numOfEdges = k * (k - 1)/2;
	sb.ensureCapacity((int) Math.pow(50, numOfEdges));
	assign(0, 1, 0, edges);
	//GQRGraphGenerator.parseGraphGQR(gOGraphs, "comp3");
}
public void generateComplete3Graph()
{
	int[][] edges = new int[3][3];
	int numOfEdges = 3;
	sb.ensureCapacity((int) Math.pow(50, numOfEdges));
	assign(0, 1, 0, edges);
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
					GQRGraphGenerator.writeGraphGQR(sb, "comp3");
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
	gg.generateCompleteGraph(4);
	Log.echo("Number of graphs  " + gg.gCounter);
}

}
