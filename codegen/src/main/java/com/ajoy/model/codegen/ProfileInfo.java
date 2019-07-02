package com.ajoy.model.codegen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfileInfo extends BaseInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8007199052814514283L;
	
	@XmlAttribute(name="name")
	private String name;
	

	@XmlAttribute(name="is-selected")
	private boolean isSelected;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String toString()
	{
		return getName();
	}
	
}
