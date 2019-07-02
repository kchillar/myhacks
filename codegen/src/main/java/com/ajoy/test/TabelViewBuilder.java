package com.ajoy.test;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ajoy.model.codegen.ClasspathEntry;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

public class TabelViewBuilder implements EventHandler<ActionEvent>
{
	private static Log log = new Log(Test.class);
	
	private GridPane gridPane;
	private TableView tb;
	private Button clear;
	private Button add;
	private Button save;
	private TextField patternField;
	private HBox buttonBox;

	private DirectoryChooser chooser;

	public TabelViewBuilder()
	{		
	}

	public Parent build()
	{
		Text currProfileLabel = new Text("Curernt Profile");
		Text patternLabel = new Text("Pattern");
		Text buttonBoxLabel = new Text("");

		Text currProfile = new Text("-- DEV -- ");
		patternField = new TextField();
		
		setupButtons();
				
		List<Node> left = new ArrayList<>();
		List<Node> right = new ArrayList<>();

		left.add(currProfileLabel);
		left.add(patternLabel);
		left.add(buttonBoxLabel);

		right.add(currProfile);
		right.add(patternField);
		right.add(buttonBox);

		buildGridPane(left, right) ;		
		buildTable();
		setupFileChooser();
		
		VBox vbox = new VBox();
		vbox.getChildren().add(gridPane);
		vbox.getChildren().add(tb);

		return vbox;
	}

	private void setupButtons()
	{
		buttonBox = new HBox();
		clear = new Button("Remove");
		add = new Button("Add");
		save = new Button("Save");
		buttonBox.getChildren().add(clear);
		buttonBox.getChildren().add(add);
		buttonBox.getChildren().add(save);
				
		clear.setOnAction(this);
	}
	
	private void setupFileChooser()
	{
		chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File("./"));		
	}
	
	private void buildGridPane(List<Node> leftList, List<Node> rightList)
	{
		gridPane = new GridPane();      
		gridPane.setMinSize(200, 200); 
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
	}

	private void buildTable()
	{
		tb = new TableView();
		tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tb.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		TableColumn<String, ClasspathEntry> tc1 = new TableColumn<>("Classpath Entry");
		tc1.setCellValueFactory(new PropertyValueFactory<>("path"));
		TableColumn<String, ClasspathEntry> tc2 = new TableColumn<>("Description");
		tc2.setCellValueFactory(new PropertyValueFactory<>("typeDesc"));

		ScrollBar hScrollBar = findScrollBar( tb, Orientation.HORIZONTAL);
		ScrollBar vScrollBar = findScrollBar( tb, Orientation.VERTICAL);

		tb.getColumns().add(tc1);
		tb.getColumns().add(tc2);
		
		for(int i=0; i<100; i++)
			tb.getItems().add(new ClasspathEntry("./dir"+i));
	}

	private ScrollBar findScrollBar(TableView<?> table, Orientation orientation) 
	{
		Set<Node> set = table.lookupAll(".scroll-bar");
		for( Node node: set) 
		{
			ScrollBar bar = (ScrollBar) node;
			if( bar.getOrientation() == orientation) 
			{
				return bar;
			}
		}
		return null;
	}

	@Override
	public void handle(ActionEvent event) 
	{
		Object source = event.getSource();
		
		if(source == clear)
		{
			ObservableList<ClasspathEntry> selectedRows = tb.getSelectionModel().getSelectedItems();			
			tb.getItems().removeAll(selectedRows);
		}
		else if(source == add)
		{			
			File file = chooser.showDialog(null);
			log.info("Selected file: "+file.getPath());
		}		
	}
	
	

}