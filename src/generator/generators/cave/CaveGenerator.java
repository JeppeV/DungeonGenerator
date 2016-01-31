package generator.generators.cave;

import generator.standard.Map;
import generator.standard.MapGenerator;
import generator.standard.TileType;
import generator.standard.cave.Cave;

/**
 * Created by Jeppe Vinberg on 30-01-2016.
 */
public class CaveGenerator implements MapGenerator {

    private float chanceToStartAlive;
    private int deathLimit, birthLimit;
    private int iterations;

    public CaveGenerator(){
        this.chanceToStartAlive = 0.5f;
        this.deathLimit = 4;
        this.birthLimit = 4;
        this.iterations = 7;

    }

    @Override
    public Map generateMap(int width, int height) {
        Cave cave = new Cave(width, height);
        boolean[][] alive = init(cave);
        for(int i = 0; i < iterations; i++){
            alive = runSimulation(alive, cave);
            System.out.println(cave);

        }
        cave = finalize(cave, alive);

        return cave;
    }

    private boolean[][] init(Cave cave){
        boolean[][] result = new boolean[cave.getWidthInTiles()][cave.getHeightInTiles()];
        for(int x = 0; x < cave.getWidthInTiles(); x++){
            for(int y = 0; y < cave.getHeightInTiles(); y++){
                if(Math.random() <= chanceToStartAlive){
                    result[x][y] = true;
                }else{
                    result[x][y] = false;
                }
            }
        }
        return result;
    }

    private Cave finalize(Cave cave, boolean[][] alive){
        for(int x = 0; x < cave.getWidthInTiles(); x++){
            for(int y = 0; y < cave.getHeightInTiles(); y++){
                if(alive[x][y]){
                    cave.setTile(x, y, TileType.WALL);
                }else{
                    cave.setTile(x, y, TileType.FLOOR);
                }
            }
        }


        //make sure walls are solid
        for (int x = 0; x < cave.getWidthInTiles(); x++) {
            cave.setTile(x, 0, TileType.WALL);
            cave.setTile(x, cave.getWidthInTiles() - 1, TileType.WALL);
        }

        for (int y = 0; y < cave.getHeightInTiles(); y++) {
            cave.setTile(0, y, TileType.WALL);
            cave.setTile(cave.getHeightInTiles() - 1, y, TileType.WALL);
        }

        return cave;
    }

    private boolean[][] runSimulation(boolean[][] alive, Cave cave){
        boolean[][] newAlive = new boolean[alive.length][alive[0].length];
        for(int x=0; x< alive.length; x++){
            for(int y=0; y<alive[0].length; y++){
                int nbs = countAliveNeighbours(x, y, alive);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if(alive[x][y]){
                    if(nbs < deathLimit){
                        newAlive[x][y] = false;
                        cave.setTile(x, y, TileType.FLOOR);
                    }
                    else{
                        newAlive[x][y] = true;
                        cave.setTile(x, y, TileType.WALL);
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else{
                    if(nbs > birthLimit){
                        newAlive[x][y] = true;
                        cave.setTile(x, y, TileType.WALL);
                    }
                    else{
                        newAlive[x][y] = false;
                        cave.setTile(x, y, TileType.FLOOR);
                    }
                }
            }
        }

        return newAlive;
    }

    public int countAliveNeighbours(int x, int y, boolean[][] alive){
        int count = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int neighbour_x = x+i;
                int neighbour_y = y+j;
                //If we're looking at the middle point
                if(i == 0 && j == 0){
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= alive.length || neighbour_y >= alive[0].length){
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if(alive[neighbour_x][neighbour_y]){
                    count = count + 1;
                }
            }
        }
        return count;
    }
}
