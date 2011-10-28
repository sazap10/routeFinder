import java.io.*;
import java.util.*;
import java.net.*;

public class load
{

    public void load(Network network)
    {
        try{
            InputStream in = this.getClass().getResourceAsStream("data.txt");
            InputStreamReader file = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(file);
            String strLine;
            int counter =  1;
            while ((strLine = br.readLine()) != null)   {
                String[] strArry = strLine.split(":");
                String[] lineStations = ((strArry[1]).toString()).split(",");
                Line line = new Line(strArry[0]);
                network.addLine(line);
                Station prevStation = null;
                for(String lineStation:lineStations){
                    Station station = null;
                    if(counter == 1)
                    {
                        station = new Station(lineStation);
                    }
                    else{
                        ArrayList<Station> allStations = network.getStations();
                        for(Station netStation:allStations)
                        {
                            if((netStation.getName()).equals(lineStation))
                            {
                                station = netStation;
                                break;
                            }
                        }
                        if(station == null)
                        {
                            station = new Station(lineStation);
                        }
                    }

                    line.addStation(station);
                    station.addLine(line);
                    network.addStation(station);
                    if(prevStation != null)
                    {
                        if(!(station.getLinks()).contains(prevStation))
                        {
                            station.addLink(prevStation);
                        }
                        if(!(prevStation.getLinks()).contains(station))
                        {
                            prevStation.addLink(station);
                        }
                    }
                    prevStation = station;
                    counter++;
                }
            }

            //Close the input stream
            file.close();
        }
        catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

