/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajoy.client.base.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kalyanc
 */
@XmlRootElement(name="view-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class ViewConfig 
{
	private String id;
	
    @XmlAttribute(name="name")
    private String name;
    
    @XmlAttribute(name="component-class")
    private String componentClass;
    
    @XmlAttribute(name="view-class")
    private String viewBuilderClassname;
    
    @XmlAttribute(name="fxml")
    private String fxml;
    
    @XmlAttribute(name="is-selected")
    private boolean isSelected = false;
    
    @XmlElementRef(name="view-config")
    private List<ViewConfig> childViewConfigList;

    
    public String toString()
    {
    	StringBuilder buff = new StringBuilder();    	
    	buff.append("name: "+name+" fxml: "+fxml+" className:"+viewBuilderClassname);
    	return buff.toString();
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the viewBuilderClassname
     */
    public String getViewBuilderClassname() {
        return viewBuilderClassname;
    }

    /**
     * @param viewBuilderClassname the viewBuilderClassname to set
     */
    public void setViewBuilderClassname(String viewBuilderClassname) {
        this.viewBuilderClassname = viewBuilderClassname;
    }

    /**
     * @return the childViewConfigList
     */
    public List<ViewConfig> getChildViewConfigList() {
        return childViewConfigList;
    }

    /**
     * @param childViewConfigList the childViewConfigList to set
     */
    public void setChildViewConfigList(List<ViewConfig> childViewConfigList) {
        this.childViewConfigList = childViewConfigList;
    }
    
    
    public String getComponentClass() {
		return componentClass;
	}

	public void setComponentClass(String componentClass) {
		this.componentClass = componentClass;
	}
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getFxml() {
		return fxml;
	}

	public void setFxml(String fxml) {
		this.fxml = fxml;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static ViewConfig getObjectFromStream(InputStream inp) throws JAXBException
    {
        JAXBContext jc = JAXBContext.newInstance(ViewConfig.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();        
        ViewConfig object = (ViewConfig) unmarshaller.unmarshal(inp);
        return object;
    }
    
    public static void writeToStream(OutputStream os, ViewConfig object) throws JAXBException
    {
    	JAXBContext jc = JAXBContext.newInstance(ViewConfig.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, os);
    }
  
}