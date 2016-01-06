package generator.generators;

import generator.standard.Dungeon;
import generator.standard.Room;

public interface RoomGenerator {

    public Room generateRoom(int x1, int y1, int width, int height);

    public Dungeon generateRooms(Dungeon dungeon);

}
