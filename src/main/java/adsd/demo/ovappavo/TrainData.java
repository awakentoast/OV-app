package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;

public class TrainData extends Data {
    
    public TrainData() {
        // === Train stations ===
        var location = new Location("Abcoude",52.270281,4.971043, true, true, false, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Amersfoort",52.15625,5.389694, true, true, true, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Amsterdam",52.37276,4.893604, true, true, true, true);
        locationMap.put(location.getName(), location);
        
        location = new Location("Arnhem",51.985103,5.89873, false, true, true, true);
        locationMap.put(location.getName(), location);
        
        location = new Location("Emmen",52.788937,6.6939, false, true, false, true);
        locationMap.put(location.getName(), location);
        
        location = new Location("Groningen",53.219065,6.568008, false, true, false, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Haarlem",52.388532,4.638805, true, true, false, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Maastricht",50.857985,5.696988, true, true, true, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Nijmegen",51.844884,5.742828, true, true, true, true);
        locationMap.put(location.getName(), location);
        
        location = new Location("Rotterdam",51.922896,4.463173, false, true, true, true);
        locationMap.put(location.getName(), location);
        
        location = new Location("Utrecht",52.080952,5.12768, false, true, false, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Vlissingen",51.418093,3.579799, false, true, false, false);
        locationMap.put(location.getName(), location);
        
        location = new Location("Xanten",51.661519,6.45432, true, true, false, false);
        locationMap.put(location.getName(), location);
        
    }

    // set all trainData routes
    public void setRoute() {
        /// === Route Haarlem-Xanten ========
        for (int hour = 6; hour <= 19; hour += 1) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Haarlem"), departure);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hourTraject, 18), LocalTime.of(hourTraject, 23));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 51));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hourTraject, 25), LocalTime.of(hourTraject, 27));
            hourTraject = hourTraject + 3;
            route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hourTraject, 40));
            routeMap.put(route.getKey(), route);
        }

        //=== Route Xanten-Nijmegen ===
        for (int hour = 6; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Xanten"), departure);
            hourTraject = hourTraject + 3;
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hourTraject, 45), LocalTime.of(hourTraject, 47));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(++hourTraject, 15), LocalTime.of(hourTraject, 17));
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hourTraject, 45), LocalTime.of(hourTraject, 47));
            route.addEndPoint(locationMap.get("Haarlem"), LocalTime.of(++hourTraject, 02));
            routeMap.put(route.getKey(), route);
        }

        // === Route Groningen-Vlissingen ===
        for (int hour = 7; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Groningen"), departure);
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(++hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 52));
            route.addStopOver(locationMap.get("Rotterdam"), LocalTime.of(++hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addEndPoint(locationMap.get("Vlissingen"), LocalTime.of(++hourTraject, 58));
            routeMap.put(route.getKey(), route);
        }

        // === Route Vlissingen-Groningen ===
        for (int hour = 8; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Vlissingen"), departure);
            route.addStopOver(locationMap.get("Rotterdam"), LocalTime.of(++hourTraject, 8), LocalTime.of(hourTraject, 10));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 52));
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(++hourTraject, 5), LocalTime.of(hourTraject, 8));
            route.addEndPoint(locationMap.get("Groningen"), LocalTime.of(++hourTraject, 46));
            routeMap.put(route.getKey(), route);
        }
        
        // === Route Emmen-Maastricht ===
        for (int hour = 7; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Emmen"), departure);
            hourTraject = hourTraject + 2;
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hourTraject, 8), LocalTime.of(hourTraject, 10));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hourTraject, 28), LocalTime.of(hourTraject, 30));
            route.addEndPoint(locationMap.get("Maastricht"), LocalTime.of(++hourTraject, 22));
            routeMap.put(route.getKey(), route);
        }

        // === Route Maastricht-Groningen ===
        for (int hour = 7; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Maastricht"), departure);
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(++hourTraject, 51), LocalTime.of(hourTraject, 55));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(++hourTraject, 8), LocalTime.of(hourTraject, 10));
            route.addEndPoint(locationMap.get("Emmen"), LocalTime.of(hourTraject, 48));
            routeMap.put(route.getKey(), route);
        }
    }
    
    @Override
    public Location findLocation(String locationName)
    {
        Location foundLocation= null;
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
        return "Train";
    }
}

// https://www.ns.nl/binaries/_ht_1574840979865/content/assets/ns-nl/dienstregeling/december-2019/spoorkaart-trajecten.pdf