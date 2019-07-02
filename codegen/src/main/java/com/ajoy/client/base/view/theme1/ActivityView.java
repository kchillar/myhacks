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
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author kalyanc
 */
public class ActivityView extends BaseView implements View
{
	private static Logger log = LogManager.getLogger(ActivityView.class);
	private BorderPane pane;
	private VBox leftMenu;
	
	public ActivityView()
	{    	
	}

	@Override
	public Parent createJavaFXParentObject(ViewConfig config) 
	{
		Parent p;			 		
		pane = new BorderPane();	 		
		leftMenu = new VBox();

		if(config.getChildViewConfigList() != null)
		{
			for(ViewConfig child: config.getChildViewConfigList())
			{
				Node node = super.createComponent(child, this);	
				leftMenu.getChildren().add(node);					
				try
				{
					if(child.getViewBuilderClassname() != null)
					{
						p = ViewBuilder.buildJavaFXParent(child);
						BaseView childView = (BaseView) p.getUserData();
						childView.setParentView(this);

						UIDataModel.get().putJFXParent(child.getId(), p);
					}
					else
					{
						log.info("view-clasname is NOT configured for child "+child.getName());
					}
				}
				catch(Exception exp)
				{
					log.error("Error in view creation",  exp);
				}
				
				if(child.isSelected())
				{
					if(node instanceof Hyperlink)
						((Hyperlink)node).fire();
				}					
			}	 			
		}	 		
		pane.setLeft(leftMenu);
		return pane;
	}

	public void displayView(String viewName)
	{
		log.info("Setting activity: "+viewName+" as centre");
		
		if(UIDataModel.get().getJFXParent(viewName)!=null)
		{
			Node node = UIDataModel.get().getJFXParent(viewName);
			
			View view = (View) node.getUserData();
			
			view.beforeDisplay();
			
			pane.setCenter(node);

		}
		else
			log.info("View is not defined yet for "+viewName);
		
		
	}
	

}
