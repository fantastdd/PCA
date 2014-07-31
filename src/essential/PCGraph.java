package essential;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;


public class PCGraph implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2385706987589929950L;
	public  int edges[][];
	private String name = "";
	
	/**
	 * Generate a CSV file of the adjacency matrix (then will be used in Gephi)
	 * @throws IOException 
	 * **/
	public void exportCSV() throws IOException
	{
		if(name == "")
			name = "graph_exported_" + (new Date(System.currentTimeMillis()));
		name = name.replace(" ", "");
		name = name.replace(":", "_");
		File file = new File(name + ".csv");
		BufferedWriter b = new BufferedWriter(new FileWriter(file)); 
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < edges.length; i++)
		{
			str.append(";" + i);
		}
		str.append("\n");
		b.write(str.toString());
		int count = 0;
		int _count = 0;
		for (int[] row : edges)
		{	
			str = new StringBuilder();
			str.append(count++);
			for(int j = 0; j < row.length; j++)
			{
			   if(row[j] != 255 && row[j] != 0)
				{
				   //_count ++;
				   str.append(";" + 1);
				}
			   else
				   str.append(";" + 0);
			}
			str.append("\n");
			b.write(str.toString());
		}
		//System.out.println(_count);
		b.close();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public int numOfNodes = -1;
	public int degree = -1;
	public int numOfEdges = -1;
	public int labelSize = -1;
	private LinkedList<Constraint> allConstraints;
    private PathQueue pathQueue = new PathQueue();
	
	public LinkedList<Constraint> getAllConstraints() {
		return allConstraints;
	}
	public void setAllConstraints(LinkedList<Constraint> allConstraints) {
		// Constraints should not be independent (Two Objects) from nodes
		this.allConstraints = new LinkedList<Constraint>();
	//	System.out.println(allConstraints);
		for (Constraint c:allConstraints)
		{
			
			Constraint cc = new Constraint(c.getSource(),c.getDestination());
		    allConstraints.add(cc);
		}
		
	}
	
	/**
	 * Create a Empty Graph, and then accept nodes
	 * need manually call create path queue and create all constraints, since they
	 * depend on the nodes input
	 * @param numOfNodes
	 * @param graphDegree
	 * @param labelSize
	 */
	public PCGraph(int numOfNodes,int graphDegree, int labelSize)
	{
		this.numOfNodes = numOfNodes;
		this.degree = graphDegree > numOfNodes?(numOfNodes-1):graphDegree;
	    this.numOfEdges = (numOfNodes * this.degree) / 2;
	    this.labelSize = labelSize;
	    initialize();
	}
	/**
	 * Create a graph based on adjacency matrix
	 * **/
	public PCGraph(int[][] matrix){
		
		numOfNodes = matrix.length;
		edges = matrix;
		numOfEdges = getNumOfEdges();
	}
	public PCGraph(int numOfNodes)
	{
		this.numOfNodes = numOfNodes;
		initialize();
		
	}
	public PCGraph(int numOfRegions,int graphDegree, int labelSize,String name)
	{
		this.numOfNodes = numOfRegions;
		this.degree = graphDegree > numOfRegions?(numOfRegions-1):graphDegree;
	    this.numOfEdges = (numOfRegions * degree) / 2;
	    this.labelSize = labelSize;
	    this.name = name;
	    initialize();
		
		
		
	}
	public PCGraph(int numOfRegions, int graphDegree, int labelSize,boolean save,String labelType)
	{
		// init the graph
		this.numOfNodes = numOfRegions;
		this.degree = graphDegree > numOfRegions?(numOfRegions-1):graphDegree;
	    this.numOfEdges = (numOfRegions * this.degree) / 2;
	    this.labelSize = labelSize;
	
	    initialize();
	    labelEdges(labelType);
	    // Init path set Q // 0.5 seconds required
	    createPathQueue();
	    // All constraints need to be evaluated during Back tracking
	    createAllConstraintsNotHorn();
	    if(save)
	    	this.saveGraph();
		
		
	}
	
	public PCGraph(int numOfRegions, int graphDegree, int labelSize,boolean save){
		// init the graph
				this.numOfNodes = numOfRegions;
				this.degree = graphDegree > numOfRegions?(numOfRegions-1):graphDegree;
			    this.numOfEdges = (numOfRegions * this.degree) / 2;
			    this.labelSize = labelSize;
			 //   Timer.start();
			    initialize();
			 //   Timer.end("Nodes");
			//    Timer.start();
			    labelEdges("");
			//    Timer.end("Edges");
			    // Init path set Q
			 //   Timer.start();
			    createPathQueue();
			//    Timer.end("Path");
			    // All constraints need to be evaluated during Back tracking
			//    Timer.start();
			    createAllConstraintsNotHorn();
			//    Timer.end("All Constraints");
			    if(save)
			    	this.saveGraph();

	}
	public PCGraph() {
		
	}
	public static int[][] initializeEdges(int numOfNodes)
	{
		int[][] _edges = new int[numOfNodes][numOfNodes];
	    for (int i=0;i< numOfNodes;i++)
	    {
	    	for(int j = 0; j< numOfNodes;j++)
	    	{
	    		
	             _edges[i][j] = 255;
	    	}
	    	}
	    return _edges;
	}
	
	private void initialize()
	{ 
		edges = new int[this.numOfNodes][this.numOfNodes];
	    for (int i=0;i<this.numOfNodes;i++)
	    {
	    	for(int j = 0; j<this.numOfNodes;j++)
	    	{
	    		
	             edges[i][j] = 255;
	    	}
	    	}
		
	}
	public void createAllConstraintsNotHorn()
	{
	    	allConstraints = new LinkedList<Constraint>();
		  for (int i = 0;i< numOfNodes;i++){
				for (int j = i+1;j<  numOfNodes; j++)
				{
					 
					
				    if(!MTS.getBIMTS("H8").contains(edges[i][j]))
					{	
						
						allConstraints.add(new Constraint(i,j)); 
					}
				}
				
				
			}
	}	
	public void createConstraints()
	{
		allConstraints = new LinkedList<Constraint>();
		  for (int i = 0;i < numOfNodes;i++)
				for (int j = i+1;j <  numOfNodes; j++)
					allConstraints.add(new Constraint(i,j)); 
					
	}
	private void labelEdges(String type)
	{
		
		 int EdgesCount = 0;
		 int labels = 0;
		    while (EdgesCount<this.numOfEdges)
		    {
		    
		    	int index = (int)(Math.random()* (this.numOfNodes*this.numOfNodes))+1;
		    	int theRowIndex = (int) Math.ceil((double)index / this.numOfNodes) - 1;
		    	int theColumnIndex = ((index-1) % this.numOfNodes);
		    //	System.out.println(theRowIndex);
		    //	System.out.println(theColumnIndex);
		    	if(edges[theRowIndex][theColumnIndex]==255 && theRowIndex!=theColumnIndex)// CURRENTLY NO EDAGE AT THIS LOCATION,universal. No Self Connected
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
		    		edges[theRowIndex][theColumnIndex] = labels;
		    		
		    		// Inverse -------------------------------------------
		    		edges[theColumnIndex][theRowIndex] = SetOperators.inverse(labels);
		    		EdgesCount++;
		    	}
		    }
		    
		   
	}
	public void createPathQueue()
	{
		
			for (int i = 0; i<this.numOfNodes-1;i++)
		         for (int j = i+1; j < this.numOfNodes; j++)
		         {
		             for (int k = 0; k < this.numOfNodes;k++)
		        	 {	 
		        	     if (k!=i & k!=j &!( this.edges[i][j] == 255&this.edges[j][k] == 255))
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
	

	public void saveGraph(String graphName)
	{
		 FileOutputStream fos;
		try {
			if(graphName == "")
			fos = new FileOutputStream("Graph.obj");
			else
				{
				 fos = new FileOutputStream(graphName+".obj");
				}
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
	public static PCGraph  loadGraph()
	{
		 FileInputStream fos;

			try 
			{
				fos = new FileInputStream("Graph.obj");
		         ObjectInputStream     out = new ObjectInputStream(fos);
			     return (PCGraph) out.readObject();
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
	public static PCGraph  loadGraph(String fileName)
	{
		 FileInputStream fos;

			try 
			{
			  if (fileName == "")
				  fileName = "Graph.obj";
			 	fos = new FileInputStream(fileName+".obj");
		         ObjectInputStream     out = new ObjectInputStream(fos);
			     return (PCGraph) out.readObject();
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
	public void printEdges()
	{
		for (int i = 0; i < edges.length; i ++)
			for (int j = 0; j < edges.length; j++)
				System.out.println(edges[i][j]);
	}
	public String printGraph()
	{
	
        // write the headline per graph
		String header = (this.getNumOfNodes()-1)+" #"+(1)
				+"-N"+this.getNumOfNodes()+"-D"+this.getGraphDegree()+
				"-L"+this.getLabelSize()+"\n";
      
		 String line = new String("");
		 for (int i = 0;i<this.getNumOfNodes();i++)
		 {
			 for (int j =0;j<this.getNumOfNodes();j++)
			 {
				 int rels =this.getEdges()[i][j];
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
	public String printGraph(HashMap<Integer,Integer> entropy)
	{
	
        // write the headline per graph
		String header = (this.getNumOfNodes()-1)+" #"+(1)
				+"-N"+this.getNumOfNodes()+"-D"+this.getGraphDegree()+
				"-L"+this.getLabelSize()+"\n";
      
		 String line = new String("");
		 for (int i = 0;i<this.getNumOfNodes()-1;i++)
		 {
			 for (int j =i+1;j<this.getNumOfNodes();j++)
			 {
				 int rels =this.getEdges()[i][j];
			/*	 System.out.println(rels);
				 System.out.println(rels.size());*/
				 if(rels!=255)
				 {
		            line += i +"  " + j + " "+Relation.translateToString(rels);
		            line = line.replace("[", "(");
		            line = line.replace("]", ")");
		            line = line.replace(",", " ");
		            line = line.replace("i", "I");
		            line+= "Entropy  " + entropy.get((i*17)^j);
		            line+="\n";        
				 }
			 }
		 }
		 System.out.println(header+line+".");
		 return header + line + ".";
		           
	}
	public int[][] getEdges() {
		return edges;
	}
	public void setNodes(int[][] nodes) {
		if(this.edges==null)
			 this.edges = new int[this.numOfNodes][this.numOfNodes];
		for (int i =0;i<this.numOfNodes;i++)
			for (int j =0;j<this.numOfNodes;j++)
			{  
			
				this.edges[i][j]= nodes[i][j];
					
			}
	}
	public int getNumOfNodes() {
		return numOfNodes;
	}
	public void setNumOfNodes(int numOfRegions) {
		this.numOfNodes = numOfRegions;
	}
	public int getGraphDegree() {
		return degree;
	}
	public void setGraphDegree(int graphDegree) {
		this.degree = graphDegree;
	}
	public int getNumOfEdges() {
		
		int count = 0;
		for (int i = 0; i < edges.length - 1; i++)
			for(int j = i + 1; j < edges.length; j++)
			{
				if(edges[i][j] != 255 && edges[i][j] != 0)
					count ++;
			}
		return count;
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
		} //while (MTS.BIMTS_H8.contains(rels)&(this.labelSize!=1));
	        while (MTS.BIMTS.contains(rels) & (this.labelSize != 1));
		return rels;
	}

	public PCGraph clone()
	{
		// long time = System.currentTimeMillis();
		PCGraph graph = new PCGraph();
		graph.setGraphDegree(getGraphDegree());
		graph.setLabelSize(getLabelSize());
		graph.setNumOfEdges(numOfEdges);
		graph.setNumOfNodes(getNumOfNodes());
		graph.setNodes(getEdges());
	    graph.createPathQueue();
	    if(this.allConstraints!=null)
	    	graph.setAllConstraints(this.allConstraints);
	//	 System.out.println(System.currentTimeMillis() - time);
	    return graph;
	}
	public PCGraph buildEntropyO(int node,int label)
	{
		PCGraph graph = new PCGraph(node,4,label);
		for (int row = 0;row < 9;row ++)
			for (int col = 0; col<9;col++)
			{
				graph.getEdges()[row*10 + col][row*10 + col + 1 ] = this.init_generateLable(label); // Right
				graph.getEdges()[row *10 + col][(row +1)*10 + col] = this.init_generateLable(label); // Down
				graph.getEdges()[row * 10 + col][(row + 1)*10 + col + 1] = this.init_generateLable(label); // diagonal
     }
	
	return graph;
	}
	
	public PCGraph buildEntropyO_Seven(int label)
	{
		PCGraph graph = new PCGraph(8,6,label);
		
		for (int row = 0;row < 1;row ++)
			for (int col = 0; col< 3;col++)
			{
				graph.getEdges()[row * 4 + col][row*4 + col + 1 ] = this.init_generateLableNP8(label); // Right
				if (row == 0)
				{	
					graph.getEdges()[row * 4 + col][(row +1)*4 + col] = this.init_generateLableNP8(label); // Down
			        graph.getEdges()[row * 4 + col][(row + 1)*4 + col + 1] = this.init_generateLableNP8(label); // diagonal
				}
     }
	
	return graph;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[])
	{
		long time1 = System.currentTimeMillis();
		PCGraph graph = new PCGraph(100, 50, 4,false);
		
		 System.out.println(System.currentTimeMillis() - time1);
	
		System.out.println("PCA----------------------");
	   // graph.printGraph();
	    long time = System.currentTimeMillis();
	    System.out.println("CA----------------------");
	  //  clone.printGraph();
	    PCGraph clone = graph.clone();
	    System.out.println(System.currentTimeMillis() - time);
	  /* // graph.getNodes()[0][1].getRelations().clear();
	    System.out.println("Again CA----------------------");
	    clone.printGraph();*/
	}
	public void setPathQueue(PathQueue pathQueue) 
	{
		this.pathQueue = pathQueue;
		
	}
}
