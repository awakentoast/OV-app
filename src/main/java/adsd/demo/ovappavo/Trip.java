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
        int margin = 30;

        String fromPrefix = bundle.getString("begin.string");
        String toPrefix = bundle.getString("destination.string");
        String durationPrefix = bundle.getString("duration.string");
        String distancePrefix = bundle.getString("distance.string");
        String departurePrefix = bundle.getString("departure.string");




        String first =  + ": " + destination.getName();
        String last =  + ": " + durationString;
        String fullString = first + " ".repeat(margin - first.length()) + last + "\n";

        margin--;
        first = bundle.getString("begin.string") + ": " + start.getName();
        last =  + ": " + departure;
        String fullString2 = first + " ".repeat(--margin - first.length()) + last + "\n";

        return String.format(fullString) +
                String.format(fullString2) +
                String.format( + " " +  distance + " km" + "\n");
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

