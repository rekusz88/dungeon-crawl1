package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Npcs extends Actor {

    public static ArrayList<Npcs> getNpcList() {
        return npcList;
    }

    public static ArrayList<Npcs> npcList = new ArrayList<>();

    public int getRandomNum() { return new Random().nextBoolean() ? -1 : 1; }

    public void moveNPCs(){
            int dx = getRandomNum();
            int dy = getRandomNum();
            Cell nextCell = cell.getNeighbor(dx, dy);
                if(isEnemy(nextCell)){
                    fight(enemy);
                    if (this.isDead(this)) {
                        cell.setActor(null);
                    } else {
                        cell.setActor(null);
                        nextCell.setActor(this);
                        cell = nextCell;
                    }

                } else if (nextCell.isFloor(nextCell)) {
                    cell.setActor(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                }
        }

    @Override
    public String getTileName() {
        return null;
    }

    public Npcs(Cell cell, int health, int attack) {
        super(cell, health, attack);
        npcList.add(this);
    }
}
