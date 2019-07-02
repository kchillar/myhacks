package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="field")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldInfo
{
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="classname")
	private String classname;
	
	@XmlElementRef(name="field")
	private List<FieldInfo> fieldList;
	
	@XmlAttribute(name="user-defined")
	private boolean userDefined = false;
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getClassname() 
	{
		return classname;
	}
	
	public boolean isUserDefined() {
		return userDefined;
	}
	public void setUserDefined(boolean userDefined) {
		this.userDefined = userDefined;
	}
	public void setClassname(String classname) 
	{
		this.classname = classname;
	}
	public List<FieldInfo> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}
	
	public String toString()
	{
		if(getName() != null)
			return getClassname()+":"+getName();
		else
			return getClassname()+":";
	}
	
}
