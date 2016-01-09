package generator.generators;

import generator.standard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 06-01-2016.
 */
public class ConnectionGenerator {

    private HashMap<Region, ArrayList<Coordinates>> regions;
    private Random random;


    public ConnectionGenerator(){
        this.regions = new HashMap<>();
        this.random = new Random();
    }

    private void init(Dungeon dungeon){
        // Get regions from dungeon
        for(Region r : dungeon.getRegions()){
            regions.put(r, new ArrayList<>());
        }
    }

    public Dungeon generateConnections(Dungeon dungeon){
        init(dungeon);
        HashMap<Coordinates, ArrayList<Region>> connectors = findConnectors(dungeon);
        //pick arbitrary starting region
        System.out.println(regions);

        return dungeon;
    }

    private HashMap<Coordinates,ArrayList<Region>> findConnectors(Dungeon dungeon){
        HashMap<Coordinates,ArrayList<Region>> result = new HashMap<>();
        ArrayList<Region> temp;
        ArrayList<Coordinates> regionCoords;
        Coordinates c;
        Region north, east, south, west;
        for(int x = 0; x < dungeon.getWidth(); x++){
            for(int y = 0; y < dungeon.getHeight(); y++){
                if(dungeon.getVisited(x, y)) continue;
                c = new Coordinates(x, y);
                temp = new ArrayList<>();
                north = dungeon.getRegionAt(new Coordinates(x, y-1));
                south = dungeon.getRegionAt(new Coordinates(x, y+1));
                west = dungeon.getRegionAt(new Coordinates(x-1, y));
                east = dungeon.getRegionAt(new Coordinates(x+1, y));


                if(regionsAreDifferent(north, south)){
                    temp.add(north);
                    temp.add(south);
                    result.put(c, temp);
                    regionCoords = regions.get(north);
                    regionCoords.add(c);
                    regionCoords = regions.get(south);
                    regionCoords.add(c);
                }else if(regionsAreDifferent(west, east)){
                    temp.add(west);
                    temp.add(east);
                    result.put(c, temp);
                    regionCoords = regions.get(west);
                    regionCoords.add(c);
                    regionCoords = regions.get(east);
                    regionCoords.add(c);

                }
            }
        }
        return result;
    }

    private boolean regionsAreDifferent(Region r1, Region r2){
        if(r1 != null && r2 != null){
            return !r1.equals(r2);
        }
        return false;
    }



}
