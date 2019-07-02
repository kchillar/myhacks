package com.ajoy.service.codegen.workflow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.Profiles;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.bo.DbDAO;
import com.ajoy.service.codegen.bo.ProfileDAO;

public class DataServiceResources 
{
	private static Logger log = LogManager.getLogger(DataServiceResources.class);
		
	private static DataServiceResources singleton = new DataServiceResources();
	
	private DataServiceConfig dataServiceConfig;
	
	private File dataDir;
	
	public static DataServiceResources get()
	{
		return singleton;
	}
	
	private DataServiceResources()
	{
		init(new DataServiceConfig());
	}
	
	private void init(DataServiceConfig config)	
	{
		this.dataServiceConfig = config;
		dataDir = new File(config.getBaseDir());		
	}
	
	public File getDataDir()
	{
		return dataDir;
	}
	
	public String getDatabasesFilename()
	{
		return dataServiceConfig.getDatabasesFilename();
	}
	
	public String getProfilesFilename()
	{
		return dataServiceConfig.getProfilesFilename();
	}
	
	public String getClasspathFilename()
	{
		return dataServiceConfig.getClasspathFilename();
	}

	public String getDaosFilename()
	{
		return dataServiceConfig.getDaosFilename();
	}

	
	public void checkAndCreate()
	{
		if(!getDataDir().exists())
			if(getDataDir().mkdirs())
				log.info("Created data directory: "+getDataDir().getPath());
			else
				log.error("Data directory: "+getDataDir().getPath()+" does not exist and not able to create !!!");
		else
			log.info("Data directory: "+getDataDir().getPath()+" alreay exists!!");
		
		
		File profilesFile = new File(getDataDir(), dataServiceConfig.getProfilesFilename());
		
		if(profilesFile.exists())
			log.info("Profiles file: "+profilesFile.getPath()+" exists");
		else
		{	
			ProfileInfo pInfo = new ProfileInfo();
			
			
			pInfo.setName("Dev");
			pInfo.setSelected(true);			
			List<ProfileInfo> list = new ArrayList<>();
			list.add(pInfo);
			ProfileDAO dao = new ProfileDAO();
			
			Profiles pf = new Profiles();
			pf.setProfiles(list);			
			dao.persistToStore(pf);
			log.info("Created default Dev profile");

			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setSelectedProfile(pInfo.getName());

			
			File file = new File(getDataDir(), pInfo.getName());
			
			if(file.mkdirs())
				log.info("Created directory for '"+pInfo.getName()+"' profile files");
			
			List<DBInfo> dbList = new ArrayList<DBInfo>();
			DBInfo dbInfo = new DBInfo();
			dbInfo.setName("DevDB");
			dbInfo.setSelected(true);
			dbList.add(dbInfo);			
			DbDAO dbDao = new DbDAO();
			Databases db = new Databases();
			db.setDbInfoList(dbList);
			dbDao.persistToStore(sessionInfo, db);
			
			if(file.mkdirs())
				log.info("Created default database connection "+dbInfo.getName()+" at "+file.getPath());
		}
	}
}
