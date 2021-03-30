package com.codecool.dungeoncrawl.logic.ingamemenu;

import com.codecool.dungeoncrawl.Main;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class InGameMenu {

    public static void display(String title, String message){
        Stage inGameMenu = new Stage();

        inGameMenu.initModality(Modality.APPLICATION_MODAL);
        inGameMenu.setTitle(title);
        inGameMenu.setMinWidth(200);

        Label label = new Label();
        label.setText(message);

        Button resumeButton = new Button("Resume Game");
        resumeButton.setOnAction(e -> inGameMenu.close());

        Button saveButton = new Button("Save");
        //saveButton.setOnAction(actionEvent -> save());

        Button loadButton = new Button("Load");
        //loadButton.setOnAction(actionEvent -> loadMenu);

        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(actionEvent -> System.exit(0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,resumeButton,saveButton,loadButton,exitGameButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        inGameMenu.setScene(scene);
        inGameMenu.showAndWait();

    }
}
