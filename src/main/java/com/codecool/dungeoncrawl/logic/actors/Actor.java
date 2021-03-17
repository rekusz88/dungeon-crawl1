package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int attack;
    public boolean reachedDoor;
    public String doorName;

    private Actor enemy;


    public Actor(Cell cell,int health,int attack) {
        this.health=health;
        this.attack=attack;
        this.cell = cell;
        this.cell.setActor(this);
    }

    public boolean isEnemy(Cell nextCell){
        return Npcs.npcList.stream().anyMatch(npcs -> {
            enemy = npcs;
            return enemy.equals(nextCell.getActor());
        });
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isEnemy(nextCell)) {
            System.out.println("enemy");
            fight(enemy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.isDoor(nextCell)) {
            checkWhichDoor(nextCell);
            reachedDoor = true;
            System.out.println("door");
        } else if (nextCell.isFloor(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }else if (nextCell.isInventory(nextCell)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
   }


    public void fight(Actor enemy){
        while(!isDead(this) || !isDead(enemy)){
            strike(this ,enemy);
            strike(enemy,this);
            if(enemy.health <= 0){
                break;
            }
            else if (this.health <= 0){
                System.out.println("You died!");    // could implement loosing screen
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

    public void setHealth(int health){
        this.health = health;
    }

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
