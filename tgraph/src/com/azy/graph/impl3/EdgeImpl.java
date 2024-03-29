package com.azy.graph.impl3;

import com.azy.graph.Edge;

public class EdgeImpl<T extends Comparable<T>> implements Edge<T>
{
	private T value;
	
	public EdgeImpl(T val)
	{
		value = val;
	}
	
	public T getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return value.toString();
	}	
}
