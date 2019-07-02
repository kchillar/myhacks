package com.ajoy.graph.impl3;

import com.ajoy.graph.Edge;
import com.ajoy.graph.Graph2;

/**
 * 
 * @author kalyanc
 *
 * @param <T>
 */
public class ArrayBackedGraph<T extends Comparable<T>> implements Graph2<T> 
{	
	private int order;
	private boolean[][] adjacencyMatrix;
	private Edge<T>[][] edgeValues;
	
	public ArrayBackedGraph(int order)
	{
		this.order = order;		
		if(order > 0)
		{
			adjacencyMatrix = new boolean[order][order];
			edgeValues = new Edge[order][order];											
		}		
	}
	
	public void addEdge(Edge<T> edge, int from, int to)
	{
		adjacencyMatrix[from][to] = true;
		adjacencyMatrix[to][from] = true;
		edgeValues[from][to] = edge;
		edgeValues[to][from] = edge;	
	}
	
	/**
	 * Used arrays for return values as it is most efficient<br>
	 */
	public int[][] getIncidentNodes(Edge<T> edge)
	{
		int size = 0;		
		for(int i=0; i<order; i++)
			for(int j=0; j<order; j++)
			{
				if(edgeValues[i][j] != null && edgeValues[i][j].getValue() !=null)
					if(edgeValues[i][j].getValue().compareTo(edge.getValue()) == 0)
						size++;
			}
		
		if(size == 0)
			return null;	
		int[][] xy = new int[size][2];		
		size = 0;
		for(int i=0; i<order; i++)
			for(int j=0; j<order; j++)
			{
				if(edgeValues[i][j] != null && edgeValues[i][j].getValue() !=null)
					if(edgeValues[i][j].getValue().compareTo(edge.getValue()) == 0)
					{
						xy[size][0] = i;
						xy[size][1] = j;
						size++;
					}				
			}		
		return xy;
	}
	
	public Edge<T> getEdge(int node1, int node2)
	{
		return edgeValues[node1][node2];
	}
	
	public boolean areNodesAdjacent(int node1, int node2)
	{
		return adjacencyMatrix[node1][node2];
	}
	
	public int[] getAdjacentNodesFor(int node)
	{
		if(node < 0 || node > order-1 )
			throw new IllegalStateException("Node: "+node+" is not in the graph. Graph order is : "+order);		
		int size = 0;		
		for(int j=0; j<order; j++)		
			if(adjacencyMatrix[node][j])
				size++;		
		if(size == 0)
			return null;			
		int[]  nodes = new int[size];
		int k = 0;		
		for(int j=0; j<order; j++)	
			if(adjacencyMatrix[node][j])
			nodes[k++]=j;		
		return nodes;
	}
	
	public void showAdjacencyMatrix()
	{
		System.out.println("Adjacency Matrix: ");		
		if(adjacencyMatrix != null)
			for(int i=0; i< order; i++)
			{
				for(int j=0; j< order; j++)
					System.out.print(adjacencyMatrix[i][j]+" ");				
				System.out.println();
			}
	}
	
	public void showEdgeValues()
	{
		System.out.println("Edge Values: ");		
		if(edgeValues != null)
			for(int i=0; i< order; i++)
			{
				for(int j=0; j< order; j++)
					if(edgeValues[i][j]!=null)
						System.out.println("from: "+i+" to: "+j+" -> "+edgeValues[i][j]);								
			}
	}
	
	public int getOrder()
	{
		return order;
	}
}
