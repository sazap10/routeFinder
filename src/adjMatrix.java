import java.util.*;
import java.lang.*;

public class adjMatrix
{

    private Network network;
    private int inf;
    private int[][] adjMatrix;
    private int size;
    private ArrayList<Station> networkStations;    
    private Station[][] path;

    public adjMatrix(Network network)
    {
        this.network = network;
        inf = Integer.MAX_VALUE;
        networkStations = network.getStations();
        size = networkStations.size();
        adjMatrix = new int[size][size]; // creates an adjancency matrix of the stations by stations
        path = new Station[size][size];
        createAdjMatrix();
        floydPath();
    }

    public void createAdjMatrix() // Iterates through all the stations, filling in the appropriate values in the adjMatrix
    {
        for(int row = 0;row < size;row++)
        {
            ArrayList<Station> adjStations = networkStations.get(row).getLinks();
            for(int col = 0;col < size;col++)
            {
                if(row == col)
                {
                    adjMatrix[row][col] = 0;
                }
                else{
                    if(adjStations.contains(networkStations.get(col)))
                    {
                        adjMatrix[row][col] = 1;
                    }
                    else
                    {
                        adjMatrix[row][col] = inf;
                    }
                }
            }
        }

    }

    public void floydPath() // Implementation of floydPath algorithm
    {
        for(int k = 0; k < size; k++)
        {
            for(int i = 0; i < size; i++)
            {
                for(int j = 0; j < size; j++)
                {
                    if(adjMatrix[i][k] != inf && adjMatrix[k][j] != inf && (adjMatrix[i][k]+adjMatrix[k][j]) < adjMatrix[i][j])
                    {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        path[i][j] = networkStations.get(k);
                    }
                }
            }
        }
    } 

    public ArrayList<Station> getPath(int i, int j) // returns the path by recursion
    {
        Station intermediate = path[i][j];
        if(intermediate == null)
        {
            return new ArrayList<Station>();
        }
        else
        {
            ArrayList<Station> route = new ArrayList<Station>();
            route.addAll(getPath(i,networkStations.indexOf(intermediate)));
            route.add(intermediate);
            route.addAll(getPath(networkStations.indexOf(intermediate),j));
            return route;
        }
    }

    public ArrayList<String> pathReturn(Station source, Station destination) // returns the route to routeFinder
    {
        int i = networkStations.indexOf(source);
        int j = networkStations.indexOf(destination);
        ArrayList<Station> routePath = getPath(i,j);
        routePath.add(0, source);
        routePath.add(destination);
        ArrayList<String> route = mod(routePath);
        return route;
    }
    
    public ArrayList<String> mod(ArrayList<Station> route)
    {
        Station source = route.get(0);
        while(!source.equals(route.get(route.size()-1)))
        {
            Station change = route.get(route.size()-1);
            boolean contains = false;
            while(contains == false)
            {
                int counter = 0;
                for (Line line : source.getLines())
                {
                    if (line.getStations().contains(change) && counter == 0)
                    { 
                        if (route.indexOf(change) - route.indexOf(source) != 1)
                        {
                            route.subList(route.indexOf(source) + 1 ,route.indexOf(change)).clear(); // after finding a chain of stations it removes it as it is in the same line
                        }
                        counter++;
                        contains = true;
                    }
                }
                if (contains == false)
                {
                    change = route.get(route.indexOf(change) - 1);
                }
            }
            source = change;
        }
        ArrayList<String> stringRoute = new ArrayList<String>();
        /**
         * Returns the correct format for outputting the route
         */
        for(int i = 0; i < route.size() -1; i++)
        {
            Station current = route.get(i);
            Station next = route.get(i+1);
            int counter = 0;
            for(Line curLine:current.getLines())
            {
                for(Line nextLine: next.getLines())
                {
                    if(curLine == nextLine && counter == 0)
                    {
                        Station endOfLine = null;
                        ArrayList<Station> lineStations = curLine.getStations();
                        if(lineStations.indexOf(next) > lineStations.indexOf(current))
                        {
                            endOfLine = lineStations.get(lineStations.size()-1);
                        }
                        else if(lineStations.indexOf(next) < lineStations.indexOf(current))
                        {
                            endOfLine = lineStations.get(0);
                        }
                        if(i != 0)
                        {
                            stringRoute.add(curLine.getName());
                        }
                        stringRoute.add(endOfLine.getName());
                        stringRoute.add(curLine.getName());
                        if(i != route.size() -2)
                        {
                            stringRoute.add(next.getName());
                        }
                        counter++;
                    }
                }
            }
        }
        return stringRoute;
    }
}
