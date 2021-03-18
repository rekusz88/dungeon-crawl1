package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    protected Actor enemy;


    public Actor(Cell cell,int health,int attack) {
        this.health=health;
        this.attack=attack;
        this.cell = cell;
        this.cell.setActor(this);
    }


    public boolean isEnemy(Cell nextCell){
        System.out.println(nextCell);
       /* if ((enemy = nextCell.getActor()) instanceof Npcs) {
            enemy = nextCell.getActor();
        }
        return enemy.equals(nextCell.getActor());*/

        return Npcs.npcList.stream().anyMatch(npcs -> {
            enemy = npcs;
            return enemy.equals(nextCell.getActor());
        });
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
            if(isEnemy(nextCell)){
                fight(enemy);
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
            else if (nextCell.isFloor(nextCell)) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
    }

    public void fight(Actor enemy){
        while(!isDead(this) || !isDead(enemy)){
            strike(this ,enemy);
            strike(enemy,this);
            if(enemy.health <= 0){
                break;
            }
            else if (this.health <= 0){
                System.out.println("You died!");    // could implement loosing screen
                break;
            }
        }
    }

    private boolean isDead(Actor actor) {
        return actor.getHealth() <= 0;
    }

    private void strike(Actor actor, Actor actor2) {
        actor2.health -= actor.attack;
    }


    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
