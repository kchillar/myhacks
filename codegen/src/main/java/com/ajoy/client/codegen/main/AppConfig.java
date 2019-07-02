package com.ajoy.client.codegen.main;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author kalyanc
 *
 */
@XmlRootElement(name="app-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppConfig 
{
	@XmlElement(name="data-dir")
	private String dataDir;
	
	@XmlElement(name="view-config")
	private String viewConfig;

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getViewConfig() {
		return viewConfig;
	}

	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}

	public String toString()
	{
		StringBuilder buff = new StringBuilder();
		buff.append("<app-config>");
		//buff.append(" <>"+ +"</>");
		buff.append(" <data-dir>"+getDataDir()+"</data-dir>");
		buff.append(" <view-config>"+getViewConfig()+"</view-config>");
		buff.append("</app-config>");
		return buff.toString();
	}
	
	public static AppConfig getObjectFromStream(InputStream inp) throws JAXBException
    {
        JAXBContext jc = JAXBContext.newInstance(AppConfig.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();        
        AppConfig object = (AppConfig) unmarshaller.unmarshal(inp);
        return object;
    }
	
	
}
