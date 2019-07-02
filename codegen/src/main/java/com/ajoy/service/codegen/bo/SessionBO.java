package com.ajoy.service.codegen.bo;

import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.model.codegen.UserCredentials;

public class SessionBO extends BaseBO 
{
	public ResponseCode<SessionInfo> createSession(UserCredentials userCredentials)
	{
		ResponseCode<SessionInfo> code = new ResponseCode<>();
		
		SessionInfo sInfo = new SessionInfo();
		sInfo.setSessionId("XYZ");
		sInfo.setSelectedProfile("Dev");
		code.setObject(sInfo);
		return code;
		
	}
}
