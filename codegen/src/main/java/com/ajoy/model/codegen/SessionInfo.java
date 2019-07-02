package com.ajoy.model.codegen;

public class SessionInfo 
{
	private String sessionId;
	
	private String selectedProfile; //selected profile
	private String selectedDB; //selected database
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSelectedProfile() {
		return selectedProfile;
	}
	public void setSelectedProfile(String selectedProfile) {
		this.selectedProfile = selectedProfile;
	}
	public String getSelectedDB() {
		return selectedDB;
	}
	public void setSelectedDB(String selectedDB) {
		this.selectedDB = selectedDB;
	}
	
	

}
