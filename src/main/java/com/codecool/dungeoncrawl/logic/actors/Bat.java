package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Npcs {
    public Bat(Cell cell){
        super(cell,1,2);
    }

    @Override
    public String getTileName() {
        return "Bat";
    }
}
