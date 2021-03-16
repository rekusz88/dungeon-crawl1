package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("door", new Tile(4, 13));
        tileMap.put("grass1", new Tile(5, 0));
        tileMap.put("grass2", new Tile(6, 0));
        tileMap.put("grass3", new Tile(7, 0));
        tileMap.put("grass4", new Tile(0, 2));
        tileMap.put("tree1", new Tile(1, 1));
        tileMap.put("tree2", new Tile(2, 1));
        tileMap.put("tree3", new Tile(3, 1));
        tileMap.put("grave1", new Tile(0, 14));
        tileMap.put("grave2", new Tile(1, 14));
        tileMap.put("wall1", new Tile(0, 13));
        tileMap.put("wall2", new Tile(2, 11));
        tileMap.put("wall3", new Tile(3, 11));
        tileMap.put("window1", new Tile(1, 13));
        tileMap.put("window2", new Tile(2, 13));
        tileMap.put("cat", new Tile(30, 7));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
