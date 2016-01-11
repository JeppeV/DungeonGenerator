package generator.test;

import generator.generators.MazeGenerator;
import generator.standard.Dungeon;
import generator.generators.DungeonGenerator;
import generator.generators.RoomGenerator;

public class TestGen {

	public static void main(String[] args) {
		DungeonGenerator generator = new DungeonGenerator();
		Dungeon d = generator.generateDungeon(50, 50);

		

	}

}
