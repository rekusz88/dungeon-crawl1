package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Booster;
import com.codecool.dungeoncrawl.logic.items.Equipments;
import com.codecool.dungeoncrawl.logic.items.Usable;


import java.util.*;

public class Player extends Actor {
    private Map<Equipments, Integer> equipments = new LinkedHashMap<>();
    private Map<Usable, Integer> usables = new LinkedHashMap<>();


    public Player(Cell cell) {
        super(cell,10,5);
    }


    public void pickUpItem(){
        Item item = getCell().getItem();
        if (item != null) {
            if (item instanceof Equipments){
                addToInventory(equipments, item);
            }
            else if (item instanceof Usable){
                addToInventory(usables, item);
            }
            else if (item instanceof Booster) {
                ((Booster) item).useBooster(this);
            }
            item.clearCell();
        }

    }

    public <K> void addToInventory(Map<K, Integer> inventory, Item item){
        K key = (K) item;
        if (inventory.containsKey(key)){
            inventory.replace(key, inventory.get(key) + 1);
        }
        else {
            inventory.putIfAbsent(key, 1);
        }
    }

    public String getTileName() {
        return "player";
    }

    public Map<Equipments, Integer> getEquipments(){
        return equipments;
    }

    public Map<Usable, Integer> getUsables(){
        return usables;
    }

    public void setUsables(Map<Usable, Integer> usables){
        this.usables = usables;
    }

    public void setEquipments(Map<Equipments, Integer> equipments){
        this.equipments = equipments;
    }

    public <K> String getInventoryItem(Map<K, Integer> inventory) {
        StringBuilder sb = new StringBuilder();
        for (K key : inventory.keySet()) {
            sb.append(key + ": " + inventory.get(key) + "\n");
        }
        return sb.toString();
    }
}
