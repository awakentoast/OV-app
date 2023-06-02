package adsd.demo.ovappavo;

import java.time.LocalTime;

public class Trip {
    private final LocalTime departure;
    private final Location  locationA;
    private final Location  locationB;
    private final double distance;
    private final int duration;
    private final String transportType;
    
    
    public Trip(LocalTime departure, Location locationA, Location locationB, double distance, int duration, String transportType) {
        this.departure = departure;
        this.locationA = locationA;
        this.locationB = locationB;
        this.distance = distance;
        this.duration = duration;
        this.transportType = transportType;
    }
    
    public LocalTime getDeparture()
    {
        return departure;
    }

    public String getStringForDisplay() {
        return  String.format("Van: " + locationA.getName()) +
                String.format("%29s", "Vervoerstype: " + transportType + "\n") +
                String.format("Tot: " + locationB.getName()) +
                String.format("%27s", "Reisduur: " + duration + "\n") +
                String.format("Vertrek: " + departure) +
                String.format("%25s", "Distance: " + distance + "\n");
    }
    
    public String getStringForSaving() {
        return  departure.toString() + "-" +
                locationA.getName() + "-" +
                locationB.getName() + "-" +
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
