package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.actors.Boss;
import com.codecool.dungeoncrawl.logic.actors.Npcs;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private Item item;
    private int x, y;



    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean isFloor(Cell nextCell){
        return nextCell.getType().equals(CellType.FLOOR) || nextCell.getType().equals(CellType.FLOOR2) ||
                nextCell.getType().equals(CellType.FLOOR3) || nextCell.getType().equals(CellType.FLOOR4) ||
                nextCell.getType().equals(CellType.GRASS1) || nextCell.getType().equals(CellType.GRASS2) ||
                nextCell.getType().equals(CellType.GRASS3) || nextCell.getType().equals(CellType.OPENED);
    }

    public boolean isDoor(Cell nextCell){
        return nextCell.getType().equals(CellType.INSIDE) || nextCell.getType().equals(CellType.TREASURE) ||
                nextCell.getType().equals(CellType.BACK_INSIDE) || nextCell.getType().equals(CellType.INSIDE2) ||
                nextCell.getType().equals(CellType.FINAL) || nextCell.getType().equals(CellType.OUTSIDE);
    }

    public boolean isInventory(Cell nextCell){
        return nextCell.getType().equals(CellType.WEAPON) || nextCell.getType().equals(CellType.HEALTH) ||
                nextCell.getType().equals(CellType.KEY) || nextCell.getType().equals(CellType.KEY2) ||
                nextCell.getType().equals(CellType.FRIEND1) || nextCell.getType().equals(CellType.FRIEND2) ||
                nextCell.getType().equals(CellType.FRIEND3);
    }


    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Item getItem() { return item; }

    public void setItem(Item item){
        this.item = item;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
