package generator.standard;

public class StandardRoomGenerator implements RoomGenerator {
	
	public Room generateRoom(int x1, int y1, int width, int height){
		Room room = new Room(x1, y1, width, height);
		for(int x = 0; x < room.getWidth(); x++){
			for(int y = 0; y < room.getHeight(); y++){
				room.setTile(x, y, TileType.FLOOR);
			}
		}
		return room;
	}
}
