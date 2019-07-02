package com.ajoy.service.codegen.bo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ClasspathEntry;

/**
 * 
 * @author kalyanc
 *
 */
public class CustomClassloader extends ClassLoader
{
	private static Logger log = LogManager.getLogger(CustomClassloader.class);
	private HashMap<String, ClasspathEntry> map;
	 
	
    public CustomClassloader(HashMap<String, ClasspathEntry> aMap) 
    {
    	super(CustomClassloader.class.getClassLoader());    
    	map = aMap;
    }
    
    public CustomClassloader(HashMap<String, ClasspathEntry> aMap, ClassLoader parent) 
    {
    	super(parent);    
    	map = aMap;
    }

     
    /**
    *
    * @param name
    *            Full class name
    */
   @Override
   public Class<?> loadClass(String name) throws ClassNotFoundException 
   {
	   log.info("Loading Class '" + name + "'");
       ClasspathEntry entry = map.get(name);
       	                 
       if(entry != null && entry.getType() == 0)
       {    	   
    	   return getClass(name);
       }
       else 
       {
    	   return super.loadClass(name);
       }
   }
    
    /**
     * 
     */
    private Class<?> getClass(String name) throws ClassNotFoundException 
    {   
    	ClasspathEntry entry = map.get(name);
        
        byte[] b = null;
        try 
        {
        	File file = new File(entry.getPath());
            b = loadClassFileData(file);
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
 
 
    /**
     * 
     */
    private byte[] loadClassFileData(File file) throws IOException 
    {    	    	    
    	FileInputStream fip = new FileInputStream(file);
        int size = fip.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(fip);
        in.readFully(buff);
        in.close();
        return buff;
    }
    
}