package generator.test;

import generator.generators.FloodFillMazeGenerator;
import generator.generators.MazeGenerator;
import generator.standard.Dungeon;
import generator.generators.DungeonGenerator;
import generator.generators.StandardRoomGenerator;

public class TestGen {

	public static void main(String[] args) {
		DungeonGenerator generator = new DungeonGenerator(new StandardRoomGenerator(), new FloodFillMazeGenerator());
		Dungeon d = generator.generateDungeon(40, 40);
		System.out.println(d);

		

	}

}
