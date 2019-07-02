package com.ajoy.client.base.view.theme1;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * 
 * @author kalyanc
 *
 */
public class DisplayEvent extends Event 
{
	public static final String BeforeDisplayEventType = "BeforeDisplay";
	public static final String AfterDisplayEventType = "AfterDisplay";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6063209255916176716L;

	public DisplayEvent(EventType<? extends Event> eventType) 
	{
		super(eventType);	
	}

	
	

}
