package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="classpath")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classpath 
{
	@XmlElement(name="classpath-entry")
	private List<ClasspathEntry> classpathEntryList;

	public List<ClasspathEntry> getClasspathEntryList() 
	{
		return classpathEntryList;
	}

	public void setClasspathEntryList(List<ClasspathEntry> classpathEntryList) 
	{
		this.classpathEntryList = classpathEntryList;
	}
	
	
}
