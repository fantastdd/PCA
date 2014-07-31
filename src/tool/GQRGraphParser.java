package tool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import essential.PCGraph;
import essential.Relation;


public class GQRGraphParser {
	
	private static int counter = 1;
	private static File file ; 
	private static String filename="";
	private static FileWriter fis;
	 
	
	public static void parseGraphGQR(List<PCGraph> graphs, String... _filename)
	{
		if(_filename.length > 0 )
			filename = _filename[0];
		for (PCGraph graph : graphs)
			parseGraphGQR(graph);
	}
	
	public static void generateGraphGQR(PCGraph graph,String filename)
	{
		GQRGraphParser.filename = filename;
		parseGraphGQR(graph);
	}

	/**@param int rept: the number graphs will be generated. The graphs are identical , used for testing. calculate average time
	 * */
	public static void generateGraphGQR(PCGraph graph,String filename,int rept)
	{
		GQRGraphParser.filename = filename;
		for (int i = 0;i < rept;i++)
			parseGraphGQR(graph);
	}
	public static void generateGraphGQR(int node,int degree,int label,String splitSet,int num,String filename)
	{
		GQRGraphParser.filename = filename;
		for (int i = 0;i<num;i++)
		{
		 PCGraph graph = new PCGraph(node,degree,label,false,splitSet);
		// graph.saveGraph(filename+"_"+i);
		 
		 parseGraphGQR(graph);
		}
		
	} 
	
	
	
	
	public static void parseGraphGQR(PCGraph graph) 
	{
	if(filename!="")
		file	= new File(filename+".csp");
	else 
		file = new File("CSPForGQR.csp");
    if (!file.exists())
		try {
			file.createNewFile();
			counter = 1;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
		     
    		fis = new FileWriter(file,true);
	        // write the headline per graph
			String header = (graph.numOfNodes-1)+" #"+(counter++)
					+"-N"+graph.numOfNodes+"-D"+graph.getGraphDegree()+
					"-L"+graph.getLabelSize()+"\n";
            fis.write(header);
		
			 for (int i = 0;i<graph.numOfNodes -1;i++)
			 {
				 for (int j =i;j<graph.numOfNodes;j++)
				 {
					 int rels =graph.getEdges()[i][j];

					 if(Integer.bitCount(rels)!=8)
					 {
			            String line =" "+ i +"  " + j + " "+ Relation.translateToString(rels);
			            line = line.replace("[", "(");
			            line = line.replace("]", ")");
			            line = line.replace(",", " ");
			            line = line.replace("i", "I");
			            line+="\n";        
			            fis.write(line);
			           
					 }
					
				 }
			 }		
	   fis.append(".\n");
	   fis.close();
	} catch (FileNotFoundException e) {
	

	
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		}
	
	// Every GQR graph should be ended with a "." at the bottom line
	public static void endWrite()
	{
	
	}
}

