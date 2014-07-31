package analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tool.GQRGraphReader;

import essential.GQR;
import essential.PCGraph;
import essential.Key;
import essential.Constraint;

public class Entropy {
	public static LinkedList<Constraint> constraints = new LinkedList<Constraint>();
	public static LinkedList<EntropyConstraint> entropy = new LinkedList<EntropyConstraint>();
	public static LinkedList<EntropyConstraint> priorityQueue = new LinkedList<EntropyConstraint>();
	public static ArrayList<Integer> greenvariable = new ArrayList<Integer>();

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	public static void calEntropy(PCGraph graph)
	{

		iniGreenVariables(graph.getNumOfNodes());
		for (int i = 0; i < graph.getNumOfNodes()-1;i++)
			for (int j = i+1; j <graph.getNumOfNodes();j++)
			{   
				if(graph.getEdges()[i][j] != 255) {
				int entropy = 0;
				entropy = Entropy.entropy(graph, i, j);
			    EntropyConstraint cons = new EntropyConstraint(i,j,entropy);
				Entropy.entropy.add(cons);
				EntropyConstraint en = new EntropyConstraint(i,j,entropy);
				priorityQueue.add(en);
				greenvariable.set(i,greenvariable.get(i)+ entropy);
				greenvariable.set(j,greenvariable.get(i)+ entropy);
				if((entropy >= 1 && Integer.bitCount(graph.getEdges()[i][j])<=5)||(entropy >= 7 && Integer.bitCount(graph.getEdges()[i][j])==6)|| Integer.bitCount(graph.getEdges()[i][j])<=3)
				constraints.add(new Constraint(i,j));
				
				System.out.println(i + "  "+ j + "  "+ entropy);
				}
			}
/*	   int count = 0;
	   for (Integer en : greenvariable)
	   {
		   System.out.println((count++) + "  " + en);
	   }
		*/
	}

	private static void iniGreenVariables(int num)
	{
		for (int i = 0; i < num; i ++)
		{
			
			greenvariable.add(0);
		}
	}
	// 1. Find out the variable with highest accumulated entropy
	// 2. Make pairs, if a pair exists, then consider it. 
	public static void analysis(PCGraph graph)
	{
	
		LinkedList<EntropyConstraint> analysisResult = new LinkedList<EntropyConstraint>();
		for (int i = 0; i < greenvariable.size() - 1; i++)
	    {
		    if (greenvariable.get(i) > 5)
		    {
	    	  for (int j = i + 1 ; j < greenvariable.size(); j++)
		      {
		    	
	    		if (greenvariable.get(j) > 5)
		    	{
		    		if (graph.getEdges()[i][j]!= 255)
	    			analysisResult.add(new EntropyConstraint(i,j,((greenvariable.get(i) + greenvariable.get(j))) * (int)(Math.pow(( 8 - Integer.bitCount(graph.getEdges()[i][j]) + 1),1.5))));
		    	}
	    		
		      
		      } 
		    }
			
	    }
		Collections.sort(analysisResult);
		for (EntropyConstraint c : analysisResult)
		{
			System.out.println("-------");
			System.out.println(c);
			
		}
		
		
		
	}
	
    public static void produceQueueWithEntropySorted(PCGraph graph)
    {
       calEntropy(graph);
       Collections.sort(priorityQueue);
      
     
       for (EntropyConstraint ec: priorityQueue)
    	   System.out.println(ec);
       produceQueueWithEntropy(graph);
    	
    	
    }
    public static void calHotConstraints()
    {
    	
    	
    }
	public static void produceQueueWithEntropy(PCGraph graph)
	{
		File file = new File("./JAVAGNI/Entropy_Split_First/"+ graph.getName() + "Queue");
		try {
			FileWriter fo = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fo);
		

			 for (EntropyConstraint ec: priorityQueue)
			  {
				 if (ec.getEntropy() < 4)
					 continue;
				 String result = "green_tuples.push_back(Tuple("+ec.getI()+","+ec.getJ()+"));"; 
				 bw.write(result+"\n");
				
				 
			  }
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	public static void tryMinNetwork(PCGraph graph)
	{
		 calEntropy(graph);
		 PCGraph clone = new PCGraph(graph.getNumOfNodes(),graph.getGraphDegree(),graph.getLabelSize());
		 for (Constraint<Integer> c:constraints)
		 {
			 clone.getEdges()[c.getSource()][c.getDestination()] = graph.getEdges()[c.getSource()][c.getDestination()];
			 clone.getEdges()[c.getDestination()][c.getSource()] = graph.getEdges()[c.getDestination()][c.getSource()];
			 }

		GQR.generateGraphGQR(clone,"./Target/tryMinNetwork"+graph.getName());
		
		
	}
	public static void tryMinNetwork(PCGraph graph, LinkedList<Constraint<Integer>> lb)
	{
		 calEntropy(graph);
		 constraints.addAll(lb);
		 PCGraph clone = new PCGraph(graph.getNumOfNodes(),graph.getGraphDegree(),graph.getLabelSize());
		 for (Constraint<Integer> c:constraints)
		 {
			 clone.getEdges()[c.getSource()][c.getDestination()] = graph.getEdges()[c.getSource()][c.getDestination()];
			 clone.getEdges()[c.getDestination()][c.getSource()] = graph.getEdges()[c.getDestination()][c.getSource()];
			 }

		GQR.generateGraphGQR(clone,"./Target/tryMinNetwork"+graph.getName());
		
		
	}
public static int CommonEdge(PCGraph g, int i, int j)

{
	int count = 0;
	for (int index = 0; index < g.getNumOfNodes(); index ++)
	{
		if (g.getEdges()[i][index]!=255 && g.getEdges()[j][index]!=255)
			count++;
	}
return count;
}
public static int entropy(PCGraph g, int i , int j)
{
	int count = 0;
if (g.getEdges()[i][j]!=255){	
	for (int index = 0; index < g.getNumOfNodes(); index ++)
	{
		if (g.getEdges()[i][index]!=255 && g.getEdges()[j][index]!=255)
		{
			for (int k = index +1; k < g.getNumOfNodes();k++)
			{
				if (g.getEdges()[i][k]!=255 & g.getEdges()[j][k]!=255 & (i!=k) & (j!=k))
				{
					if (g.getEdges()[index][k]!=255)
						count++;
				}
			}
		}
		
	}
} 
else
  return 0;
/*if(i == 0 & j==5)
	System.out.println("In Original ************************"+count);*/
return count;
}
public static HashMap<Integer,LinkedList<Integer>> calEntropyHM(PCGraph graph)
{

	HashMap<Integer,LinkedList<Integer>> entropys = new HashMap<Integer,LinkedList<Integer>>();
	for (int i = 0; i < graph.getNumOfNodes()-1;i++)
		for (int j = i+1; j <graph.getNumOfNodes();j++)
		{   
			int entropy = 0;
			entropy = Entropy.entropy(graph, i, j);
			if (!entropys.containsKey(entropy))
			{
				LinkedList<Integer> list = new LinkedList<Integer>();
				list.add(((i<<8)+j)); // 256 nodes are allowed
				entropys.put(entropy, list);
			}
			else
			{
				entropys.get(entropy).add(((i<<8)+j));
			}
		}
	
	
	
	
	
	return entropys;
	

}
public static int entropyWithRecords(PCGraph g, int i , int j,LinkedList<Constraint<Integer>> cons)
{
	int count = 0;
    if (g.getEdges()[i][j]!=255){	
	for (int index = 0; index < g.getNumOfNodes(); index ++)
	{
		if (g.getEdges()[i][index]!=255 & g.getEdges()[j][index]!=255)
		{
			for (int k = index +1; k < g.getNumOfNodes();k++)
			{
				if (g.getEdges()[i][k]!=255 & g.getEdges()[j][k]!=255)
				{
					if (g.getEdges()[index][k]!=255)
					{	
						cons.add(new Constraint(i,index));
						cons.add(new Constraint(j,index));
						cons.add(new Constraint(index,k));
						count++;
					
					}
				}
			}
		}
		
	}
} 
else
  return 0;
 /* for (biConstraint c : cons)
	  System.out.println(c);*/
return count;
}
public static void allInvolvedEdgesPrint(PCGraph g, int i , int j)
{
	
    if (g.getEdges()[i][j]!=255){	
	for (int index = 0; index < g.getNumOfNodes(); index ++)
	{
		if (g.getEdges()[i][index]!=255 & g.getEdges()[j][index]!=255)
		{
			if (index > i)
             System.out.println(i + "  "+ index);		
			else 
		        System.out.println(index + "  "+ i);		
			if (index > j)
             System.out.println(j + "  "+ index);
			else 
				System.out.println(index + "  "+ j);
         	for (int k = index +1; k < g.getNumOfNodes();k++)
         	{
         		if (g.getEdges()[index][k]!=255){
         		 if (index < k)
         			System.out.println(index + "  "+ k);
         		 else 
         			System.out.println(k + "  " + index);
         		}		
         	}
             
			}
		}
		
	}
} 
public static void allInvolvedEdges(PCGraph g, PCGraph pc, PCGraph clone,int i , int j, boolean sign)
{
  if (sign)
	  allInvolvedEdges(g, pc, clone,i ,j);
  else
  {
	  clone.getEdges()[i][j] = g.getEdges()[i][j];
	  clone.getEdges()[j][i] = g.getEdges()[j][i];
  }
}
public static void allInvolvedEdges(PCGraph g, PCGraph pc, PCGraph clone,int i , int j)
{
	
    if (g.getEdges()[i][j]!=255){	
    	  clone.getEdges()[i][j] = g.getEdges()[i][j];
    	  clone.getEdges()[j][i] = g.getEdges()[j][i];
	for (int index = 0; index < g.getNumOfNodes(); index ++)
	{
		if ((g.getEdges()[i][index]!=255) ||( g.getEdges()[j][index]!=255))
		{
			boolean sign = true;
			if (g.getEdges()[i][index]!=255 & sign){
			    clone.getEdges()[i][index] = pc.getEdges()[i][index];
     	        clone.getEdges()[index][i] = pc.getEdges()[index][i];
     	   
     	     }
			if (g.getEdges()[j][index]!=255 & sign){
      	     
				clone.getEdges()[j][index] = pc.getEdges()[j][index];
                clone.getEdges()[index][j] = pc.getEdges()[index][j];
			}
			for (int k = index +1; k < g.getNumOfNodes();k++)
         	{
         		if (g.getEdges()[index][k]!=255){
         			  clone.getEdges()[index][k] = pc.getEdges()[index][k];
         	    	  clone.getEdges()[k][index] = pc.getEdges()[k][index];
         	   
         		}		
         	}
             
			}
		}
		
	}
} 


public static int entropyWithRecordsInt(PCGraph g, int i , int j,LinkedList<Integer> cons)
{
	int count = 0;
	//System.out.println(i+"    "+j);
	
    if (g.getEdges()[i][j]!=255){	
	for (int index = 0; index < g.getNumOfNodes(); index ++)
	{
		if (g.getEdges()[i][index]!=255 & g.getEdges()[j][index]!=255)
		{
			for (int k = index +1; k < g.getNumOfNodes();k++)
			{
				if (g.getEdges()[i][k]!=255 & g.getEdges()[j][k]!=255)
				{
					if (g.getEdges()[index][k]!=255)
					{	
		/*				System.out.println(Integer.toBinaryString(j));
						System.out.println(Integer.toBinaryString(j<<8));
						System.out.println((j<<8)+index);*/
						
						cons.add((i<<8)+j);
						cons.add((j<<8)+i);
						
						cons.add((i<<8)+index);
						cons.add((index<<8)+i);
						
						cons.add((j<<8)+index);
						cons.add((index<<8)+j);
						
						cons.add((i<<8)+k);
						cons.add((k<<8)+i);
						
						cons.add((j<<8)+k);
						cons.add((k<<8)+j);
						
						cons.add((index<<8)+k);
						cons.add((k<<8)+index);
						
						count++;
					
					}
				}
			}
		}
		
	}
} 
else
  return 0;

return count;



}

public static void main(String args[]) throws IOException
{
/*	Graph graph = new Graph(4,3,4);
	graph.getNodes()[0][1] = 132;
	graph.getNodes()[1][0] = 132;
	graph.getNodes()[0][2] = 132;
	graph.getNodes()[2][0] = 132;
	graph.getNodes()[0][3] = 132;
	graph.getNodes()[3][0] = 132;
	graph.getNodes()[1][2] = 132;
	graph.getNodes()[2][1] = 132;
	graph.getNodes()[1][3] = 132;
	graph.getNodes()[3][1] = 132;
	graph.getNodes()[2][3] = 132;
	graph.getNodes()[3][2] = 132;
	Entropy.calEntropy(graph);
	*/
	PCGraph graph = GQRGraphReader.readGraphFromGQR("H_2ndcluster14_OG.csp");
	Entropy.calEntropy(graph);

}
}

