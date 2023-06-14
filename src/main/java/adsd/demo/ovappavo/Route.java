package adsd.demo.ovappavo;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Stop;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Route
{
    private final ArrayList<StopOver> stopOvers = new ArrayList<>();

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public Route(Location beginLocation, LocalTime departure )
    {
        StopOver stopover = new StopOver(beginLocation, null, departure);
        stopOvers.add(stopover);
    }
    
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public void addStopOver(Location beginLocation, LocalTime arrival, LocalTime departure) {
        
        StopOver stopover = new StopOver(beginLocation, arrival, departure);
        stopOvers.add(stopover);
    }

    public void addStopOver(StopOver stopOver) {
        stopOvers.add(stopOver);
    }
    
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public void addEndPoint(Location beginLocation, LocalTime arrival) {
        StopOver stopover = new StopOver(beginLocation, arrival, null);
        stopOvers.add(stopover);
    }

    ///////////////////////////////////////////////////////////////
    // Construct a key associated with a Route instance by appending
    // the names of the stopovers in this route, separated by a '-'.
    // To make the key unique, append '|' + departure time.
    ///////////////////////////////////////////////////////////////
    public String getKey()
    {
        String key = stopOvers.get( 0 ).getName();

        for (int i = 1; i < stopOvers.size(); i++)
        {
            key += "-";
            key += stopOvers.get( i ).getName();
        }

        key += "|";
        key += stopOvers.get( 0 ).getDeparture();

        return key;
    }
    
    public List<Location> getLocationList() {
        return new ArrayList<>(stopOvers);
    }

    public Route getSubRoute(int startIndex, int endIndex, boolean reversed) {
        StopOver stopOver = stopOvers.get(startIndex);

        Route route;

        if (!reversed) {
            route = new Route(stopOver, stopOver.getDeparture());

            startIndex++;
            for (; startIndex < endIndex; startIndex++) {
                route.addStopOver(stopOvers.get(startIndex));
            }

            route.addEndPoint(stopOvers.get(startIndex), stopOvers.get(startIndex).getArrival());
        } else {
            route = new Route(stopOver, stopOver.getArrival());

            startIndex--;
            for (; startIndex > endIndex; startIndex--) {
                route.addStopOver(stopOvers.get(startIndex));
            }

            route.addEndPoint(stopOvers.get(startIndex), stopOvers.get(startIndex).getDeparture());
        }
        return route;
    }


    public int getTripTime()
    {
        LocalTime departure = stopOvers.get(0).getDeparture();
        LocalTime arrival = stopOvers.get(stopOvers.size() - 1).getArrival();

        //if it's a reverse trip the time is negative
        return Math.abs((int) departure.until(arrival, ChronoUnit.MINUTES));
    }
    
    public double getDistance(Location start, Location destination)
    {
        return haversineCalculator(start.getLatitude(), start.getLongitude(), destination.getLatitude(), destination.getLongitude());
    }

    public double haversineCalculator(double lat1, double lon1, double lat2, double lon2)  // Haversine methode
    {
        final double EARTH_RADIUS = 6371; // Aardstraal in kilometers

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS * c;

    }
    public void printMessageFromRoute(TextArea textArea)
    {
        String message = "Dit is een bericht vanuit de Route";
        textArea.setText(message + "\n");
    }
}

