package deprecated;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import newstory.NewGraph;

import tool.GQRGraphReader;
import analysis.Entropy;
import analysis.EntropyConstraint;
import essential.Constraint;
import essential.PCGraph;

public class GraphDistanceAnalyzer {
	
	private PCGraph graph;
	private List<PCGraph> resultingGraphs;
	private int iniDistance = 1;
	private int cutOffDistance;
	private int top = 1;// -1 : use all constraints
	private int totalEdges = 0; // number of edges in the original graph
	private List<Constraint<Integer>> allConstraints;
	private int[][] disMatrix;
	public int[][] adjacencyMatrix;
	private PrintWriter writer;
	public GraphDistanceAnalyzer(PCGraph graph, int distance, int top)
	{
		try {
			writer =  new PrintWriter("disMatrix.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.graph = graph;
		this.iniDistance = distance;
		resultingGraphs = new LinkedList<PCGraph>();
		this.top = top;
		allConstraints = new LinkedList<Constraint<Integer>>();
		for (int i = 0; i < graph.edges.length - 1; i++)
			for (int j = i + 1; j < graph.edges.length; j++)
				if(graph.edges[i][j] != 255 && graph.edges[i][j] != 0)
					{
						totalEdges ++;
						allConstraints.add(new Constraint<Integer>(i,j));
					}
		disMatrix = new int[totalEdges][totalEdges];
		adjacencyMatrix = new int[totalEdges][totalEdges];
		//initialize matrice
		initializeMatrice();
		cutOffDistance = 100;
		
	}
	public List<PCGraph> getResultingGraphs(){
		return resultingGraphs;
	}
	
	/*public void run()
	{
		//Analyze Entropy;
		Entropy.calEntropy(graph);
		//Sort entropy constraints
		Collections.sort(Entropy.entropy, new Comparator<EntropyConstraint>(){

			@Override
			public int compare(EntropyConstraint arg0, EntropyConstraint arg1) {
				
				return -((Integer)arg0.getEntropy()).compareTo((Integer)arg1.getEntropy());
			}
			
		});
		//Get the top n entropies
		if(!Entropy.entropy.isEmpty())
		{
			int n = 0;
			while( ( n <= top || top == -1) && (Entropy.entropy.size() > n ))
			{
				
			
				resultingGraphs.add(getSubGraphByDistance( new Constraint<Integer>(
						Entropy.entropy.get(n).getI(), Entropy.entropy.get(n).getJ()),
						graph
					));
				n++;
			}
		}
	}*/
	
	
	
/*	public PCGraph getSubGraphByDistance(Constraint<Integer> constraint, PCGraph graph)
	{
		PCGraph newGraph = new PCGraph(graph.numOfNodes);
		newGraph.degree = graph.degree;
		newGraph.labelSize = graph.labelSize;
	
		int[][] _edges = PCGraph.initializeEdges(graph.numOfNodes);
		
		LinkedList<Constraint<Integer>> initialConstriants = 
				new LinkedList<Constraint<Integer>>();
		
		initialConstriants.add(constraint);
		
		_edges = getSubGraphByDistance(initialConstriants, _edges, graph.edges, iniDistance);
		
		newGraph.edges = _edges;
		//newGraph.printEdges();
		
		return newGraph;
	}*/
	/*private int[][] getSubGraphByDistance(List<Constraint<Integer>> constraints, int[][] newEdges
			, int[][] originalEdges, int distance)
	{
		
		if(distance == 0)
				return newEdges;
		
		LinkedList<Constraint<Integer>> newConstriants = new LinkedList<Constraint<Integer>>();
		for( Constraint<Integer> constraint : constraints)
		{
			newEdges[constraint.getSource()][constraint.getDestination()] = 
					originalEdges[constraint.getSource()][constraint.getDestination()];
			
			newEdges = getSubGraphByDistanceOne(constraint, newEdges, originalEdges, newConstriants);
			
		}
		
		return getSubGraphByDistance(newConstriants, newEdges, originalEdges, --distance);
	}*/
	
/*	*//**
	 *  @param edges:  edges of the original graph
	 *  @param newEdges: edges of the resulting graph
	 *  @param constraint: candidate 
	 * *//*
	private int[][] getSubGraphByDistanceOne(Constraint<Integer> constraint, int[][] newEdges, int[][] edges
			, List<Constraint<Integer>> newConstraints)
	{
		newConstraints.clear();
		for(int i = 0; i < edges.length - 1; i++)
	    for (int j = i + 1 ; j < edges.length; j++)
		{
			if( (i == constraint.source || i == constraint.destination ||
					j == constraint.source || j == constraint.destination) &&
					(i!= constraint.source || j!= constraint.destination)
						&& edges[i][j] != 255 //Only the edges in the original graph counts//Incorrect: although no edges, but still can propogate
			  )
				
				if(newEdges[i][j] == 255)
				{
					newEdges[i][j] = edges[i][j];		
					newConstraints.add(new Constraint<Integer>( newEdges[i][j], i , j));
				} 
					
			
		}	
		return newEdges;
	}*/
	/**
	 * @param edges: graph's edges
	 * @return distance matrix: get the distance matrix between edges
	 * **//*
	public int[][] getDistanceMatrix()
	{
		System.out.println(" total constraints: " + allConstraints.size() + " total edges: " + totalEdges);
		for (int current_index = 0; current_index < allConstraints.size(); current_index++)
		{
			getDistance(current_index);
			if(current_index%1 == 0)
				System.out.println(current_index);
		}
		return disMatrix;
	}*/
	/**
	 * @param edges: original graph's edges
	 * @param i,j: the current edge's index
	 * @return distance: the current edge's distance with others
	 *//*
	public void getDistance(int current_index)
	{
		int updated_edges = 0;
		int distance = 0;
		LinkedList<Integer> edgesIndices = new LinkedList<Integer>();
		edgesIndices.add(current_index);
		int lastUpdate = updated_edges;
		//System.out.println(" current index " + current_index);
		while(updated_edges < (allConstraints.size() - current_index - 1) && distance < cutOffDistance//distance < totalEdges 
				)
		{
			//if(updated_edges%10 == 0)
			//System.out.println(updated_edges + "  " + distance);
			long time = System.currentTimeMillis();
				updated_edges += getEdgesByDistanceOne(current_index, edgesIndices, ++distance);
		
			System.out.println(" time consumption: " + (System.currentTimeMillis() - time));
			if(edgesIndices.isEmpty())
			{
				System.out.println(" No more reachable edges");
				break;
			} else
				if(updated_edges == lastUpdate)
				{
					System.out.println(" updates convergence");
					break;
				}
			lastUpdate = updated_edges;
		}
		//update the last edge
		for (int i = 0; i < totalEdges; i++)
			disMatrix[totalEdges - 1][i] = disMatrix[i][totalEdges - 1];

	}*/
	/**
	 * @param edgesIndices: an array of indices of the edges which have the distance 1 with the target edge
	 * @param currentIndex: the index of the current edge
	 * @param distance: previous distance
	 * @return number of newly updates
	 * **//*
	public int getEdgesByDistanceOne(int currentIndex, List<Integer> edgesIndice, int distance)
	{
		int updates = 0;
	
		LinkedHashSet<Integer> temp = new LinkedHashSet<Integer>();
		System.out.println(" edgesIndice size: " + edgesIndice.size());
		Constraint<Integer> remaining_constraint;
		for (Integer index : edgesIndice)
		{
			//long time = System.currentTimeMillis();
			Constraint<Integer> target_constraint = allConstraints.get(index);
			

			//update the distance calculated previously
			for (int i = 0; i < index; i++)
			{
				if(disMatrix[i][index] == 1)			{	
					temp.add(i);
					//update the disMatrix
					if(disMatrix[currentIndex][i] == -1)
					{
						disMatrix[currentIndex][i] = distance;
						if(i > currentIndex)
							updates++;
					}
				}
				else
					if(index == currentIndex && disMatrix[i][index] != -1)
						disMatrix[currentIndex][i] = disMatrix[i][index];
				
			}

			//Calculate the distance with the remaining edges
			for (int i = index + 1; i < totalEdges; i++)
			{
				remaining_constraint = allConstraints.get(i);
				if (remaining_constraint.getSource() == target_constraint.getSource() || remaining_constraint.getDestination() == target_constraint.getSource()
						|| remaining_constraint.getSource() == target_constraint.getDestination() || remaining_constraint.getDestination() == target_constraint.getDestination())
				{
					if(disMatrix[currentIndex][i] == -1)
					{
						disMatrix[currentIndex][i] = distance;
						if(i > currentIndex)
							updates++;
					}
					temp.add(i);
					
				}
				
			}
			//System.out.println(" time consumption: " + (System.currentTimeMillis() - time));
		
		}
	
		edgesIndice.clear();
		edgesIndice.addAll(temp);
		
		return updates;
	}*/
	public void printMatrix(int[][] matrix)
	{
		//i : row number
		for (int i = 0; i < matrix.length + 1; i++)
		{
			if(i == 0)
			{
				System.out.print("   ");
				writer.print("  ");
				for(int j = 0; j < matrix.length; j++)
					{
						System.out.print(j + "  ");
						writer.print(j + "  ");
					}
			}
			else{
				System.out.println();
				writer.println();
				System.out.print(i - 1);
				writer.print(i - 1);
				for(int j = 0; j < matrix.length; j++)
				{
					if(matrix[i-1][j] > cutOffDistance)
						{
							System.out.print("  " + cutOffDistance +"+");
							writer.print("  " + cutOffDistance + "+");
						}
					else
						{
							System.out.print("  " + matrix[i - 1][j]);
							writer.print("  " + matrix[i - 1][j]);
						}
				}
			}
		}
	}
	
	public void initializeMatrice()
	{
		
		//initialize disMatrix
		 for (int i = 0; i < totalEdges; i++)
				for(int j = 0; j < totalEdges; j++)
				{ 
					adjacencyMatrix[i][j] = Integer.MAX_VALUE;
					
					if(i == j)
					{
						disMatrix[i][j] = 0;
						adjacencyMatrix[i][j] = 0;
					}
					else
						disMatrix[i][j] = -1;
				}
	
		Constraint<Integer> target_constraint;
		Constraint<Integer> remaining_constraint;
		for (int count = 0; count < totalEdges - 1; count++)
		{
			target_constraint = allConstraints.get(count);
			//System.out.println("target: " + target_constraint);
			for (int i = count + 1; i < totalEdges; i++)
			{
				 remaining_constraint = allConstraints.get(i);
				 /*System.out.println("r: " + remaining_constraint + " " +  
						 remaining_constraint.getSource() + " " +
						 target_constraint.getDestination() + 
						 (remaining_constraint.getSource() == target_constraint.getDestination()));*/
				if (remaining_constraint.getSource().intValue() == target_constraint.getSource().intValue()
					|| remaining_constraint.getDestination().intValue() == target_constraint.getSource().intValue()
					|| remaining_constraint.getSource().intValue() == target_constraint.getDestination().intValue() 
					|| remaining_constraint.getDestination().intValue() == target_constraint.getDestination().intValue())
				{
					//System.out.println(" neighbor: " + remaining_constraint);
					disMatrix[i][count] = 1;
					disMatrix[count][i] = 1;
					adjacencyMatrix[i][count] = 1;
					adjacencyMatrix[count][i] = 1;
				}
				
					
			}
			
		
		}
	}
	/**
	 * Adjancency Matrix: neighbor edges have distance 1.
	 * */
	public int[][] formatAdjMatrixForGephi()
	{
		//Convert Integer.Max_VALUE to 0 to comply the input requirement of Gephi
		for (int i = 0; i < adjacencyMatrix.length; i++)
			for (int j = 0; j < adjacencyMatrix.length; j++)
			{
			  if(adjacencyMatrix[i][j] == Integer.MAX_VALUE)
				adjacencyMatrix[i][j] = 0;
			}
		return adjacencyMatrix;
	}
	public int[][] getDistanceMatrixNew()
	{
		/*printMatrix(adjacencyMatrix);
		System.out.println();
		System.out.println();*/
		NewGraph _graph = new NewGraph(adjacencyMatrix);
		disMatrix = _graph.floyd();
		
		return disMatrix;
	}
	
	/**
	 * @param distance matrix of vertices (vertex: edge in original graph)
	 * @return the index of the vertex with the lowest eccentricity
	 * **/
	public int findVertexWithLowestEccentricity(int[][] disMatrix)
	{
		int vertexIndex = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < disMatrix.length; i++)
		{
			int totalEccentrity = Integer.MAX_VALUE;
			for (int j = 0; j < disMatrix.length; j++)
			{
				if(disMatrix[i][j] != Integer.MAX_VALUE)
				{
					if(totalEccentrity == Integer.MAX_VALUE)
						totalEccentrity = disMatrix[i][j];
					else
						totalEccentrity += disMatrix[i][j];
				}
			}
			if (totalEccentrity < min)
			{	
				vertexIndex = i;
				min = totalEccentrity;
			}
				
		}
		
		return vertexIndex;
		
	}
	
	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException{
		
/*		PCGraph graph = new PCGraph(4,3,4);
		graph.edges[0][1] = 132;
		graph.edges[1][0] = 132;
		graph.edges[0][2] = 132;
		graph.edges[2][0] = 132;
		graph.edges[0][3] = 132;
		graph.edges[3][0] = 132;
		graph.edges[1][2] = 132;
		graph.edges[2][1] = 132;
		graph.edges[1][3] = 132;
		graph.edges[3][1] = 132;
		graph.edges[2][3] = 132;
		graph.edges[3][2] = 132;

		GraphDistanceAnalyzer gda = new GraphDistanceAnalyzer(graph,1,1);
		gda.printMatrix(gda.getDistanceMatrixNew());*/
		
		//graph.exportCSV();
	
		/*	int distance = 4;
		
		String file = "H_42th_220_20_4_PC_Dis5_inconsistency.csp";
		Graph graph = GQRReader.readGraphFromGQR(file);
		
		int top = //50;
				//graph.numOfEdges;
				-1; // -1 means all edges
		GraphDistanceAnalyzer gda = new GraphDistanceAnalyzer(graph,distance,top);
		gda.run();
		for (Graph _graph : gda.getResultingGraphs())
		{
			//_graph.printGraph();
			GQR.generateGraphGQR(_graph, "/home/users/xiaoyuge/Emp/" + file.substring(0, file.indexOf(".")) + "_Dis"+ distance +"_T" + top +"");
			
		}*/
		
		
		/*
		String prefix = "";
		String file = "H_42th_220_20_4_PC_Dis5_inconsistency.csp";
		
		PCGraph graph = GQRReader.readGraphFromGQR(prefix + "" +file);
		graph.exportCSV();
		GraphDistanceAnalyzer gda = new GraphDistanceAnalyzer(graph,1,1);

		gda.printMatrix(gda.getDistanceMatrixNew());
		*/
		
		String prefix = "src/graph_files/";
		String file = "sparse2.csp";
		PCGraph graph =  GQRGraphReader.readGraphFromGQR(prefix + file);
		GraphDistanceAnalyzer gda = new GraphDistanceAnalyzer(graph, 1,1);
		//gda.printMatrix(gda.getDistanceMatrixNew());
		//PCGraph hyperGraph = new PCGraph(gda.formatAdjMatrixForGephi());
	/*	PCGraph hyperGraph = new PCGraph(gda.getDistanceMatrixNew());
		hyperGraph.exportCSV();*/
		int[][] disMatrix = gda.getDistanceMatrixNew();
		for (int i = 0; i < disMatrix.length; i++)
		{	
			int count = 0;
			int _max = 0;
			int _less = 0;
			for(int j = 0; j < disMatrix.length; j++)
			{
				if(disMatrix[i][j] == Integer.MAX_VALUE)
					_max ++;
				else 
					if(disMatrix[i][j] < 5)
						_less ++;
					else
						count += disMatrix[i][j];
			}
		
		System.out.println("Less than 5 " + _less +  "  Distance: " + count + "  Max distance: " + _max);
		}
	}
}
