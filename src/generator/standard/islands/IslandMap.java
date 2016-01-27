package generator.standard.islands;

import generator.standard.Coordinates;
import generator.standard.Map;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jeppe Vinberg on 27-01-2016.
 */
public class IslandMap implements Map {

    private int width, height;
    private char map[][];
    private boolean visited[][];

    public IslandMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new char[width][height];
        this.visited = new boolean[width][height];
    }

    @Override
    public char getTile(int x, int y) {
        return map[x][y];
    }

    @Override
    public char getTile(Coordinates coords) {
        int x = coords.getX();
        int y = coords.getY();
        return map[x][y];
    }

    @Override
    public void setTile(int x, int y, char c) {
        map[x][y] = c;
    }

    @Override
    public void setTile(Coordinates coords, char c) {
        int x = coords.getX();
        int y = coords.getY();
        map[x][y] = c;
    }

    public void setVisited(int x, int y, boolean b) {
        visited[x][y] = b;
    }

    public void setVisited(Coordinates coords, boolean b) {
        int x = coords.getX();
        int y = coords.getY();
        visited[x][y] = b;
    }

    public boolean getVisited(int x, int y) {
        return visited[x][y];
    }

    public boolean getVisited(Coordinates coords) {
        int x = coords.getX();
        int y = coords.getY();
        return visited[x][y];
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
