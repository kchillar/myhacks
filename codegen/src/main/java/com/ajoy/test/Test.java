package com.ajoy.test;

import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.codegen.main.AppUI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application 
{    	
	//private static Logger log = LogManager.getLogger(Test.class);
	private ViewConfig config;
	private static Log log = new Log(Test.class);
	 
	public Test()
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
				//String viewConfigFile = params.getRaw().get(0);
				//log.debug("Got viewConfigFile: "+viewConfigFile);				
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
	
    @Override
    public void start(Stage stage) throws Exception 
    {
    	log.info("start() start");
    	TabelViewBuilder builder = new TabelViewBuilder();
        Parent root = builder.build();      
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
    	AppUI.launch(args);
    }
 

}
