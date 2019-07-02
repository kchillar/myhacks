package com.ajoy.graph;

public interface Graph2<T extends Comparable<T>>
{
	public int getOrder();
	public void addEdge(Edge<T> edge, int from, int to);
	public int[][] getIncidentNodes(Edge<T> edge);

	public boolean areNodesAdjacent(int node1, int node2);
	public int[] getAdjacentNodesFor(int node);
	public Edge<T> getEdge(int node1, int node2);
	public void showAdjacencyMatrix();
	public void showEdgeValues();
	
}
