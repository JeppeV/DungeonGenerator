package generator.standard.dungeon;

import generator.standard.Coordinates;

import java.util.ArrayList;

/**
 * Created by Jeppe Vinberg on 04-01-2016.
 */
public interface Region {

    ArrayList<Coordinates> getTiles();

    void addTile(Coordinates coords);


}
