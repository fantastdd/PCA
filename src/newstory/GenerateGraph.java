package newstory;

import java.io.File;

import tool.GQRGraphParser;
import tool.GQRGraphReader;
import essential.PCGraph;

public class GenerateGraph {
public static void main(String args[])
{
	PCGraph graph = new PCGraph(500, 40, 4, false ,"NP8");
	String filename = "H_1st_500_40_4";
	TriangleFreeGraph tfg = new TriangleFreeGraph(graph);
	File file = new File("TFG_" + filename + ".csp");
	if (file.exists())
		file.delete();
	GQRGraphParser.generateGraphGQR(graph, "TFG_" + filename);

}
}
