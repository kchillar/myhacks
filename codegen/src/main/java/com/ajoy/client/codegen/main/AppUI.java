/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajoy.client.codegen.main;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.ViewBuilder;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.service.codegen.workflow.DataServiceResources;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author kalyanc
 */
public class AppUI extends Application 
{    	
	private static Logger log = LogManager.getLogger(AppUI.class);
	private ViewConfig config;
	 
	public AppUI()
	{
		log.info("Created "+this);		
	}
	
	
	public void init()
	{
		log.info("init() start");
		
		Parameters params = getParameters();
		
		if(params != null)
		{
			try
			{
				String viewConfigFile = params.getRaw().get(0);
				log.debug("Got viewConfigFile: "+viewConfigFile);				
				FileInputStream fis = new FileInputStream(new File(viewConfigFile));
				config = ViewConfig.getObjectFromStream(fis);
				populateViewId(config, null);
				fis.close();
				log.debug("Got config:");
				ViewConfig.writeToStream(System.out, config);
			}
			catch(Exception exp)
			{
				log.error("Unable to get confguration to create UI", exp);
			}
		}
		else
		{
			log.debug("No Params");
		}

		log.info("init() end");
	}
	
	private void populateViewId(ViewConfig config, String parentPath)
	{
		String id ;
		
		if(parentPath != null)
			id = parentPath + "/" + config.getName();
		else
			id = "/" + config.getName();
		
		if(log.isDebugEnabled())
			log.debug("--> ID for: "+config.getName()+" is :"+id);
		
		config.setId(id);
		
		if(config.getChildViewConfigList()!=null)
		{
			for(ViewConfig child: config.getChildViewConfigList())
				populateViewId(child, config.getId());
		}
	}
	
	
    @Override
    public void start(Stage stage) throws Exception 
    {
    	log.info("start() start");
    	UIDataModel.get().setStage(stage);
    	
        Parent root =  ViewBuilder.buildJavaFXParent(config);    
        Scene scene = new Scene(root, 500, 500);    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
        log.info("start() end");
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {    	
    	//For now Services is in-memory so will launch from the same main as that of UI
    	launchServices(null);
    	
    	AppConfig appConfig = AppConfig.getObjectFromStream(new FileInputStream(new File(args[0])));        	
    	UIDataModel.init(appConfig); //UIDataModel communicates with DataService using 
    	
    	log.info("Got Config:\n"+appConfig.toString());
        	
    	String[] uiArgs = new String[1];
    	uiArgs[0] = appConfig.getViewConfig();
        	
    	AppUI.launch(uiArgs);
    }
    
    private static void launchServices(String[] args)
    {
    	//This will create default DataServiceResources if they donot exists
    	DataServiceResources.get().checkAndCreate();
    }
    
}
