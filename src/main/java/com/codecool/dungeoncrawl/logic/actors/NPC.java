package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;

public class NPC extends Actor {

    public static ArrayList<NPC> npcList = new ArrayList<>();


    public NPC(Cell cell, int health, int attack) {
        super(cell, health, attack);
    }


    @Override
    public String getTileName() {
        return null;
    }
}
