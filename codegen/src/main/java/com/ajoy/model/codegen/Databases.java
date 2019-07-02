package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="databases")
@XmlAccessorType(XmlAccessType.FIELD)
public class Databases extends BaseInfo
{		
	@XmlElement(name="db-info")
	private List<DBInfo> dbInfoList;
	
	public Databases()
	{	
	}

	public List<DBInfo> getDbInfoList() {
		return dbInfoList;
	}

	public void setDbInfoList(List<DBInfo> dbInfoList) {
		this.dbInfoList = dbInfoList;
	}

	

}
