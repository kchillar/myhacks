package com.ajoy.service.codegen.bo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.DataServiceResources;

public class DbDAO extends BaseDAO
{
	private static Logger log = LogManager.getLogger(DbDAO.class);
	private static final String DataFile = DataServiceResources.get().getDatabasesFilename();
	
	public ResponseCode<Databases> readFromStorage(SessionInfo sessionInfo)
	{
		log.info("readFromStorage() start");
		ResponseCode<Databases> code = new ResponseCode<>();	
		File file = new File(getDataDir(), sessionInfo.getSelectedProfile()+File.separator+DataFile);

		try
		{
			Databases dbs = (Databases) getObjectFromStream(Databases.class, file);			
			code.setObject(dbs);
		}
		finally
		{
		}
		log.info("readFromStorage() end");
		return code;
	}
	
	
	public ResponseCode<Databases> persistToStore(SessionInfo sessionInfo, Databases databases)
	{
		log.info("persistToStore() start");		
		ResponseCode<Databases> code = new ResponseCode<>();	
		
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
			writeObjectToStream(Databases.class, databases, file);						
			code.setObject(databases);		
		}
		finally
		{		
		}
		log.info("persistToStore() end");
		return code;
	}
	

}
