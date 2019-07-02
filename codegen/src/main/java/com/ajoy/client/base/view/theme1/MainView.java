/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajoy.client.base.view.theme1;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewBuilder;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.codegen.main.UIDataModel;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 *
 * @author kalyanc
 */
public class MainView extends BaseView implements View
{	
	private static Logger log = LogManager.getLogger(MainView.class);    
    private BorderPane pane;
    private VBox top = new VBox();
    private HBox topMenu;
    private Label msgDisplayArea = new Label();
            
    public MainView()
    {    	    	
    }

	@Override
	public Parent createJavaFXParentObject(ViewConfig config)  
	{
		setParentView(null);
		pane = new BorderPane(); 	
		setupMenus(config);
		top.getChildren().add(topMenu);
		
		top.getChildren().add(getNewOffsetLabel());
				
		HBox box = new HBox();
		box.getChildren().add(getNewOffsetLabel());
		box.getChildren().add(getNewOffsetLabel());
		box.getChildren().add(msgDisplayArea);		
		
		top.getChildren().add(box);
		msgDisplayArea.setText("application messages will appear here");
		pane.setTop(top);
		
		Pane rightPane = new Pane();
		rightPane.getChildren().add(getNewOffsetLabel());
		pane.setRight(rightPane);
		
		VBox vbox = new VBox();
		vbox.getChildren().add(getNewOffsetLabel());
		pane.setBottom(vbox);
		
		return pane;
	}

	private void setupMenus(ViewConfig config)
	{
		topMenu = new HBox();
		topMenu.getChildren().add(getNewOffsetLabel());
		
		if(config.getChildViewConfigList() != null)
		{									
			for(ViewConfig child: config.getChildViewConfigList())
			{
				try
				{
					Node node = super.createComponent(child, this);					
					topMenu.getChildren().add(node);
					Parent p = ViewBuilder.buildJavaFXParent(child);
					
					BaseView childView = (BaseView) p.getUserData();
					childView.setParentView(this);
					
					UIDataModel.get().putJFXParent(child.getId(), p);
					
					if(child.isSelected())
						pane.setCenter(p);					
				}
				catch(Exception exp)
				{
					log.error("Error in creation",  exp);
				}
			}			
		}	
	}

	private Label getNewOffsetLabel()
	{
		return new Label("                    ");
	}
	
	
	public void displayView(String viewName)
	{
		log.info("Setting activity: "+viewName+" as centre");
		Node node = UIDataModel.get().getJFXParent(viewName);
		
		if(node!=null)	
		{
			View view = (View) node.getUserData();
			view.beforeDisplay();
			pane.setCenter(UIDataModel.get().getJFXParent(viewName));			
		}
		else			
			log.info("View is not defined yet for "+viewName);
	}


	@Override
	public void showInfoMsg(String msg) 
	{
		msgDisplayArea.setText(msg);
	}

	@Override
	public void showErrorMsg(String msg) 
	{
		msgDisplayArea.setText("ERROR: "+msg);
	}
	
	@Override
	public void clearMsg()
	{
		msgDisplayArea.setText("");
	}

}
