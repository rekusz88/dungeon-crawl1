package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Optional;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int attack;
    public boolean reachedDoor;
    public String doorName;
    private Actor enemy;
    public int keys;


    public Actor(Cell cell,int health,int attack) {
        this.health=health;
        this.attack=attack;
        this.cell = cell;
        keys = 0;
        this.cell.setActor(this);
    }

    public boolean isEnemy(Cell nextCell){
        return NPC.npcList.stream().anyMatch(npcs -> {
            enemy = npcs;
            return enemy.equals(nextCell.getActor());
        });
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isEnemy(nextCell)) {
            fight(enemy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.isDoor(nextCell) && hasKey()) {
            checkWhichDoor(nextCell);
            reachedDoor = true;
        } else if (nextCell.isFloor(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.isInventory(nextCell)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
   }

    public boolean hasKey() {
        if (this.getKeys() != 0) {
            this.takeKey();
            return true;
        } else return false;
    }

    public void fight(Actor enemy){
        while(!isDead(this) || !isDead(enemy)){
            strike(this ,enemy);
            strike(enemy,this);
            if(enemy.health <= 0){
                break;
            } else if (this.health <= 0){
                modal("GAME OVER");
                break;
            }
        }
    }

    public void checkWhichDoor(Cell nextCell) {
        if (nextCell.getType().equals(CellType.INSIDE)) {
            doorName = "door1";
        } else if (nextCell.getType().equals(CellType.TREASURE)) {
            doorName = "door2";
        } else if (nextCell.getType().equals(CellType.BACK_INSIDE)) {
            doorName = "door3";
        } else if (nextCell.getType().equals(CellType.INSIDE2)) {
            doorName = "door4";
        } else if (nextCell.getType().equals(CellType.FINAL)) {
            doorName = "door5";
        } else if (nextCell.getType().equals(CellType.OUTSIDE)) {
            doorName = "door6";
        }
    }

    public void modal(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message);
        alert.setContentText("Would you like to start over?");
        ButtonType restart = new ButtonType("hogy a viharba ne");
        ButtonType exit = new ButtonType("nah", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(restart, exit);
        alert.initStyle(StageStyle.UTILITY);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == exit){
            Platform.exit();
        } else {
            Main.getStage().close();
            Platform.runLater( () -> { try {
                    new Main().start( new Stage() );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void setHealth(int health){ this.health = health; }

    public int getKeys(){ return this.keys; }

    public void addKey() { this.keys += 1; }

    public void takeKey() { this.keys -= 1; }

    public void setKeys(int keys) { this.keys = keys; }

    private boolean isDead(Actor actor) {
        return actor.getHealth() <= 0;
    }

    private void strike(Actor actor, Actor actor2) {
        actor2.health -= actor.attack;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
