package com.ajoy.client.base.view.theme1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

public class MenuHandler implements EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(MenuHandler.class);
	private View view;
	
	public MenuHandler(View view)
	{
		log.info("created");
		this.view = view;
	}
	
	@Override
	public void handle(ActionEvent event) 
	{
		Hyperlink link = (Hyperlink) event.getSource();			
		view.displayView(link.getId());		
	}

}
