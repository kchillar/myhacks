package com.ajoy.client.codegen.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.codegen.delegate.DataServiceClient;
import com.ajoy.model.codegen.ClassInfo;
import com.ajoy.model.codegen.Classes;
import com.ajoy.model.codegen.Classpath;
import com.ajoy.model.codegen.DAOInfo;
import com.ajoy.model.codegen.DAOS;
import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.Profiles;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * 
 * @author kalyanc
 *
 */
public class UIDataModel 
{
	private static Logger log = LogManager.getLogger(UIDataModel.class);
	private Stage stage;
	private static UIDataModel singleton;
	
	private AppConfig config;
	private DataServiceClient dataServiceClient = new DataServiceClient(); //used to communicate to service
	
	private HashMap<String,Parent> idParentMap = new HashMap<>();
	private SessionInfo sessionInfo;

	private UIDataModel(AppConfig config)
	{
		this.config = config;
	}
	
	public static void init(AppConfig config)
	{
		UIDataModel obj = new UIDataModel(config);
		obj.createSession(); //create a session
		singleton = obj;		
	}

	public static UIDataModel get()
	{
		if(singleton == null)
			throw new IllegalStateException("Not setup");
		return singleton;
	}

	public void createSession()
	{
		ResponseCode<SessionInfo> code = dataServiceClient.createSession(null);
		
		sessionInfo = code.getObject();
	}
	
	public String addProfile(ProfileInfo pInfo)
	{				
		String msg = "Added profile "+pInfo.getName()+" successfully.";
		
		ResponseCode<ProfileInfo> code = dataServiceClient.createProfile(pInfo, sessionInfo);
		
		if(!code.isSuccess())
		{
			msg = "Failed to add profile "+pInfo.getName();
		}
		else
		{
			log.info("Set "+pInfo.getName()+" as selected");
			if(pInfo.isSelected())
				sessionInfo.setSelectedProfile(pInfo.getName());
		}
		
		return msg;
	}

	public String addDBInfo(DBInfo dbInfo)
	{		
		String msg = "Added database "+dbInfo.getName()+" successfully.";
		
		ResponseCode<DBInfo> code = dataServiceClient.createDBInfo(dbInfo, sessionInfo);
		
		if(!code.isSuccess())
			msg = "Failed to add profile "+dbInfo.getName();
		
		return msg;
	}

	public List<ProfileInfo> getProfileInfoList()
	{		
		ResponseCode<Profiles> code = dataServiceClient.getProfileList(sessionInfo);
		
		if(code.isSuccess() && code.getObject().getProfiles() != null)
			return code.getObject().getProfiles();
		else
			return null;
			//return new ArrayList<ProfileInfo>();
	}

	public String getSelectedProfile()
	{
		if(sessionInfo.getSelectedProfile() == null)
		{						
			ResponseCode<Profiles> code = dataServiceClient.getProfileList(sessionInfo);
			if(code.isSuccess())
			{
				Profiles profiles = code.getObject();
				List<ProfileInfo> list = profiles.getProfiles();

				if(list != null && list.size() > 0)
				{
					for(ProfileInfo pInfo: list) //not putting breaking logic as lists are small					
						if(pInfo.isSelected())
						{
							sessionInfo.setSelectedProfile(pInfo.getName());
						}
				}	
			}
		}
		return sessionInfo.getSelectedProfile();
	}

	
	public List<DBInfo> getDatabaseInfoList()
	{
		ResponseCode<Databases> code = dataServiceClient.getDatabaseInfoList(sessionInfo);
			
		if(code.isSuccess() && code.getObject().getDbInfoList() != null)
			return code.getObject().getDbInfoList();
		else
			return null;
	}

	public String getSelectedDBInfo() 
	{
		if(sessionInfo.getSelectedDB() != null)
			return sessionInfo.getSelectedDB();

		List<DBInfo> list = getDatabaseInfoList();

		if(list != null && list.size()>0)
		{
			for(DBInfo dInfo: list)			
				if(dInfo.isSelected()) //not putting breaking logic as lists are small					
					sessionInfo.setSelectedDB(dInfo.getName());							
		}				
		return sessionInfo.getSelectedDB();
	}

	public Classpath getClasspath() 
	{
		ResponseCode<Classpath> code = dataServiceClient.getClasspath(sessionInfo);
		
		if(code.isSuccess())
			return code.getObject();
		else
			return null;
	}

	public List<String> getClasses()
	{
		ResponseCode<Classes> code = dataServiceClient.getClasses(sessionInfo);
		
		if(code != null && code.isSuccess())
		{
			List<String> list = code.getObject().getClassInfoList();			
			Collections.sort(list);
			return list;
		}
		else
			return null;
	}
	
	public ClassInfo getClassDetails(ClassInfo classInfo)
	{
		ResponseCode<ClassInfo> code = dataServiceClient.getClassDetails(classInfo, sessionInfo);
		
		if(code.isSuccess())
			return code.getObject();
		else
			return null;
	}
	
	public String updateProfile(ProfileInfo pInfo)
	{		
		ResponseCode<ProfileInfo> code = dataServiceClient.updateProfile(pInfo, sessionInfo);				
		if(code.isSuccess())
		{
			sessionInfo.setSelectedProfile(pInfo.getName());
			return "Made profile '"+sessionInfo.getSelectedProfile()+"' as selected profile.";
		}
		
		return "Error in updating the profile '"+pInfo.getName()+"' as selected profile.";
	}
	
	public void updateDatabaseInfo(DBInfo dbInfo)
	{
		sessionInfo.setSelectedDB(dbInfo.getName());
		log.info("Made db: "+sessionInfo.getSelectedDB()+" as selected in profile: "+sessionInfo.getSelectedProfile());
		dataServiceClient.updateDatabaseInfo(dbInfo, sessionInfo);
	}

	public void updateClasspath(Classpath classpath)
	{			
		dataServiceClient.updateClasspath(classpath, sessionInfo);
	}

	public void updateDaos(DAOS daos)
	{
		dataServiceClient.updateDAOS(daos, sessionInfo);
	}

	public List<DAOInfo> getDaoInfoList()
	{
		ResponseCode<DAOS> code = dataServiceClient.getDAOS(sessionInfo);
		if(code.isSuccess())
			return code.getObject().getDaoList();
		else
		{
			log.warn("Got non successful error code from Service, returning null list ");
			return null;
		}
	}

	
	public void putJFXParent(String id, Parent parent)
	{
		idParentMap.put(id, parent);
	}

	public Parent getJFXParent(String id)
	{
		return idParentMap.get(id);
	}

	public AppConfig getAppConfig()
	{
		return config;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	

}
