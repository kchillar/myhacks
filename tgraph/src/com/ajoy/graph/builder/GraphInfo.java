package com.ajoy.graph.builder;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="graph")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphInfo 
{
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="vertices")
	private int vertices;
	
	@XmlElement(name="edge")
	private EdgeInfo [] edgeInfoList;

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	public EdgeInfo[] getEdgeInfoList() {
		return edgeInfoList;
	}

	public void setEdgeInfoList(EdgeInfo[] edgeInfoList) {
		this.edgeInfoList = edgeInfoList;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString()
	{
		StringBuilder buff = new StringBuilder();		
		buff.append("<graph>");	
		buff.append("\n");
		buff.append(" <description>"+getVertices()+"</description>");
		buff.append(" <vertices>"+getVertices()+"</vertices>");
		buff.append("\n");
		if(getEdgeInfoList() != null)
		{
			for(EdgeInfo info: getEdgeInfoList())				
				buff.append(" "+info.toString()+"\n");
		}		
		buff.append("</graph>");
		return buff.toString();
	}
	
	public static GraphInfo getFromStream(InputStream in)
			throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(GraphInfo.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GraphInfo info = (GraphInfo) jaxbUnmarshaller.unmarshal(in);
		return info;
	}

}
