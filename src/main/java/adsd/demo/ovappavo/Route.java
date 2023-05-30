package adsd.demo.ovappavo;

import adsd.demo.ovappavo.Location;
import adsd.demo.ovappavo.StopOver;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route
{

    private final ArrayList<StopOver> stopOvers = new ArrayList<>();

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public Route(Location beginLocation, LocalTime departure )
    {
        var stopover = new StopOver( beginLocation.getName(), null, departure );
        stopOvers.add( stopover );
    }

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public void addStopOver( Location loc, LocalTime arrival, LocalTime departure )
    {
        var stopover = new StopOver( loc.getName(), arrival, departure );
        stopOvers.add( stopover );
    }

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public void addEndPoint( Location loc, LocalTime arrival )
    {
        var stopover = new StopOver( loc.getName(), arrival, null );
        stopOvers.add( stopover );
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


    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public void write(Location comboA, Location comboB, String filteredRoute, LocalTime time)
    {
            double distance = getDistance(comboA,comboB);

            if(getStopOver(comboA.getName()).getDeparture().isAfter(time)) {
                System.out.format("route: %s, dep. %s at %s arr. %s at %s\n", filteredRoute, getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
                printTripTime(comboA.getName(), comboB.getName());
                System.out.format("afstand: %.2f km\n\n",distance);
            }


    }


///////////////////////////////

     //   System.out.format("route: %s, dep. %s at %s arr. %s at %s\t", ,getStopOver(comboA).getDeparture(),getStopOver(comboA).getName(),getStopOver(comboB).getArrival(),getStopOver(comboB).getName());



    public void printTripTime(String comboA,String comboB)
    {
        var timeA = getStopOver(comboA).getDeparture();
        var timeB = getStopOver(comboB).getArrival();
        var tripTime =timeA.until(timeB,ChronoUnit.MINUTES);
        if (tripTime < 0)
        {
            throw new IllegalArgumentException("Het aantal minuten moet een positieve waarde zijn.");
        }

        long hours = tripTime / 60;
        long remainingMinutes = tripTime % 60;
        if(hours > 0){ System.out.format("Reisduur: %s uur en %s minuten\t",hours,remainingMinutes);}
        else System.out.format("Reisduur: %s minuten\t", tripTime);


    }
    public StopOver getStopOver (String locationKey)
    {
        for (var h : stopOvers)
        {
            if (h.getName().equals(locationKey))
            {
                return h;
            }
        }

        return null;
    }
    public double getDistance(Location comboA, Location comboB) {


        double distance = HaversineCalculator(comboA.getLatitude(), comboA.getLongitude(), comboB.getLatitude(), comboB.getLongitude());
        return distance;
    }

    public double HaversineCalculator(double lat1, double lon1, double lat2, double lon2)  // Haversine methode
    {
       final double EARTH_RADIUS = 6371; // Aardstraal in kilometers


            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            double distance = EARTH_RADIUS * c;
            return distance;

    }

}

