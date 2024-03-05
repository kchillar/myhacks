package com.azy.graph.builder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="edge")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdgeInfo 
{
	@XmlAttribute(name="from")
	private int from;
	@XmlAttribute(name="to")
	private int to;	
	@XmlAttribute(name="value")
	private String value;
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString()
	{
		return "<edge from=\""+getFrom()+"\" to=\""+getTo()+"\" value=\""+getValue()+"\" />";
	}
	
}
