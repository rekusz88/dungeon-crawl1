package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    FLOOR2("floor2"),
    FLOOR3("floor3"),
    FLOOR4("floor4"),
    INSIDE("inside"),
    OUTSIDE("outside"),
    TREASURE("treasure"),
    BACK_INSIDE("back-inside"),
    INSIDE2("inside2"),
    FINAL("final"),
    OPENED("opened"),
    GRASS1("grass1"),
    GRASS2("grass2"),
    GRASS3("grass3"),
    GRASS4("grass4"),
    TREE1("tree1"),
    TREE2("tree2"),
    TREE3("tree3"),
    GRAVE1("grave1"),
    GRAVE2("grave2"),
    FIRE("fire"),
    FIRE2("fire2"),
    CAT("cat"),
    FRIEND1("friend1"),
    FRIEND2("friend2"),
    FRIEND3("friend3"),
    WALL1("wall1"),
    WALL2("wall2"),
    WALL3("wall3"),
    WALL4("wall4"),
    KEY2("key2"),
    WINDOW1("window1"),
    WINDOW2("window2"),
    WALL("wall"),
    WEAPON("weapon"),
    HEALTH("health"),
    FIRE3("fire3"),
    ENDING("ending"),
    SHIELD("Fire Shield"),
    KEY("key");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
