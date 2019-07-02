package com.ajoy.service.codegen.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.workflow.DataServiceResources;

public class BaseDAO 
{
	private static Logger log = LogManager.getLogger(BaseDAO.class);
	
	
	private HashMap<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	private HashMap<String, Marshaller> marshallerMap = new HashMap<String, Marshaller>();
	private HashMap<String, Unmarshaller> unmarshallerMap = new HashMap<String, Unmarshaller>();
		
	public Object getObjectFromStream(Class objectClass, File file)
	{
		if(file != null)		
			if(log.isDebugEnabled())
				log.debug("Will read from file: "+file.getPath());
		
		InputStream inp = getInputStream(file);

		Unmarshaller unmarshaller = unmarshallerMap.get(objectClass.getName());
		

		try
		{
			if(unmarshaller == null)
			{
				JAXBContext jc = jaxbContextMap.get(objectClass.getName());			
				if(jc == null)
				{
					jc = JAXBContext.newInstance(objectClass);
					jaxbContextMap.put(objectClass.getName(), jc);
				}

				unmarshaller = jc.createUnmarshaller();
				unmarshallerMap.put(objectClass.getName(), unmarshaller);
			}

			Object object = unmarshaller.unmarshal(inp);
			return object;
		}
		catch(JAXBException jaxbe)
		{
			throw new IllegalStateException("Unable to get object of class: "+objectClass.getName()+" from file", jaxbe);
		}
		finally
		{
			closeStreams(inp, null);
		}
	}

	public void writeObjectToStream( Class objectClass, Object object, File file)
	{
		if(file != null)		
			if(log.isDebugEnabled())
				log.debug("Will write to file: "+file.getPath());
		
		OutputStream os = getOutputStream(file);

		Marshaller marshaller = marshallerMap.get(objectClass.getName());
		try
		{

			if(marshaller == null)
			{
				JAXBContext jc = jaxbContextMap.get(objectClass.getName());			
				if(jc == null)
				{
					jc = JAXBContext.newInstance(objectClass);
					jaxbContextMap.put(objectClass.getName(), jc);
				}

				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshallerMap.put(objectClass.getName(), marshaller);
			}

			marshaller.marshal(object, os);
		}
		catch(JAXBException jaxbe)
		{
			throw new IllegalStateException("Unable to store object of class: "+objectClass.getName()+" to file", jaxbe);
		}
		finally
		{
			closeStreams(null, os);
		}
	}

	private InputStream getInputStream(File file)
	{
		try
		{
			if(file.isFile() && file.exists() && file.canRead())
			{
				FileInputStream fis = new FileInputStream(file);
				return fis;
			}
			else
			{
				log.warn("Unable to create input stream to file "+file.getPath()+". Will return null");
				return null;
			}
		}
		catch(Exception exp)
		{
			log.error("Error",  exp);
			throw new IllegalStateException("Error in loading file: "+file.getPath());
		}
	}

	private OutputStream getOutputStream(File file)
	{
		try
		{
				FileOutputStream fos = new FileOutputStream(file);
				return fos;
		}
		catch(Exception exp)
		{
			log.error("Error",  exp);
			throw new IllegalStateException("Error in loading file: "+file.getPath());
		}
	}

	public void closeStreams(InputStream inp, OutputStream out)
	{
		if(inp != null) 
		{
			try { inp.close(); } catch (Exception exp) {};
		}
		if(out != null) 
		{
			try { out.close(); } catch (Exception exp) {};
		}
	}
	
	public File getDataDir()
	{
		return DataServiceResources.get().getDataDir();
	}

}
