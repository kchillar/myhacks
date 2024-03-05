package com.azy.graph.algo;

import java.util.Stack;

import com.azy.graph.Graph2;

public class MinSpanningTreeMST 
{
	private static final int Infinity = 2000000;
	private Graph2<Integer> graph;
	private boolean[] visitedList;
	private int[] distanceList;
	private int[] parentList; //Need this if we have to print the nodes in the computed path
	private int end;	
	private int current; 	
	
	public MinSpanningTreeMST()
	{		
	}
	

	public MinSpanningTreeMST(Graph2<Integer> graph)
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


