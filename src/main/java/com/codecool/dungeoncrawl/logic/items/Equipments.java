package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Equipments extends Item {
    private int attack;
    private int health;
    private int strength;
    public boolean used;


    public Equipments(Cell cell){ super(cell); used = false; }

    public int getAttack(){
        return attack;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setUsed(boolean use) { this.used = use; }

    public boolean getUsed() { return used; }


}
