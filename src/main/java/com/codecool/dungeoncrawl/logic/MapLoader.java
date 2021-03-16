package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Bat;
import com.codecool.dungeoncrawl.logic.actors.Boss;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Health;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'D':
                            cell.setType(CellType.FLOOR);
                            new Bat(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Boss(cell);
                            break;
                        case 'x':
                            cell.setType(CellType.DOOR);
                            break;
                        case '-':
                            cell.setType(CellType.GRASS1);
                            break;
                        case ',':
                            cell.setType(CellType.GRASS2);
                            break;
                        case '_':
                            cell.setType(CellType.GRASS3);
                            break;
                        case ':':
                            cell.setType(CellType.GRASS4);
                            break;
                        case 'T':
                            cell.setType(CellType.TREE1);
                            break;
                        case 't':
                            cell.setType(CellType.TREE2);
                            break;
                        case 'Q':
                            cell.setType(CellType.TREE3);
                            break;
                        case '>':
                            cell.setType(CellType.GRAVE1);
                            break;
                        case '<':
                            cell.setType(CellType.GRAVE2);
                            break;
                        case 'C':
                            cell.setType(CellType.CAT);
                            break;
                        case '%':
                            cell.setType(CellType.WALL1);
                            break;
                        case '/':
                            cell.setType(CellType.WALL2);
                            break;
                        case '=':
                            cell.setType(CellType.WALL3);
                            break;
                        case '?':
                            cell.setType(CellType.WINDOW1);
                            break;
                        case '!':
                            cell.setType(CellType.WINDOW2);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Weapon(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Health(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
