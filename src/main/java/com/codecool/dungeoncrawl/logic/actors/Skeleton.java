package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Npcs {
    public Skeleton(Cell cell) {
        super(cell,6,1);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
