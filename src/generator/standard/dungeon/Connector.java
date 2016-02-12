package generator.standard.dungeon;

import generator.standard.Coordinates;

/**
 * Created by Jeppe Vinberg on 02-02-2016.
 */
public class Connector {

    private Coordinates position;
    private Region region1, region2;

    public Connector(Coordinates position, Region region1, Region region2) {
        this.position = position;
        this.region1 = region1;
        this.region2 = region2;
    }

    public Coordinates getCoordinates() {
        return position;
    }

    public Region getRegion1() {
        return region1;
    }

    public Region getRegion2() {
        return region2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connector)) return false;

        Connector connector = (Connector) o;

        if (position != null ? !position.equals(connector.position) : connector.position != null) return false;
        if (region1 != null ? !region1.equals(connector.region1) || !region1.equals(connector.region2) : connector.region1 != null)
            return false;
        return !(region2 != null ? !region2.equals(connector.region2) || !region2.equals(connector.region1) : connector.region2 != null);

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (region1 != null ? region1.hashCode() : 0) + (region2 != null ? region2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return position + ": " + region1 + " <-> " + region2;
    }
}
