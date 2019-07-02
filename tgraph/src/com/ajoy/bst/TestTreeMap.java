package com.ajoy.bst;

import java.util.TreeMap;

public class TestTreeMap 
{
	public static void main(String[] args)
	{
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		tm.put(0, 10);
		tm.put(0,  11);
		
		for(Integer val: tm.values())
		{
			System.out.println("val :"+val);
		}
	}
}
