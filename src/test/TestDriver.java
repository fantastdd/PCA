package test;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

import deprecated.ReRelation;

import essential.GQR;
import essential.CompositionTable;
import essential.FastSimulator;
import essential.PCGraph;
import essential.Key;
import essential.SetOperators;


public class TestDriver {
public static double calcualateProbability()
{ 
	int count;
	int[] array = new int[8];
	int[] array1 = new int[8];
    double result = 0;
    double result1 = 0;
    double countTotalEntires;
   
for (int i =1;i<=8;i++){
	count = 0;
	for(Key<ReRelation> key:CompositionTable.getTotaltable().keySet())
	{
		if (CompositionTable.getTotaltable().get(key).size()==i)
			count ++;
	}

	array1[i-1] = count;
    array[i-1] = (int) (count * ((Math.pow(2, (8-i)))-1));
}
	for(int i = 0;i<(array.length-1);i++)
		result +=array[i];
	JOptionPane.showMessageDialog(null,result);
	result = result / Math.pow((Math.pow(2, 8)-1),3);
	for(int i = 0;i<array1.length;i++)
	{	result1 +=array1[i];
	   System.out.println(result1 +=array1[i]);
	}
	
	
//	JOptionPane.showMessageDialog(null,CompositionTable.getTotaltable().size()+"   "+result1);
return result;
	}
public static void main(String args[]) throws Exception
{
	FastSimulator simulator = new FastSimulator();
/*	double count = 0;
//==for (int j = 1;j<=8;j++){
	for(long i =0;i<100000000;i++)
	{
		if(simulator.Path_Consitency(3, 2, (int)(Math.random()*8+1)))
			count ++;
	//	System.out.println(count);
	}
	
//}
	System.out.println(count);
	System.out.println("Emp:  "+ (1-(count/10000000)));
	System.out.println(TestDriver.calcualateProbability());*/
//	System.out.println(TestDriver.calcualateProbability());
	PCGraph graph = new PCGraph(4,3,2);

	graph.getEdges()[0][1] = 72;
	graph.getEdges()[1][0] = SetOperators.inverse(72);
	
	graph.getEdges()[0][2]=9;
	graph.getEdges()[2][0]= SetOperators.inverse(9);
	
	
	graph.getEdges()[0][3] = 10;
	graph.getEdges()[3][0] = SetOperators.inverse(10);
	
	

	graph.getEdges()[1][2] = 10;
	graph.getEdges()[2][1]= SetOperators.inverse(10);
	
	
	graph.getEdges()[1][3] = 34;
	graph.getEdges()[3][1] = SetOperators.inverse(34);
	
	graph.getEdges()[2][3]= 48;
	graph.getEdges()[3][2]= SetOperators.inverse(48);
    
	
	graph.createAllConstraintsNotHorn();
    graph.createPathQueue();
	
	PCGraph clone = new PCGraph();
	
	clone = graph.clone();
/*	clone.setGraphDegree(graph.getGraphDegree());
	clone.setLabelSize(graph.getLabelSize());
	clone.setNumOfEdges(graph.getNumOfEdges());
	clone.setNumOfRegions(graph.getNumOfRegions());
//	System.out.println(graph.getNodes());
	clone.setNodes(graph.getNodes());
//	System.out.println(graph.getPathQueue());
	clone.setPathQueue(graph.getPathQueue());
*/
	clone.printGraph();
	GQR.generateGraphGQR(clone,"goal");
	
	System.out.println("   PCA   "+simulator.Path_Consistency(graph)+"CA  "+
	simulator.Consistency(clone));
    
	//+"  CA "+simulator.Consistency(clone)
	simulator.printReport();
	
	
//	System.out.println(TestDriver.calcualateProbability());
	
/*		double[] array = new double[8];
	for(long i =0;i<100000;i++)
	{
		int index = (int)(Math.random()*8+1);
	array[index-1]= array[index-1]+1;
	}
	for (int i = 0;i<array.length;i++)
	System.out.println((double)(array[i]/100000));
*/
	}


}
