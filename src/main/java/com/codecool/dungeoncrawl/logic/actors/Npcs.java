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

    public int getRandomNum(int a, int b) { return new Random().nextBoolean() ? a : b; }

    public void moveNPCs(){
            int dx = getRandomNum(1, -1);
            int dy = getRandomNum(1, -1);
            int xBeforeMove = cell.getX();
            int yBeforeMove = cell.getY();
            int XorY = getRandomNum(xBeforeMove, yBeforeMove);
            Cell nextCell;
            if (XorY == xBeforeMove) {
                nextCell = cell.getNeighbor(0, dy);
            } else {
                nextCell = cell.getNeighbor(dx, 0);
            }
            if(isEnemy(nextCell)){
                fight(enemy);
                if (this.getHealth() <= 0) {
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
