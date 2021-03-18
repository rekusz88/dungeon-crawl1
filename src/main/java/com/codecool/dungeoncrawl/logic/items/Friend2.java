package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Friend2 extends Equipments {
    public Friend2(Cell cell) {
        super(cell);
        this.setStrength(10);
    }

    @Override
    public String getTileName() {
        return "marcsi";
    }

}