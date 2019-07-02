package com.ajoy.graph.impl;


import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Stack;

import com.ajoy.graph.Graph;
import com.ajoy.graph.Node;
import com.ajoy.graph.builder.EdgeInfo;
import com.ajoy.graph.builder.GraphInfo;

public class Test 
{
	
	private static Graph createGraph() throws Exception
	{
		FileInputStream fis = new FileInputStream(new File("/Users/kalyanc/workspace/telugukeyboard/tgraph/conf/graph-01.xml"));
		GraphInfo info = GraphInfo.getFromStream(fis);
		//System.out.println(info.toString());
		
		Graph<Integer> graph = new ArrayListBackedGraph<Integer>(info.getVertices());
				
		for(EdgeInfo eInfo: info.getEdgeInfoList())					
			graph.addEdge(Integer.parseInt(eInfo.getValue()), eInfo.getFrom(), eInfo.getTo());			

		return graph;
	}
	
	public static void main(String args[]) throws Exception
	{
		Graph graph = createGraph();		
		//minDistance(graph);
		mst(graph);
		
	}
	
	private static void mst(Graph graph)
	{
		PrimMinSpanningTreeeMST<Integer> mst = new PrimMinSpanningTreeeMST<Integer>(graph);		
		List<Node<Integer>> list = mst.computeMST();
		
		if(list != null)
		{
			System.out.println("MST: ");
			for(Node<Integer> node: list)
				System.out.println(node.getId()+" --> "+node.getValue());
		}
	}	
	
	private static void minDistance(Graph graph)
	{
		DykstaMinDistance dykmd = new DykstaMinDistance(graph);
		int start = 2;
		int end = 4;
		
		int minDist = dykmd.computeMinDistance(start, end);		
		System.out.println("Min distance between ("+start+", "+end+") = "+minDist);		
		Stack<Integer> path = dykmd.getAllNodesInComputedPath();		
		if(path != null)
		{
			while(!path.isEmpty())
				System.out.print(" --> "+path.pop());
			System.out.println();
		}
		else
		{
			System.out.println("No path");
		}		

	}
	
}
