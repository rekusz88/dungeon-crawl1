package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Equipments {
   public Weapon(Cell cell) {
       super(cell);
       this.setStrength(10);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
