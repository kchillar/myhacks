package com.azy.graph.algo;

import java.util.Stack;

import com.azy.graph.Graph2;

/**
 * 
 * @author Kalyan Chillara
 *
 *
 * Problem Statement: In a Graph, find the shortest path between any two given nodes.<br>
 *  
 *  Solution By: Dykstras Minimum Distance Algorithm<br>
 * 
 *  Solution Summary:<br>
 *  1) Initially assume all nodes in the graph are at infinite distance from the start node.<br>
 *  2) The start node itself is at zero distance from itself. So set distance as zero for it.<br>
 *  3) Start visiting each neighbor of the start node and re-assign the a distance to the visited node (from start node)<br>
 *  4) Note that nodes that are not connected to start, will still have infinite distance.<br>
 *  5) After the first pass, mark the start as visited and the pick the node that is not yet visited and at minimum distance, as next start node.<br>
 *  6) If the next start node is a valid node, repeat 3), 4) and 5) 
 *  7) If the next start node is not valid node, termination condition is reached: 
 *     a) If the end is mark as visited, return the distance of end<br>.
 *     b) If the end node is not marked as visited, there is a no-connectivity between start and end nodes in the given graph, return a sentinel value.<br>
 *
 * 
 *  Implementation:<br>
 * 
 *  1) Create a list of visited booleans with size same as the order of the graph. The initial value is false for every node.<br>
 *  2) Create a list of distances with size same as the order of the graph. The initial distance for each node is set to infinity.<br>
 *  3) Set variable, current to start node, and in the distances list, set the distance for the start node as 0.<br>
 *  4) For the current node, Get the neighbors from the graph. 
 *  5) For each neighbor node, if it is NOT already visited: <br>
 *     a) Compute new distance as sum of distance of the current node and distance of neighbor from the current node.<br>
 *     b) If the computed new distance is less than the current value of the distance for the neighbor (in distances list),  
 *       update the value of the distance for that neighbor node (in the distances list) with the new computed distances.<br>
 *  6) After all the neighbors of the current are completed, set visited as 'true' in the visited list for the current.<br>  *  
 *  7) From the nodes that are not marked yet as visited, get the node that has minimum distance value. If such valid node is found, set it as current and repeat from steps 4).<br>
 *  8) If no such node found terminate the computation.<br> *  
 *     a) If the end is marked visited, distance[end] will be the minimum distance.<br>
 *     b) if the end is marked unvisited, there is no connection between start and end, return -1 <br>
 *  
 * 
 */
public class DykstraMinDistance 
{	
	private static final int Infinity = 2000000;
	private Graph2<Integer> graph;
	private boolean[] visitedList;
	private int[] distanceList;
	private int[] parentList; //Need this if we have to print the nodes in the computed path
	private int end;	
	private int current; 	
	

	public DykstraMinDistance()
	{		
	}
	

	public DykstraMinDistance(Graph2<Integer> graph)
	{	
		this.graph = graph;	
		createLists();		
	}

	private void createLists()
	{
		visitedList = new boolean[graph.getOrder()];
		distanceList = new int[graph.getOrder()];
		parentList = new int[graph.getOrder()];
	}
	
	private void doSetup(int start, int end)
	{		
		this.end = end;

		for(int i=0; i<graph.getOrder(); i++)							
			parentList[i] = -1;		
				
		for(int i=0; i<graph.getOrder(); i++)							
			distanceList[i] = Infinity;		

		for(int i=0; i<graph.getOrder(); i++)							
			visitedList[i] = false;		

		distanceList[start] = 0;
	}
	
	public int computeMinDistance(Graph2<Integer> graph, int start, int end)	
	{
		this.graph = graph;
		createLists();
		return computeMinDistance(start, end);
	}
	
	/**
	 * To compute repeatedly to get min distances between different nodes in a graph.<br>
	 */
	public int computeMinDistance(int start, int end)	
	{
		doSetup(start, end);		
		int newCurr = start;		
		do
		{					
			current = newCurr;			
			int[] adjNodeList = graph.getAdjacentNodesFor(current);					
			if(adjNodeList != null)
			{
				for(int to : adjNodeList)
				{
					if(!visitedList[to])
					{																									
						int val = distanceList[current] + graph.getEdge(current, to).getValue().intValue() ; 
						if(val < distanceList[to])	
						{
							distanceList[to] =  val ;
							parentList[to] = current;
						}
					}
				}
			}						
			visitedList[current] = true;			
			newCurr = getMinNode();	
		}		
		while( (newCurr>=0) &&  (!visitedList[end]));
		
		if(visitedList[end])
			return distanceList[current];
		else
			return -1;
	}
				
	public Stack<Integer> getAllNodesInComputedPath()
	{
		Stack<Integer> path = new Stack<Integer>();		
		if(visitedList[end])
		{			
			int idx = end;	
			do	
			{
				path.push(idx);
			}
			while( (idx = parentList[idx]) > -1);
		}
		return path;
	}
	
	private int getMinNode()
	{
		int min = Infinity;
		int nextCurr = -1;
		
		for(int i = 0; i<distanceList.length; i++)
		{
			if(!visitedList[i])
				if(distanceList[i] < min)
				{
					min = distanceList[i];
					nextCurr = i;
				}
		}		
		return nextCurr;
	}
	
	public String displayArrayString(int[] arr)
	{
		if (arr == null || arr.length == 0)
			return "[]";				
		StringBuilder buff = new StringBuilder();
		buff.append("[");
		for(int val: arr)
			buff.append(val+", ");
		buff.append("]");
		return buff.toString();
	}
	
	private static void log(String msg)
	{
		System.out.println(msg);
	}
}
