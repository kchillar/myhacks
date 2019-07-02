package com.ajoy.service.codegen.workflow;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.api.codegen.AppDataAPI;
import com.ajoy.model.codegen.ClassInfo;
import com.ajoy.model.codegen.Classes;
import com.ajoy.model.codegen.Classpath;
import com.ajoy.model.codegen.DAOMethodInfo;
import com.ajoy.model.codegen.DAOS;
import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.DBTable;
import com.ajoy.model.codegen.DBTables;
import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ProfileInfo;
import com.ajoy.model.codegen.Profiles;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.model.codegen.UserCredentials;


/**
 * 
 * @author kalyanc
 * 
 * Need to re-visit this implementation if we are going to use REST adapter before this.<br>
 * At that point need to annotate this class to extract message data from JSON and also perform session checks.<br>  
 *
 */
public class DataServiceRESTEndPoint implements AppDataAPI
{	
	private static Logger log = LogManager.getLogger(DataServiceRESTEndPoint.class);
	
	/** The Service classes that chain calls to BO objects */
	private SessionService sessionService = new SessionService();
	private ProfileService profileService = new ProfileService();
	private DBInfoService dbInfoService = new DBInfoService();
	private ClasspathService classpathService = new ClasspathService();
	private DBTablesService dbTablesService = new DBTablesService();
	private DAOService daoService = new DAOService();
	private DAOMethodService daoMethodService = new DAOMethodService();
    

	private CallContext context = null;
			
	public DataServiceRESTEndPoint()
	{	
		
	}

	/** 
	 * Need to create this per request later.<br>
	 * For now using instance variable as this is single threaded in-memory implementation.<br>
	 **/
	private CallContext createContext(SessionInfo sessionInfo) 
	{		
		//checkSession(sessionInfo) and if good create context		
		if(context == null)
		{
			log.info("context is null, creating new context");
			context = new CallContext();
			context.setSessionInfo(sessionInfo);
		}		
		return context;
	}


	@Override
	public ResponseCode<SessionInfo> createSession(UserCredentials userCredentials)
	{
		log.info("createSession() start");
		ResponseCode<SessionInfo> code = getSessionService().createSession(userCredentials);
		createContext(code.getObject());
		log.info("createSession() end");		
		return code;
	}
	
	@Override
	public ResponseCode<ProfileInfo> createProfile(ProfileInfo pInfo, SessionInfo sessionInfo)
	{
		return getProfileService().createProfile(pInfo, createContext(sessionInfo));
	}

	@Override
	public ResponseCode<Profiles> getProfileList(SessionInfo sessionInfo)
	{
		return getProfileService().getProfileList(createContext(sessionInfo));
	}
	
	@Override
	public ResponseCode<ProfileInfo> updateProfile(ProfileInfo pInfo, SessionInfo sessionInfo)
	{
		return getProfileService().updateProfile(pInfo,createContext(sessionInfo));
	}

	@Override
	public ResponseCode<DBInfo> createDBInfo(DBInfo dbInfo, SessionInfo sessionInfo)
	{				
		return getDBInfoService().createDBInfo(dbInfo, createContext(sessionInfo));
	}

	public ResponseCode<Databases> getDatabaseInfoList(SessionInfo sessionInfo)
	{		
		return getDBInfoService().getDatabaseInfoList(createContext(sessionInfo));	
	}

	public ResponseCode<DBInfo> updateDatabaseInfo(DBInfo dbInfo, SessionInfo sessionInfo)
	{
		return getDBInfoService().updateDatabaseInfo(dbInfo, createContext(sessionInfo));
	}

	public ResponseCode<Classpath> getClasspath(SessionInfo sessionInfo)
	{
		return getClassPathService().getClasspath(createContext(sessionInfo));		
	}
	
	public ResponseCode<Classpath> updateClasspath(Classpath classpath, SessionInfo sessionInfo)
	{
		return getClassPathService().updateClasspath(classpath, createContext(sessionInfo));
	}

	public ResponseCode<Classes> getClasses(SessionInfo sessionInfo)
	{
		return getClassPathService().getClasses(createContext(sessionInfo));
	}
	
	public ResponseCode<ClassInfo> getClassDetails(ClassInfo classInfo, SessionInfo sessionInfo)
	{
		return getClassPathService().getClassDetails(classInfo, createContext(sessionInfo));
	}
	
	public ResponseCode<DBTables> getDBTables(SessionInfo sessionInfo)
	{
		return getDBTablesService().getDBTables(createContext(sessionInfo));
	}
	
	public ResponseCode<DBTable> getDBTableDetails(DBTable dbTable, SessionInfo sessionInfo)
	{
		return getDBTablesService().getDBTableDetails(dbTable, createContext(sessionInfo));
	}

	/**  DAO API **/
	public ResponseCode<DAOS> updateDAOS(DAOS daos, SessionInfo sessionInfo)
	{
		return getDAOService().updateDAOS(daos, createContext(sessionInfo));
	}
	
	public ResponseCode<DAOS> getDAOS(SessionInfo sessionInfo)
	{
		return getDAOService().getDAOS(createContext(sessionInfo));
	}
	
	/**  DAO Method API **/
	public ResponseCode<DAOMethodInfo> createDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo)
	{
		return null;
	}
	
	public ResponseCode<DAOMethodInfo> getDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo)
	{
		return null;
	}
	
	public ResponseCode<DAOMethodInfo> updateDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo)
	{
		return null;
	}
	
	private ProfileService getProfileService()
	{
		return profileService;
	}
	
	private SessionService getSessionService()
	{
		return sessionService;
	}
	
	private DBInfoService getDBInfoService()
	{
		return dbInfoService;
	}
	
	private ClasspathService getClassPathService()
	{
		return classpathService;
	}
	
	private DBTablesService getDBTablesService()
	{
		return dbTablesService;
	}
	
	private DAOService getDAOService()
	{
		return daoService;
	}

	private DAOMethodService getDAOMethodService()
	{
		return daoMethodService;
	}



}