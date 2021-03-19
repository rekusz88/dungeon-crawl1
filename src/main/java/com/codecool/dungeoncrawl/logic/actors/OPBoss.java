package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class OPBoss extends Npcs {
    public OPBoss(Cell cell){
        super(cell,20,20);
    }

    @Override
    public String getTileName() {
        return "OPBoss";
    }
}
