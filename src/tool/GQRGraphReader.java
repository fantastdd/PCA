package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import analysis.StructureDifference;
import essential.PCGraph;
import essential.Relation;
import essential.SetOperators;

public class GQRGraphReader {
	public static LinkedList<PCGraph> graphs = new LinkedList<PCGraph>();

public static PCGraph readGraphFromGQR(String filename) throws IOException
{
   FileReader fr = new FileReader(new File(filename));
   BufferedReader br = new BufferedReader(fr);
   String header = br.readLine();
   boolean gqr1500Format = true;
   int node;
   int label;
   int degree;
   if(header.contains("R"))
   {
	   //gqr1500 header N R D S
	   node = Integer.parseInt(header.substring(header.indexOf("N") + 1,header.indexOf("R") - 1));
	   label = Integer.parseInt(header.substring(header.indexOf("R") + 1, header.indexOf("D") - 1));
	   degree = Integer.parseInt(header.substring(header.indexOf("D") + 1,header.indexOf("S") - 1));
   }
   else
   {
	 //default gqr1418 header
	   node = Integer.parseInt(header.substring(header.indexOf("N") + 1,header.indexOf("D")-1));
	   label = Integer.parseInt(header.substring(header.indexOf("L")+1));
	   degree = Integer.parseInt(header.substring(header.indexOf("D")+1,header.indexOf("L")-1));
	   //System.out.println(node + " " + label + " " + degree);
	   gqr1500Format = false;
   }
   PCGraph graph = new PCGraph(node,degree,label);

  
  
   while(br.ready())
   {
	   
	   String line = br.readLine();
	   if(line.contains("."))
		   break;
	   // <space>i<space><space>j
	   int i = 0;
	   int j = 0;
	   if(!line.contains("  "))
	   {
		   	i = Integer.parseInt(line.substring(0,line.indexOf(" ")));
		    j = Integer.parseInt(line.substring(line.indexOf(" ") + 1,line.indexOf("(")- 1));
		    //line = line.substring(line.indexOf('(') + 1,line.indexOf(')'));
		    //line = line.substring(line.indexOf('(') + 1);
		    line = line.substring(line.indexOf('(') + 2,line.indexOf(')'));
		   //System.out.println(i + "  " + j + " " + line);
	   }
	   else
	   {
	        i = Integer.parseInt(line.substring(1,line.lastIndexOf("  ")));
	        j = Integer.parseInt(line.substring(line.lastIndexOf("  ")+2,line.indexOf("(")-1));
	        line = line.substring(line.indexOf('(') + 2,line.indexOf(')'));
	       
	   }
	 
	   int rels = 0;
	   //System.out.println(line);
	

	   while(line.contains(" "))
	   {
		   //System.out.println(line);
		   rels |= Relation.get(line.substring(0, line.indexOf(" ")));
		   //System.out.println("process " + line.substring(0, line.indexOf(" ")) + " " + rels);
		   line = line.substring(line.indexOf(" ") + 1);
		  
		}
	   if(gqr1500Format)
		   rels |= Relation.get(line);
	
	   graph.getEdges()[i][j] = rels;
	 
	   graph.getEdges()[j][i] = SetOperators.inverse(rels);
	   
	   
 }
 if (filename.contains("."))
graph.setName(filename.substring(0,filename.indexOf(".")));
 else
	 graph.setName(filename);
 
//System.out.println("  " + graph.getNumOfEdges());
br.close();
return graph;

}

public static LinkedList<PCGraph> readGraphsFromGQR(String filename) throws IOException
{
   FileReader fr = new FileReader(new File(filename));
   BufferedReader br = new BufferedReader(fr);
 
   while(br.ready())
   {
	  
	   String header ="";
	   while(!header.contains("#")&br.ready())
		   {
		     header = br.readLine();
		   }
	   if(!br.ready())
		   break;

	   int node = Integer.parseInt(header.substring(header.indexOf("N")+1,header.indexOf("D")-1));
	   int label = Integer.parseInt(header.substring(header.indexOf("L")+1));
	   int degree = Integer.parseInt(header.substring(header.indexOf("D")+1,header.indexOf("L")-1));
	   PCGraph graph = new PCGraph(node,degree,label);
	   String end = "";
	while(true){   
	   String line = br.readLine();
	   if(line.contains("."))
		   break;
	   // <space>i<space><space>j
	   int i = 0;
	   int j = 0;
	   if(!line.contains("  "))
	   {
		   i = Integer.parseInt(line.substring(0,line.indexOf(" ")));
		    j = Integer.parseInt(line.substring(line.indexOf(" ")+1,line.indexOf("(")-1));
	   }
	   else
	   {
	        i = Integer.parseInt(line.substring(1,line.lastIndexOf("  ")));
	        j = Integer.parseInt(line.substring(line.lastIndexOf("  ")+2,line.indexOf("(")-1));
	   }
	   line = line.substring(line.indexOf('(')+2,line.indexOf(')'));
	   int rels = 0;
	   while(line.contains(" "))
	   {
		
		   rels |= Relation.get(line.substring(0,line.indexOf(" "))); 
		   line = line.substring(line.indexOf(" ")+1);
		}
	   graph.getEdges()[i][j] = rels;
	   graph.getEdges()[j][i] = SetOperators.inverse(rels);
	}
	br.close();
	graphs.add(graph);
	   
 }
return graphs;

}

public static void main(String args[]) throws IOException
{
	 LinkedList<PCGraph> graphs = GQRGraphReader.readGraphsFromGQR("./FindMin/H601441RU_PC.csp");
	 System.out.println(graphs.size());
	 PCGraph graph = PCGraph.loadGraph("./FindMin/H_60_14_4_1");
	 graph.setName("Origin Graph");
	 PCGraph newGraph = new PCGraph(60,14,4);
	 for(PCGraph currentGraph:graphs)
	 {
		 StructureDifference.shootNonRedundancy(graph, currentGraph, newGraph);
		// Difference.printDifference(graph, currentGraph);
	 }
	// newGraph.saveGraph("./FindMin/NewGraph");
	 GQRGraphParser.generateGraphGQR(newGraph,"./FindMin/NewGraph");
  /*Graph graph = ReadGraphFromGQR.readGraphFromGQR("./Target/H601440RU_#522.csp");
  graph.printGraph();
  graph.setName("H601440RU_#522");
  graph.saveGraph("./Target/H601440RU_#522");
  
  Graph graph1 = ReadGraphFromGQR.readGraphFromGQR("./Target/PC_601440RU_#522");
  graph1.printGraph();
  graph1.setName("PC_601440RU_#532");
  graph1.saveGraph("./Target/PC_601440RU_#522");*/
  
  /*Graph graph1 = Graph.loadGraph("./Target/H_60_14_4_0");
  graph1.setName("H_60_14_4_0");
  graph1.saveGraph("./Target/H_60_14_4_0");
  
  Graph graph2 = Graph.loadGraph("./Target/H_60_14_4_0_PC");
  graph2.setName("H_60_14_4_0_PC");
  graph2.saveGraph("./Target/H_60_14_4_0_PC");
  */

/*  Graph graph = ReadGraphFromGQR.readGraphFromGQR("./Target/PC_601440RU_#396");
  graph.printGraph();
  graph.setName("PC_601440RU_#396");
  graph.saveGraph("./Target/PC_601440RU_#396");
  

  Graph graph1 = ReadGraphFromGQR.readGraphFromGQR("./Target/H601440RU_#396.csp");
  graph1.printGraph();
  graph1.setName("H601440RU_#396");
  graph1.saveGraph("./Target/H601440RU_#396");*/
  
	 /* Graph graph = ReadGraphFromGQR.readGraphFromGQR("./Target/H_80_14_4_0_PC.csp");
	  graph.printGraph();
	  graph.setName("H_80_14_4_0_PC");
	  graph.saveGraph("./Target/H_80_14_4_0_PC");*/
}
}
