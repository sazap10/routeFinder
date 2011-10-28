import java.util.*;
public class Station
{
    // instance variables - replace the example below with your own
    private String name;
    private ArrayList<Line> lines;
    private ArrayList<Station> stationLinks;
    public Station(String name)
    {
        this.name = name;
        lines = new ArrayList<Line>();
        stationLinks = new ArrayList<Station>();
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Line> getLines()
    {
        return lines;
    }

    public void addLine(Line line)
    {
        lines.add(line);
    }
    public ArrayList<Station> getLinks()
    {
        return stationLinks;
    }

    public void addLink(Station station)
    {
        stationLinks.add(station);
    }
}
