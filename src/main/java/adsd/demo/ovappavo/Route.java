package adsd.demo.ovappavo;

import adsd.demo.ovappavo.Location;
import adsd.demo.ovappavo.StopOver;

import java.time.LocalTime;
import java.util.ArrayList;

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
    public void write()
    {
        var first = stopOvers.get( 0 );
        var last  = stopOvers.get( stopOvers.size() - 1 );

        System.out.format( "route: %s, dep. %s at %s; arr. %s at %s\n", getKey(),
                first.getName(), first.getDeparture(), last.getName(), last.getArrival() );
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

