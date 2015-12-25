package generator.test;

import generator.standard.MazeGenerator;
import generator.standard.TileType;

public class TestGen {

	public static void main(String[] args) {
		//DungeonGenerator generator = new DungeonGenerator(20, 20, new StandardRoomGenerator());
		MazeGenerator mg = new MazeGenerator(20,20);
		char[][] maze = mg.getMaze();
		TileType.printChars(maze);
		

	}

}
