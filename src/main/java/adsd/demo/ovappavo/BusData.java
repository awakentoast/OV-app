package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;

public class BusData extends Data {


    public BusData() {
        locationMap = locationFile.getLocations();
        routeMap = routeFile.getRoutes();
    }
    
    @Override
    public Location findLocation(String locationName) {
        Location foundLocation;
        for (Map.Entry<String, Location> entry : locationMap.entrySet()) {
            Location location = entry.getValue();
            
            if (location.getName().equals(locationName)) {
                foundLocation = location;
                return foundLocation;
            }
        }
        return null;
    }
    
    @Override
    public String getTransportType() {
        return "Bus";
    }
}
