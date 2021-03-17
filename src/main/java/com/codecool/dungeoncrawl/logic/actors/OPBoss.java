package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class OPBoss extends NPC {
    public OPBoss(Cell cell){
        super(cell,15,6);
        npcList.add(this);
    }

    @Override
    public String getTileName() {
        return "OPBoss";
    }
}
