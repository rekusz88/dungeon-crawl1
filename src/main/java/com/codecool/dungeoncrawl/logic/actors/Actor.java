package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int attack;
    public boolean reachedDoor;
    public String doorName;


    public Actor(Cell cell,int health,int attack) {
        this.health=health;
        this.attack=attack;
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.isFloor(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.isDoor(nextCell)) {
            checkWhichDoor(nextCell);
            reachedDoor = true;
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
