package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;


import java.util.*;

public class Player extends Actor {
    private Map<Equipments, Integer> equipments = new LinkedHashMap<>();
    private Map<Usable, Integer> usables = new LinkedHashMap<>();
    private String shield;


    public Player(Cell cell) { super(cell,10,1); shield = "none"; }


    public void pickUpItem(){
        Item item = getCell().getItem();
        if (item != null) {
            if (item instanceof Equipments){
                addAttack(((Equipments) item).getStrength());
                addToInventory(equipments, item);
                if (item instanceof Weapon) {
                    addWeapon();
                } else if (item instanceof HellFireShield) {
                    shield = "active (attack +1)";
                } else if (item instanceof Friend1 || item instanceof Friend2 || item instanceof Friend3) {
                    friends += 1;
                }
            } else if (item instanceof Usable){
                addToInventory(usables, item);
                if (item instanceof Key) {
                    addKey();
                }
            } else if (item instanceof Booster) {
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

    public void takeFromInventory(String item) {
        Iterator<Map.Entry<Usable,Integer>> iter = usables.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Usable,Integer> entry = iter.next();
            if(item.equalsIgnoreCase(String.valueOf(entry.getKey()))){
                iter.remove();
                break;
            }
        }
//        takes all of them
//        usables.entrySet().removeIf(entry -> item.equalsIgnoreCase(String.valueOf(entry.getKey())));
    }

    public void takeFromEquipments(String item) {
        Iterator<Map.Entry<Equipments,Integer>> iter = equipments.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Equipments,Integer> entry = iter.next();
            if(item.equalsIgnoreCase(String.valueOf(entry.getKey()))){
                iter.remove();
                break;
            }
        }
//        takes all of them
//        usables.entrySet().removeIf(entry -> item.equalsIgnoreCase(String.valueOf(entry.getKey())));
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

    public String getShieldStatus() { return shield; }

    public void setShieldStatus(String shield) { this.shield = shield; }

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
