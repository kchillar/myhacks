package com.ajoy.test;

public class Log 
{
	private String name;
	
	public Log(Class aClass)
	{
		name = aClass.getName();
	}
	
	private final void out(String msg)
	{
		System.out.println("["+name+"] "+msg);
	}
	
	public final void info(String msg)
	{
		out(msg);
	}
	
	public final void debug(String msg)
	{
		out(msg);		
	}
	
	public final void error(String msg)
	{
		out(msg);
	}
	
	public final void error(String msg, Exception exp)
	{		
		out(msg);
		exp.printStackTrace();
	}
	
}
