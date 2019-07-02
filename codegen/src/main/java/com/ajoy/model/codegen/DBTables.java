package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="db-tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBTables 
{
	@XmlAttribute(name="db-name")
	private String dbName;
	
	@XmlElement(name="db-table")
	private List<DBTable> tableList;

	public List<DBTable> getTableList() 
	{
		return tableList;
	}

	public void setTableList(List<DBTable> tableList) 
	{
		this.tableList = tableList;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	
		
}
