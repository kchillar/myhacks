package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ClassInfo;
import com.ajoy.model.codegen.Classes;
import com.ajoy.model.codegen.Classpath;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.bo.ClasspathBO;

public class ClasspathService 
{

	private static Logger log = LogManager.getLogger(ClasspathService.class);
	private ClasspathBO bo = new ClasspathBO();
	
	public ResponseCode<Classpath> getClasspath(CallContext context)
	{
		ClasspathBO cpbo = getClasspathBO();
		return cpbo.getClasspath(context);
	}
	
	public ResponseCode<Classpath> updateClasspath(Classpath classpath, CallContext context)
	{
		ClasspathBO cpbo = getClasspathBO();
		return cpbo.updateClasspath(classpath, context);
	}


	public ResponseCode<Classes> getClasses(CallContext context)
	{
		ClasspathBO cpbo = getClasspathBO();
		return cpbo.getClasses(context);
	}
	
	public ResponseCode<ClassInfo> getClassDetails(ClassInfo classInfo, CallContext context)
	{
		ClasspathBO cpbo = getClasspathBO();
		return cpbo.getClassDetails(classInfo, context);
	}

	private ClasspathBO getClasspathBO()
	{
		return bo;
	}

}
