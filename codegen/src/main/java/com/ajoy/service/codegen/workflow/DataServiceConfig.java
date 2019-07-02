package com.ajoy.service.codegen.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="data-service-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataServiceConfig 
{
	@XmlElement(name="base-dir")
	private String baseDir = "./data";
	
	@XmlElement(name="profiles-filename")
	private String profilesFilename = "profiles.xml";
	
	@XmlElement(name="databases-filename")
	private String databasesFilename = "databases.xml";
	
	@XmlElement(name="classpath-filename")
	private String classpathFilename = "classpath.xml";

	@XmlElement(name="daos-filename")
	private String daosFilename = "daos.xml";

	
	public String getBaseDir() 
	{
		return baseDir;
	}

	public void setBaseDir(String baseDir) 
	{
		this.baseDir = baseDir;
	}

	public String getProfilesFilename() {
		return profilesFilename;
	}

	public void setProfilesFilename(String profilesFilename) {
		this.profilesFilename = profilesFilename;
	}

	public String getDatabasesFilename() {
		return databasesFilename;
	}

	public void setDatabasesFilename(String databasesFilename) {
		this.databasesFilename = databasesFilename;
	}

	public String getClasspathFilename() {
		return classpathFilename;
	}

	public void setClasspathFilename(String classpathFilename) {
		this.classpathFilename = classpathFilename;
	}

	public String getDaosFilename() {
		return daosFilename;
	}

	public void setDaosFilename(String daosFilename) {
		this.daosFilename = daosFilename;
	}

	
	
}
