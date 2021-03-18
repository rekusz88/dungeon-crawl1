package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.items.Friend1;
import com.codecool.dungeoncrawl.logic.items.Friend2;
import com.codecool.dungeoncrawl.logic.items.Friend3;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String fileName) {
        InputStream is = MapLoader.class.getResourceAsStream(fileName);
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
                        case '*':
                            cell.setType(CellType.FLOOR2);
                            break;
                        case ';':
                            cell.setType(CellType.FLOOR3);
                            break;
                        case '|':
                            cell.setType(CellType.FLOOR4);
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
                            cell.setType(CellType.INSIDE);
                            break;
                        case 'X':
                            cell.setType(CellType.TREASURE);
                            break;
                        case 'O':
                            cell.setType(CellType.BACK_INSIDE);
                            break;
                        case 'E':
                            cell.setType(CellType.INSIDE2);
                            break;
                        case 'Y':
                            cell.setType(CellType.FINAL);
                            break;
                        case 'A':
                            cell.setType(CellType.OUTSIDE);
                            break;
                        case 'o':
                            cell.setType(CellType.OPENED);
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

                        case 'R':
                            cell.setType(CellType.FIRE);
                            break;
                        case 'r':
                            cell.setType(CellType.FIRE2);
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

                        case 'l':
                            cell.setType(CellType.FRIEND1);
                            new Friend1(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FRIEND2);
                            new Friend2(cell);
                            break;
                        case 'z':
                            cell.setType(CellType.FRIEND3);
                            new Friend3(cell);
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
                        case '+':
                            cell.setType(CellType.WALL4);
                            break;

                        case '?':
                            cell.setType(CellType.WINDOW1);
                            break;
                        case '!':
                            cell.setType(CellType.WINDOW2);
                            break;
                        case 'G':
                            cell.setType(CellType.ENDING);
                            break;

                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'N':
                            cell.setType(CellType.FLOOR);
                            new OPBoss(cell);
                            break;

                        case 'I':
                            cell.setType(CellType.SHIELD);
                            new HellFireShield(cell);
                            break;

                        case 'w':
                            cell.setType(CellType.WEAPON);
                            new Weapon(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.HEALTH);
                            new Health(cell);
                            break;

                        case 'J':
                            cell.setType(CellType.FIRE3);
                            new Fire(cell);
                            break;

                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'Z':
                            cell.setType(CellType.KEY2);
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
