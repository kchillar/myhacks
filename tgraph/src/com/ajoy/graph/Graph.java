package com.ajoy.graph;

import java.util.List;

public interface Graph<T extends Comparable<T>>
{
	public int getOrder();
	public Node<T> getNode(int nodeId);	
	public List<Node<T>> getAdjacentNodesFor(int nodeId);
	public void addEdge(T edge, int from, int to);
	
	
	
}
