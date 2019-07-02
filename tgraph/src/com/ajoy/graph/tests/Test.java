package com.ajoy.graph.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Stack;

import com.ajoy.graph.Edge;
import com.ajoy.graph.Graph2;
import com.ajoy.graph.algo.DykstraMinDistance;
import com.ajoy.graph.builder.EdgeInfo;
import com.ajoy.graph.builder.GraphInfo;
import com.ajoy.graph.impl3.ArrayBackedGraph;
import com.ajoy.graph.impl3.EdgeImpl;

public class Test 
{
	
	private static Graph2 createGraph() throws Exception
	{
		FileInputStream fis = new FileInputStream(new File("/Users/kalyanc/workspace/telugukeyboard/tgraph/conf/graph-01.xml"));
		GraphInfo info = GraphInfo.getFromStream(fis);
		//System.out.println(info.toString());
		
		Graph2<Integer> graph = new ArrayBackedGraph<Integer>(info.getVertices());
		
		Edge<Integer> last = null;
		
		for(EdgeInfo eInfo: info.getEdgeInfoList())
		{
			Edge<Integer> edge = new EdgeImpl<Integer>(Integer.parseInt(eInfo.getValue()));			
			graph.addEdge(edge, eInfo.getFrom(), eInfo.getTo());			
		}

		return graph;
	}
	
	public static void main(String args[]) throws Exception
	{
		Graph2 graph = createGraph();
		
		//graph.showAdjacencyMatrix();		
		graph.showEdgeValues();
				
		DykstraMinDistance dykmd = new DykstraMinDistance(graph);
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
