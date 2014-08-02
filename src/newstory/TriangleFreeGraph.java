package newstory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import tool.GQRGraphGenerator;
import tool.GQRGraphReader;
import tool.Log;
import analysis.EntropyConstraint;
import essential.PCGraph;

public class TriangleFreeGraph {
	public TriangleFreeGraph(PCGraph graph)
	{
		//graph.printEdges();
		//System.out.println(graph.getNumOfEdges());
		getTriangleFreeGraph(graph);
	}
	public int[][] getTriangleFreeGraph(PCGraph graph)
	{
		EntropyConstraint econ = null;
		int count = 0;
		int[][] edges = graph.edges;
		econ = getMostTrianglesEdge(edges);
		while (econ != null)
		{	
			if(econ.getEntropy() == 0)
				break;
			edges[econ.getI()][econ.getJ()] = 255;
			count ++;
			econ = getMostTrianglesEdge(edges);
			
		}
		Log.echo(count + " edges have been removed from " + graph.numOfEdges, "getTriangleFreeGraph()", "TFG");
		return edges;
	}
	
	private EntropyConstraint getMostTrianglesEdge(int[][] edges)
	{
		LinkedList<EntropyConstraint> econs = new LinkedList<EntropyConstraint>();
		EntropyConstraint econ = null;
		for (int i = 0; i < edges.length - 1; i++)
			for (int j = i + 1; j < edges.length; j++)
		{
			if(edges[i][j] != 255)
			{
				int count = calTriangles(i, j , edges);
				
				econ = new EntropyConstraint(i, j, count);
				econs.add(econ);
			}
		}
		
		Collections.sort(econs);
		if(!econs.isEmpty())
		{
			econ = econs.getLast();
			
		
		}
		//Log.echo(econ.getI() + "  " + econ.getJ());
		return econ;
	}
	
	private int calTriangles(int source, int destination, int[][] edges)
	{
		int count = 0;
		for (int index = 0; index < edges.length; index ++)
		{
			if (index < source && edges[index][source] != 255)
			{
				if (edges[index][destination] != 255)
					count ++;
			}
			else
				if (index > source && edges[source][index] != 255)
				{
					if (edges[index][destination] != 255 || edges[destination][index] != 255)
						count ++;
				}
		}
		return count;
		
	}
	
	public static void main(String args[]) throws IOException
	{
		//String filename = "H_42th_220_20_4";
		//String filename = "H_2ndCluster14_OG";
		String filename = "H_49th_220_20_4";
		PCGraph graph = GQRGraphReader.readGraphFromGQR("/home/xiaoyu/Emp/GNI/" + filename + ".csp");
		TriangleFreeGraph tfg = new TriangleFreeGraph(graph);
		File file = new File("TFG_" + filename + ".csp");
		if (file.exists())
			file.delete();
		GQRGraphGenerator.generateGraphGQR(graph, "TFG_" + filename);
		
	}
}
