package generator.test;

import generator.generators.dungeon.DungeonGenerator;
import generator.standard.Map;
import generator.standard.MapGenerator;

public class TestGen {

    public static void main(String[] args) {
        MapGenerator generator = new DungeonGenerator();
        Map d = generator.generateMap(30, 30);
        System.out.println(d);
        /*
		int testI = 10000;
		for(int i = 0; i < testI; i++){
			generator.generateMap(40, 40);
			System.out.println(i);
		}
		*/


    }

}
