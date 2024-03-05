package com.azy.graph.impl;

import java.util.ArrayList;

import com.azy.graph.Node;

public class NodeImpl<T extends Comparable<T>> implements Node<T>
{
	private int id;
	private T value;
	private ArrayList<Node<T>> adjascentList;
	
	NodeImpl(int id)
	{
		setId(id);
		setValue(value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ArrayList<Node<T>> getAdjascentNodes() {
		return adjascentList;
	}

	public void addNode(Node<T> node)
	{
		if(adjascentList == null)
			adjascentList = new ArrayList<>();
		adjascentList.add(node);
	}
	
	public String toString()
	{
		StringBuilder buff = new StringBuilder();
		buff.append("{id:\""+getId()+"\", value:\""+getValue()+"\" adjascentList:[");
		
		if(getAdjascentNodes()!=null)
		{
			for(Node<T> node: getAdjascentNodes())
			buff.append("\n          "+node.toString()+", ");
		}
		
		buff.append("]}");
		
		return buff.toString();
	}

	@Override
	public int compareTo(Node<T> to) 
	{
		return this.getValue().compareTo(to.getValue());
	}

	
	
}
