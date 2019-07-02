package com.ajoy.service.codegen.bo;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.Profiles;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.workflow.DataServiceResources;

public class ProfileDAO extends BaseDAO
{
	private static Logger log = LogManager.getLogger(ProfileDAO.class);
	private static final String DataFile = DataServiceResources.get().getProfilesFilename();
		
	public ResponseCode<Profiles> readFromStorage()
	{
		log.info("readFromStorage() start");
		ResponseCode<Profiles> code = new ResponseCode<>();	
		File file = new File(getDataDir(), DataFile);

		try
		{
			Profiles profiles = (Profiles) getObjectFromStream(Profiles.class, file);			
			code.setObject(profiles);
		}
		finally
		{
		}
		log.info("readFromStorage() end");
		return code;
	}
		
	public ResponseCode<Profiles> persistToStore(Profiles profiles)
	{
		log.info("persistToStore() start list.size(): "+profiles.getProfiles().size());		
		ResponseCode<Profiles> code = new ResponseCode<>();	
		File file = new File(getDataDir(), DataFile);		
		try
		{
			writeObjectToStream(Profiles.class, profiles, file);						
			code.setObject(profiles);		
		}
		finally
		{		
		}
		log.info("persistToStore() end");
		return code;
	}

}
