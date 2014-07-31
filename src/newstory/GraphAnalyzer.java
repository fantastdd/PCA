package newstory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tool.GQRGraphReader;
import essential.Constraint;
import essential.GQR;
import essential.PCGraph;
import essential.SetOperators;

public class GraphAnalyzer {

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

	public GraphAnalyzer(PCGraph graph, int distance, int top) {
		try {
			writer = new PrintWriter("disMatrix.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" ------ Initialize -----");
		this.graph = graph;
		this.iniDistance = distance;
		resultingGraphs = new LinkedList<PCGraph>();
		this.top = top;
		allConstraints = new LinkedList<Constraint<Integer>>();
		System.out.println("      Creating Constraints ------");
		for (int i = 0; i < graph.edges.length - 1; i++)
			for (int j = i + 1; j < graph.edges.length; j++)
				if (graph.edges[i][j] != 255 && graph.edges[i][j] != 0) {
					totalEdges++;
					allConstraints.add(new Constraint<Integer>(graph.edges[i][j], i, j));
				}
		disMatrix = new int[totalEdges][totalEdges];
		adjacencyMatrix = new int[totalEdges][totalEdges];
		System.out.println(" total edges: " + totalEdges);
		// initialize matrice
		System.out.println("      Initialze Matrices ------");
		initializeMatrice();
		cutOffDistance = 10;

	}

	public List<PCGraph> getResultingGraphs() {
		return resultingGraphs;
	}

	/*
	 * public void run() { //Analyze Entropy; Entropy.calEntropy(graph); //Sort
	 * entropy constraints Collections.sort(Entropy.entropy, new
	 * Comparator<EntropyConstraint>(){
	 * 
	 * @Override public int compare(EntropyConstraint arg0, EntropyConstraint
	 * arg1) {
	 * 
	 * return
	 * -((Integer)arg0.getEntropy()).compareTo((Integer)arg1.getEntropy()); }
	 * 
	 * }); //Get the top n entropies if(!Entropy.entropy.isEmpty()) { int n = 0;
	 * while( ( n <= top || top == -1) && (Entropy.entropy.size() > n )) {
	 * 
	 * 
	 * resultingGraphs.add(getSubGraphByDistance( new Constraint<Integer>(
	 * Entropy.entropy.get(n).getI(), Entropy.entropy.get(n).getJ()), graph ));
	 * n++; } } }
	 */

	public void printMatrix(int[][] matrix) {
		// i : row number
		for (int i = 0; i < matrix.length + 1; i++) {
			if (i == 0) {
				System.out.print("   ");
				writer.print("  ");
				for (int j = 0; j < matrix.length; j++) {
					System.out.print(j + "  ");
					writer.print(j + "  ");
				}
			} else {
				System.out.println();
				writer.println();
				System.out.print(i - 1);
				writer.print(i - 1);
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[i - 1][j] > cutOffDistance) {
						System.out.print("  " + cutOffDistance + "+");
						writer.print("  " + cutOffDistance + "+");
					} else {
						System.out.print("  " + matrix[i - 1][j]);
						writer.print("  " + matrix[i - 1][j]);
					}
				}
			}
		}
	}

	public void initializeMatrice() {

		// initialize disMatrix
		for (int i = 0; i < totalEdges; i++)
			for (int j = 0; j < totalEdges; j++) {
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;

				if (i == j) {
					disMatrix[i][j] = 0;
					adjacencyMatrix[i][j] = 0;
				} else
					disMatrix[i][j] = -1;
			}

		Constraint<Integer> target_constraint;
		Constraint<Integer> remaining_constraint;
		for (int count = 0; count < totalEdges - 1; count++) {
			
			if( count % 100 == 0)
				System.out.println( "      " + ( ((double)count/(double)totalEdges) * 100) + " %  completed" );
			target_constraint = allConstraints.get(count);
			// System.out.println("target: " + target_constraint);
			for (int i = count + 1; i < totalEdges; i++) {
				remaining_constraint = allConstraints.get(i);
				/*
				 * System.out.println("r: " + remaining_constraint + " " +
				 * remaining_constraint.getSource() + " " +
				 * target_constraint.getDestination() +
				 * (remaining_constraint.getSource() ==
				 * target_constraint.getDestination()));
				 */
				if (remaining_constraint.getSource().intValue() == target_constraint
						.getSource().intValue()
						|| remaining_constraint.getDestination().intValue() == target_constraint
								.getSource().intValue()
						|| remaining_constraint.getSource().intValue() == target_constraint
								.getDestination().intValue()
						|| remaining_constraint.getDestination().intValue() == target_constraint
								.getDestination().intValue()) {
					// System.out.println(" neighbor: " + remaining_constraint);
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
	public int[][] formatAdjMatrixForGephi() {
		// Convert Integer.Max_VALUE to 0 to comply the input requirement of
		// Gephi
		for (int i = 0; i < adjacencyMatrix.length; i++)
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				if (adjacencyMatrix[i][j] == Integer.MAX_VALUE)
					adjacencyMatrix[i][j] = 0;
			}
		return adjacencyMatrix;
	}

	public int[][] getDistanceMatrix() {
		/*
		 * printMatrix(adjacencyMatrix); System.out.println();
		 * System.out.println();
		 */
		NewGraph _graph = new NewGraph(adjacencyMatrix);
		disMatrix = _graph.floyd();

		return disMatrix;
	}

	/**
	 * @param disMatrix
	 *            : distance matrix
	 * @param vertexIndex
	 *            : the index of the center vertex
	 * @param distance
	 *            : the maximum distance
	 * @return EdgeIndice: all the neighbor vertices of the center vertex within
	 *         the specified maximum distance
	 * */
	public List<Integer> findSubgraphByVertexEccentricity(int[][] disMatrix,
			int vertexIndex, int distance) {
		List<Integer> vertices = new ArrayList<Integer>();
		//System.out.println(" distance : " + distance);
		for (int col = 0; col < disMatrix.length; col++) {
			//System.out.println(disMatrix[vertexIndex][col]);
			if (vertexIndex != col && disMatrix[vertexIndex][col] <= distance)
				vertices.add(col);
		}
		return vertices;
	}

	/**
	 * @param distance
	 *            matrix of vertices (vertex: edge in original graph)
	 * @return the index of the vertex with the lowest eccentricity
	 * **/
	public int findVertexWithLowestEccentricityMostNeighbors(int[][] disMatrix, int maximum_distance) {
		int vertexIndex = -1;
		int maxNeighbors = 0;
		for (int i = 0; i < disMatrix.length; i++) {
			int count = 0;
			for (int j = 0; j < disMatrix.length; j++) {
				if (disMatrix[i][j] != Integer.MAX_VALUE ) {
					if (disMatrix[i][j] < maximum_distance && i != j)
						count ++;
				}
			}
			if (count > maxNeighbors) {
				vertexIndex = i;
				maxNeighbors = count;
			}

		}
		//System.out.println(" min " + min);
		return vertexIndex;

	}

	public PCGraph getGraphByEdgeIndice(List<Integer> edgeIndice, List<Constraint<Integer>> allConstraints)
	{
		PCGraph _graph = new PCGraph(graph.numOfNodes); 
		for (Integer index : edgeIndice )
		{
			Constraint<Integer> edge = allConstraints.get(index);
			_graph.edges[edge.getSource()][edge.getDestination()] = edge.getRelations();
		}
		return _graph;
	}
	
	public PCGraph getGraphByEccentricityAnalysis()
	{
		//calculate distance matrix
		System.out.println(" --- Get Distance Matrix ---");
		getDistanceMatrix();
		// find the center vertex index
		int index = findVertexWithLowestEccentricityMostNeighbors(disMatrix, cutOffDistance);
		// find the list of neighbour vertices
		List<Integer> vertices = findSubgraphByVertexEccentricity(disMatrix, index, cutOffDistance/2);
		//System.out.println(" vertices size: " + vertices.size());
		// Get the graph
		return getGraphByEdgeIndice(vertices, allConstraints);
	}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {

		/*
		 * PCGraph graph = new PCGraph(4,3,4); graph.edges[0][1] = 132;
		 * graph.edges[1][0] = 132; graph.edges[0][2] = 132; graph.edges[2][0] =
		 * 132; graph.edges[0][3] = 132; graph.edges[3][0] = 132;
		 * graph.edges[1][2] = 132; graph.edges[2][1] = 132; graph.edges[1][3] =
		 * 132; graph.edges[3][1] = 132; graph.edges[2][3] = 132;
		 * graph.edges[3][2] = 132;
		 * 
		 * GraphDistanceAnalyzer gda = new GraphDistanceAnalyzer(graph,1,1);
		 * gda.printMatrix(gda.getDistanceMatrixNew());
		 */

		// graph.exportCSV();

		/*
		 * int distance = 4;
		 * 
		 * String file = "H_42th_220_20_4_PC_Dis5_inconsistency.csp"; Graph
		 * graph = GQRReader.readGraphFromGQR(file);
		 * 
		 * int top = //50; //graph.numOfEdges; -1; // -1 means all edges
		 * GraphDistanceAnalyzer gda = new
		 * GraphDistanceAnalyzer(graph,distance,top); gda.run(); for (Graph
		 * _graph : gda.getResultingGraphs()) { //_graph.printGraph();
		 * GQR.generateGraphGQR(_graph, "/home/users/xiaoyuge/Emp/" +
		 * file.substring(0, file.indexOf(".")) + "_Dis"+ distance +"_T" + top
		 * +"");
		 * 
		 * }
		 */

		/*
		 * String prefix = ""; String file =
		 * "H_42th_220_20_4_PC_Dis5_inconsistency.csp";
		 * 
		 * PCGraph graph = GQRReader.readGraphFromGQR(prefix + "" +file);
		 * graph.exportCSV(); GraphDistanceAnalyzer gda = new
		 * GraphDistanceAnalyzer(graph,1,1);
		 * 
		 * gda.printMatrix(gda.getDistanceMatrixNew());
		 */

		String prefix = "src/graph_files/";
		String file = "test74.csp";
		PCGraph graph = GQRGraphReader.readGraphFromGQR(prefix + file);
		GraphAnalyzer gda = new GraphAnalyzer(graph, 1, 1);
		PCGraph _graph = gda.getGraphByEccentricityAnalysis();
		_graph.setName("test74_dis10");
		GQR.generateGraphGQR(_graph);
		
		/*PCGraph readGraph = GQRReader.readGraphFromGQR("CSPForGQR_dis10.csp");
		GraphAnalyzer gda = new GraphAnalyzer(readGraph, 1, 1);
		PCGraph hyperGraph = new PCGraph(gda.formatAdjMatrixForGephi());
		hyperGraph.exportCSV();*/
		
		
		// gda.printMatrix(gda.getDistanceMatrixNew());
		// PCGraph hyperGraph = new PCGraph(gda.formatAdjMatrixForGephi());
		/*
		 * PCGraph hyperGraph = new PCGraph(gda.getDistanceMatrixNew());
		 * hyperGraph.exportCSV();
		 */
		/*
		 * int[][] disMatrix = gda.getDistanceMatrix(); for (int i = 0; i <
		 * disMatrix.length; i++) { int count = 0; int _max = 0; int _less = 0;
		 * for(int j = 0; j < disMatrix.length; j++) { if(disMatrix[i][j] ==
		 * Integer.MAX_VALUE) _max ++; else if(disMatrix[i][j] < 5) _less ++;
		 * else count += disMatrix[i][j]; }
		 * 
		 * System.out.println("Less than 5 " + _less + "  Distance: " + count +
		 * "  Max distance: " + _max); }
		 */
	}
}
