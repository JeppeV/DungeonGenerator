package generator.standard.cave;

import generator.standard.Coordinates;
import generator.standard.Map;
import generator.standard.dungeon.Maze;
import generator.standard.dungeon.Region;
import generator.standard.dungeon.Room;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jeppe Vinberg on 30-01-2016.
 */
public class Cave implements Map {
    private int width, height;
    private char[][] dungeon;

    public Cave(int width, int height) {
        this.width = width;
        this.height = height;
        this.dungeon = new char[width][height];
    }

    public void setTile(int x, int y, char c) {
        dungeon[x][y] = c;
    }

    public void setTile(Coordinates coords, char c) {
        int x = coords.getX();
        int y = coords.getY();
        dungeon[x][y] = c;
    }

    @Override
    public char getTile(int x, int y) {
        return dungeon[x][y];
    }

    @Override
    public char getTile(Coordinates coords) {
        int x = coords.getX();
        int y = coords.getY();
        return dungeon[x][y];
    }

    @Override
    public int getWidthInTiles() {
        return width;
    }

    @Override
    public int getHeightInTiles() {
        return height;
    }

    @Override
    public String toString() {
        String string = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                string = string + getTile(x, y) + " ";
            }
            string = string + "\n";
        }

        return string;
    }
}