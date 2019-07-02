package com.ajoy.client.base.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.Parent;

/**
 * 
 * @author kalyanc
 *
 */
public class ViewBuilder 
{
	private static Logger log = LogManager.getLogger(ViewBuilder.class);

	public static Parent buildJavaFXParent(ViewConfig config) 	
	{
		try
		{
			if(config == null)			
				throw new IllegalStateException("Invalid ViewConfig: "+null);

			if(config.getViewBuilderClassname() != null)
			{
				if(log.isDebugEnabled())
					log.debug("using class: "+config.getViewBuilderClassname()+" to create view: "+config.getName());

				Class<?> aClass =  Class.forName(config.getViewBuilderClassname());
				View view = (View) aClass.newInstance();				
				Parent parent = view.createJavaFXParentObject(config);
				parent.setUserData(view);
				return parent;
			}
			else
			{
				log.warn("No view-classname is provided for view :"+config.getName()+", will return null");
				return null;
			}
		}
		catch(Exception exp)
		{
			log.error("Error in creating View instance", exp);
			return null;
		}
	}
}
