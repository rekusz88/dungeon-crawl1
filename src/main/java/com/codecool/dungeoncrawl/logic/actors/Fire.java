package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Fire extends Npcs{
    public Fire(Cell cell){
        super(cell,1,1);
        npcList.add(this);
    }

    @Override
    public String getTileName() {
        return "fire3";
    }
}
