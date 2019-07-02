package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.bo.DBInfoBO;

public class DBInfoService 
{
	private static Logger log = LogManager.getLogger(DBInfoService.class);
	private DBInfoBO bo = new DBInfoBO();
	
	public ResponseCode<DBInfo> createDBInfo(DBInfo dbInfo, CallContext context)
	{		
		return bo.addDBInfo(dbInfo, context);
	}

	public ResponseCode<Databases> getDatabaseInfoList(CallContext context)
	{
		return bo.getDatabaseInfoList(context);	
	}

	public ResponseCode<DBInfo> updateDatabaseInfo(DBInfo dbInfo, CallContext context)
	{
		return bo.updateDatabaseInfo(dbInfo,  context);
	}

}
