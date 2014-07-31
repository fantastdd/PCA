package essential;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import tool.Timer;


public class EdgesGraph implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2385706987589929950L;
	private  int nodes[][];
	private int numOfRegions;
	private int graphDegree;
	private int numOfEdges;
	private int labelSize;
	private LinkedList<Constraint> allConstraints;
    private PathQueue pathQueue = new PathQueue();
	
	public LinkedList<Constraint> getAllConstraints() {
		return allConstraints;
	}
	public void setAllConstraints(LinkedList<Constraint> allConstraints) {
		// Constraints should not be independent (Two Objects) from nodes
		this.allConstraints = new LinkedList<Constraint>();
		for (Constraint c:allConstraints)
		{
			
			Constraint cc = new Constraint(c.getSource(),c.getDestination());
		    this.allConstraints.add(cc);
		}
		
	}
	
	
	// counter of the number of graphs has been written into the GQR graph file
	private int counter = 1;
	/**
	 * Create a Empty Graph, and then accept nodes
	 * need manually call create path queue and create all constraints, since they
	 * depend on the nodes input
	 * @param numOfRegions
	 * @param graphDegree
	 * @param labelSize
	 */
	public EdgesGraph(int numOfRegions,int graphDegree, int labelSize)
	{
		this.numOfRegions = numOfRegions;
		this.graphDegree = graphDegree > numOfRegions?(numOfRegions-1):graphDegree;
	    this.numOfEdges = (numOfRegions * this.graphDegree) / 2;
	    this.labelSize = labelSize;
	    createNodes();
		
		
		
	}
	public EdgesGraph(int numOfRegions, int graphDegree, int labelSize,boolean save,String labelType)
	{
		// init the graph
		this.numOfRegions = numOfRegions;
		this.graphDegree = graphDegree > numOfRegions?(numOfRegions-1):graphDegree;
	    this.numOfEdges = (numOfRegions * this.graphDegree) / 2;
	    this.labelSize = labelSize;
	
	    createNodes();
	    createEedges(labelType);
	    // Init path set Q // 0.5 seconds required
	    createPathQueue();
	    // All constraints need to be evaluated during Back tracking
	    createAllConstraints();
	    if(save)
	    	this.saveGraph();
		
		
	}
	
	public EdgesGraph(int numOfRegions, int graphDegree, int labelSize,boolean save){
		// init the graph
				this.numOfRegions = numOfRegions;
				this.graphDegree = graphDegree > numOfRegions?(numOfRegions-1):graphDegree;
			    this.numOfEdges = (numOfRegions * this.graphDegree) / 2;
			    this.labelSize = labelSize;
			 //   Timer.start();
			    createNodes();
			 //   Timer.end("Nodes");
			//    Timer.start();
			    createEedges("");
			//    Timer.end("Edges");
			    // Init path set Q
			 //   Timer.start();
			    createPathQueue();
			//    Timer.end("Path");
			    // All constraints need to be evaluated during Back tracking
			//    Timer.start();
			    createAllConstraints();
			//    Timer.end("All Constraints");
			    if(save)
			    	this.saveGraph();

	}
	private void createNodes()
	{ 
	nodes = new int[this.numOfRegions][this.numOfRegions];
    for (int i=0;i<this.numOfRegions;i++)
    {
    	for(int j = 0; j<this.numOfRegions;j++)
    	{
    		
             nodes[i][j] = 255;
    	}
    	}
		
	}
	public void createAllConstraints()
	{
	    this.allConstraints = new LinkedList<Constraint>();
		  for (int i = 0;i<this.getNumOfRegions();i++){
				for (int j = i+1;j<this.getNumOfRegions();j++)
				{
					 
					
				    if(!MTS.getBIMTS("H8").contains(this.nodes[i][j]))
					{	
						
						allConstraints.add(new Constraint(i,j)); 
					}
				}
				
				
			}
	}	
		
		
		
		
		
		

	private void createEedges(String type)
	{
		 int EdgesCount = 0;
		 int labels = 0;
		    while (EdgesCount<this.numOfEdges)
		    {
		    	int index = (int)(Math.random()* (this.numOfRegions*this.numOfRegions))+1;
		    	int theRowIndex = (int) Math.ceil((double)index / this.numOfRegions) - 1;
		    	int theColumnIndex = ((index-1) % this.numOfRegions);
		    //	System.out.println(theRowIndex);
		    //	System.out.println(theColumnIndex);
		    	if(nodes[theRowIndex][theColumnIndex]==255&& theRowIndex!=theColumnIndex)// CURRENTLY NO EDAGE AT THIS LOCATION,universal. No Self Connected
		    	{
		    	
		    		if(type.equals("NP8")){
		    		   labels =  init_generateLableNP8(labelSize);
		    //		LinkedHashSet<Relation> labels =  init_generateLableNP8(labelSize);
		    		}
		    		else
		    		{
		    			 labels =  init_generateLable(labelSize);
		    		}
		    //		LinkedHashSet<Relation> labels = init_generateLable((int)(Math.random()*8+1));
		    		nodes[theRowIndex][theColumnIndex] = labels;
		    		
		    		// Inverse -------------------------------------------
		    		nodes[theColumnIndex][theRowIndex] = SetOperators.inverse(labels);
		    		EdgesCount++;
		    	}
		    }
		   
	}
	public void createPathQueue()
	{
		
			for (int i = 0; i<this.numOfRegions-1;i++)
		         for (int j = i+1; j < this.numOfRegions; j++)
		         {
		             for (int k = 0; k < this.numOfRegions;k++)
		        	 {	 
		        	     if (k!=i & k!=j &!( this.nodes[i][j] == 255&this.nodes[j][k] == 255))
		        		 {
		        	    	
		        	    		pathQueue.add((i<<20)+(j<<10)+(k));
		        	    	
		        	    		pathQueue.add((k<<20)+(i<<10)+(j));
		        		  }
		        	 }
		         }
	}
	public void refreshPathQueue()
	{
		createPathQueue();
	}

	public void saveGraph()
	{
		 FileOutputStream fos;
		try {
			fos = new FileOutputStream("Graph.obj");
			 ObjectOutputStream     out = new ObjectOutputStream(fos);
		     out.writeObject(this);
		     out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static EdgesGraph  loadGraph()
	{
		 FileInputStream fos;

			try 
			{
				fos = new FileInputStream("Graph.obj");
		         ObjectInputStream     out = new ObjectInputStream(fos);
			     return (EdgesGraph) out.readObject();
			}	
			
	        catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return null;
			
	}

	public String printGraph()
	{
	
        // write the headline per graph
		String header = (this.getNumOfRegions()-1)+" #"+(1)
				+"-N"+this.getNumOfRegions()+"-D"+this.getGraphDegree()+
				"-L"+this.getLabelSize()+"\n";
      
		 String line = new String("");
		 for (int i = 0;i<this.getNumOfRegions();i++)
		 {
			 for (int j =0;j<this.getNumOfRegions();j++)
			 {
				 int rels =this.getNodes()[i][j];
			/*	 System.out.println(rels);
				 System.out.println(rels.size());*/
				 if(Integer.bitCount(rels)!=8)
				 {
		            line += i +"  " + j + " "+Relation.translateToString(rels);
		            line = line.replace("[", "(");
		            line = line.replace("]", ")");
		            line = line.replace(",", " ");
		            line = line.replace("i", "I");
		            line+="\n";        
				 }
			 }
		 }
		 System.out.println(header+line+".");
		 return header + line + ".";
		           
	}
	public int[][] getNodes() {
		return nodes;
	}
	public void setNodes(int[][] nodes) {
		if(this.nodes==null)
			 this.nodes = new int[this.numOfRegions][this.numOfRegions];
		for (int i =0;i<this.numOfRegions;i++)
			for (int j =0;j<this.numOfRegions;j++)
			{  
			
				this.nodes[i][j]= nodes[i][j];
					
			}
	}
	public int getNumOfRegions() {
		return numOfRegions;
	}
	public void setNumOfRegions(int numOfRegions) {
		this.numOfRegions = numOfRegions;
	}
	public int getGraphDegree() {
		return graphDegree;
	}
	public void setGraphDegree(int graphDegree) {
		this.graphDegree = graphDegree;
	}
	public int getNumOfEdges() {
		return numOfEdges;
	}
	public void setNumOfEdges(int numOfEdges) {
		this.numOfEdges = numOfEdges;
	}
	public int getLabelSize() {
		return labelSize;
	}
	public void setLabelSize(int labelSize) {
		this.labelSize = labelSize;
	}
	public PathQueue getPathQueue() {
		return pathQueue;
	}
	public void setPathQueue(PathQueue pathQueue) 
	{
		this.createPathQueue();
	}
	private int init_generateLable(int labelSize)
	{
		int rels = 0;
		// Choose one relation with uniform distribution
		
		  int r1 = 1<<((int)(Math.random()*8));
		  rels+=r1;
		// remaining selected with probability (l-1)/(Relation.size-1)
		  for (int i = 0;i<8;i++)
			 {
			 
			  if ((1<<i)!= r1)
			 	{
					  int dice = (int)(Math.random()*7)+1;
			 	
			 		if (dice <= (labelSize-1))
			 		{
			 			rels += 1<<i;
			 		}
			 	}
			 	else continue;
			
			}
		
		return rels;
	}

	
	
	//���ˣ���Ҫ��??
	private int init_generateLableNP8(int labelSize)
	{
		int rels = 0;
		do{
			rels = 0;
		  // Choose one relation with uniform distribution
		
		  int r1 = 1<<((int)(Math.random()*8));
		  rels+=r1;
		
		  // remaining selected with probability (l-1)/Relation.size-1
		  for (int i = 0;i<8;i++)
		 {
		 
		  if ((1<<i)!= r1)
		 	{
				  int dice = (int)(Math.random()*7)+1;
		 	
		 		if (dice <= (labelSize-1))
		 		{
		 			rels += 1<<i;
		 		}
		 	}
		 	else continue;
		
		}
		}while (MTS.BIMTS.contains(rels));
	
		return rels;
	}
	public EdgesGraph(){}
	public EdgesGraph clone()
	{
		// long time = System.currentTimeMillis();
		EdgesGraph graph = new EdgesGraph();
		graph.setGraphDegree(this.getGraphDegree());
		graph.setLabelSize(this.getLabelSize());
		graph.setNumOfEdges(this.getNumOfEdges());
		graph.setNumOfRegions(this.getNumOfRegions());
		graph.setNodes(this.getNodes());
	    graph.setPathQueue(this.getPathQueue());
		graph.setAllConstraints(this.allConstraints);
	//	 System.out.println(System.currentTimeMillis() - time);
	    return graph;
	}
	public static void main(String args[])
	{
		long time1 = System.currentTimeMillis();
		EdgesGraph graph = new EdgesGraph(100, 50, 4,false);
		
		 System.out.println(System.currentTimeMillis() - time1);
	
		System.out.println("PCA----------------------");
	   // graph.printGraph();
	    long time = System.currentTimeMillis();
	    System.out.println("CA----------------------");
	  //  clone.printGraph();
	    EdgesGraph clone = graph.clone();
	    System.out.println(System.currentTimeMillis() - time);
	  /* // graph.getNodes()[0][1].getRelations().clear();
	    System.out.println("Again CA----------------------");
	    clone.printGraph();*/
	}
}
