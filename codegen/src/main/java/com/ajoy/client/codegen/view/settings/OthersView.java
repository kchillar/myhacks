package com.ajoy.client.codegen.view.settings;


import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.base.view.theme1.BaseView;
import com.ajoy.client.codegen.main.UIDataModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class OthersView extends BaseView implements View, EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(ProjectProfilesView.class);

	private GridPane gridPane;
	
	//New or Edit
	private TextField dataDir = new TextField();
	private HBox buttonBox = new HBox();
	private Button save = new Button("Save");
	


	public OthersView()
	{    	
	}

	@Override
	public Parent createJavaFXParentObject(ViewConfig config) 
	{					
		Text text1 = new Text("Data Directory");
		dataDir.setText("./data");
		buttonBox.getChildren().add(save);					
		save.setOnAction(this);
				
		ArrayList<Node> leftList = new ArrayList<Node>();
		leftList.add(text1);
		leftList.add(new Label(" "));
		
		ArrayList<Node> rightList = new ArrayList<Node>();
		rightList.add(dataDir);
		rightList.add(buttonBox);
		
		save.setDisable(true);
		dataDir.setDisable(true);
		
		gridPane = UIComponentsBuilder.createGrid(leftList, rightList);
		
		return gridPane;
	}

	
	@Override
	public void displayView(String viewName) 
	{
		// TODO Auto-generated method stub
	}

	public void handle(ActionEvent event) 
	{
		Object source = event.getSource();
				
		if(source == save)
		{
			showErrorMsg("you have save");
		}						

	}
	

	@Override
	public void beforeDisplay()
	{
		showInfoMsg("Changing data dir from UI is not supported yet!!!");		
	}

	
}
