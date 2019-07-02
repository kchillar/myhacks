package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="db-table")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBTable 
{
	@XmlElement(name="db-name")
	private String dbName;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlElement(name="db-column")
	private List<DBColumn> columnList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DBColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<DBColumn> columnList) {
		this.columnList = columnList;
	}
	
	public String toString()
	{
		return getName();
	}
}
