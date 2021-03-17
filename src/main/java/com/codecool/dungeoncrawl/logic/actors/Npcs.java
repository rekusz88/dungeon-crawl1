package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;

public class Npcs extends Actor {

    public static ArrayList<Npcs> npcList = new ArrayList<>();


    public Npcs(Cell cell, int health, int attack) {
        super(cell, health, attack);
    }


    @Override
    public String getTileName() {
        return null;
    }
}
