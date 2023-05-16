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
    public void write(String comboA, String comboB, String filteredRoute, LocalTime time)
    {
            if(getStopOver(comboA).getDeparture().isAfter(time)) {
                System.out.format("route: %s, dep. %s at %s arr. %s at %s\t", filteredRoute, getStopOver(comboA).getDeparture(), getStopOver(comboA).getName(), getStopOver(comboB).getArrival(), getStopOver(comboB).getName());
                getTripTime(comboA, comboB);
            }


        }


///////////////////////////////

     //   System.out.format("route: %s, dep. %s at %s arr. %s at %s\t", ,getStopOver(comboA).getDeparture(),getStopOver(comboA).getName(),getStopOver(comboB).getArrival(),getStopOver(comboB).getName());



    public void getTripTime(String comboA,String comboB)
    {
        var timeA = getStopOver(comboA).getDeparture();
        var timeB = getStopOver(comboB).getArrival();
        var tripTime =timeA.until(timeB,ChronoUnit.MINUTES);
        if (tripTime < 0) {
            throw new IllegalArgumentException("Het aantal minuten moet een positieve waarde zijn.");
        }

        long hours = tripTime / 60;
        long remainingMinutes = tripTime % 60;
        if(hours > 0){ System.out.format("Reisduur: %s uur en %s minuten\n",hours,remainingMinutes);}
        else System.out.format("Reisduur: %s minuten\n", tripTime);

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
}

