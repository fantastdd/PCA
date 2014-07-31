package test;

import essential.GQR;
import essential.FastSimulator;
import essential.PCGraph;

public class testSpeed {
public static void main(String args[]) throws Exception
{
	
 FastSimulator fs = new FastSimulator();
 long time = System.currentTimeMillis();
 PCGraph graph = new PCGraph(4,1,1,false,"NP8");
 System.out.println(System.currentTimeMillis() - time);
 long time1 = System.currentTimeMillis();
 System.out.println(fs.Consistency(graph));
 System.out.println(System.currentTimeMillis() - time1);
 fs.printReport();
 //BuildGraphForGQR.generateGraphGQR(graph,"PAC_100_50_4");









}
}
