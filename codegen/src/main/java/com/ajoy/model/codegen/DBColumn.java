package com.ajoy.model.codegen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="db-column")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBColumn 
{
	public static final String StringDataType = "varchar";
	public static final String IntDataType = "int";
	public static final String LongDataType = "number";
	public static final String BooleanDataType = "boolean";
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="data-type")
	private String dataType;
	
	@XmlAttribute(name="java-type")
	private String javaType;

	public DBColumn()
	{		
	}
	
	public DBColumn(String name, String dataType)	
	{		
		setName(name);
		setDataType(dataType);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	
	
}
