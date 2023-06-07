package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;

public class BusData extends Data {
    public BusData() {
        // === Bus stations ===
        Location location;
        location = new Location("Amersfoort", 51.180707, 5.137016);
        locationMap.put(location.getName(), location);

        location = new Location("Spakenburg", 52.25, 5.3666667);
        locationMap.put(location.getName(), location);

        location = new Location("De Bilt", 52.1092717, 5.1809676);
        locationMap.put(location.getName(), location);

        location = new Location("Bilthoven", 52.1307201, 5.2052623);
        locationMap.put(location.getName(), location);

        location = new Location("Soesterberg",52.120899,5.283606);
        locationMap.put(location.getName(), location);

        location = new Location("Rijnsweerd",52.0892,5.150985);
        locationMap.put(location.getName(), location);

        location = new Location("Utrecht",52.080952,5.12768);
        locationMap.put(location.getName(), location);

        location = new Location("Zeist", 52.1038954, 5.2605939);
        locationMap.put(location.getName(), location);
    }
    
    // set all trainData routes
    public void setRoute()
    {
        // Spakenburg, amersfoort, de bilt, bilthoven, soesterberg, rijnsweerd, zeist, Utrecht
        for (int hour = 7; hour <= 21; hour += 1) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Spakenburg"), departure);
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(hourTraject, 22), LocalTime.of(hourTraject, 24));
            route.addStopOver(locationMap.get("Soesterberg"), LocalTime.of(hourTraject, 35), LocalTime.of(hourTraject, 36));
            route.addEndPoint(locationMap.get("Rijnsweerd"), LocalTime.of(hourTraject, 53));
            routeMap.put(route.getKey(), route);
        }
        //=== Routes Utrecht-Amersfoort ===
        for (int hour = 7; hour <= 21; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Rijnsweerd"), departure);
            route.addStopOver(locationMap.get("Soesterberg"), LocalTime.of(hourTraject, 20), LocalTime.of(hourTraject, 23));
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(hourTraject, 32), LocalTime.of(hourTraject, 34));
            route.addEndPoint(locationMap.get("Spakenburg"), LocalTime.of(hourTraject, 53));
            routeMap.put(route.getKey(), route);
        }
        for (int hour = 7; hour <= 21; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 30);
            var route = new Route(locationMap.get("Zeist"), departure);
            route.addEndPoint(locationMap.get("Utrecht"), LocalTime.of(++hourTraject,0 ));
            route.addStopOver(locationMap.get("Rijnsweerd"), LocalTime.of(hourTraject, 24), LocalTime.of(hourTraject, 25));
            route.addStopOver(locationMap.get("De Bilt"), LocalTime.of(hourTraject, 55), LocalTime.of(hourTraject, 56));
            route.addEndPoint(locationMap.get("Bilthoven"), LocalTime.of(++hourTraject, 06));
            routeMap.put(route.getKey(), route);
        }
        for (int hour = 7; hour <= 21; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Bilthoven"), departure);
            route.addStopOver(locationMap.get("De Bilt"), LocalTime.of(hourTraject, 10), LocalTime.of(hourTraject, 11));
            route.addStopOver(locationMap.get("Rijnsweerd"), LocalTime.of(hourTraject, 43), LocalTime.of(hourTraject, 44));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(++hourTraject, 9), LocalTime.of(hourTraject, 10));
            route.addEndPoint(locationMap.get("Zeist"), LocalTime.of(hourTraject,40 ));
            routeMap.put(route.getKey(), route);
        }
        for (int hour = 7; hour <= 21; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Spakenburg"), departure);
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(hourTraject, 20), LocalTime.of(hourTraject, 22));
            route.addEndPoint(locationMap.get("Utrecht"), LocalTime.of(hourTraject,55 ));
            routeMap.put(route.getKey(), route);
        }
        for (int hour = 7; hour <= 21; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Utrecht"), departure);
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addEndPoint(locationMap.get("Spakenburg"), LocalTime.of(hourTraject,53 ));
            routeMap.put(route.getKey(), route);
        }
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
