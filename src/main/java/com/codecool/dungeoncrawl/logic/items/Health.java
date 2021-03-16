package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public class Health implements Drawable{
    private Cell cell;
    public Health(Cell cell) {
        this.cell = cell;
        this.cell.setHealth(this);
    }

    @Override
    public String getTileName() {
        return "health";
    }
}
