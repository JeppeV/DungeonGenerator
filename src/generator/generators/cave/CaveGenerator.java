package generator.generators.cave;

import generator.standard.Coordinates;
import generator.standard.Map;
import generator.standard.MapGenerator;
import generator.standard.TileType;
import generator.standard.cave.Cave;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 30-01-2016.
 */
public class CaveGenerator implements MapGenerator {

    private float chanceToStartAlive;
    private int innerBirthLimit, outerBirthLimit;
    private int iter1, iter2;

    public CaveGenerator(){
        this.chanceToStartAlive = 0.4f;
        this.innerBirthLimit = 5;
        this.outerBirthLimit = 2;
        this.iter1 = 4;
        this.iter2 = 3;

    }

    @Override
    public Map generateMap(int width, int height) {
        Cave cave = new Cave(width, height);

        boolean[][] alive = init(cave);

        for(int i = 0; i < iter1; i++){
            alive = runIterationType1(alive);
        }
        for(int i = 0; i < iter2; i++){
            alive = runIterationType2(alive);
        }

        cave = finalize(cave, alive);

        cave = generateRiver(cave);

        return cave;
    }

    private boolean[][] init(Cave cave){
        boolean[][] result = new boolean[cave.getWidthInTiles()][cave.getHeightInTiles()];
        for(int x = 0; x < cave.getWidthInTiles(); x++){
            for(int y = 0; y < cave.getHeightInTiles(); y++){
                result[x][y] = Math.random() < chanceToStartAlive;
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


        //make sure edges are solid
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

    private boolean[][] runIterationType1(boolean[][] alive){
        boolean[][] newAlive = new boolean[alive.length][alive[0].length];
        int nbs1, nbs2;
        for(int x=0; x< alive.length; x++){
            for(int y=0; y<alive[0].length; y++){
                nbs1 = countAliveNeighboursInRange(x, y, 1, alive);
                nbs2 = countAliveNeighboursInRange(x, y, 2, alive);
                newAlive[x][y] = nbs1 >= innerBirthLimit || nbs2 <= outerBirthLimit;

            }
        }

        return newAlive;
    }

    private boolean[][] runIterationType2(boolean[][] alive){
        boolean[][] newAlive = new boolean[alive.length][alive[0].length];
        int nbs;
        for(int x=0; x< alive.length; x++){
            for(int y=0; y<alive[0].length; y++){
                nbs = countAliveNeighboursInRange(x, y, 1, alive);
                newAlive[x][y] = nbs >= innerBirthLimit;
            }
        }

        return newAlive;
    }

    private int countAliveNeighboursInRange(int x0, int y0, int range, boolean[][] alive){
        int count = 0;
        for(int x = -1 * range; x < range + 1; x++){
            for(int y = -1 * range; y < range + 1; y++){
                int neighbour_x = x0 + x;
                int neighbour_y = y0 + y;

                //In case the index we're looking at it off the edge of the map
                if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= alive.length || neighbour_y >= alive[0].length){
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

    private Cave generateRiver(Cave cave){
        Random random = new Random();
        int stray = 3;
        int initX, initY;
        boolean right;
        Coordinates current;
        LinkedList<Coordinates> neighbours = new LinkedList<>();
        if(Math.random() < 0.5){
            initX = 0;
            initY = 1 + random.nextInt(cave.getHeightInTiles() - 1);
            current = new Coordinates(initX, initY);
            right = true;
        }else{
            initX = 1 + random.nextInt(cave.getWidthInTiles() - 1);
            initY = 0;
            current = new Coordinates(initX, initY);
            right = false;
        }
        int count;
        while(current.isWithinBoundsOf(cave)){
            cave.setTile(current, '.');

            for(Coordinates c : current.getCardinalNeighbours()){
                count = 0;
                for(Coordinates cc : c.getCardinalNeighbours()){
                    if(cc.isWithinBoundsOf(cave) && cave.getTile(cc) == '.'){
                        count++;
                    }
                }
                if(count == 1){
                    if(right){
                        if(c.getX() > current.getX() || (c.getX() >= current.getX() && c.getY() < initY + stray && c.getY() > initY - stray)){

                            if(c.getX() == 0 || c.getX() == cave.getWidthInTiles() - 1 || c.getY() == 0 || c.getY() == cave.getHeightInTiles() - 1){

                            }else{
                                neighbours.add(c);
                            }
                        }
                    }else{
                        if(c.getY() > current.getY() || (c.getY() >= current.getY() && c.getX() < initX + stray && c.getX() > initX - stray)){

                            if(c.getX() == 0 || c.getX() == cave.getWidthInTiles() - 1 || c.getY() == 0 || c.getY() == cave.getHeightInTiles() - 1){

                            }else{
                                neighbours.add(c);
                            }
                        }
                    }
                }


            }
            if(neighbours.isEmpty()) break;
            current = neighbours.get(random.nextInt(neighbours.size()));
            neighbours.clear();

        }

        return cave;
    }


}
