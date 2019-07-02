package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.model.codegen.UserCredentials;
import com.ajoy.service.codegen.bo.ProfileBO;
import com.ajoy.service.codegen.bo.SessionBO;

public class SessionService 
{
	private static Logger log = LogManager.getLogger(SessionService.class);
	private SessionBO bo = new SessionBO();

	public ResponseCode<SessionInfo> createSession(UserCredentials userCredentials)
	{
		log.info("createSession() start");		
		ResponseCode<SessionInfo> code = bo.createSession(userCredentials);		
		log.info("createSession() end");		
		return code;
	}

}
