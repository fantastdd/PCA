package newstory;

import java.util.LinkedList;

/**
 * Instances of this class represent a graph that may be directed or undirected,
 * weighted or unweighted Methods are provided for solving the all-pairs
 * shortest- paths problem, and for finding connected components in the
 * undirected case. An adjacency matrix is used to represent the graph.
 */
/*
 * A more efficient representation would use flags to indicate whether the graph
 * is weighted or unweighted whether the graph is directed or undirected whether
 * the path variable has been updated and if so, by which method The constructor
 * would initialize the first two of these flags
 */

public class NewGraph {

	// a dummy vertex number for representing missing vertices
	// in paths
	public static final int NOT_A_VERTEX = -1;

	// the adjacency matrix
	private int[][] a;

	// the number of vertices
	private int n;

	// path information from the last time that an all-pairs
	// shortest-paths method was invoked
	// the i,j entry gives the number of a vertex on the
	// cheapest path from i to j, or NOT_A_VERTEX if no
	// such vertex exists

	private int[][] path = new int[0][0];

	/**
	 * Constructs a graph with a given adjacency matrix
	 * 
	 * @param int[][] adj the desired adjacency matrix
	 * @throws IllegalArgumentException
	 *             if the adjacency matrix is not square
	 */

	public NewGraph(int[][] adj) {
		n = adj.length;
		a = new int[n][n];
		for (int i = 0; i < n; i++) {
			if (adj[i].length != n)
				throw new IllegalArgumentException(
						"input arrays must be square");
			for (int j = 0; j < n; j++) {
				int c = adj[i][j];
				if (c < 0)
					throw new IllegalArgumentException("the cost value at " + i
							+ "," + j + " cannot be negative");
				a[i][j] = adj[i][j];
			}
		}
	}

	/**
	 * Determines whether two vertices in the graph are adjacent
	 * 
	 * @param i
	 *            the source vertex
	 * @param j
	 *            the destination vertex
	 * @return <code>true</code> iff the two vertices appear in the graph and
	 *         there is an edge from i to j
	 */

	public boolean isAdjacent(int i, int j) {
		return this.isAdajcent(a, i, j);
	}

	/**
	 * Determines whether two vertices are adjacent in the graph with a given
	 * adjacency matrix
	 * 
	 * @param aa
	 *            the adjacency matrix
	 * @param i
	 *            the source vertex
	 * @param j
	 *            the destination vertex
	 * @return <code>true</code> iff the two vertices appear in the graph and
	 *         there is an edge from i to j
	 */

	private boolean isAdajcent(int[][] aa, int i, int j) {
		if (i < 0 || i >= aa.length || j < 0 || j >= aa.length)
			return false;
		if (isUnweighted())
			return aa[i][j] != 0;
		else
			return aa[i][j] != Integer.MAX_VALUE;
	}

	/**
	 * Determines whether the graph object can represent an unweighted graph
	 * 
	 * @return <code>true</code> iff every value in the adjacency matrix is
	 *         either 0 or 1
	 */

	public boolean isUnweighted() {
		for (int i = 0; i < n; i++)
			for (int j = i; j < n; j++)
				if (a[i][j] < 0 || a[i][j] > 1)
					return false;
		return true;
	}

	/**
	 * Determines whether the graph object can represent an undirected graph
	 * 
	 * @return <code>true</code> iff the adjacency matrix is symmetric about the
	 *         main diagonal
	 */

	public boolean isUndirected() {
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (a[i][j] != a[j][i])
					return false;
		return true;
	}

	/**
	 * Gets a copy of the adjacency matrix
	 * 
	 * @return a new copy of the array containing the adjacency matrix
	 */

	public int[][] getAdjacencyMatrix() {
		int[][] output = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				output[i][j] = a[i][j];
		return output;
	}

	/**
	 * Solves the all-pairs shortest-paths algorithm using Floyd's algorithm.
	 * Stores path information so that it can be accessed by the getCheapestPath
	 * method
	 * 
	 * @return a matrix giving the cost of the cheapest path between every pair
	 *         of vertices, if the graph is weighted. In the case of an
	 *         unweighted graph the return value is an unweighted graph
	 *         representing accessibility (the existence of a path between every
	 *         two vertices)
	 */

	public int[][] floyd() {
		int[][] d = new int[n][n];
		path = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				d[i][j] = a[i][j];
				path[i][j] = NOT_A_VERTEX;
			}
		if (isUnweighted()) {
			for (int i = 0; i < n; i++)
				d[i][i] = 1;
			allPairsUnweighted(a, d, path);
		} else
			allPairs(d, path);
		return d;
	}

	/**
	 * A helper method for Floyd's algorithm for weighted graphs, that creates
	 * successive copies of the D(k) matrix. It also maintains the path
	 * information that the <code>floyd</code> method needs.
	 * 
	 * @param d
	 *            the matrix D(1), a copy of the adjacency matrix
	 * @param path
	 *            a matrix whose (i,j) value is the highest-numbered vertex on
	 *            the cheapest path so far from i to j. Initially this value is
	 *            the dummy value NOT_A_VERTEX
	 * @return D(n-1), the final output of Floyd's algorithm in the weighted
	 *         case
	 */

	private void allPairs(int[][] d, int[][] path) {
		for (int k = 0; k < n; k++){
			if(k%100 == 0)
			System.out.println(k);
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (d[i][k] != Integer.MAX_VALUE
							&& d[k][j] != Integer.MAX_VALUE
							&& d[i][k] + d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + d[k][j];
						path[i][j] = k;
					}
		}
	}

	/**
	 * A helper method for Floyd's algorithm for unweighted graphs, that creates
	 * successive copies of the D(k) matrix. It also maintains the path
	 * information that the <code>floyd</code> method needs.
	 * 
	 * @param d
	 *            the matrix D(1), a copy of the adjacency matrix
	 * @param path
	 *            a matrix whose (i,j) value is an intermediate vertex on the
	 *            cheapest path so far from i to j. Initially this value is the
	 *            dummy value NOT_A_VERTEX
	 * @return D(n-1), the final output of Floyd's algorithm in the unweighted
	 *         case
	 */
	/*
	 * Note that the new value of d[i][j] could be expressed as d[i][j] |
	 * (d[i][k] & d[k][j])
	 */
	private void allPairsUnweighted(int[][] a, int[][] d, int[][] path) {
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (d[i][j] == 0 && (d[i][k] & d[k][j]) == 1) {
						d[i][j] = 1;
						path[i][j] = k;
					}
	}

	/**
	 * Solves the all-pairs shortest-paths algorithm using an O(n^3 log n)
	 * algorithm that works like Floyd's, but whose successive D(k) matrices
	 * store the cost of the cheapest path of length at least k Stores path
	 * information so that it can be accessed by the getCheapestPath method
	 * Assumes that the graph is weighted
	 * 
	 * @return a matrix giving the cost of the cheapest path between every pair
	 *         of vertices
	 */
	/*
	 * To get the stated time complexity, we consider only values for the
	 * maximum path length k that are powers of 2
	 */

	public int[][] protoFloyd() {
		int[][] d = new int[n][n];
		path = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				d[i][j] = a[i][j];
				path[i][j] = NOT_A_VERTEX;
			}
		protoAllPairs(d, path);
		return d;
	}

	/**
	 * A helper method for the <code>protoFloyd</code> method that creates
	 * successive copies of the D(k) matrix. Here each successor matrix doubles
	 * the value of the maximum path length k. It also maintains the path
	 * information that the <code>protoFloyd</code> method needs.
	 * 
	 * @param d
	 *            the matrix D(1), a copy of the adjacency matrix
	 * @param path
	 *            a matrix whose (i,j) value is an intermediate vertex on the
	 *            cheapest path so far from i to j. Initially this value is the
	 *            dummy value NOT_A_VERTEX
	 * @return D(n-1), the final output of the version of Floyd's algorithm used
	 *         by <code>protoFloyd</code>
	 */

	private void protoAllPairs(int[][] d, int[][] path) {
		int k = 1; // the maximum path length
		while (k < n) {
			System.out.println(k);
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					for (int v = 0; v < n; v++)

					{

						// v is a hypothetical midpoint of the
						// new path of length 2k
						if (d[i][v] != Integer.MAX_VALUE
								&& d[v][j] != Integer.MAX_VALUE
								&& d[i][v] + d[v][j] < d[i][j]) {
							d[i][j] = d[i][v] + d[v][j];
							path[i][j] = v;
						}
					}
			k *= 2;

			System.out.println(k);
		}

	}

	/**
	 * Finds the cheapest path between two given vertices according to the
	 * latest computation of one of the methods <code>floyd</code> or
	 * <code>protoFloyd</code>.
	 * 
	 * @param u
	 *            the source vertex
	 * @param v
	 *            the destination vertex
	 * @return the cheapest path
	 * @throws IllegalArgumentException
	 *             if either vertex fails to exist in the graph
	 */

	public LinkedList<Integer> findCheapestPath(int u, int v) {
		LinkedList<Integer> output = new LinkedList<Integer>();
		if (u < 0 || u >= a.length)
			throw new IllegalArgumentException("vertex " + u
					+ " is not present in the graph");
		if (v < 0 || v >= a.length)
			throw new IllegalArgumentException("vertex " + v
					+ " is not present in the graph");
		int k = path[u][v];
		if (k == NOT_A_VERTEX) {
			output.add(u);
			output.add(v);
		} else {
			LinkedList<Integer> suffix = findCheapestPath(k, v);
			suffix.remove();
			output = findCheapestPath(u, k);
			output.addAll(suffix);
		}
		return output;
	}

	/**
	 * Finds the connected components of the graph (if it's undirected) by
	 * invoking Floyd's algorithm and inspecting the result.
	 * 
	 * @return an array of vertex numbers where the kth value is the number of a
	 *         representative vertex of the component containing vertex k. Each
	 *         component has a unique representative.
	 * @throws IllegalArgumentException
	 *             if the graph is not undirected
	 */

	public int[] findConnectedComponents() {
		if (!isUndirected())
			throw new IllegalArgumentException(
					"the findConnectedComponents method assumes an undirected graph");
		int[] output = new int[n];
		int NO_COMPONENT = -1;
		for (int i = 0; i < n; i++)
			output[i] = NO_COMPONENT;
		int d[][] = floyd();
		for (int i = 0; i < n; i++) {
			if (output[i] == NO_COMPONENT) {
				output[i] = i;
				for (int j = i + 1; j < n; j++)
					if (output[j] == NO_COMPONENT && this.isAdajcent(d, i, j)/*
																			 * d[
																			 * i
																			 * ]
																			 * [
																			 * j
																			 * ]
																			 * !=
																			 * 0
																			 */)
						output[j] = i;
			}
		}
		return output;
	}

}