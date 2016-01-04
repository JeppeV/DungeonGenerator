package generator.standard;

import java.util.ArrayList;

public class Maze implements DungeonArea {
	
	private ArrayList<Coordinates> tiles;
	
	public Maze(){
		this.tiles = new ArrayList<Coordinates>();
	}
	
	public void addTile(Coordinates coords){
		tiles.add(coords);
	}
	
	public ArrayList<Coordinates> getTiles(){
		return tiles;
	}

}
