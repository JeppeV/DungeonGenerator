package generator.standard;

public class Coordinates {

    int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //returns neighbours in range 1, starting with the tile north of this, and the rest in clockwise order
    public Coordinates[] getAllNeighbours() {
        Coordinates[] neighbours = new Coordinates[8];
        neighbours[0] = new Coordinates(x, y - 1);
        neighbours[1] = new Coordinates(x + 1, y - 1);
        neighbours[2] = new Coordinates(x + 1, y);
        neighbours[3] = new Coordinates(x + 1, y + 1);
        neighbours[4] = new Coordinates(x, y + 1);
        neighbours[5] = new Coordinates(x - 1, y + 1);
        neighbours[6] = new Coordinates(x - 1, y);
        neighbours[7] = new Coordinates(x - 1, y - 1);
        return neighbours;
    }


    public Coordinates[] getCardinalNeighbours() {
        Coordinates[] neighbours = new Coordinates[4];
        neighbours[0] = new Coordinates(x, y - 1);
        neighbours[1] = new Coordinates(x + 1, y);
        neighbours[2] = new Coordinates(x, y + 1);
        neighbours[3] = new Coordinates(x - 1, y);
        return neighbours;
    }

    public boolean isWithinBoundsOf(Map map) {
        return (x >= 0 && x <= map.getWidthInTiles() - 1) && (y >= 0 && y <= map.getHeightInTiles() - 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }


}
