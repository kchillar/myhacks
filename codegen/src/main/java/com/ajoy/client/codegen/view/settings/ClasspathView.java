package com.ajoy.client.codegen.view.settings;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.client.base.view.View;
import com.ajoy.client.base.view.ViewConfig;
import com.ajoy.client.base.view.theme1.BaseView;
import com.ajoy.client.codegen.main.UIDataModel;
import com.ajoy.model.codegen.ClassInfo;
import com.ajoy.model.codegen.Classpath;
import com.ajoy.model.codegen.ClasspathEntry;
import com.ajoy.model.codegen.FieldInfo;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

/**
 * 
 * @author kalyanc
 *
 */
public class ClasspathView extends BaseView implements View, EventHandler<ActionEvent>
{
	private static Logger log = LogManager.getLogger(ClasspathView.class);

	private GridPane gridPane;
	private TableView tb;
	private Button clear;
	private Button add;
	private Button save;
	private Button showClasses;
	
	private HBox buttonBox;

	private DirectoryChooser chooser;

	public ClasspathView()
	{		
	}

	public Parent createJavaFXParentObject(ViewConfig config)
	{		
		Text buttonBoxLabel = new Text("");
		
		setupButtons();
				
		List<Node> left = new ArrayList<>();
		List<Node> right = new ArrayList<>();
		
		left.add(buttonBoxLabel);
		right.add(buttonBox);

		buildGridPane(left, right) ;		
		buildTable();
		setupFileChooser();
		
		VBox vbox = new VBox();
		vbox.getChildren().add(tb);
		vbox.getChildren().add(gridPane);
		
		return vbox;
	}

	private void setupButtons()
	{
		buttonBox = new HBox();
		clear = new Button("Remove");
		add = new Button("Add");
		save = new Button("Save");
		showClasses = new Button("Classes");
		
		buttonBox.getChildren().add(clear);
		buttonBox.getChildren().add(add);
		buttonBox.getChildren().add(save);		
		buttonBox.getChildren().add(showClasses);
				
		save.setOnAction(this);
		clear.setOnAction(this);
		add.setOnAction(this);
		showClasses.setOnAction(this);
	}
	
	private void setupFileChooser()
	{
		chooser = new DirectoryChooser();
		//chooser.setInitialDirectory(new File("./"));		
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
				
		tb.getItems().add(new ClasspathEntry("./"));
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
			File dir = chooser.showDialog(UIDataModel.get().getStage());
			if(dir != null)
			{
				log.info("Selected file: "+dir.getPath());
				processDir(dir);
			}
			else
			{
				log.info("Not selected any dir");
			}
		}
		else if(source == save)
		{
			List<ClasspathEntry> list = tb.getItems();
			
			if(list != null && list.size() > 0)
			{
				Classpath cp = new Classpath();
				cp.setClasspathEntryList(list);
				
				UIDataModel.get().updateClasspath(cp);
			}
		}
		else if(source == showClasses)
		{
			List<String> classList = UIDataModel.get().getClasses();
			if(classList != null)
			{
				for(String classname: classList)
				{
					log.info("Got class: "+classname);
					ClassInfo info = new ClassInfo();
					info.setName(classname);
					info = UIDataModel.get().getClassDetails(info);
					
					if(info != null)
					{
						for(FieldInfo f: info.getFieldList())
						{
							log.info(f.getName()+" type: "+f.getClassname());
						}
					}
				}
			}
		}
		
	}
	
	public void beforeDisplay()
	{
		log.info("beforeDisplay() start");
		String pInfo = UIDataModel.get().getSelectedProfile();
		if(pInfo != null)
		{
			showInfoMsg("PV Using Profile: - "+pInfo+" - ");			
		}

		Classpath classpath = UIDataModel.get().getClasspath();
		if(classpath != null && classpath.getClasspathEntryList() != null)
		{
			List<ClasspathEntry> currEntries = tb.getItems();				
			tb.getItems().remove(0, currEntries.size());								
			tb.getItems().addAll(classpath.getClasspathEntryList());
			showInfoMsg("PV Using Profile: - "+pInfo+" - fetched classpaths");
		}
		else	
		{			
		}
		
		
		log.info("beforeDisplay() end");
	}

	
	@Override
	public void displayView(String viewName) 
	{
	}

	public void processDir(File dir)
	{
		log.info("processDir() start "+dir.getPath());		
		List<ClasspathEntry> list = new ArrayList<>();		
		processDir(dir, list);		
		if(list.size() == 0)
			list.add(new ClasspathEntry(dir.getPath(),0));
		
		tb.getItems().addAll(list);		
		tb.getItems().sort(new ClasspathEntryComparator());		
		log.info("processDir() end "+list.size());
	}

	private void processDir(File dir, List<ClasspathEntry> list)
	{				
		for(File file: dir.listFiles())
		{
			if(fileFilter.accept(file))			
				list.add(new ClasspathEntry(file.getPath(),1));
			else if(file.isDirectory())
				processDir(file, list);
		}
	}
	
	
	private FileFilter fileFilter = new FileFilter() 
	{		
		@Override
		public boolean accept(File pathname) 
		{
			if(pathname.isFile() && pathname.getName().endsWith(".jar"))
				return true;
			else
				return false;
		}
	};

}
