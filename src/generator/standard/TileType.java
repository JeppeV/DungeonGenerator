package generator.standard;

public abstract class TileType {
	
	public static final char FLOOR = ' ';
	public static final char WALL = 'X';
	
	public static void printCharArray(char[][] chars){
		String string = "";
		for(int y = 0; y < chars[0].length; y++){
			for(int x = 0; x < chars.length; x++){
				string = string + chars[x][y] + " ";
			}
			string = string + "\n";
		}
		
		System.out.println(string);
	}
	
}
