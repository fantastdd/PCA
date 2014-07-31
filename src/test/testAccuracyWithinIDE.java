package test;

import essential.GQR;
import essential.FastSimulator;
import essential.PCGraph;

public class testAccuracyWithinIDE {
public static void main(String args[]) throws Exception
{
	FastSimulator fs = new FastSimulator();
	double count = 0;
  for (int n = 4;n <10;n++)
  {	
	for (int d = 1;d<n;d++)
	{	
	for (int l = 1;l<8;l++ )
	{
		count = 0;
		for (int i = 0;i< 10000;i++)
		{	
		     PCGraph graph = new PCGraph(n,d,l,false,"ALL");
		 
			   if(!fs.Consistency(graph)== fs.Path_Consistency(graph))
			{
				/*   if(n == 5&d==4&l==3)
					   //	BuildGraphForGQR.generateGraphGQR(graph,"Accuracy_5_4_2");
					   graph.saveGraph("Graph_5_4_3_ALL_"+count);*/
				   
				   
				   count++;
			}
		   
		}
		
	//	System.out.println("Node: "+n+"   Degree: "+ d +"  Label: "+ l +"   "+(1-count/10000));
		System.out.println(" "+n+" "+ d +" "+ l +" "+(1-count/10000));
	}
	}
	
	//fs.printReport();
}
}
}



