package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;

public class BusData extends Data {
    
    
    
    
    public BusData() {
        super("Bus");
        
        // === Bus stations ===
        Location location;
        location = new Location("Amersfoort", 51.180707, 5.137016);
        busLocationMap.put(location.getName(), location);
        
        location = new Location("De Bilt", 52.1092717, 5.1809676);
        busLocationMap.put(location.getName(), location);
        
        location = new Location("Bilthoven", 52.1307201, 5.2052623);
        busLocationMap.put(location.getName(), location);
        
        location = new Location("Soesterberg", 52.120899, 5.283606);
        busLocationMap.put(location.getName(), location);
        
        location = new Location("Rijnsweerd", 52.0892, 5.150985);
        busLocationMap.put(location.getName(), location);
        
        location = new Location("Zeist", 52.1038954, 5.2605939);
        busLocationMap.put(location.getName(), location);
        
        
    }
    
    // set all trainData routes
    public void setRoute() {
        /// === Routes Amersfoort-Utrecht ========
        for (int hour = 6; hour <= 19; hour += 1) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(busLocationMap.get("Haarlem"), departure);
            route.addStopOver(busLocationMap.get("Amsterdam"), LocalTime.of(hourTraject, 18), LocalTime.of(hourTraject, 23));
            route.addStopOver(busLocationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 51));
            route.addStopOver(busLocationMap.get("Arnhem"), LocalTime.of(++hourTraject, 25), LocalTime.of(hourTraject, 27));
            route.addStopOver(busLocationMap.get("Nijmegen"), LocalTime.of(hourTraject, 37), LocalTime.of(hourTraject, 39));
            hourTraject = hourTraject + 3;
            route.addEndPoint(busLocationMap.get("Xanten"), LocalTime.of(hourTraject, 52));
            routeMap.put(route.getKey(), route);
        }
        //=== Routes Utrecht-Amersfoort ===
        for (int hour = 6; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(busLocationMap.get("Xanten"), departure);
            hourTraject = hourTraject + 3;
            route.addStopOver(busLocationMap.get("Nijmegen"), LocalTime.of(hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addStopOver(busLocationMap.get("Arnhem"), LocalTime.of(hourTraject, 45), LocalTime.of(hourTraject, 47));
            route.addStopOver(busLocationMap.get("Utrecht"), LocalTime.of(++hourTraject, 15), LocalTime.of(hourTraject, 17));
            route.addStopOver(busLocationMap.get("Amsterdam"), LocalTime.of(hourTraject, 45), LocalTime.of(hourTraject, 47));
            route.addEndPoint(busLocationMap.get("Haarlem"), LocalTime.of(++hourTraject, 02));
            routeMap.put(route.getKey(), route);
        }
        //        // === Routes Groningen-Vlissingen ===
        //        for (int hour = 7; hour <= 19; hour += 2) {
        //            int hourTraject = hour;
        //            var departure = LocalTime.of(hour, 0);
        //            var route = new Route(busLocationMap.get("Groningen"), departure);
        //            route.addStopOver(busLocationMap.get("Amersfoort"), LocalTime.of(++hourTraject, 30), LocalTime.of(hourTraject, 33));
        //            route.addStopOver(busLocationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 52));
        //            route.addStopOver(busLocationMap.get("Rotterdam"), LocalTime.of(++hourTraject, 30), LocalTime.of(hourTraject, 33));
        //            route.addEndPoint(busLocationMap.get("Vlissingen"), LocalTime.of(++hourTraject, 58));
        //            routeMap.put(route.getKey(), route);
        //        }
        //        // === Routes Vlissingen-Groningen ===
        //        for (int hour = 8; hour <= 19; hour += 2) {
        //            int hourTraject = hour;
        //            var departure = LocalTime.of(hour, 0);
        //            var route = new Route(busLocationMap.get("Vlissingen"), departure);
        //            route.addStopOver(busLocationMap.get("Rotterdam"), LocalTime.of(++hourTraject, 8), LocalTime.of(hourTraject, 10));
        //            route.addStopOver(busLocationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 52));
        //            route.addStopOver(busLocationMap.get("Amersfoort"), LocalTime.of(++hourTraject, 5), LocalTime.of(hourTraject, 8));
        //            route.addEndPoint(busLocationMap.get("Groningen"), LocalTime.of(++hourTraject, 46));
        //            routeMap.put(route.getKey(), route);
        //        }
        //        // === Routes Emmen-Maastricht ===
        //        for (int hour = 7; hour <= 19; hour += 2) {
        //            int hourTraject = hour;
        //            var departure = LocalTime.of(hour, 0);
        //            var route = new Route(busLocationMap.get("Emmen"), departure);
        //            hourTraject = hourTraject + 2;
        //            route.addStopOver(busLocationMap.get("Arnhem"), LocalTime.of(hourTraject, 8), LocalTime.of(hourTraject, 10));
        //            route.addStopOver(busLocationMap.get("Nijmegen"), LocalTime.of(hourTraject, 28), LocalTime.of(hourTraject, 30));
        //            route.addEndPoint(busLocationMap.get("Maastricht"), LocalTime.of(++hourTraject, 22));
        //            routeMap.put(route.getKey(), route);
        //        }
        //
        //        // === Routes Maastricht-Groningen ===
        //        for (int hour = 7; hour <= 19; hour += 2) {
        //            int hourTraject = hour;
        //            var departure = LocalTime.of(hour, 0);
        //            var route = new Route(busLocationMap.get("Maastricht"), departure);
        //            route.addStopOver(busLocationMap.get("Nijmegen"), LocalTime.of(++hourTraject, 51), LocalTime.of(hourTraject, 55));
        //            route.addStopOver(busLocationMap.get("Arnhem"), LocalTime.of(++hourTraject, 8), LocalTime.of(hourTraject, 10));
        //            route.addEndPoint(busLocationMap.get("Emmen"), LocalTime.of(hourTraject, 48));
        //            routeMap.put(route.getKey(), route);
        //        }
    }
    
    @Override
    public Location findLocation(String locationName) {
        Location foundLocation = null;
        for (Map.Entry<String, Location> entry : busLocationMap.entrySet()) {
            Location location = entry.getValue();
            
            if (location.getName().equals(locationName)) {
                foundLocation = location;
                return foundLocation;
            }
        }
        return null;
        
    }
}
