package com.ajoy.service.codegen.bo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DAOMethodInfo;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;

public class DAOMethodDAO extends BaseDAO
{
	private static Logger log = LogManager.getLogger(DAOSDAO.class);
		
	public ResponseCode<DAOMethodInfo> readFromStorage(DAOMethodInfo methodInfo, SessionInfo sessionInfo)
	{
		log.info("readFromStorage() start");
		ResponseCode<DAOMethodInfo> code = new ResponseCode<>();	
		
		
		File file = new File(getDataDir(), sessionInfo.getSelectedProfile()+File.separator+"daos"+ File.separator + methodInfo.getDaoName()+
				File.separator+methodInfo.getName()+".xml");

		try
		{
			DAOMethodInfo daos = (DAOMethodInfo) getObjectFromStream(DAOMethodInfo.class, file);			
			code.setObject(daos);
		}
		finally
		{
		}
		log.info("readFromStorage() end");
		return code;
	}
	
	
	public ResponseCode<DAOMethodInfo> persistToStore(SessionInfo sessionInfo, DAOMethodInfo methodInfo)
	{
		log.info("persistToStore() start");		
		ResponseCode<DAOMethodInfo> code = new ResponseCode<>();	
		
		File dir = new File(getDataDir(), sessionInfo.getSelectedProfile()+File.separator+"daos" + File.separator + methodInfo.getDaoName());
		
		if(!dir.exists())
		{
			if(dir.mkdirs())
				log.info("Created datadir "+dir.getPath());
			else
				throw new IllegalStateException("Unable to create data dir: "+dir.getPath());
		}
		
		File file = new File(dir, methodInfo.getName()+".xml");		
		try
		{
			writeObjectToStream(DAOMethodInfo.class, methodInfo, file);						
			code.setObject(methodInfo);		
		}
		finally
		{		
		}
		log.info("persistToStore() end");
		return code;
	}
	


}
