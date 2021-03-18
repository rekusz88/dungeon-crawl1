package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Friend1 extends Equipments {
    public Friend1(Cell cell) {
        super(cell);
        this.setStrength(10);
    }

    @Override
    public String getTileName() {
        return "pistike";
    }

}