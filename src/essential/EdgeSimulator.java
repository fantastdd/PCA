package essential;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import tool.Timer;


public class EdgeSimulator<AnyType> {
// matrix -- > graph. 
	//private Graph cacheGraph;
	private PCGraph graph;

	public int revisecalls = 0;
	private int numberOfVistitedNodes;
	private int numberOfBackTrackingOccurace;
	private LinkedList<Constraint<Integer>> result = new LinkedList<Constraint<Integer>>();
    private Setting setting = new Setting("H8","ALL");
	public EdgeSimulator(Setting setting)
	
	{
		this.setting = setting;
	}
	public EdgeSimulator()
	
	{
		
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	public boolean Consistency(int numOfRegions, int graphDegree, int labelSize) throws Exception{
      
		return Consistency(this.buildGraph(numOfRegions, graphDegree, labelSize,this.setting.getLableModel()));
	}
	private boolean CheckConsistency(PCGraph graph) throws Exception
	{
		// ---- Forward Checking --------------
		PCGraph ThisStateGraph = graph.clone();
		if (!runPAC(ThisStateGraph))
			return false;
	//	System.out.println("In Consistency()   ");
	//	this.graph.printGraph();
		// ------ Pop up a constraint----------
		LinkedList<Constraint> allConstraints = ThisStateGraph.getAllConstraints();
		if (allConstraints.isEmpty()) // CALCULATE ALL CONSTRAINT EACH TIME . NEED REFINE
			return true;
	
		Constraint<Integer> currentConstraint = allConstraints.pop();

	    LinkedList<Integer>	splitConstraints = SetOperators.splitToMinmumSubsets(ThisStateGraph.getEdges()[currentConstraint.getSource()][currentConstraint.getDestination() ],this.setting.getSplitSet());
		                                                            
		for (Integer rel:splitConstraints)
		{
			ThisStateGraph.getEdges()[ currentConstraint.getSource()][ currentConstraint.getDestination()] = rel;
				
			// set the inverse as well
			ThisStateGraph.getEdges()[ currentConstraint.getSource()][ currentConstraint.getDestination()] = SetOperators.inverse(rel);
					                                             
			
			// result perform likes a stack
	       // result.addFirst(graph.getNodes()[cc.getSource()][cc.getDestination()]);
	//		System.out.println("In Consistency: Expand Node:"+	ThisStateGraph.getNodes()[cc.getSource()][cc.getDestination()]);
			this.numberOfVistitedNodes ++;
		
			if (CheckConsistency(ThisStateGraph))
			{	
			
			//	this.graph.printGraph();
				return true;
			}
			else
			{	
			  //  result.pop();	
				this.numberOfBackTrackingOccurace++; 
			}
		}
		
	
		return false;
	}
	// Run Path Consistency with Input Graph
	public boolean Path_Consistency(PCGraph graph) throws Exception 
	{
		  Timer.start();
	  	  this.graph = graph.clone(); 
	  	  Timer.end("In Path Consistency");
	//  	  this.graph.printGraph();
	//	  this.cacheGraph = graph.clone();
	//	  this.result =  new LinkedList<Constraint<Integer>>();
	//	  System.out.println("Graph PCA ----------------------");
	//	  graph.printGraph();
		// Init path set Q
	    
	    // init completed
		if (runPAC(this.graph))
			{
			
	//	    System.out.println("The given graph is Path Consistency");
			return true;
			}
		else
			{
	//		System.out.println("Inconsistency Detected in PCA");
			return false;
			}
	}
	// Run Consistency with Input Graph-----------------------
	public boolean Consistency(PCGraph graph) throws Exception{
		
	//      this.cacheGraph = graph.clone(); // Alway The Original Graph
		 
	//	  System.out.println("Graph CA ----------------------");
	  PCGraph iniGraph = graph.clone();
	
	//	  result = new LinkedList<Constraint<Integer>>();
		 
		  numberOfVistitedNodes = 0;
		  numberOfBackTrackingOccurace=0;
		// --------------Initiate a Queue to Store all constraints----------------

		  // ------ do not store the inverse relation, so , j starts with i
	//	this.graph = cacheGraph.clone();
		if (CheckConsistency(iniGraph))
		{
			// BuildGraphForGQR.generateGraphGQR(this.graph, "Last_State_HT60");
		//		System.out.println("Last_State_HT60");
	//		System.out.println("Attentaion------------");
		//	this.graph.printGraph();
		//	this.graph.saveGraph();
		//	System.out.println("The given graph is Consistency");
			return true;
		}
		else
		{	
			// back tracking , restore the network to previous state
		//	
			//	System.out.println("Inconsistency Detected in CA");
		    return false;
		    
		}
	}
	public boolean Path_Consitency(int numOfRegions, int graphDegree, int labelSize) throws Exception{
	
		return Path_Consistency(this.buildGraph(numOfRegions, graphDegree, labelSize,this.setting.getLableModel()));
	}
	public PCGraph buildGraph(int numOfRegions, int graphDegree, int labelSize)
	{
	
	 	this.graph = new PCGraph(numOfRegions, graphDegree, labelSize,false);
	 	//this.cacheGraph = graph.clone();
	//	  System.out.println("Graph Initialized IN Simulator.BuildGraph----------------------");
	//	  graph.printGraph();
		return  graph;
	}
	public PCGraph buildGraph(int numOfRegions, int graphDegree, int labelSize,String labelType)
	{
	
	 	this.graph = new PCGraph(numOfRegions, graphDegree, labelSize,false,labelType);
	// 	this.cacheGraph = graph.clone();
	//	System.out.println("Graph Initialized In Simulator.BuildGraph----------------------");
	//	  graph.printGraph();
		return  graph;
	}
	private boolean runPAC(PCGraph graph) throws Exception
	{
 
		while (! graph.getPathQueue().isEmpty())
		{
		
			int path =  graph.getPathQueue().pop();
			
			if (revise(path,graph))
			{
				revisecalls++;
				// 0,2
				int rel = graph.getEdges()[(path>>20) & ((1<<31)|(1023))][path & ((1<<31)|(1023))];
				if (rel == 0)
					return false;
				else 
				{
				/*	graph.getPathQueue().add(new Path(path.getPath()[0],path.getPath()[2],path.getPath()[1]));
					graph.getPathQueue().add(new Path(path.getPath()[1],path.getPath()[0],path.getPath()[2]));}*/
					
					for(int j = 0;j<graph.getNumOfNodes()-1;j++)	
					{	
					
					int i0 = (path>>20) & ((1<<31)|(1023));
					int i2 =  path & ((1<<31)|(1023));
					if(j!=(i0)&j!=(i2))
					{
				
					    graph.getPathQueue().add(((i0<<20)+(i2<<10)+j));
				        graph.getPathQueue().add(((j<<20)+(i0<<10)+i2));
					    
					}
					
				}
			}
			}
		}
		return true;
	} 
	
	private boolean revise(int path,PCGraph graph)
	{
	
	 //  System.out.println("in revise:     ");
	//	graph.printGraph();
		// oldRel = path[0][2] with 0 is at the leftmost,1 in middle
		int oldRel =  graph.getEdges()[(path>>20) & ((1<<31)|(1023))][path & ((1<<31)|(1023))];
		// relatedRel_ik 0,1
		//System.out.println((path>>10) & ((1<<31)|(1023)));
		int relatedRel_ik = graph.getEdges()[(path>>20) & ((1<<31)|(1023))][(path>>10) & ((1<<31)|(1023))];
		// relatedRel)kj 1,2
		int relatedRel_kj = graph.getEdges()[(path>>10) & ((1<<31)|(1023))][path & ((1<<31)|(1023))];
		int newRel = SetOperators.intersection(oldRel,CompositionTable.LookUpTable(relatedRel_ik,relatedRel_kj));

	//	graph.getNodes()[path.getPath()[0]][path.getPath()[2]]= newConstraint;

	if (oldRel == newRel)
		{   
	 /*     System.out.println("----------"+oldConstraint);
	      System.out.println("----------"+newConstraint);*/		
		//	graph.getNodes()[path.getPath()[0]][path.getPath()[2]].setRelations(newConstraint.getRelations());
			return false; }
		else{
			/*System.out.println("Compose2:  "+BinaryRelations.translateToString(CompositionTable.LookUpTable(2,72)));
			//System.out.println("related Cons2:  "+ relatedRel_ik);
			//System.out.println("related Cons2:  "+ relatedRel_kj);
			System.out.println("Compose:  "+BinaryRelations.translateToString(CompositionTable.LookUpTable(relatedRel_ik,relatedRel_kj)));
			System.out.println("revised oldCons:  "+BinaryRelations.translateToString(oldRel));
			System.out.println("revised newCons:  "+BinaryRelations.translateToString(newRel));
			System.out.println("related Cons:  "+ BinaryRelations.translateToString(relatedRel_ik));
			System.out.println("related Cons:  "+ BinaryRelations.translateToString(relatedRel_kj));*/
			// 0,2
			graph.getEdges()[(path>>20) & ((1<<31)|(1023))][path & ((1<<31)|(1023))]= newRel;
		    // 2,0
			graph.getEdges()[path & ((1<<31)|(1023))][(path>>20) & ((1<<31)|(1023))]= SetOperators.inverse(newRel);
			return true;
			}
	}
	public void printReport()
	{
		System.out.println("Number of Visisted Nodes:   "+numberOfVistitedNodes);
		System.out.println("Number of Backtracing Occurance:   "+numberOfBackTrackingOccurace);
	}
	


	public static void main(String args[]) throws Exception
	{
	//	System.out.println((int) Math.ceil((double)1 / 4));
		
		EdgeSimulator simulator = new EdgeSimulator();
/*		System.out.println("n,d,l");
		
		if (Integer.parseInt(args[0])== 0)
			simulator.Path_Consitency(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		if (Integer.parseInt(args[0])== 1)
	    simulator.Consistency(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		if (Integer.parseInt(args[0])== 0)
		System.out.println("PCA Completed");
		if (Integer.parseInt(args[0])== 1)
			System.out.println("CA Completed");
	    */
		
		
		//  simulator.Consistency(4,2,1);
	/*	for (int i = 0;i<1;i++)
		simulator.Path_Consitency(4, 2, 1);
		for (int i = 0;i<1;i++)
			simulator.Consistency(4, 2, 1);*/
	//	   simulator.run(4, 1, 1);
	      //  simulator.printGraph();
		
		
		
		boolean PCA = false;
		boolean CA = false;
	    int repet = 0;
	    
	    int counter = 0;
 /* do	{

	    Graph graph = simulator.buildGraph(9, 4, 3);
	    BuildGraphForGQR.generateGraphGQR(graph,"Accu");
		Graph clone = graph.clone();
		clone.setGraphDegree(graph.getGraphDegree());
		clone.setLabelSize(graph.getLabelSize());
		clone.setNumOfEdges(graph.getNumOfEdges());
		clone.setNumOfRegions(graph.getNumOfRegions());
	//	System.out.println(graph.getNodes());
		clone.setNodes(graph.getNodes());
	//	System.out.println(graph.getPathQueue());
		clone.setPathQueue(graph.getPathQueue());

		PCA = simulator.Path_Consistency(graph);
	    Simulator simulator1 = new Simulator();
		CA = simulator1.Consistency(clone);
		if (PCA == CA)
			counter ++;
		
		repet++;
	  }  while(PCA == CA);*/
	//   System.out.println("Accuracy:  "+ (double)counter/repet);
	//	Graph graph1 = new Graph(5,4,2,false,"NP8");
	//	graph1.printGraph();
		  for (int i = 0;i<100000;i++)
		  {
			  PCGraph graph = simulator.buildGraph(4, 3, 2);
			    GQR.generateGraphGQR(graph,"Accu");
				PCGraph clone = graph.clone();
				PCA = simulator.Path_Consistency(graph);
			    EdgeSimulator simulator1 = new EdgeSimulator();
			    
				CA = simulator1.Consistency(clone);
				if (PCA == CA)
					counter ++;
				
				repet++;
		  }
		   System.out.println("Accuracy:  "+ (double)counter/repet);
	
	}

    
	
}
