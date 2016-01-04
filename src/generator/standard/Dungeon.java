package generator.standard;

import java.util.ArrayList;

public class Dungeon {
	private int width, height;
	private char[][] dungeon;
	private ArrayList<Room> rooms;
	
	public Dungeon(int width, int height){
		this.width = width;
		this.height = height;
		this.dungeon = new char[width][height];
		this.rooms = new ArrayList<Room>();
	}
	
	public void addRoom(Room room){
		rooms.add(room);
	}
	
	public ArrayList<Room> getRooms(){
		return rooms;
	}
	
	private Room getRoomAt(int x, int y){
		for(Room room : rooms){
			if(room.getX1() <= x && room.getY1() <= y && room.getX2() > x && room.getY2() > y){
				return room;
			}
		}
		return null;
	}
		
	public void setTile(int x, int y, char c){
		dungeon[x][y] = c;
	}
	
	public char getTile(int x, int y){
		return dungeon[x][y];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
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
}
