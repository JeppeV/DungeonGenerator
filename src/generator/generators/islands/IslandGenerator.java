package generator.generators.islands;

import generator.standard.Coordinates;
import generator.standard.Map;
import generator.standard.MapGenerator;
import generator.standard.TileType;
import generator.standard.islands.IslandMap;

import java.time.chrono.IsoChronology;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 28-01-2016.
 */
public class IslandGenerator implements MapGenerator {

    private int iterations;

    public IslandGenerator(){
        this.iterations = 5;
    }

    @Override
    public Map generateMap(int width, int height) {
        IslandMap islandMap = new IslandMap(width, height);
        islandMap = init(islandMap);
        islandMap = gen(islandMap);
        return islandMap;
    }

    private IslandMap init(IslandMap islandMap){
        //scatter tiles
        for(int x = 0; x < islandMap.getWidthInTiles(); x++){
            for(int y = 0; y < islandMap.getHeightInTiles(); y++){
                if(Math.random() <= 45){
                    islandMap.setTile(x, y, TileType.FLOOR);
                }else{
                    islandMap.setTile(x, y, TileType.WALL);
                }
            }
        }
        return islandMap;
    }

    private IslandMap resetVisited(IslandMap islandMap){
        for(int x = 0; x < islandMap.getWidthInTiles(); x++){
            for(int y = 0; y < islandMap.getHeightInTiles(); y++){
                islandMap.setVisited(x, y, false);
            }
        }
        return islandMap;
    }

    private IslandMap gen(IslandMap islandMap){
        LinkedList<Coordinates> queue = new LinkedList<>();
        Coordinates current;

        double chance;
        for(int i = 0; i < iterations; i++){
            islandMap = resetVisited(islandMap);
            current = getRandomCoordinates(islandMap);
            queue.add(current);

            while(!queue.isEmpty()){
                chance = 0.2;
                System.out.println("__________");
                System.out.println(queue.size());
                current = queue.poll();
                queue = addNeighboursInRandomOrder(current, queue, islandMap);
                System.out.println(queue.size());
                for(Coordinates c : current.getNeighbours()){
                    if(!islandMap.isWithinBounds(c)) continue;
                    if(islandMap.getTile(c) == TileType.FLOOR){
                        chance += 0.1;
                    }
                }
                if(Math.random() <= chance){
                    islandMap.setTile(current, TileType.FLOOR);
                }else{
                    islandMap.setTile(current, TileType.WALL);
                }
                islandMap.setVisited(current, true);

            }
        }

        return islandMap;
    }

    private Coordinates getRandomCoordinates(IslandMap islandMap){
        Random random = new Random();
        int x = random.nextInt(islandMap.getWidthInTiles());
        int y = random.nextInt(islandMap.getHeightInTiles());
        return new Coordinates(x, y);
    }

    private LinkedList<Coordinates> addNeighboursInRandomOrder(Coordinates coords, LinkedList<Coordinates> queue, IslandMap islandMap) {
        LinkedList<Coordinates> neighbours = new LinkedList<>();
        Random random = new Random();
        for (Coordinates c : coords.getNeighbours()) {
            if(islandMap.isWithinBounds(c) && !islandMap.getVisited(c)){
                neighbours.add(c);
            }

        }

        for (int i = neighbours.size(); i > 0; i--) {
            queue.add(neighbours.remove(random.nextInt(i)));
        }

        return queue;
    }
}
