package com.ajoy.service.codegen.bo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DAOS;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.CallContext;

public class DAOSBO extends BaseBO
{
	private static Logger log = LogManager.getLogger(DAOSBO.class);
	private DAOSDAO dao = new DAOSDAO();
	
	public ResponseCode<DAOS> updateDOAS(DAOS daos, CallContext context)
	{
		log.info("updateDOAS() start"); 		
		SessionInfo sessionInfo = context.getSessionInfo();
		ResponseCode<DAOS> code = dao.persistToStore(sessionInfo, daos);
		log.info("updateDOAS() end");
		return code;
	}

	public ResponseCode<DAOS> getDAOS(CallContext context)
	{
		log.info("getDAOS() start");
		SessionInfo sessionInfo = context.getSessionInfo();
		ResponseCode<DAOS> code = dao.readFromStorage(sessionInfo);
		log.info("getDAOS() end ");
		return code;
	}

}
