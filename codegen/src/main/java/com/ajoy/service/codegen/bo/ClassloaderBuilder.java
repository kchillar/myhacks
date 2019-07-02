package com.ajoy.service.codegen.bo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ClasspathEntry;

/**
 * 
 * @author kalyanc
 *
 */
public class ClassloaderBuilder 
{	
	private static Logger log = LogManager.getLogger(ClassloaderBuilder.class);
	private ArrayList<URL> urls = new ArrayList<>();	
	
	private HashMap<String, ClasspathEntry> classPathMap = new HashMap<String, ClasspathEntry>();
	
	private ArrayList<String> classnamesList = new ArrayList<String>();
	
	public ClassloaderBuilder()
	{		
	}
	
	public ClassloaderBuilder(String[] pathList)
	{
		for(int i=0; i<pathList.length; i++)
			addPath(pathList[i]);		
	}
	
	public ClassLoader build()
	{
		ClassLoader fileSystemClassLoader;
		if(urls.size() > 0)
		{
			URLClassLoader jarClassLoader = URLClassLoader.newInstance(urls.toArray(new URL[urls.size()]));
			fileSystemClassLoader = new CustomClassloader(classPathMap, jarClassLoader);
		}
		else
		{
			fileSystemClassLoader = new CustomClassloader(classPathMap);
		}
		return fileSystemClassLoader;
	}
	
	
	public void addPath(String path)
	{
		if(path == null || path.trim().length() == 0)
			throw new IllegalStateException("Invalid path: '"+path+"', Cannot accept this path!!");

		try
		{			
			if(path.endsWith(".jar"))
			{
				urls.add( new URL("jar:file:" + path +"!/"));
				processJarAtPath(path);
			}
			else
			{				
				File file = new File(path);
				if(!(file.isDirectory() && file.canRead()))
					throw new IllegalStateException("Invalid directory path: "+path);
				processDir(file, file);
			}
		}
		catch(Exception exp)
		{
			log.error("Error ", exp);
		}
	}
		
	public List<String> getClassnamesList()
	{
		return classnamesList;
	}
	
	private void processDir(File dir, File start)
	{
		for(File file: dir.listFiles())
		{
			if(file.isDirectory())
				processDir(file, start);
			else
			{
				if(file.getPath().endsWith(".class"))
				{
					String className = file.getPath().substring(start.getPath().length()+1);
					className = className.replaceAll(File.separator, ".");
					className = className.substring(0, className.length()-6);
					
					if(classPathMap.get(className)==null)
					{
						classnamesList.add(className);
						classPathMap.put(className, new ClasspathEntry(file.getPath(), 0));
						log.info("Key1: "+className+" Value: "+classPathMap.get(className));
					}
					else
					{
						log.warn("Class: "+className+" is duplicate");
					}
				}
			}				
		}
	}
	
	private void processJarAtPath(String jarPath) throws IOException, ClassNotFoundException
	{
		log.info("processJarAtPath() start "+jarPath);
		
		JarFile jarFile = new JarFile(jarPath);
		Enumeration<JarEntry> e = jarFile.entries();
		try
		{
			while (e.hasMoreElements()) 
			{
				JarEntry je = e.nextElement();
				if(je.isDirectory() || !je.getName().endsWith(".class"))
				{
					continue;
				}
				// -6 because of .class						

				String className = je.getName().substring(0,je.getName().length()-6);
				
				if(classPathMap.get(className) == null)
				{
					className = className.replaceAll(File.separatorChar+"", ".");
					classnamesList.add(className);
					classPathMap.put(className, new ClasspathEntry(jarPath, 1));					
					log.info("Key2: "+className+" Value: "+classPathMap.get(className));					
				}
				else
				{
					log.warn("Class: "+className+" is duplicate");
				}				
			}
		}
		finally
		{
			jarFile.close();
		}
		log.info("processedJar: "+jarPath);
	}

	
	public static void main(String[] args) throws Exception
	{
		String[] paths = {
				"/Users/kalyanc/test/test.jar",
				"/Users/kalyanc/test/bin"
				
				};
		
		ClassloaderBuilder builder = new ClassloaderBuilder(paths);	
		
		List<String> list = builder.getClassnamesList();
		
		for(String name: list)
		{
			System.out.println("Found class: "+name);
		}
		
		
		ClassLoader loader = builder.build();
		
		
				
		Class c = loader.loadClass("com.test.model.Bank");
		Object obj = c.newInstance();
		
		Method[] m = c.getDeclaredMethods();
		
		log.info("ClassLoader for App: "+builder.getClass().getClassLoader());
		
		for(int i=0; i<m.length; i++)
		{		
			log.info("Method name: "+m[i].getName()+" "+(m[i].invoke(obj, null)));
		
			if(m[i].getName().equals("getDataVO"))
			{
				Object data = m[i].invoke(obj, null);
				log.info("ClassLoader for DataVO "+data.getClass().getClassLoader());
			}
		}
	}
}

