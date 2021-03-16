package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    DOOR("door"),
    GRASS1("grass1"),
    GRASS2("grass2"),
    GRASS3("grass3"),
    GRASS4("grass4"),
    TREE1("tree1"),
    TREE2("tree2"),
    TREE3("tree3"),
    GRAVE1("grave1"),
    GRAVE2("grave2"),
    CAT("cat"),
    WALL1("wall1"),
    WALL2("wall2"),
    WALL3("wall3"),
    WINDOW1("window1"),
    WINDOW2("window2"),
    WALL("wall"),
    WEAPON("weapon"),
    HEALTH("health"),
    KEY("key");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
