package com.ajoy.client.codegen.delegate;

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
import com.ajoy.service.codegen.workflow.DataServiceRESTEndPoint;

/**
 * 
 * @author kalyanc
 * For now the client is simple in memory call to Service.<br>
 * Can me made to use HTTP and REST<br>
 *
 */
public class DataServiceClient implements AppDataAPI
{
	private static Logger log = LogManager.getLogger(DataServiceClient.class);

	private DataServiceRESTEndPoint inMemoryService = new DataServiceRESTEndPoint();
	
	@Override
	public ResponseCode<SessionInfo> createSession(UserCredentials userCredentials) 
	{	
		return inMemoryService.createSession(userCredentials);
	}

	
	@Override
	public ResponseCode<ProfileInfo> createProfile(ProfileInfo pInfo, SessionInfo sessionInfo) 
	{
		return inMemoryService.createProfile(pInfo, sessionInfo);
	}

	@Override
	public ResponseCode<Profiles> getProfileList(SessionInfo sessionInfo) 
	{
		return inMemoryService.getProfileList(sessionInfo);
	}

	@Override
	public ResponseCode<ProfileInfo> updateProfile(ProfileInfo pInfo, SessionInfo sessionInfo) 
	{
		return inMemoryService.updateProfile(pInfo, sessionInfo);
	}

	@Override
	public ResponseCode<DBInfo> createDBInfo(DBInfo dbInfo, SessionInfo sessionInfo) 
	{
		return inMemoryService.createDBInfo(dbInfo, sessionInfo);
	}

	@Override
	public ResponseCode<Databases> getDatabaseInfoList(SessionInfo sessionInfo) 
	{
		return inMemoryService.getDatabaseInfoList(sessionInfo);
	}

	@Override
	public ResponseCode<DBInfo> updateDatabaseInfo(DBInfo dbInfo, SessionInfo sessionInfo) 
	{
		return inMemoryService.updateDatabaseInfo(dbInfo, sessionInfo);
	}

	@Override
	public ResponseCode<Classpath> updateClasspath(Classpath classpath, SessionInfo sessionInfo) 
	{
		return inMemoryService.updateClasspath(classpath, sessionInfo);
	}

	@Override
	public ResponseCode<Classpath> getClasspath(SessionInfo sessionInfo) 
	{
		return inMemoryService.getClasspath(sessionInfo);
	}

	public ResponseCode<Classes> getClasses(SessionInfo sessionInfo)
	{
		log.info("getClasse() start");		
		ResponseCode<Classes> code = inMemoryService.getClasses(sessionInfo);		
		log.info("getClasse() end "+code);
		return code;
	}
	
	public ResponseCode<ClassInfo> getClassDetails(ClassInfo classInfo, SessionInfo sessionInfo)
	{
		log.info("getClassDetails() start");		
		ResponseCode<ClassInfo> code = inMemoryService.getClassDetails(classInfo, sessionInfo);
		log.info("getClassDetails() end "+code);
		return code;
	}
	
	public ResponseCode<DBTables> getDBTables(SessionInfo sessionInfo)
	{
		log.info("getDBTables() start");
		log.info("getDBTables() end");
		return null;
		
	}
	
	public ResponseCode<DBTable> getDBTableDetails(DBTable dbTable, SessionInfo sessionInfo)
	{
		log.info("getDBTableDetails() start");
		log.info("getDBTableDetails() end");

		return null;
	}

	/**  DAO API **/
	public ResponseCode<DAOS> updateDAOS(DAOS daos, SessionInfo sessionInfo)
	{
		log.info("updateDAO() start");
		ResponseCode<DAOS> code = inMemoryService.updateDAOS(daos, sessionInfo);
		log.info("updateDAO() end");
		return code;
	}
	
	public ResponseCode<DAOS> getDAOS(SessionInfo sessionInfo)
	{
		log.info("getDAOS() start");
		ResponseCode<DAOS> code = inMemoryService.getDAOS(sessionInfo);
		log.info("getDAOS() end");
		return code;
	}
	
	/**  DAO Method API **/
	public ResponseCode<DAOMethodInfo> createDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo)
	{
		log.info("createDAOMethod() start");
		ResponseCode<DAOMethodInfo> code = inMemoryService.createDAOMethod(daoInfo, sessionInfo);
		log.info("createDAOMethod() end");

		return code;
	}
	
	public ResponseCode<DAOMethodInfo> getDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo)
	{
		log.info("getDAOMethod() start");
		ResponseCode<DAOMethodInfo> code = inMemoryService.getDAOMethod(daoInfo, sessionInfo);
		log.info("getDAOMethod() end");
		return code;
	}
	
	public ResponseCode<DAOMethodInfo> updateDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo)
	{
		log.info("getDAOMethod() start");
		ResponseCode<DAOMethodInfo> code = inMemoryService.updateDAOMethod(daoInfo, sessionInfo);
		log.info("getDAOMethod() end");
		return code;
	}


}
