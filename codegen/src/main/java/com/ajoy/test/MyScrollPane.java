package com.ajoy.test;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyScrollPane extends Application 
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();
        root.getChildren().add(new Label("Select Number of Checkboxes you feel like clicking"));

        VBox vBox = new VBox();
        for (int i = 0; i < 5; i++)
            vBox.getChildren().add(new CheckBox("i:" + i));

        ScrollPane scrollPane = new ScrollPane(vBox);
        //Easily changeable Max Height
        scrollPane.setMaxHeight(10);

        // Create TitledPane.
        TitledPane titledPane = new TitledPane("Check Boxes", scrollPane);
        //Add to Accordion
        Accordion accordion = new Accordion(titledPane);
        //Add to root VBox
        root.getChildren().add(accordion);

        root.getChildren().add(new Label("Some Other Content"));

        stage  = new Stage();
        stage.setHeight(200);
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }

}