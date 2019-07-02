package com.ajoy.graph;


import java.util.List;

public interface Node<T extends Comparable<T>> extends Comparable<Node<T>>
{
	public int getId();
	public T getValue();
	public void setValue(T value);
	public void addNode(Node<T> aNode);
	public List<Node<T>> getAdjascentNodes();
	
}
