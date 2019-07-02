package com.ajoy.model.codegen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="db-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBInfo extends BaseInfo
{
	public static final String DefaultDBName = "A Logical Name";
	public static final String DefaultDBURL = "jdbc:mysql://IP:PORT/schema";
	public static final String DefaultDBUSER = "schema";
	public static final String DefaultDBPASS = "schema";
	public static final String DefaultDriverClassname = "com.mysql.jdbc.Driver";
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1389180406892937328L;
		
	
	@XmlAttribute(name="name")
	private String name = DefaultDBName;
	@XmlElement(name="url")
	private String url = DefaultDBURL;
	@XmlElement(name="user")
	private String user = DefaultDBUSER;
	@XmlElement(name="pass")
	private String pass = DefaultDBPASS;
	@XmlElement(name="driver-class-name")
	private String driverClassName = DefaultDriverClassname;
	
	@XmlAttribute(name="is-selected")
	private boolean isSelected = false;
	
	public String toString()
	{
		return "-- "+getName()+" --";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


}
