package test;

import essential.FastSimulator;
import essential.PCGraph;


public class testAccuracy {
public static void main(String args[]) throws Exception
{
	FastSimulator fs = new FastSimulator();
	double count = 0;
  for (int n = Integer.parseInt(args[0]);n < Integer.parseInt(args[1]);n++)
  {	
	for (int d = 1;d<n;d++)
	{	
	for (int l = 1;l<8;l++ )
	{
		count = 0;
		for (int i = 0;i< Integer.parseInt(args[2]);i++)
		{	
		     PCGraph graph = new PCGraph(n,d,l,false,"NP8");
		   if(fs.Consistency(graph)==fs.Path_Consistency(graph))
			     count++;
		}
		
		System.out.println("Node: "+n+"   Degree: "+ d +"  Label: "+ l +"   "+count/Integer.parseInt(args[2]));
	}
	}
	
	//fs.printReport();
}
}
}



