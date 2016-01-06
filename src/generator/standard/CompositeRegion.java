package generator.standard;

import java.util.ArrayList;

/**
 * Created by Jeppe Vinberg on 06-01-2016.
 */
public class CompositeRegion implements Region {

    private ArrayList<Region> regions;

    public CompositeRegion(){
        this.regions = new ArrayList<>();
    }

    public void addRegion(Region region){
        regions.add(region);
    }

    @Override
    public ArrayList<Coordinates> getTiles() {
        ArrayList<Coordinates> result = new ArrayList<>();
        for(Region r : regions){
            result.addAll(r.getTiles());
        }
        return result;
    }

    @Override
    public void addTile(Coordinates coords) {
        //it doesn't make sense to add a tile to the composite region
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeRegion that = (CompositeRegion) o;

        return !(regions != null ? !regions.equals(that.regions) : that.regions != null);

    }

    @Override
    public int hashCode() {
        return regions != null ? regions.hashCode() : 0;
    }
}
