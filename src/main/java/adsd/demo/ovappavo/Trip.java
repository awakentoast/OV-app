package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Trip {
    private final LocalTime departure;
    private final Location  start;
    private final Location  destination;
    private final double distance;
    private final int duration;
    private final String transportType;
    
    
    public Trip(LocalTime departure, Location start, Location destination, double distance, int duration, String transportType) {
        this.departure = departure;
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.transportType = transportType;
    }
    
    public LocalTime getDeparture() {
        return departure;
    }
    
    public Location getStart() {
        return start;
    }
    
    public Location getDestination() {
        return destination;
    }
    
    public String getStringForDisplay(ResourceBundle bundle) {
        int hours   = duration / 60;
        int minutes = duration % 60;

        String durationString = String.format("%d:%02d", hours, minutes);

        String fromPrefix = bundle.getString("begin.string") + ": " + start.getName();
        String toPrefix = bundle.getString("destination.string") + ": " + destination.getName();
        String distancePrefix = bundle.getString("distance.string") + ": " + distance + " km";
        String departurePrefix = bundle.getString("departure.string") + ": " + departure;
        
        String servicesStart = bundle.getString("beginStationServices.string");
        String servicesEnd = bundle.getString("endStationServices.string");
        
        durationString = bundle.getString("duration.string") + ": " + durationString;


        String line = " ".repeat(85) + servicesEnd;
        line = line.substring(0,50) + servicesStart + line.substring(50 + servicesStart.length());
        line = line.substring(0, 20) + toPrefix + line.substring(20 + toPrefix.length());
        line = fromPrefix + line.substring(fromPrefix.length()) + "\n";
        
        String line2 = " ".repeat(24) + distancePrefix;
        line2 = durationString + line2.substring(durationString.length()) + "\n";
        
        System.out.println(line);
        
        return line + line2 + departurePrefix;
    }
    
    public String getStringForSaving() {
        return  departure.toString() + "-" +
                start.getName() + "-" +
                destination.getName() + "-" +
                distance + "-" +
                duration + "-" +
                transportType;
    }
}

