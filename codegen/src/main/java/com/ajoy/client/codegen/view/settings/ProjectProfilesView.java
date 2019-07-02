package com.ajoy.client.codegen.view.settings;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.base.view.theme1.BaseView;
import com.ajoy.client.codegen.main.UIDataModel;
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

/**
 * 
 * @author kalyanc
 *
 */
public class ProjectProfilesView extends BaseView implements View, EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(ProjectProfilesView.class);

	private GridPane gridPane;

	//New or Edit
	private HBox nameBox = new HBox();
	private Text nameFieldLabel = new Text("");
	private TextField nameFieldForNew = new TextField();
	private ComboBox<ProfileInfo> nameFieldListView = new ComboBox<ProfileInfo>();

	
	private Text checkBoxLabel = new Text("Use This");
	private CheckBox checkBox = new CheckBox();
	private RadioButton newButton;	
	private RadioButton editButton;

	private HBox buttonBox = new HBox();
	private Button save = new Button("Save");
	private Button add = new Button("Add");
	private boolean editMode = false;


	public ProjectProfilesView()
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

		HBox newOrEditBox = new HBox();		
		newOrEditBox.getChildren().add(newButton);
		newOrEditBox.getChildren().add(editButton);

		buttonBox.getChildren().add(save);


		nameFieldListView.setOnAction(this);
		checkBox.setOnAction(this);
		newButton.setOnAction(this);
		editButton.setOnAction(this);		
		save.setOnAction(this);
		add.setOnAction(this);

		//setupUIForNew();
		setupUIForEdit();

		ArrayList<Node> labelList = new ArrayList<Node>();
		labelList.add(new Label(" "));
		labelList.add(nameFieldLabel);
		labelList.add(checkBoxLabel);
		labelList.add(new Label(" "));

		ArrayList<Node> uiObjectList = new ArrayList<Node>();
		uiObjectList.add(newOrEditBox);
		uiObjectList.add(nameBox);	
		uiObjectList.add(checkBox);
		uiObjectList.add(buttonBox);

		gridPane = UIComponentsBuilder.createGrid(labelList, uiObjectList);

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

		if(source == editButton)
		{
			nameFieldForNew.setText("");
			setupUIForEdit();
		}
		else if(source == newButton)
		{
			setupUIForNew();
		}
		else if(source == save)
		{			
			ProfileInfo pInfo = nameFieldListView.getSelectionModel().getSelectedItem();
			String msg = "Error in updating the selected profile.";
			
			if(pInfo != null)			
			{
				pInfo.setSelected(true);
				msg = UIDataModel.get().updateProfile(pInfo);
			}
			else
			{
				log.info("pInfo is null");
			}
			showErrorMsg(msg);
		}						
		else if(source == add)
		{
			showErrorMsg("you have added");
			if(nameFieldForNew.getText().trim().length() > 1)
			{
				ProfileInfo pInfo = new ProfileInfo();
				pInfo.setName(nameFieldForNew.getText());
				pInfo.setSelected(checkBox.isSelected());

				List<ProfileInfo> list = UIDataModel.get().getProfileInfoList();
				
				boolean validationError = false;
				
				if(list != null) 				
					for(ProfileInfo tmp: list)	
					{
						validationError = tmp.getName().equalsIgnoreCase(pInfo.getName());
						log.info("tmp "+tmp.getName()+" pInfo: "+pInfo.getName()+" validationError: "+validationError);
					}
				
				String msg = "";
				if(!validationError)				
					 msg = UIDataModel.get().addProfile(pInfo);				
				else
					msg = "Duplicate profile name: "+pInfo.getName()+". Note Profile names are case INSENTIVE";
				showInfoMsg(msg);
			}
			else
			{
				showInfoMsg("Profile name should be atleast two characters long");
			}
		}				
		else if(source == nameFieldListView)
		{			
			ProfileInfo pInfo = nameFieldListView.getSelectionModel().getSelectedItem();

			if(pInfo != null)
			{
				if(pInfo.isSelected())
				{
					checkBox.setSelected(pInfo.isSelected());
					checkBox.setDisable(true);//disable ability to uncheck;	
					save.setDisable(true);
				}
				else
				{
					checkBox.setSelected(false);
					checkBox.setDisable(false);	
					save.setDisable(true);
				}
				log.info("index: "+nameFieldListView.getSelectionModel().getSelectedIndex());
			}
			else
			{
				save.setDisable(true);
				log.info("pInfo is null from nameFieldListView");
			}
		}
		else if(source == checkBox)
		{
			if(editMode)
			{
				ProfileInfo pInfo = nameFieldListView.getSelectionModel().getSelectedItem();
					
				if(!pInfo.isSelected())
				{
					if(checkBox.isSelected())					
						save.setDisable(false);					
					else
						save.setDisable(true);
				}
			}
		}
	}

	private void setupUIForNew()
	{
		log.info("setupUIForNew() start");
		editMode = false;
		nameFieldLabel.setText("Profile Name ");

		checkBox.setSelected(true);
		checkBox.setDisable(false);

		//remove the children
		nameBox.getChildren().remove(0, nameBox.getChildren().size());		
		nameBox.getChildren().add(nameFieldForNew);		

		//add buttons
		buttonBox.getChildren().remove(0, buttonBox.getChildren().size());
		buttonBox.getChildren().add(add);
		log.info("setupUIForNew() end");
	}

	private void setupUIForEdit()
	{
		log.info("setupUIForEdit() start");
		editMode = true;		
		setupProfileList();

		nameFieldLabel.setText("Profile Name ");
		editButton.setSelected(true);
		nameBox.getChildren().remove(0, nameBox.getChildren().size());		
		nameBox.getChildren().add(nameFieldListView);	

		//setup buttons at the bottom
		buttonBox.getChildren().remove(0, buttonBox.getChildren().size());		
		buttonBox.getChildren().add(save);

		log.info("setupUIForEdit() end");
	}

	private void setupProfileList()
	{		
		nameFieldListView.getItems().clear();		
		int index = -1;
		List<ProfileInfo> list = UIDataModel.get().getProfileInfoList();
		if(list != null && list.size() > 0)
		{							
			for(ProfileInfo pInfo: list)	
			{
				if(log.isDebugEnabled())
					log.debug("Got profile "+pInfo.getName()+" selected: "+pInfo.isSelected());
				nameFieldListView.getItems().add(pInfo);
				index++;
				if(pInfo.isSelected())
				{
					nameFieldListView.getSelectionModel().select(index);
					checkBox.setSelected(true);
					checkBox.setDisable(true);
					save.setDisable(true);
				}
			}
		}
	}

	public void beforeDisplay()
	{
		log.info("called before view ");
		String pInfo = UIDataModel.get().getSelectedProfile();
		if(pInfo != null)		
			showInfoMsg("PRV Using Profile: - "+pInfo+" - ");		
	}


}
