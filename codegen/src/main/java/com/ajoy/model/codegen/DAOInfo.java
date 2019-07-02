package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dao-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class DAOInfo 
{
	@XmlAttribute(name="name")
	private String name;
	
	@XmlElement(name="dao-method-info")
	private List<DAOMethodInfo> methodList;

	
	public DAOInfo()
	{		
	}
	
	public DAOInfo(String name)
	{
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public List<DAOMethodInfo> getMethodList() 
	{
		return methodList;
	}

	public void setMethodList(List<DAOMethodInfo> methodList) 
	{
		this.methodList = methodList;
	}
	
	
	public String toString()
	{
		return getName();
	}
}
