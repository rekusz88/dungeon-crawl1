package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Booster;
import com.codecool.dungeoncrawl.logic.items.Equipments;
import com.codecool.dungeoncrawl.logic.items.Usable;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private List<Equipments> equipments = new LinkedList<>();
    private List<Usable> usables = new LinkedList<>();


    public Player(Cell cell) {
        super(cell,10,5);
    }


    public void pickUpItem(){
        Item item = getCell().getItem();
        if (item != null) {
            if (item instanceof Equipments){
                equipments.add((Equipments) item);
            }
            else if (item instanceof Usable){
                usables.add((Usable) item);
            }
            else if (item instanceof Booster) {
                ((Booster) item).useBooster(this);
            }
            item.clearCell();
        }

    }

    public String getTileName() {
        return "player";
    }
}
