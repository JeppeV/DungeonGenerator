package generator.test;

import generator.generators.MazeGenerator;
import generator.standard.Dungeon;
import generator.generators.DungeonGenerator;
import generator.generators.RoomGenerator;
import generator.standard.Map;

public class TestGen {

	public static void main(String[] args) {
		DungeonGenerator generator = new DungeonGenerator();
		Map d = generator.generateDungeon(50, 50);

		

	}

}
