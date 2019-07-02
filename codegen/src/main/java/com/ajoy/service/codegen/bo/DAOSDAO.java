package com.ajoy.service.codegen.bo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DAOS;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.DataServiceResources;

public class DAOSDAO extends BaseDAO
{
	private static Logger log = LogManager.getLogger(DAOSDAO.class);
	private static final String DataFile = DataServiceResources.get().getDaosFilename();
	
	public ResponseCode<DAOS> readFromStorage(SessionInfo sessionInfo)
	{
		log.info("readFromStorage() start");
		ResponseCode<DAOS> code = new ResponseCode<>();	
		File file = new File(getDataDir(), sessionInfo.getSelectedProfile()+File.separator+DataFile);

		try
		{
			DAOS daos;
			if(file.exists())
			{
				daos = (DAOS) getObjectFromStream(DAOS.class, file);
			}
			else
			{
				log.info("Creating an empty object for the first time as "+file.getPath()+" does not exist");
				daos = new DAOS(); //create any emtpy object
				persistToStore(sessionInfo, daos);				
			}
				
			code.setObject(daos);
		}
		finally
		{
		}
		log.info("readFromStorage() end");
		return code;
	}
	
	
	public ResponseCode<DAOS> persistToStore(SessionInfo sessionInfo, DAOS daos)
	{
		log.info("persistToStore() start");		
		ResponseCode<DAOS> code = new ResponseCode<>();	
		
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
			writeObjectToStream(DAOS.class, daos, file);						
			code.setObject(daos);		
		}
		finally
		{		
		}
		log.info("persistToStore() end");
		return code;
	}

}
