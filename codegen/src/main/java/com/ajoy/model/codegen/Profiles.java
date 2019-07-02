package com.ajoy.model.codegen;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="profiles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Profiles 
{
	@XmlElement(name="profile")
	private List<ProfileInfo> profiles;

	public List<ProfileInfo> getProfiles() 
	{
		return profiles;
	}

	public void setProfiles(List<ProfileInfo> profiles) 
	{
		this.profiles = profiles;
	}
	
	public static Profiles getObjectFromStream(InputStream inp) throws JAXBException
    {
        JAXBContext jc = JAXBContext.newInstance(Profiles.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();        
        Profiles object = (Profiles) unmarshaller.unmarshal(inp);
        return object;
    }

    public static void writeToStream(OutputStream os, Profiles object) throws JAXBException
    {
    	JAXBContext jc = JAXBContext.newInstance(Profiles.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, os);
    }

}
