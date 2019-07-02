package com.ajoy.client.base.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.Node;

/**
 * 
 * @author kalyanc
 *
 */
public class ViewComponentBuilder 
{
	private static Logger log = LogManager.getLogger(ViewComponentBuilder.class);

	public static Node buildJavaFXNode(ViewConfig config) 	
	{
		try
		{
			if(config == null)			
				throw new IllegalStateException("Invalid ViewConfig: "+null);

			if(config.getComponentClass() != null)
			{
				if(log.isDebugEnabled())
					log.debug("using class: "+config.getComponentClass()+" to create view: "+config.getName());

				Class<?> aClass =  Class.forName(config.getComponentClass());
				ViewComponent viewComponent = (ViewComponent) aClass.newInstance();
				Node node = viewComponent.createJavaFXNodeObject(config);
				node.setUserData(viewComponent);
				return node;
			}
			else
			{
				log.warn("No component-class is provided for view :"+config.getName()+", will return null");
				return null;
			}
		}
		catch(Exception exp)
		{
			log.error("Error in creating ViewComponent instance", exp);
			return null;
		}
	}
}
