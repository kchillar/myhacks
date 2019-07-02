package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="paths")
@XmlAccessorType(XmlAccessType.FIELD)
public class Paths 
{
	@XmlElementWrapper(name="classpath")
	@XmlElement(name="classpath-entry")
	private List<ClasspathEntry> classPathEntryList;
	
	public List<ClasspathEntry> getClassPathEntryList() 
	{
		return classPathEntryList;
	}

	public void setClassPathEntryList(List<ClasspathEntry> classPathEntryList) 
	{
		this.classPathEntryList = classPathEntryList;
	}

}
