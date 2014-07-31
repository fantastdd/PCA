package essential;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class GQR {
	
	private static int counter = 1;
	private static File file ; 
	private static String filename="";
	private static FileWriter fis;
	 
	
	
	public static void generateGraphGQR(PCGraph graph,String filename)
	{
		GQR.filename = filename;
		generateGraphGQR(graph);
		
		
	}
	/**
	 * Check the update probability of rcc8 network
	 * */
	public static void checkUpdateProbabilityOnHORNRelations()
	{
		int[] base_relations = {1,2,4,8,16,32,64,128};
		int r1,r2,r3;
		int total = 0;
		int update = 0;
		for (int i = 0; i < 8; i++)
		{
			 r1 = base_relations[i];
			for(int j = 1; j < 255; j++)
			{
				r2 = j;
				if(!MTS.BIMTS_H8.contains((Integer)r2))
						continue;
				/*if(r2 == 1 || r2 == 2 || r2 == 4 || r2 == 8 || r2 == 16 || r2 == 32 || r2 == 64 || r2 == 128)
					continue;
					*/
				else
				for(int k = 1; k < 255; k++)
				{  
					r3 = k;
					//if(r3 == 1 || r3 == 2 || r3 == 4 || r3 == 8 || r3 == 16 || r3 == 32 || r3 == 64 || r3 == 128)
						if(!MTS.BIMTS_H8.contains((Integer)r3))
						continue;
					else
					{	
						total++;
						int new_r3 = CompositionTable.LookUpTable(r1, r2);
						if(new_r3 < r3)
							update++;
					}
				}
			}
			
		}
		System.out.println((double)update/total);
				
	}
	/**
	 * Check the update probability of rcc8 network
	 * */
	public static void checkUpdateProbabilityOnAtomicRelations()
	{
		int[] base_relations = {1,2,4,8,16,32,64,128};
		int r1,r2,r3;
		int total = 0;
		int update = 0;
		for (int i = 0; i < 8; i++)
		{
			 r1 = base_relations[i];
			for(int j = 0; j < 8; j++)
			{
				r2 = base_relations[j];
			
				for(int k = 0; k < 8; k++)
				{  
					r3 = base_relations[k];
					total++;
					//int new_r3 = CompositionTable.LookUpTable(r1, r2);
					int new_r3 = SetOperators.intersection(r3, 
								CompositionTable.LookUpTable(r1, r2));
					//System.out.println(new_r3);
					if( new_r3 != r3  )
					{	
						update++;
						//System.out.println(new_r3);
					}
				}
			}
		}
			
		System.out.println(" total " + total + "  update " + update);
		System.out.println((double)update/total);
				
	}
	/**
	 * Check the update probability of rcc8 network
	 * */
	public static void checkUpdateProbabilityOnALLRelations()
	{
		int[] base_relations = {1,2,4,8,16,32,64,128};
		int r1,r2,r3;
		int total = 0;
		int update = 0;
		for (int i = 0; i < 8; i++)
		{
			 r1 = base_relations[i];
			for(int j = 1; j < 255; j++)
			{
				r2 = j;
				/*if(r2 == 1 || r2 == 2 || r2 == 4 || r2 == 8 || r2 == 16 || r2 == 32 || r2 == 64 || r2 == 128)
					continue;
				else*/
				for(int k = 1; k < 255; k++)
				{  
					r3 = k;
					/*if(r3 == 1 || r3 == 2 || r3 == 4 || r3 == 8 || r3 == 16 || r3 == 32 || r3 == 64 || r3 == 128)
						continue;
					else*/
					{	
						total++;
						int new_r3 = SetOperators.intersection(r3, 
								CompositionTable.LookUpTable(r1, r2));
						if(new_r3 != r3 && new_r3 != 0)
							update++;
					}
				}
			}
			
		}
		System.out.println((double)update/total);
				
	}
	/**@param int rept: the number graphs will be generated. The graphs are identical , used for testing. calculate average time
	 * */
	public static void generateGraphGQR(PCGraph graph,String filename,int rept)
	{
		GQR.filename = filename;
	for (int i = 0;i<rept;i++)
		generateGraphGQR(graph);
		
		
	}
	public static void generateGraphGQR(int node,int degree,int label,String splitSet,int num,String filename)
	{
		GQR.filename = filename;
		for (int i = 0;i<num;i++)
		{
		 PCGraph graph = new PCGraph(node,degree,label,false,splitSet);
		// graph.saveGraph(filename+"_"+i);
		 
		 generateGraphGQR(graph);
		}
		
	} 
	
	
	
	
	public static void generateGraphGQR(PCGraph graph) 
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
public static void main(String args[])
{
	
/*for (int i =0;i<10;i++){
Graph graph = new Graph(10,9,5,false);
GQR.generateGraphGQR(graph); }*/
	//GQR.checkUpdateProbabilityOnHORNRelations();
	GQR.checkUpdateProbabilityOnALLRelations();
}








}

