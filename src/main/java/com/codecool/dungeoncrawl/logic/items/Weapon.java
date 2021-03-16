package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public class Weapon implements Drawable {
    private Cell cell;
    public Weapon(Cell cell) {
        this.cell = cell;
        this.cell.setWeapon(this);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
