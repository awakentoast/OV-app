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
        int margin = 20;

        List<String> startStationServices = new ArrayList<>();
        List<String> endStationServices = new ArrayList<>();
        String[] serviceStrings = start.getServiceStrings();
        String[] printArray = new String[5];

        int index = -1;
        for (boolean service : start.getServices()) {
            index++;
            if (service) {
                startStationServices.add(serviceStrings[index]);
            }
        }

        index = -1;
        for (boolean service : start.getServices()) {
            index++;
            if (service) {
                endStationServices.add(serviceStrings[index]);
            }
        }

        if (!startStationServices.isEmpty()) {

        }
        printArray[0] = " ".repeat(60) + bundle.getString("beginStationServices.string") + " ".repeat(20) + bundle.getString("endStationServices.string");

        String first = bundle.getString("destination.string") + ": " + destination.getName();
        String last = bundle.getString("duration.string") + ": " + durationString;
        String fullString = first + " ".repeat(margin - first.length()) + last + "\n";

        margin--;
        first = bundle.getString("begin.string") + ": " + start.getName();
        last = bundle.getString("departure.string") + ": " + departure;
        String fullString2 = first + " ".repeat(--margin - first.length()) + last + "\n";

        first = bundle.getString("distance.string") + ": " +  distance + " km";

        return String.format(fullString) +
                String.format(fullString2) +
                String.format(bundle.getString("distance.string") + " " +  distance + " km" + "\n");
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

