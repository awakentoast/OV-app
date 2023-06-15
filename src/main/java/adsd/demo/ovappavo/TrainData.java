package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;

public class TrainData extends Data {
    
    public TrainData() {
        locationMap = locationFile.getLocations();
        routeMap = routeFile.getRoutes();
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