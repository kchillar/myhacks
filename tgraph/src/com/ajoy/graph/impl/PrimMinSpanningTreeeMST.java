package com.ajoy.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.ajoy.graph.Graph;
import com.ajoy.graph.Node; 

/**
 * 
 * @author Kalyana Chillara<br>
 *
 * Problem Statement: Find a path that connects all the nodes in the graph such that, the total distance of the path is minimum<br>
 * 
 * Solution Approach:
 * 1) Initialize a list of node distances, with each node distance as infinity or undefined.<br>
 * 2) Take any node in the graph and use that as current node.<br> 
 * 3) From that current, check the distances of all its neighbors.<br>
 * 4) If the distances to a neighbor is less than the distances held in the distances list for the neighbor node, update the distance to newer value in the list.<br>
 * 5) Mark the current node as visited.<br>
 * 6) From the distances list, of the ones that are not marked visited yet, pick the node with smallest distance value as the next current.<br> 
 * 7) If a valid current is found, repeat the steps 3) to 6) for the new current.<br>
 * 8) If no such current is found, terminate the process.<br> 
 * 
 */
public class PrimMinSpanningTreeeMST<T extends Comparable<T>> 
{
	private Graph<T> graph;
	private boolean[] visitedFlag;
	private ArrayList<T> distances;
	private TreeSet<Node<T>> sortedNodes;
	private int current;
	
	private int[] path;
	
	public PrimMinSpanningTreeeMST()
	{		
	}
	
	public PrimMinSpanningTreeeMST(Graph<T> graph)
	{	
		this.graph = graph;	
		createLists();		
	}

	private void createLists()
	{
		visitedFlag = new boolean[graph.getOrder()];
		distances = new ArrayList<T>(graph.getOrder());
		path = new int[graph.getOrder()];
	}
	
	private void doSetup()	
	{		
		for(int i=0; i<graph.getOrder(); i++)			
			visitedFlag[i] = false;
		
		for(int i=0; i<graph.getOrder(); i++)			
			distances.add(null);

		for(int i=0; i<graph.getOrder(); i++)			
			path[i] = -1;
		
		sortedNodes = new TreeSet<Node<T>>();
	}
	
	/**
	 * To compute repeatedly to get min distances between different nodes in a graph.<br>
	 */
	public List<Node<T>> computeMST()	
	{	
		doSetup();
		current = 0;	
		int start = 0;
			
		do
		{								
			int i = current;
			path[start++]=current;				
			List<Node<T>> adjNodeList = graph.getAdjacentNodesFor(i);	
			
			if(adjNodeList != null)
			{
				System.out.print("current= "+current+" -Neighbours[]-->");
				for(Node<T> node : adjNodeList)
				{
					if(!visitedFlag[node.getId()])																
						checkAndStoreIfMin(node);
					
					if(!visitedFlag[node.getId()])
						System.out.print(" (id:"+node.getId()+", ev:"+node.getValue()+", "+visitedFlag[node.getId()]+") ");
					else
						System.out.print(" (id:"+node.getId()+", v:"+node.getValue()+", "+visitedFlag[node.getId()]+") ");
				}
				System.out.println();
			}
					
			visitedFlag[i] = true;
			System.out.println("visitedFlag["+i+"] = "+visitedFlag[i]);
			current = getNextCurrent();
		}	
		while(current > -1);
						
		List<Node<T>> list = new ArrayList<Node<T>>(path.length);
		
		for(int i=0; i<path.length; i++)		
		{
			System.out.println("Path: "+path[i]+" distances["+path[i]+"] = "+distances.get(path[i]));
			Node<T> node = new NodeImpl<T>(path[i]);
			node.setValue(distances.get(path[i]));
			list.add(node);
		}
		return list;
	}

	public void checkAndStoreIfMin(Node<T> node)
	{
		if((distances.get(node.getId()) == null) || (distances.get(node.getId()).compareTo(node.getValue()) > 0))
		{
			distances.set(node.getId(), node.getValue());		
			sortedNodes.add(node);
		}
	}
	
	public int getNextCurrent()
	{
		int nodeId = -1;					
		while(!sortedNodes.isEmpty())
		{
			Node<T> node = sortedNodes.first();			
			sortedNodes.remove(node);			
			if(!visitedFlag[node.getId()])
			{
				nodeId = node.getId();
				break;
			}
		}
		System.out.println("getNextCurrent() = "+nodeId+"");
		return nodeId;
	}
}
