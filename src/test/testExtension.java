package test;

import essential.PCGraph;
import extend.ExtendSimulator;

public class testExtension {
public static void main(String args[]) throws Exception
{
   ExtendSimulator simulator = new ExtendSimulator();
  
   for(int i = 0;i<29;i++)
   System.out.println(simulator.Path_Consistency(PCGraph.loadGraph("Graph_5_4_3_"+i+".0.obj")));




}
}
