import java.util.*;

public class Network
{
    // instance variables - replace the example below with your own
    private String name;
    private ArrayList<Line> lines;
    private ArrayList<Station> stations;
    /**
     * Constructor for objects of class Network
     */
    public Network()
    {
        this.name = "Underground";
        lines = new ArrayList<Line>();
        stations = new ArrayList<Station>();
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Line> getLines()
    {
        return lines;
    }

    public ArrayList<Station> getStations()
    {
        return stations;
    }

    public void addLine(Line line)
    {
        lines.add(line);
    }

    public void addStation(Station station)
    {
        if(!stations.contains(station))
        {
            stations.add(station);
        }
    }
    /**
     * This method looks at the ArrayList of all the stations, if it finds the station in
     * the arraylist then it is returned, otherwise it returns null.
     */
    public Station searchStation(String name) 
    {
        Station searchStation = null;
        for(Station station:stations)
        {
            if((station.getName()).equalsIgnoreCase(name))
            {
                searchStation = station;
            }
        }
        return searchStation;
    }
    /**
     * This resets the visited variable of all the lines to false 
     */
    public void resetVisited()
    {
        for(Line line:lines)
        {
            line.resetVisited();
        }
    }
    
}
