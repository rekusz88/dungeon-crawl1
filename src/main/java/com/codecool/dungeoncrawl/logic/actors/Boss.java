package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends NPC {
    public Boss(Cell cell){
        super(cell,7,3);
        npcList.add(this);
    }

    @Override
    public String getTileName() {
        return "Boss";
    }
}
