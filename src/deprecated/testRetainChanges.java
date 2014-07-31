package deprecated;

import analysis.RetainChanges;
import deprecated.Fun;
import essential.FastSimulator;
import essential.GQR;
import essential.PCGraph;

public class testRetainChanges {
public static void main(String args[]) throws Exception
{
	  FastSimulator fs = new FastSimulator();
	  PCGraph graph0 = PCGraph.loadGraph("./Target/H_60_14_4_1"); 
	  graph0.printGraph();
	  PCGraph graph1 = PCGraph.loadGraph("./Target/H_60_14_4_0_PC");
	  PCGraph graph2 = PCGraph.loadGraph("./Target/PC_601440RU_#128");
	  PCGraph rt = RetainChanges.retainChanges(graph0, graph1);
	  rt.printGraph();
	  PCGraph rt128 = RetainChanges.retainChanges(graph0, graph2);
//	  fs.Path_Consistency(rt);
//	  Graph rtt = fs.graph;
//	  rtt.printGraph();
//	  BuildGraphForGQR.generateGraphGQR(rt,"og_rt",128);
//	  BuildGraphForGQR.generateGraphGQR(graph0,"og",128);
	//  Fun fun = new Fun(rt128,"./Target/PC_601440RU_#128.csp");
	  Fun fun = new Fun(graph0,"og");
	  Fun fun1 = new Fun(rt,"og_rt");
	  GQR.generateGraphGQR(rt,"og_rt",128);
	  GQR.generateGraphGQR(graph0,"og",128);

}
}
