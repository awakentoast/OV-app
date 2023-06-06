package adsd.demo.ovappavo;

import javafx.scene.control.TextArea;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    public void write(Location comboA, Location comboB, String filteredRoute, LocalTime time)
    {
//        double distance = getDistance(comboA,comboB);
//
//<<<<<<< HEAD
//        if(getStopOver(comboA.getName()).getDeparture().) {
//            System.out.format("route: %s, dep. %s at %s arr. %s at %s\n", filteredRoute, getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
//=======
//            if(getStopOver(comboA.getName()).getDeparture().isAfter(time)) {
//                System.out.format("route: %s, dep. %s at %s arr. %s at %s\n", filteredRoute, getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
//
//                System.out.format("afstand: %.2f km\n\n",distance);
//
//
//
//               String message = String.format("dep. %s at %s arr. %s at %s\n",getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
//                textArea.appendText(message);
//               message = String.format("route: %s \n",filteredRoute);
//               textArea.appendText(message);
//               printTripTime(comboA.getName(), comboB.getName(),textArea);
//               message = String.format("afstand: %.2f km\n\n",distance);
//               textArea.appendText(message);
//            }
//>>>>>>> e7f3710fcf5d6ec4a53165ea5a73edb3543639d8
//
//            System.out.format("afstand: %.2f km\n\n",distance);
//
//           String message = String.format("route: %s, dep. %s at %s arr. %s at %s\n", filteredRoute, getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
//           textArea.appendText(message);
//           //getTripTime(comboA.getName(), comboB.getName());
//           message = String.format("afstand: %.2f km\n\n",distance);
//           textArea.appendText(message);
//        }
//
//        double distance = getDistance(comboA,comboB);
//
//        if(getStopOver(comboA.getName()).getDeparture().isAfter(time)) {
//            System.out.format("route: %s, dep. %s at %s arr. %s at %s\n", filteredRoute, getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
//
//            System.out.format("afstand: %.2f km\n\n",distance);
//
//
//
//            String message = String.format("dep. %s at %s arr. %s at %s\n",getStopOver(comboA.getName()).getDeparture(), comboA.getName(), getStopOver(comboB.getName()).getArrival(), comboB.getName());
//
//            message = String.format("route: %s \n",filteredRoute);
//
//            printTripTime(comboA.getName(), comboB.getName(),textArea);
//            message = String.format("afstand: %.2f km\n\n",distance);
//
//        }

    }


///////////////////////////////

     //   System.out.format("route: %s, dep. %s at %s arr. %s at %s\t", ,getStopOver(comboA).getDeparture(),getStopOver(comboA).getName(),getStopOver(comboB).getArrival(),getStopOver(comboB).getName());



    public int getTripTime(Location comboA, Location comboB)
    {
        LocalTime timeA = getStopOver(comboA.getName()).getDeparture();
        LocalTime timeB = getStopOver(comboB.getName()).getArrival();
        int tripTime = (int) timeA.until(timeB, ChronoUnit.MINUTES);
        
        
        if (tripTime < 0)
        {
            throw new IllegalArgumentException("Het aantal minuten moet een positieve waarde zijn.");
        }
        
        return tripTime;
//        String message;
//        long hours = tripTime / 60;
//        long remainingMinutes = tripTime % 60;
//        message = String.format("Reisduur: %s uur en %s minuten\t",hours,remainingMinutes);
//        textArea.appendText(message);
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

