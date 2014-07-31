package test;

import essential.FastSimulator;
import essential.PCGraph;



public class testPCA {
public static void main(String args[]) throws Exception
{
PCGraph graph = PCGraph.loadGraph("Graph_5_4_2_100.obj");
FastSimulator simulator = new FastSimulator();
graph.printGraph();
System.out.println(simulator.Path_Consistency(graph));




}
}
