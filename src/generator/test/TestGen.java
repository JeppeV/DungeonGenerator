package generator.test;

import generator.standard.DungeonGenerator;
import generator.standard.MazeGenerator;
import generator.standard.StandardRoomGenerator;
import generator.standard.TileType;

public class TestGen {

	public static void main(String[] args) {
		DungeonGenerator generator = new DungeonGenerator(40, 40, new StandardRoomGenerator());
		char[][] map = generator.generateDungeon();
		TileType.printChars(map);
		MazeGenerator mg = new MazeGenerator(map);
		char[][] maze = mg.generateMaze();
		TileType.printChars(maze);
		

	}

}
