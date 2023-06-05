package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Objects;
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
        
        if (Objects.equals(bundle.getLocale().getLanguage(), "nl")) {
            return String.format("Van: " + start.getName()) +
                    String.format("%24s", "Vertrek: " + departure + "\n") +
                    String.format("Tot: " + destination.getName()) +
                    String.format("%27s", "Reisduur: " + durationString + "\n") +
                    String.format("Afstand: " + distance + " km" + "\n");
        } else {
            return String.format("From: " + start.getName()) +
                    String.format("%28s", "Departure: " + departure + "\n") +
                    String.format("To: " + destination.getName()) +
                    String.format("%32s", "Duration: " + durationString + "\n") +
                    String.format("Distance: " + distance + " km" + "\n");
        }
    }
    
    public String getStringForSaving() {
        return  departure.toString() + "-" +
                start.getName() + "-" +
                destination.getName() + "-" +
                distance + "-" +
                duration + "-" +
                transportType;
    }
    
//    public Trip getTripFromSave(String tripString) {
//        Data dataHelper = new Data();
//        String[] tripData = tripString.split("-");
//        //return new Trip(LocalTime.of()tripData[2], new Location(tripData[0]), new Location(tripData[1]));
//        return
//    }
}
