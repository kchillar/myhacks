package com.ajoy.model.codegen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="classpath-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClasspathEntry
{
	@XmlAttribute(name="path")
	private String path = ".";
	@XmlAttribute(name="entry-type")
	private int type = 0;
	
	public ClasspathEntry()
	{		
	}

	public ClasspathEntry(String path)
	{		
		this.path = path;
	}

	
	public ClasspathEntry(String path, int type)
	{		
		this.path = path;
		this.type = type;		
	}
	
	public boolean isJar()
	{
		if(type == 1)
			return true;
		else
			return false;
	}
	
	public int getType()
	{
		return type;
	}
	
	public String getTypeDesc()
	{
		if(getType() == 0)
			return "Dir";
		else
			return "Jar";
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String toString()
	{
		return "path: "+path+" type: "+type;
	}
}
