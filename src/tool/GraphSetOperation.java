package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import analysis.StructureDifference;

import essential.Constraint;
import essential.FastSimulator;
import essential.GQR;
import essential.MTS;
import essential.PCGraph;
import essential.Relation;
import essential.SetOperators;

public class GraphSetOperation {
	public static void replaceOneRelationUsingUniversal(PCGraph graph)
	{
	    
		PCGraph clone = graph.clone(); 	
		for (int i = 0;i<graph.getNumOfNodes()-1;i++)
		{
			for (int j = i+1;j<graph.getNumOfNodes();j++)
			{
			
				if (clone.getEdges()[i][j] != 255)
				{	
					clone.getEdges()[i][j] = 255;
				    clone.getEdges()[j][i] = 255;	
			  	//    BuildGraphForGQR.generateGraphGQR(clone,"./Target/H_NEW_60_14_4_9_"+"RU"); // RU: relpace with Universal
				    GQR.generateGraphGQR(clone,"./JAVAGNI/"+graph.getName()+"RU");
				// Restore it --------------------------------
				    clone.getEdges()[i][j] = graph.getEdges()[i][j];
				    clone.getEdges()[j][i] = graph.getEdges()[j][i];
				}
				}
			
		}
		
		
		
	}
	/**
	 * @param originalEdges: edges/constraints in the original graph
	 * @param newEdges: edges/constraints in the new graph (usually after applying PC)
	 * @return strong edges in the original graph. (Weak edges that will be effected by others removed)
	 * */
/*	public static int[][] diff(int[][] originalEdges, int[][] newEdges)
	{
		int[][] diff = new int[originalEdges.length][originalEdges.length];
		for (int i = 0; i < diff.length; i++ )
			for (int j = 0; j < diff.length; j++ )
			{
				if(SetOperators.)
			}
	}*/
	public static void replaceAllRelationsUsingMTS(PCGraph graph,String filename) throws Exception
	{
		PCGraph clone = graph.clone(); 	
		for (int i = 0;i<graph.getNumOfNodes()-1;i++)
		{
			for (int j = i+1;j<graph.getNumOfNodes();j++)
			{
			
				if ( !MTS.inMTS(clone.getEdges()[i][j], "H8"))
				{	
					System.out.println("i  "+ i + "j  "+j);
				//	System.out.println(BinaryRelations.translateToString(clone.getNodes()[i][j]));
					clone.getEdges()[i][j] = MTS.extendToMTS(clone.getEdges()[i][j], "H8");
					System.out.println("------: "+Relation.translateToString(clone.getEdges()[i][j]));
				    clone.getEdges()[j][i] = SetOperators.inverse(clone.getEdges()[i][j]);
			  	    
			
				}
				}
			
		}
		GQR.generateGraphGQR(clone,filename); 
		
		
	}
	/**
	 * Split a graph into two sub-graphs, one is the relation changed after PC, one is not changed. 
	 * 
	 * 
	 * **/
	public static void SplitToTwoGraphs(PCGraph og,PCGraph ng)
	{
		
	}
	
	public static void shootMinimumInconsistentNetwork(PCGraph og)
	{
		int count = 0;
		PCGraph clone = og.clone(); 	
		for (int i = 0;i<og.getNumOfNodes()-1;i++)
		{
			for (int j = i+1;j<og.getNumOfNodes();j++)
			{
			
				
				
				if (clone.getEdges()[i][j] != 255)
				{	
					count ++;
					if (count != 46 & count != 52 & count != 58 & count != 67 & count !=71 &  count !=72 & count !=74
							& count !=77& count !=79 & count !=81  & count !=83  & count!=86  & count!=94 & count!=99 & count!=102
							& count!=104 & count!=114  & count!=117  & count!=121 & count!=122 & count!=123 & count!=128 & count!=131
							& count!=133)
					{
					 
					 clone.getEdges()[i][j] = 255;
				     clone.getEdges()[j][i] = 255;
					}
					else 
					{
						System.out.println(i+"   "+j);
					}
			  	    GQR.generateGraphGQR(clone,"./FindMin/H_60_14_4_0_"+"RUFM"); // RU: relpace with Universal
				}
				}
			
		}
		
		
		
		
	}

	
	/**
	 * Read Generated graphs index from given input
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * 
	 * 
	 * 
	 * **/
	public static LinkedList<Integer> keyConstraint(String filename) throws NumberFormatException, IOException
	{
		 LinkedList<Integer> indice = new LinkedList<Integer>();
		  FileReader fr = new FileReader(new File(filename));
		  BufferedReader br = new BufferedReader(fr);
		  while(br.ready())
		  {
			  String line = br.readLine();
			  int index = Integer.parseInt(line.substring(line.indexOf("#")+1,line.indexOf("-")));
			  indice.add(index);
		  }
		  return indice;
	}
	
	public static LinkedList<Constraint> findoutKeyConstraints(PCGraph graph, LinkedList<Integer> list)
	{
		 LinkedList<Constraint> constraints = new  LinkedList<Constraint>();
		PCGraph clone = graph.clone();
		int count = 0;
		for (int i = 0;i<graph.getNumOfNodes()-1;i++)
		{
			for (int j = i+1;j<graph.getNumOfNodes();j++)
			{
			 
				if (clone.getEdges()[i][j] != 255)
				{	
					count ++;
					//System.out.println(count);
                    if (list.contains(count))
				    {	
				    	constraints.add(new Constraint(i,j));
				    	System.out.println(i+"     "+j);
				    }
				}
			}
			
		}
		return constraints;
		
		
	}
	public static int findDifference(PCGraph g1, PCGraph g2)
	{
		int diff = 0;
		for (int i = 0; i < g1.edges.length - 1; i++)
			for (int j = i + 1; j < g1.edges.length; j++)
			{
				if (g1.edges[i][j] != g2.edges[i][j] && g1.edges[i][j] != 255 && g2.edges[i][j] != 255)
						diff++;
					
			}
		return diff;
	}
	public static void main(String args[]) throws Exception
	{
		//PCGraph graph = GQRReader.readGraphFromGQR("./JAVAGNI/Min.csp"); 
		PCGraph g1 = GQRGraphReader.readGraphFromGQR("test.csp");
		g1.createPathQueue();
		FastSimulator sim = new FastSimulator();
		//Timer.start();
		System.out.println(sim.Path_Consistency(g1));
		PCGraph g1_pc = sim.graph;
		
		
		PCGraph g2 = GQRGraphReader.readGraphFromGQR("test1.csp");
		g2.createPathQueue();
		sim = new FastSimulator();
		//Timer.start();
		System.out.println(sim.Path_Consistency(g2));
		PCGraph g2_pc = sim.graph;
		StructureDifference.printDifference(g1_pc,g2_pc);
		//Timer.end("end");
		//PCGraph g2 = GQRGraphReader.readGraphFromGQR("");
		//System.out.println(findDifference(g1,g2));
	 //   GQR.generateGraphGQR(graph, "./JAVAGNI/H_60_14_4_1");
	//	Graph graph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H_60_14_4_1.csp");
	//	GraphPolymorphsim.replaceAllRelationsUsingMTS(graph, "./JAVAGNI/H601441_MTS");
	
		
		
	//	System.out.println(graph.getNumOfEdges());
	//	graph.printGraph();
	//	Graph og = Graph.loadGraph("./Target/H_60_14_4_3");
//		Graph graph1 = ReadGraphFromGQR.readGraphFromGQR("./Target/H_NEW_60_14_4_9_PC");

		
		//		System.out.println(graph1.getNumOfEdges());
		//	graph.saveGraph("./Target/H_60_14_4_1_PC");
//	     FastSimulator fs = new FastSimulator();
//		fs.Path_Consistency(og);
		//fs.graph.printGraph();
	//	fs.graph.saveGraph("H_60_14_4_0_PC");
  //      Difference.printDifference(graph, fs.graph);
//		Graph graph = ReadGraphFromGQR.readGraphFromGQR("./JAVAJNI/H_60_14_4_1.csp");
  	//GraphSetOperation.replaceOneRelationUsingUniversal(graph);

	//GraphPolymorphsim.findoutKeyConstraints(graph,GraphPolymorphsim.keyConstraint("./JAVAGNI/H_NEW_60_14_4_3_RU_C"));	
//		 GraphPolymorphsim.shootMinimumInconsistentNetwork(graph);
		
		
	}

}
