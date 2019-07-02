package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dao-method-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class DAOMethodInfo 
{
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="type")
	private String sqlType;

	@XmlElement(name="sql")
	private String sql;

	@XmlElement(name="dao-name")
	private String daoName;
		
	@XmlElement(name="db-table")
	private List<DBTable> tableList;
		
	@XmlElement(name="input")
	private List<FieldInfo> inputList;
	
	//@XmlElementWrapper(name="output")
	//@XmlElement(name="field")
	@XmlElement(name="output")
	private FieldInfo output;
	
	public DAOMethodInfo()
	{		
	}

	public DAOMethodInfo(String name)
	{		
		setName(name);
	}

	public String toString()
	{
		return getName()+" "+ getSqlType();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String methodType) {
		this.sqlType = methodType;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<DBTable> getTableList() {
		return tableList;
	}

	public void setTableList(List<DBTable> tableList) {
		this.tableList = tableList;
	}

	public List<FieldInfo> getInputList() {
		return inputList;
	}

	public void setInputList(List<FieldInfo> inputList) {
		this.inputList = inputList;
	}

	public FieldInfo getOutput() {
		return output;
	}

	public void setOutput(FieldInfo output) {
		this.output = output;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	
	
	
	
}
