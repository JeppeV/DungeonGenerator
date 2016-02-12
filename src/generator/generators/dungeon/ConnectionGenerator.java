package generator.generators.dungeon;

import generator.standard.Coordinates;
import generator.standard.TileType;
import generator.standard.dungeon.Connector;
import generator.standard.dungeon.Dungeon;
import generator.standard.dungeon.Region;

import java.util.*;

/**
 * Created by Jeppe Vinberg on 06-01-2016.
 */
public class ConnectionGenerator {

    private HashMap<Region, Boolean> merged;
    private Random random;
    private final double randomConnectorChance = 0.3;


    public ConnectionGenerator() {
        this.random = new Random();
        this.merged = new HashMap<>();
    }

    private void init(Dungeon dungeon) {
        // Get regions from dungeon
        for (Region r : dungeon.getRegions()) {
            merged.put(r, false);
        }
    }

    public Dungeon generateConnections(Dungeon dungeon) {
        init(dungeon);
        //all connectors
        HashSet<Connector> allConnectors = findAllConnectors(dungeon);
        //connectors currently being considered and corresponding unmerged regions
        HashMap<Coordinates, Region> currentConnectors = new HashMap<>();
        //connectors to be removed
        HashSet<Connector> obsoleteConnectors = new HashSet<>();
        Region currentRegion;

        //select random starting region:
        Connector r = getRandomKey(allConnectors);
        currentRegion = r.getRegion1();

        //while there are still connectors to consider


        while (!allConnectors.isEmpty()) {

            //mark current region as merged
            merged.put(currentRegion, true);

            //find all connectors connected currently merged regions
            //here we check that one of the regions on either side of the connector is not merged;
            //then we store the coordinates of the connector, and the region that has not been merged
            for (Connector c : allConnectors) {
                Region region1 = c.getRegion1();
                Region region2 = c.getRegion2();
                if (merged.get(region1) && !merged.get(region2)) {
                    currentConnectors.put(c.getCoordinates(), region2);
                } else if (merged.get(region2) && !merged.get(region1)) {
                    currentConnectors.put(c.getCoordinates(), region1);
                } else if (merged.get(region1) && merged.get(region2)) {
                    //remove connectors that connect two merged rooms from the connector sets
                    obsoleteConnectors.add(c);
                }
            }


            if (currentConnectors.isEmpty()) break;

            //choose random connector and open it up
            Coordinates randomC = getRandomKey(currentConnectors.keySet());
            currentRegion = currentConnectors.get(randomC);
            dungeon.setTile(randomC, TileType.FLOOR);
            dungeon.setVisited(randomC, true);

            //potentially open up a random connector
            Connector con = addChanceConnector(dungeon, allConnectors);
            obsoleteConnectors.add(con);

            for (Connector c : obsoleteConnectors) {
                allConnectors.remove(c);
            }

            obsoleteConnectors.clear();
            currentConnectors.clear();


        }

        return dungeon;
    }


    private Connector addChanceConnector(Dungeon dungeon, HashSet<Connector> connectors) {
        Connector result = null;
        if (Math.random() <= randomConnectorChance && connectors.size() != 0) {
            result = getRandomKey(connectors);
            Coordinates c = result.getCoordinates();
            dungeon.setTile(c, TileType.FLOOR);
            dungeon.setVisited(c, true);
        }
        return result;
    }

    private HashSet<Connector> findAllConnectors(Dungeon dungeon) {
        HashSet<Connector> result = new HashSet<>();
        Coordinates c;
        Region north, east, south, west;
        for (int x = 0; x < dungeon.getWidthInTiles(); x++) {
            for (int y = 0; y < dungeon.getHeightInTiles(); y++) {
                if (dungeon.getVisited(x, y)) continue;
                c = new Coordinates(x, y);
                north = dungeon.getRegionAt(new Coordinates(x, y - 1));
                south = dungeon.getRegionAt(new Coordinates(x, y + 1));
                west = dungeon.getRegionAt(new Coordinates(x - 1, y));
                east = dungeon.getRegionAt(new Coordinates(x + 1, y));

                if (regionsAreDifferent(north, south)) {
                    result.add(new Connector(c, north, south));
                } else if (regionsAreDifferent(west, east)) {
                    result.add(new Connector(c, west, east));
                }
            }
        }
        return result;
    }


    private boolean regionsAreDifferent(Region r1, Region r2) {
        if (r1 != null && r2 != null) {
            return !r1.equals(r2);
        }
        return false;
    }

    private <E> E getRandomKey(Set<E> set) {
        List<E> keys = new ArrayList<>(set);
        return keys.get(random.nextInt(keys.size()));
    }


}
