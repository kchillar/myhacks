package com.ajoy.client.base.view;

import javafx.scene.Parent;

/**
 * 
 * @author kalyanc
 *
 */
public interface View 
{
	public View getParentView();
	
	public Parent createJavaFXParentObject(ViewConfig config);
			
	public void beforeDisplay();
	public void displayView(String viewName);
	
	public void showInfoMsg(String msg);
	public void showErrorMsg(String msg);
	public void clearMsg();
	
}
