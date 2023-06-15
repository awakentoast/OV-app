package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class Trip {
    private final LocalTime departure;
    private final Location  start;
    private final Location  destination;
    private final double distance;
    private final int duration;
    private final String transportType;

    //it's a list of locations instead of a route, because making a new route from plaintext after saving is a pain, and we only need the locations for the map as of now
    private final List<Location> locationList;
    
    
    public Trip(LocalTime departure, Location start, Location destination, double distance, int duration, String transportType, List<Location>locationList) {
        this.departure = departure;
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.transportType = transportType;
        this.locationList = locationList;
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
        
        String servicesStart = bundle.getString("beginStationServices.string") + " " + start.getName();
        String servicesEnd = bundle.getString("endStationServices.string") + " " + destination.getName();
        
        durationString = bundle.getString("duration.string") + ": " + durationString;


        String line = " ".repeat(85) + servicesEnd;
        line = line.substring(0,50) + servicesStart + line.substring(50 + servicesStart.length());
        line = line.substring(0, 20) + toPrefix + line.substring(20 + toPrefix.length());
        line = fromPrefix + line.substring(fromPrefix.length()) + "\n";
        
        String line2 = " ".repeat(21) + distancePrefix;
        line2 = durationString + line2.substring(durationString.length()) + "\n";
        
        //System.out.println(line);
        
        return line + line2 + departurePrefix;
    }
    
    
    public String getStringForSaving() {
        StringBuilder sb = new StringBuilder();
        for (Location location : locationList) {
            sb.append(location.getName()).append("-");
        }
        String route = sb.toString();
        return  departure.toString() + "," +
                start.getName() + "," +
                destination.getName() + "," +
                distance + "," +
                duration + "," +
                transportType + "," +
                route.substring(0,route.length() - 1);
    }
    
    public List<Location> getLocationList() {
        return locationList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Trip other = (Trip) obj;

        boolean sameList = true;

        if (locationList.size() != other.locationList.size()) {
            sameList = false;
        } else {
            for (int i = 0; i < locationList.size(); i++) {
                if (!locationList.get(i).getName().equals(other.locationList.get(i).getName())) {
                    sameList = false;
                    break;
                }
            }
        }

        System.out.println(sameList);

        return start.getName().equals(other.start.getName()) &&
                destination.getName().equals(other.destination.getName()) &&
                distance == other.distance &&
                departure.equals(other.departure) &&
                duration == other.duration &&
                transportType.equals(other.transportType) &&
                sameList;
    }
}

