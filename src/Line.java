import java.util.*;

public class Line
{
    private String name;
    private ArrayList<Station> stations;
    private boolean visited;

    /**
     * Constructor for objects of class Line
     */
    public Line(String name)
    {
        this.name = name;
        stations = new ArrayList<Station>();
        this.visited = false;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Station> getStations(){
        return stations;
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public boolean getVisited()
    {
        return visited;
    }

    public void markVisited()
    {
        visited = true;
    }

    public void resetVisited()
    {
        visited = false;
    }
}
