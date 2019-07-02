package com.ajoy.client.codegen.view.daocode;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.base.view.theme1.BaseView;
import com.ajoy.client.codegen.main.UIDataModel;
import com.ajoy.model.codegen.DAOInfo;
import com.ajoy.model.codegen.DAOS;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DAOView extends BaseView implements View, EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(DAOView.class);

	private GridPane gridPane;
	
	private TextField daoNameField = new TextField();
	private ComboBox<DAOInfo> daoNameList = new ComboBox<DAOInfo>();
	private Button add;
	private Button remove;
	private Button save;
	

	public DAOView()
	{		
	}

	public Parent createJavaFXParentObject(ViewConfig config)
	{		
		gridPane = new GridPane();      
		gridPane.setMinSize(500, 500); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		//Setting the vertical and horizontal gaps between the columns 
		gridPane.setVgap(5); 
		gridPane.setHgap(5);       
		gridPane.setAlignment(Pos.TOP_LEFT); 
		
		setupButtons();
		Text daoName = new Text("DAO Name");
		Text daoNameList = new Text("Existing DAO");
		
		daoNameField.setMinWidth(60);
		
		gridPane.add(daoName, 0, 0);
		gridPane.add(daoNameField, 1, 0);
		gridPane.add(add, 2, 0);

		gridPane.add(daoNameList, 0, 1);
		gridPane.add(this.daoNameList, 1, 1);
		gridPane.add(remove, 2, 1);
		
		String emptyLabel = "        ";
							
		gridPane.add(new Text(emptyLabel), 0, 2);
		gridPane.add(new Text(emptyLabel), 1, 2);
		gridPane.add(save, 2, 2);
		
		fetchDAOInfoList();
		
		return gridPane;
	}

	private void setupButtons()
	{
		add = new Button("Add");
		add.setOnAction(this);
		remove = new Button("Remove");
		remove.setOnAction(this);		
		save = new Button("Save");
		save.setOnAction(this);
	}
	
	private void fetchDAOInfoList()
	{
		List<DAOInfo> list = UIDataModel.get().getDaoInfoList();
		
		if(list != null && list.size() > 0)		
			daoNameList.getItems().addAll(list);
		else
			log.warn("Got empty list");
	}
	
	@Override
	public void handle(ActionEvent event) 
	{
		Object source = event.getSource();
		
		if(source == add)
		{
			if(daoNameField.getText().trim().length() > 0)
			{				
				DAOInfo info = new DAOInfo(daoNameField.getText().trim());
				
				daoNameList.getItems().add(info);
				log.info("Added");
			}
		}
		else if(source == remove)
		{
			DAOInfo dao = daoNameList.getSelectionModel().getSelectedItem();
			daoNameList.getItems().remove(dao);
			log.info("Removed "+dao);
		}
		else if(source == save)
		{
			List<DAOInfo> list = daoNameList.getItems();
			
			if(list.size() > 0)
			{
				DAOS daos = new DAOS();
				daos.setDaoList(list);
				UIDataModel.get().updateDaos(daos);
			}
		}
	}

	@Override
	public void displayView(String viewName) 
	{
	}

}
