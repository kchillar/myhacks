package com.ajoy.client.codegen.view.settings;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class UIComponentsBuilder 
{
	private static Logger log = LogManager.getLogger(UIComponentsBuilder.class);
	
	public static GridPane createGrid(List<Node> leftList, List<Node> rightList)
	{		
		GridPane gridPane = new GridPane();      
		gridPane.setMinSize(800, 600); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 

		//Setting the vertical and horizontal gaps between the columns 
		gridPane.setVgap(5); 
		gridPane.setHgap(5);       
		gridPane.setAlignment(Pos.TOP_LEFT); 

		int size = leftList.size();
		
		for(int i=0; i<size; i++)
		{
			gridPane.add(leftList.get(i), 0, (i+1));						
			gridPane.add(rightList.get(i), 1, (i+1));			
		}
		
		return gridPane;
	}



	
}
