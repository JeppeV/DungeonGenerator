package generator.standard;

/**
 * Created by Jeppe Vinberg on 18-01-2016.
 */
public interface Map {

    int BORDER = 1;

    char getTile(int x, int y);

    char getTile(Coordinates coords);

    void setTile(int x, int y, char c);

    void setTile(Coordinates coords, char c);

    int getWidthInTiles();

    int getHeightInTiles();

    boolean isWithinBounds(int x, int y);

    boolean isWithinBounds(Coordinates coords);
}
