package generator.standard;

public class Room implements DungeonArea {
	
	private int width, height;
	private char[][] room;
	private int x1, y1, x2, y2;
	
	public Room(int x1, int y1, int width, int height){
		this.width = width;
		this.height = height;
		this.room = new char[width][height];
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;		
	}
	
	
	/**
	 * @param other the room to check if this room overlaps
	 * @param padding the padding to include when checking for overlapping
	 * @return whether this room overlaps the other room
	 */
	public boolean overlaps(Room other, int padding){
		return (this.x1 - padding < other.x2) && (this.x2 + padding > other.x1) && 
				(this.y1 - padding < other.y2) && (this.y2 + padding > other.y1);
	}
	
	
	/**
	 * @param other the room to check if this room overlaps
	 * @return whether this room overlaps the other room
	 */
	public boolean overlaps(Room other){
		return (this.x1 < other.x2) && (this.x2 > other.x1) && 
				(this.y1 < other.y2) && (this.y2 > other.y1);
	}
	
	public void setTile(int x, int y, char c){
		room[x][y] = c;
	}
	
	public char getTile(int x, int y){
		return room[x][y];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getX1(){
		return x1;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY2(){
		return y2;
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
