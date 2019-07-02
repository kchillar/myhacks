package com.ajoy.client.base.view.theme1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewComponentBuilder;
import com.ajoy.client.base.view.ViewConfig;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

/**
 * 
 * @author kalyanc
 *
 */
public abstract class BaseView implements View
{
	private View parentView;
	private static Logger log = LogManager.getLogger(BaseView.class);
	private static final EventType<ActionEvent> beforeDisplayEvent = new EventType<>("BeforeDisplay");
	private static final EventType<ActionEvent> afterDisplayEvent = new EventType<>("AfterDisplay");
	
	public BaseView()
	{
	}
	
	public Node createComponent(ViewConfig child, View parent)
	{
		if(child.getComponentClass() == null)
		{
			MenuHandler handler = new MenuHandler(this);
			Hyperlink link = new Hyperlink(child.getName());
			link.setId(child.getId());	
			link.setOnAction(handler);
			return link;
		}
		else
		{
			return ViewComponentBuilder.buildJavaFXNode(child);
		}
	}


	
	public View getParentView() 
	{
		return parentView;
	}

	public void setParentView(View parent) 
	{
		this.parentView = parent;
	}
	
	@Override
	public void showInfoMsg(String msg) 
	{
		if(getParentView()!=null)
			getParentView().showInfoMsg(msg);
		else
			log.warn("getParentView() is null in showInfoMsg(): "+this.getClass().getName()+". Please check the logic.");
	}

	@Override
	public void showErrorMsg(String msg) 
	{
		getParentView().showErrorMsg(msg);		
	}
	
	@Override
	public void clearMsg()
	{
		getParentView().clearMsg();		
	}

	@Override
	public void beforeDisplay()
	{
		showInfoMsg("");		
	}

}
