package com.ajoy.graph.impl;


import java.util.ArrayList;
import java.util.List;

import com.ajoy.graph.Graph;
import com.ajoy.graph.Node;


/**
 * 
 * @author Kalyana Chillara <br>
 *
 * @param <T>
 */
public class ArrayListBackedGraph<T extends Comparable<T>> implements Graph<T>
{	
	private int order;
	private ArrayList<Node<T>> nodeList;
		
	private static void log(String msg)
	{
		System.out.println(msg);
	}
	
	public ArrayListBackedGraph(int order)
	{
		log("Order "+order);
		this.order = order;
		nodeList = new ArrayList<Node<T>>(order);
		for(int i=0; i< order; i++)
			nodeList.add(new NodeImpl<T>(i));
	}
	
	public Node<T> getNode(int nodeId)
	{
		return nodeList.get(nodeId);
	}
	
	public void addEdge(T edge, int from, int to)
	{
		addEdge(edge, from, to, true);
	}
	
	public void addEdge(T edge, int from, int to, boolean symmetric)
	{
		Node<T> node = new NodeImpl<T>(to);
		node.setValue(edge);				
		nodeList.get(from).addNode(node);
		
		if(symmetric)
		{
			node = new NodeImpl<T>(from);
			node.setValue(edge);				
			nodeList.get(to).addNode(node);
		}
	}
	
	public List<Node<T>> getAdjacentNodesFor(int nodeId)
	{
		if(nodeId < 0 || nodeId > order-1 )
			throw new IllegalStateException("Node: "+nodeId+" is not in the graph. Graph order is : "+order);
		
		return nodeList.get(nodeId).getAdjascentNodes();
	}
		
	public int getOrder()
	{
		return order;
	}
	
	public String toString()
	{
		StringBuilder buff = new StringBuilder();
		
		buff.append("{\n order:\""+getOrder()+"\",\n nodeList:[");
		
		if(nodeList!=null)
		{
			buff.append("\n");
			for(Node<T> node: nodeList)
				buff.append(node.toString()+",\n");
		}
		
		buff.append("\n  ]\n}");
		return buff.toString();
	}
}

