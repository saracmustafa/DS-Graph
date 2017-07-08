import java.util.*;

/*
 * Developer: Mustafa Saraç
 * Data Structure: Graph (implemented as Adjacency Matrix)
 *
 * --- IMPORTANT ---
 * 
 * This code was implemented by making changes to the starter code given by the instructor
 * for COMP 202 (Algorithms and Data Structures, Koç University) course project. 
 */

public class Graph {
	
	private int vertice;
	private int[][] adjacencyMatrix;
	
	// create an empty graph with V vertices
	public Graph(int V) {
		this.vertice = V;
		adjacencyMatrix = new int[V][V];
		
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				adjacencyMatrix[i][j] = 0;
			}
		}
	}
	
	// return the number of vertices
	public int getNumVertices() {
		return this.vertice;
	}
	
	// return the number of edges
	public int getNumEdges() {
		int edgeNumber = 0;
		
		for(int i=0; i<this.vertice; i++){
			for(int j=0; j<this.vertice; j++){
				if(adjacencyMatrix[i][j] == 1){
					edgeNumber++; 
				}
			}
		}

		edgeNumber = edgeNumber/2; // in order to prevent counting the same edge twice.	
		// in order to change it to DIRECTED GRAPH
		// you should delete this line.
		
		return edgeNumber;
	}
	
	// add a new vertex to the graph
	public void addVertex() {
		int[][] tempArray = new int[this.vertice][this.vertice];
		
		for(int i=0; i<this.vertice; i++){
			for(int j=0; j<this.vertice; j++){
				tempArray[i][j] = adjacencyMatrix[i][j];
				}
			}
		
		this.vertice++;
		adjacencyMatrix = new int[this.vertice][this.vertice];
		
		for(int i=0; i<this.vertice - 1; i++){
			for(int j=0; j<this.vertice - 1; j++){
				adjacencyMatrix[i][j] = tempArray[i][j];
				}
			}
		
		for(int k=0; k<this.vertice; k++){
			adjacencyMatrix[this.vertice-1][k] = 0;
		}
	}
	
	// add a new edge between vertices v and w
	public void addEdge(int v, int w) {
		adjacencyMatrix[v][w] = 1;
		adjacencyMatrix[w][v] = 1; 		 // in order to change it to DIRECTED GRAPH
										 // you should delete this line.
	}
	
	// remove the edge between vertices v and w
	public void removeEdge(int v, int w) {
		adjacencyMatrix[v][w] = 0;
		adjacencyMatrix[w][v] = 0;		// in order to change it to DIRECTED GRAPH
										// you should delete this line.
	}
	
	// return the list of vertices which are adjacent to vertex v
	public Iterable<Integer> getNeighbors(int v) {
		ArrayList<Integer> neighborOfVertice = new ArrayList<Integer>();

		for(int i=0; i<this.vertice; i++){
			if(adjacencyMatrix[v][i] == 1){
				neighborOfVertice.add(i);
			}
		}	
	
		return neighborOfVertice;
	}
	
	// return the list of the degrees of the vertices in this graph in sorted order (from largest to smallest)
	public List<Integer> degreeSequence() {
		List<Integer> degreeSequence = new ArrayList<Integer>();
		List<Integer> temp = new ArrayList<Integer>();
		int size;
		
		for(int i=0; i<this.vertice; i++){
			size = ((ArrayList<Integer>) this.getNeighbors(i)).size();
			temp.add(size);
			degreeSequence.add(size);
		}
		Collections.sort(degreeSequence);
		Collections.reverse(degreeSequence);
		
		return degreeSequence;
	}

	// return the list of the vertices which are at distance 2 away from the vertex v
	public List<Integer> verticesWithinDistance2(int v) {
	List<Integer> distance2List = new ArrayList<Integer>();
	
	if(this.getNumEdges() == 0){
		distance2List.add(v);
	} else {
		distance2List.add(v);
		for(Integer j: this.getNeighbors(v)){
			distance2List.add(j);
			for(Integer k: this.getNeighbors(j)){
				distance2List.add(k);
			}
		}
	}
	return distance2List;
	}

	// return the string representation of the graph in adjacency matrix format
	public String showAsMatrix() {
		String string = "";
		for(int i=0; i<this.vertice; i++){
			for(int j=0; j<this.vertice; j++){
				string = string.concat(adjacencyMatrix[i][j]  + "-");
			}
			string = string.concat("\n");
			if(string.isEmpty()){
				string = string.concat("-");
			}
		}
		return string;
	}
	
	// return the string representation of the graph in adjacency list format
	public String showAsAdjacencyList() {
		String string = "";
		
		for(int i=0; i<this.vertice; i++){
			for(Integer j: this.getNeighbors(i)){
				string = string.concat(j + "-");
			}
			if(((ArrayList<Integer>) this.getNeighbors(i)).size() == 0){
				string = string.concat("-");
				if(i == this.vertice){
					break;
				}
			}
			string = string.concat("\n");
		}
		return string;
	}
	
}