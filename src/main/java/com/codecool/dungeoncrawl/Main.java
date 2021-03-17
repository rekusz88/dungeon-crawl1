package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Usable;
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
import java.io.File;
import java.util.Map;

public class Main extends Application {
    GameMap map = loadMap("/outside.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Label inventoryLabel2 = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.getStyleClass().add("ui");
        Label health = new Label("Health: ");
        ui.add(health, 0, 0, 1, 1);
        ui.add(healthLabel, 1, 0, 1, 1);

        Label inventory1 = new Label("Inventory: ");
        ui.add(inventory1, 0, 1, 1, 1);
        ui.add(inventoryLabel, 0, 2, 1, 1);

        Label inventory2 = new Label("Inventory: ");
        ui.add(inventory2, 0, 1, 1, 1);
        ui.add(inventoryLabel2, 0, 2, 1, 1);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        File f = new File("src/main/java/com/codecool/dungeoncrawl/stylesheet.css");
        borderPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                changeMap();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                changeMap();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                changeMap();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                changeMap();
                refresh();
                break;
            case SPACE:
                map.getPlayer().pickUpItem();
                refresh();
                break;
        }
    }

    public GameMap loadMap(String fileName) {
        return MapLoader.loadMap(fileName);
    }

    public void changeMap() {
        if (map.getPlayer().reachedDoor) {
            Player playerBeforeDoor = map.getPlayer();
            switch (map.getPlayer().doorName) {
                case "door1":
                    map = loadMap("/inside.txt");
                    retainPlayer(playerBeforeDoor);
                    break;
                case "door2":
                    map = loadMap("/treasure_room.txt");
                    retainPlayer(playerBeforeDoor);
                    map.getPlayer().addKey();
                    break;
                case "door3":
                    map = loadMap("/back_inside.txt");
                    retainPlayer(playerBeforeDoor);
                    break;
                case "door4":
                    map = loadMap("/inside2.txt");
                    retainPlayer(playerBeforeDoor);
                    break;
                case "door5":
                    map = loadMap("/boss_room.txt");
                    retainPlayer(playerBeforeDoor);
                    break;
                case "door6":
                    map = loadMap("/outside2.txt");
                    retainPlayer(playerBeforeDoor);
                    map.getPlayer().addKey();
                    break;
            }
            map.getPlayer().takeFromInventory("key");
        }
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
        inventoryLabel.setText("" + map.getPlayer().getInventoryItem(map.getPlayer().getUsables()));
        inventoryLabel2.setText("\n" +"" + map.getPlayer().getInventoryItem(map.getPlayer().getEquipments()));
    }

    public void retainPlayer(Player playerBeforeDoor) {
        map.getPlayer().setHealth(playerBeforeDoor.getHealth());
        map.getPlayer().setUsables(playerBeforeDoor.getUsables());
        map.getPlayer().setEquipments(playerBeforeDoor.getEquipments());
        map.getPlayer().setKeys(playerBeforeDoor.getKeys());
    }
}
