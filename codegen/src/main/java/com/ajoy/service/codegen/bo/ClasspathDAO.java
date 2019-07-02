package com.ajoy.service.codegen.bo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.Classpath;
import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.DataServiceResources;

public class ClasspathDAO extends BaseDAO
{

	private static Logger log = LogManager.getLogger(ClasspathDAO.class);
	private static final String DataFile = DataServiceResources.get().getClasspathFilename();
	
	public ResponseCode<Classpath> readFromStorage(SessionInfo sessionInfo)
	{
		log.info("readFromStorage() start");
		ResponseCode<Classpath> code = new ResponseCode<>();	
		File file = new File(getDataDir(), sessionInfo.getSelectedProfile()+File.separator+DataFile);

		try
		{
			Classpath dbs = (Classpath) getObjectFromStream(Classpath.class, file);			
			code.setObject(dbs);
		}
		finally
		{
		}
		log.info("readFromStorage() end");
		return code;
	}
	
	
	public ResponseCode<Classpath> persistToStore(SessionInfo sessionInfo, Classpath classpath)
	{
		log.info("persistToStore() start");		
		ResponseCode<Classpath> code = new ResponseCode<>();	
		
		File dir = new File(getDataDir(), sessionInfo.getSelectedProfile());
		
		if(!dir.exists())
		{
			if(dir.mkdirs())
				log.info("Created datadir "+dir.getPath());
			else
				throw new IllegalStateException("Unable to create data dir: "+dir.getPath());
		}
		
		File file = new File(dir, DataFile);		
		try
		{
			writeObjectToStream(Classpath.class, classpath, file);						
			code.setObject(classpath);		
		}
		finally
		{		
		}
		log.info("persistToStore() end");
		return code;
	}
	

}
