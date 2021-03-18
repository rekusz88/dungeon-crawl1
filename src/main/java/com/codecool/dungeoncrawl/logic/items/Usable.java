package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Usable extends Item {
    private String useFor;
    public boolean used;


    public Usable(Cell cell){ super(cell); used = false; }


    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor =useFor;
    }

    public void setUsed(boolean use) { this.used = use; }

    public boolean getUsed() { return used; }
}
