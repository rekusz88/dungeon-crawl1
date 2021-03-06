package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Optional;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    public boolean reachedDoor;
    public String doorName;
    public int keys;
    public int weapons;
    public int usedWeapons;
    public int friends;
    protected Actor enemy;
    public boolean isPlayer = false;


    public Actor(Cell cell,int health,int attack) {
        this.health=health;
        this.attack=attack;
        this.cell = cell;
        weapons = keys = usedWeapons = friends = 0;
        this.cell.setActor(this);
    }

    public boolean savedFriends() {
        return friends == 3;
    }

    public boolean isEnemy(Cell nextCell){
       /* if ((enemy = nextCell.getActor()) instanceof Npcs) {
            enemy = nextCell.getActor();
        }
        return enemy.equals(nextCell.getActor());*/

        return Npcs.npcList.stream().anyMatch(npcs -> {
            enemy = npcs;
            return enemy.equals(nextCell.getActor());
        });
    }


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isPlayer) {
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
   }

    public boolean hasKey() {
        if (this.getKeys() != 0) {
            this.takeKey();
            return true;
        } else return false;
    }

    public void fight(Actor enemy){
        if (this.isPlayer && enemy instanceof Skeleton) {
            shakeStage(3);
        }
        if (this.isPlayer && enemy instanceof OPBoss) {
            shakeStage(100);
        }
        while(alive(this) || alive(enemy)){
                strike(this, enemy);
                strike(enemy,this);
                if(enemy.health <= 0){
                    break;
                } else if (this.health <= 0){
                    if (this.isPlayer) {
                        notification("GAME OVER", "You died before making it out of the castle!");
                    }
                    break;
                }
        }
    }

    private void strike(Actor actor, Actor enemy) {
        enemy.health -= actor.attack;
        if (actor.weapons > 0) {
            actor.usedWeapons += 1;
            actor.destroyWeapon();
            actor.takeAttack(2);
        }
    }

    int x = 0;
    int y = 0;

    public void shakeStage(int duration) {
        Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (x == 0) {
                    Main.getStage().setX(Main.getStage().getX() + 10);
                    x = 1;
                } else {
                    Main.getStage().setX(Main.getStage().getX() - 10);
                    x = 0;
                }
            }
        }));

        timelineX.setCycleCount(duration);
        timelineX.setAutoReverse(false);
        timelineX.play();


        Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (y == 0) {
                    Main.getStage().setY(Main.getStage().getY() + 10);
                    y = 1;
                } else {
                    Main.getStage().setY(Main.getStage().getY() - 10);
                    y = 0;
                }
            }
        }));

        timelineY.setCycleCount(duration);
        timelineY.setAutoReverse(false);
        timelineY.play();
    }

    public void checkWhichDoor(Cell nextCell) {
        if (nextCell.getType().equals(CellType.INSIDE)) {
            doorName = "door1";
        } else if (nextCell.getType().equals(CellType.TREASURE)) {
            doorName = "door2";
        } else if (nextCell.getType().equals(CellType.BACK_INSIDE)) {
            doorName = "door3";
        } else if (nextCell.getType().equals(CellType.FINAL)) {
            doorName = "door4";
        } else if (nextCell.getType().equals(CellType.OUTSIDE)) {
            doorName = "door5";
        }
        else if (nextCell.getType().equals(CellType.ENDING)) {
            if (savedFriends()) {
                notification("GOOD ENDING","All of you successfully fled the castle! This party was wild.");
            } else notification("BAD ENDING","You fled the castle, but left your friends to die. Time to sober up.");
        }
    }

    public void notification(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        ButtonType restart = new ButtonType("START OVER");
        ButtonType exit = new ButtonType("GO HOME", ButtonBar.ButtonData.CANCEL_CLOSE);
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

    public void setFriends(int friends){ this.friends = friends; }

    public int getFriends(){ return this.friends; }

    public int getWeapons(){ return this.weapons; }

    public void addKey() { this.keys += 1; }

    public void addWeapon() { this.weapons += 1; }

    public void takeKey() { this.keys -= 1; }

    public void destroyWeapon() { this.weapons -= 1; }

    public void setKeys(int keys) { this.keys = keys; }

    public boolean alive(Actor actor) {
        return actor.getHealth() > 0;
    }

    public int getHealth() { return health; }

    public int getAttack() { return attack; }

    public int getUsedWeapons() { return usedWeapons; }

    public void setAttack(int attack){ this.attack = attack; }

    public void setWeapons(int weapons){ this.weapons = weapons; }

    public void setUsedWeapons(int usedWeapon){ this.usedWeapons = usedWeapon; }

    public void addAttack(int attack){ this.attack += attack; }

    public void takeAttack(int attack){ this.attack -= attack; }

    public Cell getCell() { return cell; }

    public int getX() { return cell.getX(); }

    public int getY() { return cell.getY(); }
}
