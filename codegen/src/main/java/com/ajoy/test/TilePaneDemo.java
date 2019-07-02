package com.ajoy.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TilePaneDemo extends Application
{

	public static void main(String[] args) 
	{
        launch(args);
    }
 
    @Override 
    public void start(Stage stage)
    {
        stage.setTitle("TitledPane");
        Scene scene = new Scene(new Group(), 80, 180);
        scene.setFill(Color.GHOSTWHITE);
                               
        final Accordion accordion = new Accordion ();        
        
        TitledPane tp = createTitledPane();
        
        accordion.getPanes().add(tp);
        accordion.setExpandedPane(tp);
 
        Group root = (Group)scene.getRoot();
        root.getChildren().add(accordion);
        stage.setScene(scene);
        stage.show();
    }
	
	TitledPane createTitledPane()
	{
		TitledPane tp = new TitledPane();
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("First Name: "), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label("Last Name: "), 0, 1);
		grid.add(new TextField(), 1, 1);
		grid.add(new Label("Email: "), 0, 2);
		grid.add(new TextField(), 1, 2);        
		tp.setText("Grid");
		tp.setContent(grid);
		
		tp.setCollapsible(true);
		//prohibit animating
		//tp.setAnimated(false);
		
		return tp;
	}
}