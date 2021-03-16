package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;
    GameMap map = loadMap("/outside.txt");
    Canvas canvas = loadCanvas();
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    GridPane ui = new GridPane();
    BorderPane borderPane = new BorderPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        stage.setTitle("Dungeon Crawl");
        stage.show();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                if (map.getPlayer().reachedDoor) {
                    map = loadMap("/inside.txt");
                    canvas = loadCanvas();
                }
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                if (map.getPlayer().reachedDoor) {
                    map = loadMap("/inside.txt");
                    canvas = loadCanvas();
                }
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                if (map.getPlayer().reachedDoor) {
                    map = loadMap("/inside.txt");
                    canvas = loadCanvas();
                }
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                if (map.getPlayer().reachedDoor) {
                    map = loadMap("/inside.txt");
                    canvas = loadCanvas();
                }
                refresh();
                break;
            case SPACE:
                map.getPlayer().pickUpItem();
                refresh();
                break;
            case ENTER:
                replaceStage();

                refresh();
                break;
        }
    }

    public GameMap loadMap(String fileName) {
        return MapLoader.loadMap(fileName);
    }

    public Canvas loadCanvas() {
        return new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
    }

    public void replaceStage(){

        borderPane.setCenter(canvas);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        stage.setTitle("Dungeon Crawl");
        stage.show();
    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
