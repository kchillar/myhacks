package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="class")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassInfo 
{
	@XmlAttribute(name="classname")
	private String classname;
	
	@XmlElementRef(name="field")
	private List<FieldInfo> fieldList; 

	
	public String getName() {
		return classname;
	}
	public void setName(String name) 
	{
		this.classname = name;
	}

	public List<FieldInfo> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}

	

}
