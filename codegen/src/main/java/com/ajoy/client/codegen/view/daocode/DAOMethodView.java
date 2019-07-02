package com.ajoy.client.codegen.view.daocode;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.base.view.theme1.BaseView;
import com.ajoy.client.codegen.main.UIDataModel;
import com.ajoy.model.codegen.DAOInfo;
import com.ajoy.model.codegen.DAOMethodInfo;
import com.ajoy.model.codegen.DBTable;
import com.ajoy.model.codegen.FieldInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * @author kalyanc
 *
 */
public class DAOMethodView extends BaseView implements View, EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(DAOMethodView.class);
	private static final String emptyLabel = "        ";

	
	private ComboBox<DAOInfo> daoNameList = new ComboBox<DAOInfo>();
	private TextField daoMethodNameField = new TextField();
	private ComboBox<DAOMethodInfo> daoMethodNameList = new ComboBox<DAOMethodInfo>();	
	private ComboBox<String> sqlTypeList = new ComboBox<>();	
	private CheckBox singleMulti = new CheckBox();
	private ComboBox<DBTable> dbTableList = new ComboBox<>();
	private ComboBox<FieldInfo> inputObjectList = new ComboBox<>();
	private ComboBox<FieldInfo> outputObjectList = new ComboBox<>();
	private ComboBox<String> javaCollectionList = new ComboBox<>();


	private Button cancel;
	private Button save;
	private HBox buttonBox = new HBox();


	public DAOMethodView()
	{		
	}

	private int addEmpty(GridPane gp, int row)
	{
		gp.add(new Text(emptyLabel), 0, row);
		gp.add(new Text(emptyLabel), 1, row);
		gp.add(new Text(emptyLabel), 2, row++);

		return row;
	}

	public Parent createJavaFXParentObject(ViewConfig config)
	{
		setupButtons();
		fetchDAOInfoList();
		setupSqlTypeList();
		setupDBTableList();
		setupInputObjectList();
		setupOutputObjectList();

		TitledPane addPane = new TitledPane();
		addPane.setText("Define Query");

		GridPane gp1  = new GridPane();	      
		gp1.setPadding(new Insets(10, 10, 10, 10));
	
		int row = 0;

		Text daoName = new Text("DAO");
		gp1.add(daoName, 0, row);
		gp1.add(daoNameList, 1, row);
		gp1.add(new Text(emptyLabel), 2, row);

		row = addEmpty(gp1, ++row);

		
		gp1.add(new Text("SQL Type"), 0, row);
		gp1.add(sqlTypeList, 1, row);
		
		HBox multiBox = new HBox();
		multiBox.getChildren().add(new Text("Multi "));
		multiBox.getChildren().add(singleMulti);		
		gp1.add(multiBox, 2, row);
		row = addEmpty(gp1, ++row);

		gp1.add(new Text("Use Table(s) "), 0, row);
		gp1.add(dbTableList, 1, row);
		Button tableToggle = new Button("Add");		
		gp1.add(tableToggle, 2, row);
		row = addEmpty(gp1, ++row);

		gp1.add(new Text("Use Input Object(s) "), 0, row);
		gp1.add(inputObjectList, 1, row);
		Button inputObjectToggle = new Button("Add");		
		gp1.add(inputObjectToggle, 2, row);
		row = addEmpty(gp1, ++row);

		gp1.add(new Text("Use Output Object "), 0, row);
		gp1.add(outputObjectList, 1, row);
		Button outputObjectToggle = new Button("Add");		
		gp1.add(outputObjectToggle, 2, row);
		row = addEmpty(gp1, ++row);

		
		Text daoMethodName = new Text("Method Name ");
		daoMethodNameField.setMinWidth(60);			
		gp1.add(daoMethodName, 0, row);		  
		gp1.add(daoMethodNameField, 1, row);
		gp1.add(new Text(emptyLabel), 2, row);
		row = addEmpty(gp1, ++row);

		gp1.add(new Text(emptyLabel), 0, row);		  
		gp1.add(buttonBox, 1, row);
		gp1.add(new Text(emptyLabel), 2, row);
		row = addEmpty(gp1, ++row);

		VBox vb1 = new VBox();
		vb1.getChildren().add(gp1);

		addPane.setContent(vb1);

		// Create Second TitledPane.
		TitledPane editPane = new TitledPane();
		editPane.setText("Create Mappings");

		VBox content2 = new VBox();
		content2.getChildren().add(new Label("CShape Tutorial for Beginners"));
		content2.getChildren().add(new Label("CShape Enums Tutorial"));

		editPane.setContent(content2);

		Accordion root= new Accordion();      
		root.getPanes().addAll(addPane, editPane);
		return root;

	}


	private void setupButtons()
	{
		cancel = new Button("Cancel");
		cancel.setOnAction(this);		
		save = new Button("Save");
		save.setOnAction(this);
		buttonBox.getChildren().add(cancel);
		buttonBox.getChildren().add(save);
	}

	private void fetchDAOInfoList()
	{
		List<DAOInfo> list = UIDataModel.get().getDaoInfoList();

		if(list != null && list.size() > 0)		
			daoNameList.getItems().addAll(list);
		else
			log.warn("Got empty list");
	}

	private void setupSqlTypeList()
	{
		List<String> list = new ArrayList<String>(4);
		list.add("Insert");
		list.add("Select");
		list.add("Update");
		list.add("Prodcedure");
		sqlTypeList.getItems().addAll(list);
	}

	private void setupDBTableList()
	{
		List<DBTable> list = new ArrayList<>(3);
		DBTable table;
		table = new DBTable();
		table.setName("txn_main_tbl");
		list.add(table);

		table = new DBTable();
		table.setName("cust_info_tbl");
		list.add(table);

		table = new DBTable();
		table.setName("cust_profile_tbl");
		list.add(table);

		dbTableList.getItems().addAll(list);
	}

	private void setupInputObjectList()
	{
		List<FieldInfo> list = new ArrayList<>(3);
		FieldInfo info;
		info = new FieldInfo();
		info.setClassname("com.alacriti.model.DataVO");
		list.add(info);

		info = new FieldInfo();
		info.setClassname("com.alacriti.model.Bank");
		list.add(info);

		inputObjectList.getItems().addAll(list);
	}

	private void setupOutputObjectList()
	{
		List<FieldInfo> list = new ArrayList<>(3);
		FieldInfo info;
		info = new FieldInfo();
		info.setClassname("com.alacriti.model.DataVO");
		list.add(info);

		info = new FieldInfo();
		info.setClassname("com.alacriti.model.Bank");
		list.add(info);

		outputObjectList.getItems().addAll(list);
	}


	@Override
	public void handle(ActionEvent event) 
	{
		Object source = event.getSource();

		if(source == save)
		{
			if(daoMethodNameField.getText().trim().length() > 0)
			{				
				DAOMethodInfo info = new DAOMethodInfo(daoMethodNameField.getText().trim());

				daoMethodNameList.getItems().add(info);
				log.info("Added");
			}
		}
		else if(source == cancel)
		{
			DAOMethodInfo dao = daoMethodNameList.getSelectionModel().getSelectedItem();
			daoMethodNameList.getItems().remove(dao);
			log.info("Removed "+dao);
		}
		else if(source == save)
		{
			List<DAOMethodInfo> list = daoMethodNameList.getItems();

			if(list.size() > 0)
			{
				DAOInfo daoInfo = new DAOInfo();
				daoInfo.setMethodList(list);
				//UIDataModel.get().updateDaoInfo(daoInfo);
			}
		}
	}

	@Override
	public void displayView(String viewName) 
	{
	}

}
