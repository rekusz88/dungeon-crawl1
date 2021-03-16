package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public  class Health extends Booster {
    public Health(Cell cell) {
        super(cell);
        this.setHealth(1);
    }
    @Override
    public String getTileName() {
        return "health";
    }
}
