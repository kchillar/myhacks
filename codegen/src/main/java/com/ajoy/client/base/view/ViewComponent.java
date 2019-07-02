package com.ajoy.client.base.view;

import javafx.scene.Node;

/**
 * 
 * @author kalyanc
 *
 */
public interface ViewComponent 
{
	public Node createJavaFXNodeObject(ViewConfig config);
}
