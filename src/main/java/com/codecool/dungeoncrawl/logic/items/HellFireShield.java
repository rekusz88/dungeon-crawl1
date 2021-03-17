package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class HellFireShield extends Equipments {
    public HellFireShield(Cell cell) {
        super(cell);
        this.setStrength(10);
    }

    @Override
    public String getTileName() {
        return "Hellfire Shield";
    }

}