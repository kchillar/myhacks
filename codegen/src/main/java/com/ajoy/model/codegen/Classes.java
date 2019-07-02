package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="classes")
public class Classes 
{
	@XmlElement(name="class")
	private List<String> classInfoList;

	public List<String> getClassInfoList() {
		return classInfoList;
	}

	public void setClassInfoList(List<String> classInfoList) {
		this.classInfoList = classInfoList;
	}
	
	
}
