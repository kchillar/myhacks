package com.ajoy.client.codegen.view.settings;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.base.view.theme1.BaseView;
import com.ajoy.client.codegen.main.UIDataModel;
import com.ajoy.model.codegen.DBInfo;
import com.ajoy.model.codegen.ProfileInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class DatabasesView extends BaseView implements View, EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(DatabasesView.class);

	private GridPane gridPane;


	
	//Textfields
	private TextField dbUrl;
	private TextField dbUser;
	private TextField dbPass;
	private TextField dbDriver;

	//New or Edit
	private RadioButton newButton;	
	private RadioButton editButton;

	//Text or Dropdown
	private HBox dbNameBox = new HBox();
	private ComboBox<DBInfo> dbNameList = new ComboBox<DBInfo>();
	private TextField dbName = new TextField();       

	//Checkbox and Buttons
	private CheckBox checkBox = new CheckBox();
	
	private Button clear;	
	private Button test;
	private Button add;
	private Button save;
	private HBox buttonBox = new HBox();

	public DatabasesView()
	{    		
	}

	@Override
	public Parent createJavaFXParentObject(ViewConfig config) 
	{		
		newButton = new RadioButton("New");
		editButton = new RadioButton("Edit");	
		ToggleGroup group = new ToggleGroup();
		newButton.setToggleGroup(group);
		editButton.setToggleGroup(group);
		newButton.setSelected(true);

		HBox newOrEditBox = new HBox();
		newOrEditBox.getChildren().add(newButton);
		newOrEditBox.getChildren().add(editButton);

		Text text0 = new Text("");
		Text text1 = new Text("Name");
		Text text2 = new Text("URL"); 
		Text text3 = new Text("User"); 
		Text text4 = new Text("Pass");
		Text text5 = new Text("Driver Class");
		
		Text text6 = new Text("Use This");
		

		dbUrl = new TextField();
		dbUser = new TextField();
		dbPass = new TextField();
		dbDriver = new TextField();

		//Creating Buttons
		clear = new Button("Clear");
		test = new Button("Test");
		save = new Button("Save");
		add = new Button("Add"); 

		newButton.setOnAction(this);
		editButton.setOnAction(this);
		dbNameList.setOnAction(this);
		checkBox.setOnAction(this);
		clear.setOnAction(this);
		test.setOnAction(this);
		save.setOnAction(this);
		add.setOnAction(this);

		dbDriver.setDisable(true);
		
		setupUIForNew();

		ArrayList<Node> labelList = new ArrayList<Node>();
			
		labelList.add(text0);
		labelList.add(text1);
		labelList.add(text2);
		labelList.add(text3);
		labelList.add(text4);
		labelList.add(text5);
		labelList.add(text6);
		labelList.add(new Label(" "));
		
		
		ArrayList<Node> uiObjectList = new ArrayList<Node>();
		
		uiObjectList.add(newOrEditBox);
		uiObjectList.add(dbNameBox);
		uiObjectList.add(dbUrl);
		uiObjectList.add(dbUser);
		uiObjectList.add(dbPass);
		uiObjectList.add(dbDriver);
		uiObjectList.add(checkBox);
		uiObjectList.add(buttonBox);

		gridPane = UIComponentsBuilder.createGrid(labelList, uiObjectList);
		return gridPane;
	}

	@Override
	public void displayView(String viewName) 
	{
	}

	@Override
	public void handle(ActionEvent event) 
	{
		Object source = event.getSource();

				
		if(source == editButton)
		{
			setupUIForEdit();
		}
		else if(source == newButton)
		{
			setupUIForNew();
		}
		else if(source == test)
		{
			showInfoMsg("need to implement test");
		}
		else if(source == clear)
		{
			showErrorMsg("You have cleared");
			setTextInTextFields(null);
		}
		else if(source == save)
		{
			showErrorMsg("you have save");
		}						
		else if(source == add)
		{
			showInfoMsg("Added db");			
		}				
		else if(source == dbNameList)
		{			
			DBInfo dInfo = dbNameList.getSelectionModel().getSelectedItem();
			setTextInTextFields(dInfo);
			log.info("index: "+dbNameList.getSelectionModel().getSelectedIndex());
		}
		else if(source == checkBox)
		{
			log.info("checkBox isSelected: "+checkBox.isSelected()+" editButton: "+editButton.isSelected()+" newButton: "+newButton.isSelected());
		}	
	}

	public void beforeDisplay()
	{
		log.info("called before view ");
		String pInfo = UIDataModel.get().getSelectedProfile();
		if(pInfo != null)		
			showInfoMsg("DBV Using Profile: - "+pInfo+" - ");		
	}
	
	private void setupUIForNew()
	{
		log.info("setupUIForNew() start");
		//remove the children
		dbNameBox.getChildren().remove(0, dbNameBox.getChildren().size());		
		dbNameBox.getChildren().add(dbName);		
		//reset text
		setTextInTextFields(null);
		//add buttons
		buttonBox.getChildren().remove(0, buttonBox.getChildren().size());
		buttonBox.getChildren().add(clear);
		buttonBox.getChildren().add(test);		
		buttonBox.getChildren().add(add);
		log.info("setupUIForNew() end");
	}

	private void setupUIForEdit()
	{
		log.info("setupUIForEdit() start");
		//clean up the list
		dbNameBox.getChildren().remove(0, dbNameBox.getChildren().size());		
		
		int index = -1;
		List<DBInfo> list = UIDataModel.get().getDatabaseInfoList();
		
		DBInfo selectedDB = null;

		for(DBInfo info : list)
		{
			index++;
			dbNameList.getItems().add(info);					
			if(info.isSelected())			
			{
				dbNameList.getSelectionModel().select(index);
				selectedDB = info;
			}
		}			
				
		dbNameBox.getChildren().add(dbNameList);	
		setTextInTextFields(selectedDB);
		
		
		//setup buttons at the bottom
		buttonBox.getChildren().remove(0, buttonBox.getChildren().size());		
		buttonBox.getChildren().add(clear);
		buttonBox.getChildren().add(test);		
		buttonBox.getChildren().add(save);

		log.info("setupUIForEdit() end");
	}


	private void setTextInTextFields(DBInfo dInfo)
	{
		if(dInfo != null)
		{
			dbName.setText(dInfo.getName());       
			dbUrl.setText(dInfo.getUrl());
			dbUser.setText(dInfo.getUser());
			dbPass.setText(dInfo.getPass());
			dbDriver.setText(dInfo.getDriverClassName());
		}
		else
		{
			dbName.setText(DBInfo.DefaultDBName);       
			dbUrl.setText(DBInfo.DefaultDBURL);
			dbUser.setText(DBInfo.DefaultDBUSER);			
			dbPass.setText(DBInfo.DefaultDBPASS);			
			dbDriver.setText(DBInfo.DefaultDriverClassname);
		}
	}


}
