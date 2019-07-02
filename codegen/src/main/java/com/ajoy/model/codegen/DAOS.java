package com.ajoy.model.codegen;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="daos")
@XmlAccessorType(XmlAccessType.FIELD)
public class DAOS 
{
	@XmlElement(name="dao-info")
	private List<DAOInfo> daoList;

	public List<DAOInfo> getDaoList() 
	{
		return daoList;
	}

	public void setDaoList(List<DAOInfo> daoList) 
	{
		this.daoList = daoList;
	}
	
	
}
