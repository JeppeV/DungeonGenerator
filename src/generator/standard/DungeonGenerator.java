package generator.standard;

import java.util.ArrayList;
import java.util.Random;

public class DungeonGenerator {
	
	private final int NO_ATTEMPTS = 20;
	
	private int width, height;
	private char[][] map;
	private boolean[][] visited;
	private RoomGenerator roomGenerator;
	private ArrayList<Room> rooms;
	private int numberOfRooms;
	private int minRoomSize, maxRoomSize;
	
	public DungeonGenerator(int width, int height, RoomGenerator roomGenerator){
		this.width = width;
		this.height = height;
		this.map = new char[width][height];
		this.visited = new boolean[width][height];
		this.roomGenerator = roomGenerator;
		this.rooms = new ArrayList<Room>();
		this.numberOfRooms = 20;
		this.minRoomSize = 3;
		this.maxRoomSize = 5;
		
		initializeVisited();
		
	}

	private void initializeVisited(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				visited[x][y] = false;
			}
		}
	}
	
	public char[][] generateDungeon(){
		//fillMapWith(TileType.WALL);
		generateRooms();		
		return map;
	}	
	
	private void fillMapWith(char c){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				map[x][y] = c;
			}
		}
	}
	
	private void generateRooms(){
		boolean overlapsExisting;
		int x1, y1, roomWidth, roomHeight;
		int attempts;
		int roomsAdded = 0;
		int maxX = width - maxRoomSize + 2;
		int maxY = height - maxRoomSize + 2;
		Random random = new Random();
		Room room;
		
		
		for(int i = 0; i < numberOfRooms; i++){
			attempts = NO_ATTEMPTS;
			while(attempts > 0){
				x1 = random.nextInt(maxX);
				y1 = random.nextInt(maxY);
				roomWidth = minRoomSize + random.nextInt(maxRoomSize - minRoomSize);
				roomHeight = minRoomSize + random.nextInt(maxRoomSize - minRoomSize);
				room = roomGenerator.generateRoom(x1, y1, roomWidth, roomHeight);
				overlapsExisting = false;
				for(Room r : rooms){
					overlapsExisting = room.overlaps(r, 1);
					if(overlapsExisting){
						attempts--;
						break;
					}
				}
				if(!overlapsExisting){
					rooms.add(room);
					addRoom(room);
					roomsAdded++;
					break;
				}
			}
			
		}
		System.out.println("Succesfully created " + roomsAdded + "/" + numberOfRooms + " rooms, using " + NO_ATTEMPTS + " attempts each.");
	}
	
	private void addRoom(Room room){
		int x1 = room.getX1();
		int y1 = room.getY1();
		for(int x = 0; x < room.getWidth(); x++){
			for(int y = 0; y < room.getHeight(); y++){
				map[x1 + x][y1 + y] = room.getTile(x, y);
			}
		}
	}
			
	private Room getRoomAt(int x, int y){
		for(Room room : rooms){
			if(room.getX1() <= x && room.getY1() <= y && room.getX2() > x && room.getY2() > y){
				return room;
			}
		}
		return null;
	}

	@Override
	public String toString(){
		String string = "";
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				string = string + getTile(x,y) + " ";
			}
			string = string + "\n";
		}
		
		return string; 
	}
	
	public char getTile(int x, int y){
		return map[x][y];
	}
	
	
	
	
}
