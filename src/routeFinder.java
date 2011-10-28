import java.util.*;

public class routeFinder
{
    private Network network;
    /**
     * Constructor for objects of class routeFinder
     */
    public routeFinder()
    {
        network = new Network();
        load load = new load();
        load.load(network);
    }

    public Network getNetwork()
    {
        return network;
    }

    public ArrayList<String> finder(Station source, Station destination)
    {
        ArrayList<Line> sourceLines = source.getLines();
        ArrayList<String> routes = new ArrayList<String>();
        boolean found = false;
        for(Line line:sourceLines)
        {
            ArrayList<Station> stations = line.getStations();
            if(stations.contains(destination))
            {
                Station endStart;
                if(stations.indexOf(destination) > stations.indexOf(source))
                {
                    endStart = stations.get(stations.size()-1);
                }
                else{
                    endStart = stations.get(0);
                }
                routes.add(endStart.getName());
                routes.add(line.getName());
                found = true;
                break;
            }
        }
        if(found == false){
            routes = search(source, destination, routes);
        }
        return routes;
    }
    /**
     * This method finds the junctions ie the stations which has more than one line,
     * then checks the lines of those junctions if it contains the destination.
     */
    public ArrayList<String> search(Station source, Station destination, ArrayList<String> routes)
    {
        boolean found = false;
        ArrayList<Line> sourceLines = source.getLines();
        for(Line line:sourceLines)
        {
            ArrayList<Station> searchList = new ArrayList<Station>();
            ArrayList<Station> lineStations = line.getStations();
            if(line.getVisited() == false)
            {
                for(Station station: lineStations)
                {
                    if(station.getLines().size() > 1)
                    {
                        searchList.add(station);
                    }
                }
            }

            for(Station station:searchList)
            {
                ArrayList<String> stationChanges = new ArrayList<String>();
                ArrayList<Line> lines = station.getLines();
                Station endLine;
                if(lineStations.indexOf(station) > lineStations.indexOf(source))
                {
                    endLine = lineStations.get(lineStations.size()-1);
                }
                else{
                    endLine = lineStations.get(0);
                }
                stationChanges.add(endLine.getName());
                stationChanges.add(line.getName());
                for(Line stationLine:lines)
                {
                    ArrayList<Station> stations = stationLine.getStations();
                    if(stations.contains(destination))
                    {
                        stationChanges.add(station.getName());
                        stationChanges.add(stationLine.getName());
                        Station endStart;
                        if(stations.indexOf(destination) > stations.indexOf(station))
                        {
                            endStart = stations.get(stations.size()-1);
                        }
                        else{
                            endStart = stations.get(0);
                        }
                        stationChanges.add(endStart.getName());
                        stationChanges.add(stationLine.getName());
                        for(String str:stationChanges)
                        {
                            routes.add(str);
                        }
                        found = true;
                        return routes;
                    }
                    else
                    {
                       line.markVisited();                        
                    }
                }

            }
        }
        /**
         * If the destination is not in the junctions lines then it runs the floydPath method to find the route.
         */
        if(found == false)
        {
            adjMatrix adj = new adjMatrix(network);
            routes = adj.pathReturn(source,destination);
        }
        return routes;
    }
}
