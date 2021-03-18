package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends Npcs{
    public Boss(Cell cell){
        super(cell,3,5);
    }

    @Override
    public String getTileName() {
        return "Boss";
    }
}
