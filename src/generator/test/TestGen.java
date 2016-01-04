package generator.test;

import generator.standard.Dungeon;
import generator.standard.DungeonGenerator;
import generator.standard.StandardRoomGenerator;

public class TestGen {

	public static void main(String[] args) {
		DungeonGenerator generator = new DungeonGenerator(new StandardRoomGenerator(), null);
		Dungeon d = generator.generateDungeon(40, 40);
		System.out.println(d);
		/*
		MazeGenerator mg = new MazeGenerator(map);
		char[][] maze = mg.generateMaze();
		TileType.printCharArray(maze);
		*/
		

	}

}
