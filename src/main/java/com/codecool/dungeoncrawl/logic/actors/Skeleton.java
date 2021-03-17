package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends NPC {
    public Skeleton(Cell cell) {
        super(cell,5,1);
        npcList.add(this);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
