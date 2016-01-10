package generator.generators;

import generator.standard.Constants;
import generator.standard.Dungeon;

public class DungeonGenerator {

    private RoomGenerator roomGenerator;
    private MazeGenerator mazeGenerator;
    private ConnectionGenerator connectionGenerator;

    public DungeonGenerator(RoomGenerator roomGenerator, MazeGenerator mazeGenerator) {
        this.roomGenerator = roomGenerator;
        this.mazeGenerator = mazeGenerator;
        this.connectionGenerator = new ConnectionGenerator();
    }

    public Dungeon generateDungeon(int width, int height) {
        Dungeon dungeon = new Dungeon(width, height);
        dungeon = fillDungeonWith(dungeon, Constants.WALL);
        dungeon = fillVisitedWith(dungeon, false);
        dungeon = roomGenerator.generateRooms(dungeon);
        System.out.println(dungeon);
        dungeon = mazeGenerator.generateMazes(dungeon);
        System.out.println(dungeon);
        dungeon = connectionGenerator.generateConnections(dungeon);
        return dungeon;
    }

    private Dungeon fillDungeonWith(Dungeon dungeon, char c) {
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                dungeon.setTile(x, y, c);
            }
        }
        return dungeon;
    }

    private Dungeon fillVisitedWith(Dungeon dungeon, boolean b) {
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                dungeon.setVisited(x, y, b);
            }
        }
        return dungeon;
    }





}
