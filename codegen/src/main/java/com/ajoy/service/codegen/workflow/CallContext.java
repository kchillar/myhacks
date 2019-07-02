package com.ajoy.service.codegen.workflow;

import java.sql.Connection;

import com.ajoy.model.codegen.SessionInfo;

public class CallContext 
{
	private SessionInfo sessionInfo;
	private Connection conn;
	
	public void setSessionInfo(SessionInfo sessionInfo)
	{
		this.sessionInfo = sessionInfo;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public SessionInfo getSessionInfo() {
		return sessionInfo;
	}
	
	
	
}
