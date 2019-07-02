package com.ajoy.api.codegen;

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

public interface AppDataAPI 
{
	public ResponseCode<SessionInfo> createSession(UserCredentials userCredentials);
	
	/**  Project Profile API **/	
	public ResponseCode<ProfileInfo> createProfile(ProfileInfo pInfo, SessionInfo sessionInfo);
	
	public ResponseCode<Profiles> getProfileList(SessionInfo sessionInfo);
	
	public ResponseCode<ProfileInfo> updateProfile(ProfileInfo pInfo, SessionInfo sessionInfo);	
	
	/**  Databases Info API **/
	public ResponseCode<DBInfo> createDBInfo(DBInfo dbInfo, SessionInfo sessionInfo);
	
	public ResponseCode<Databases> getDatabaseInfoList(SessionInfo sessionInfo);
	
	public ResponseCode<DBInfo> updateDatabaseInfo(DBInfo bInfo, SessionInfo sessionInfo);

	/**  Classpath API **/	
	public ResponseCode<Classpath> getClasspath(SessionInfo sessionInfo);
	
	public ResponseCode<Classpath> updateClasspath(Classpath classpath, SessionInfo sessionInfo);
	
	/**  Classes Info API **/	  
	public ResponseCode<Classes> getClasses(SessionInfo sessionInfo);
	
	public ResponseCode<ClassInfo> getClassDetails(ClassInfo classInfo, SessionInfo sessionInfo);

	/**  DB Tables Info API **/	
	public ResponseCode<DBTables> getDBTables(SessionInfo sessionInfo);
	
	public ResponseCode<DBTable> getDBTableDetails(DBTable dbTable, SessionInfo sessionInfo);

	/**  DAO API **/
	public ResponseCode<DAOS> updateDAOS(DAOS daos, SessionInfo sessionInfo);
	
	public ResponseCode<DAOS> getDAOS(SessionInfo sessionInfo);
	
	/**  DAO Method API **/
	public ResponseCode<DAOMethodInfo> createDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo);
	
	public ResponseCode<DAOMethodInfo> getDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo);
	
	public ResponseCode<DAOMethodInfo> updateDAOMethod(DAOMethodInfo daoInfo, SessionInfo sessionInfo);
}
