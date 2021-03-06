package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable{
    private Cell cell;


    public Item(Cell cell){
        this.cell = cell;
        this.cell.setItem(this);
    }


    public Cell getCell(){
        return cell;
    }

    public int getX(){
        return cell.getX();
    }

    public int getY(){
        return cell.getY();
    }

    public void clearCell(){
        cell.setItem(null);
        cell.setType(CellType.FLOOR);
    }

    @Override
    public String toString(){
        return this.getTileName();
    }


}
