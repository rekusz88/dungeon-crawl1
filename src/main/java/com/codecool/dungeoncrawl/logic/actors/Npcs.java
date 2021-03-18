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

    public int getRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }


    public void moveNPCs(){
        for(Actor npc : npcList){
            int dx = getRandomNum(-1, 1);
            int dy = getRandomNum(-1, 1);
            Cell nextCell = cell.getNeighbor(dx, dy);
                if(isEnemy(nextCell)){
                    fight(enemy);
                    cell.setActor(null);
                    nextCell.setActor(npc);
                    cell = nextCell;
                }
                else if (nextCell.isFloor(nextCell)) {
                    cell.setActor(null);
                    nextCell.setActor(npc);
                    cell = nextCell;
                }
        }
    }

    @Override
    public String getTileName() {
        return null;
    }

    public Npcs(Cell cell, int health, int attack) {
        super(cell, health, attack);
    }
}
