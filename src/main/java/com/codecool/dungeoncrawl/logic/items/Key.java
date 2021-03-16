package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public class Key implements Drawable {
    private Cell cell;
    public Key(Cell cell) {
        this.cell = cell;
        this.cell.setKey(this);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
