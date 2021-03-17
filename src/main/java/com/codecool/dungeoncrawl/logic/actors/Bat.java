package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Npcs{
    public Bat(Cell cell){
        super(cell,2,2);
        npcList.add(this);
    }

    @Override
    public String getTileName() {
        return "Bat";
    }
}
