package com.ajoy.service.codegen.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.Databases;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.CallContext;

/**
 * 
 * @author kalyanc
 *
 */
public class DBInfoBO extends BaseBO
{
	private static Logger log = LogManager.getLogger(DBInfoBO.class);
	private DbDAO dao = new DbDAO();
		
	public DBInfoBO()
	{		
	}
		
	public ResponseCode<DBInfo> addDBInfo(DBInfo dbInfo, CallContext context)
	{		
		SessionInfo sessionInfo = context.getSessionInfo();
		log.info("addDBInfo() start");
		ResponseCode<Databases> aCode = getDatabaseInfoList(context);
				
		Databases dbs =  aCode.getObject();
		List<DBInfo> list = dbs.getDbInfoList();

		if(list == null)
		{
			list = new ArrayList<DBInfo>();
			dbs.setDbInfoList(list);
		}

		boolean exists = false;

		for(DBInfo tmp: list)
		{
			if(tmp.getName().equals(dbInfo.getName()))
				exists = true;				
		}

		ResponseCode<DBInfo> code = new ResponseCode<>();
		code.setObject(dbInfo);

		if(!exists)
		{				
			log.info("Added database: "+dbInfo);
			list.add(dbInfo);
		}
		else
		{
			log.warn("Duplicate database entry");
			code.setSuccess(false);			
		}		
		log.info("addDBInfo() end");
		return code;
	}

	public ResponseCode<Databases> getDatabaseInfoList(CallContext context)
	{
		SessionInfo sessionInfo = context.getSessionInfo();
		log.info("getDatabaseInfoList() start");
		ResponseCode<Databases> code = new ResponseCode<>();
		if(sessionInfo == null)
		{
			log.warn("Given Profile is null, will return null for Database List");
			code.setSuccess(false);
			return code;
		}
		log.info("getDatabaseInfoList() end");
		return dao.readFromStorage(sessionInfo);		
	}

	public ResponseCode<DBInfo> updateDatabaseInfo(DBInfo bInfo, CallContext context)
	{
		log.info("updateDatabaseInfo() start"); 
		SessionInfo sessionInfo = context.getSessionInfo();
		ResponseCode<DBInfo> code = new ResponseCode<>();
		code.setSuccess(false);
		
		ResponseCode<Databases> aCode = getDatabaseInfoList(context);
		if(aCode.isSuccess())
		{		
			Databases dbs = aCode.getObject();
			List<DBInfo> list = dbs.getDbInfoList();

			if(list != null)
				throw new IllegalStateException("The retrieved list size is null. Canno update !!! "+bInfo.getName());
			
			for(DBInfo tmp: list)
			{
				tmp.setSelected(false);			
				if(tmp.getName().equals(bInfo.getName()))
				{
					bInfo.setSelected(true);
					code.setSuccess(true);
				}
			}
			
			dao.persistToStore(sessionInfo, dbs);
		}	
		log.info("updateDatabaseInfo() end");
		return code;
	}

}
