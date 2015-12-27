package generator.standard;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
	private int width, height;               // dimensions of maze
	private final int BORDER = 2;
    private boolean[][] visited;
    private char[][] maze;
    private Random random;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.random = new Random();
        init();
    }
    
    public MazeGenerator(char[][] map){
    	//initialize border using other constructor 
    	this(map.length, map[0].length);
    	
    	//copy contents of input array
    	for(int x = 0; x < map.length; x++){
    		for(int y = 0; y < map[0].length; y++){
    			maze[x+1][y+1] = map[x][y];
    		}
    	}
    	
    }
    
    public char[][] generateMaze(){
    	Coordinates coords = findNextEligibleInitialTile();
    	while(coords != null){
    		floodFill(coords.getX(), coords.getY());
    		coords = findNextEligibleInitialTile();
    	}
    	return maze;
    	
    }
    
    public char[][] getMaze(){
    	return maze;
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[width + BORDER][height + BORDER];
        for (int x = 0; x < visited.length ; x++) {
            visited[x][0] = true;
            visited[x][height+1] = true;
        }
        for (int y = 0; y < visited[0].length; y++) {
            visited[0][y] = true;
            visited[width+1][y] = true;
        }


        maze = new char[width + BORDER][height + BORDER];
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                maze[x][y] = TileType.WALL;
            }
        }
    }


    // generate the maze
    private void floodFill(int initX, int initY) {
    	Stack<Coordinates> stack = new Stack<Coordinates>();
    	stack.push(new Coordinates(initX, initY));
    	Coordinates coords;
    	int x, y;
    	while(!stack.isEmpty()){
    		coords = stack.pop();
    		x = coords.getX();
        	y = coords.getY();
        	if (visited[x][y] || maze[x][y] != TileType.WALL){
        		continue;
        	}
        	if(isLegalTile(x,y)){
        		maze[x][y] = TileType.FLOOR;
        		stack = addNeighboursInRandomOrder(x, y, stack);
        	}
    	}
    }
    
    private Stack<Coordinates> addNeighboursInRandomOrder(int x, int y, Stack<Coordinates> stack){
    	LinkedList<Coordinates> neighbours = new LinkedList<Coordinates>();
    	
    	neighbours.add(new Coordinates(x-1, y));
    	neighbours.add(new Coordinates(x, y+1));
    	neighbours.add(new Coordinates(x+1, y));
    	neighbours.add(new Coordinates(x, y-1));
		
		for (int i = 4; i > 0; i--)
		{
			stack.add(neighbours.remove(random.nextInt(i)));
		}
		return stack;
	}
    
    private boolean isLegalTile(int x, int y){
    	char[] neighbours = getNeighbours(x, y);
    	return isLegalTileFromDirection(neighbours, 0) && 
    		   isLegalTileFromDirection(neighbours, 2) && 
    		   isLegalTileFromDirection(neighbours, 4) &&
    		   isLegalTileFromDirection(neighbours, 6);
    }
    
    private boolean isLegalTileFromDirection(char[] neighbours, int i){
    	boolean result = true;
    	if(neighbours[i] != TileType.WALL){
    		for(int j = i+2; j < i+7; j++){
    			int n = j % 8;
    			result = result && (neighbours[n] == TileType.WALL);
    		}
    	}
    	return result;
    }
    
    private boolean isEligibleInitialTile(int x, int y){
    	boolean result = true;
    	if(visited[x][y]) return false;
    	for(char c : getNeighbours(x,y)){
    		result = result && (c == TileType.WALL);
    	}
    	return result;
    }
    
    private Coordinates findNextEligibleInitialTile(){
    	for (int x = 0; x < maze.length; x++) {
    		for (int y = 0; y < maze[0].length; y++) {
                if(isEligibleInitialTile(x, y)) return new Coordinates(x,y);
            }
        }
    	return null;
    }
    
    private char[] getNeighbours(int x, int y){
    	char[] neighbours = new char[8];
    	neighbours[0] = maze[x][y-1];
    	neighbours[1] = maze[x+1][y-1];
    	neighbours[2] = maze[x+1][y];
    	neighbours[3] = maze[x+1][y+1];
    	neighbours[4] = maze[x][y+1];
    	neighbours[5] = maze[x-1][y+1];
    	neighbours[6] = maze[x-1][y];
    	neighbours[7] = maze[x-1][y-1];
    	return neighbours;
    }
    


  

    
}
