package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;

public class TrainData extends Data {
    
    public TrainData() {
        super("Train");
        // === Train stations ===
        var location = new Location("Abcoude",52.270281,4.971043);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Amersfoort",52.15625,5.389694);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Amsterdam",52.37276,4.893604);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Arnhem",51.985103,5.89873);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Emmen",52.788937,6.8939);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Groningen",53.219065,6.568008);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Haarlem",52.388532,4.638805);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Maastricht",50.857985,5.696988);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Nijmegen",51.844884,5.842828);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Rotterdam",51.922896,4.463173);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Utrecht",52.080952,5.12768);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Vlissingen",51.448093,3.569799);
        trainLocationMap.put(location.getName(), location);
        
        location = new Location("Xanten",51.661519,6.45432);
        trainLocationMap.put(location.getName(), location);
        
    }

    // set all trainData routes
    public void setRoute() {
        /// === Route Haarlem-Xanten ========
        for (int hour = 6; hour <= 19; hour += 1) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(trainLocationMap.get("Haarlem"), departure);
            route.addStopOver(trainLocationMap.get("Amsterdam"), LocalTime.of(hourTraject, 18), LocalTime.of(hourTraject, 23));
            route.addStopOver(trainLocationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 51));
            route.addStopOver(trainLocationMap.get("Arnhem"), LocalTime.of(++hourTraject, 25), LocalTime.of(hourTraject, 27));
            route.addStopOver(trainLocationMap.get("Nijmegen"), LocalTime.of(hourTraject, 37), LocalTime.of(hourTraject, 39));
            hourTraject = hourTraject + 3;
            route.addEndPoint(trainLocationMap.get("Xanten"), LocalTime.of(hourTraject, 52));
            routeMap.put(route.getKey(), route);
        }

        //=== Route Xanten-Nijmegen ===
        for (int hour = 6; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(trainLocationMap.get("Xanten"), departure);
            hourTraject = hourTraject + 3;
            route.addStopOver(trainLocationMap.get("Nijmegen"), LocalTime.of(hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addStopOver(trainLocationMap.get("Arnhem"), LocalTime.of(hourTraject, 45), LocalTime.of(hourTraject, 47));
            route.addStopOver(trainLocationMap.get("Utrecht"), LocalTime.of(++hourTraject, 15), LocalTime.of(hourTraject, 17));
            route.addStopOver(trainLocationMap.get("Amsterdam"), LocalTime.of(hourTraject, 45), LocalTime.of(hourTraject, 47));
            route.addEndPoint(trainLocationMap.get("Haarlem"), LocalTime.of(++hourTraject, 02));
            routeMap.put(route.getKey(), route);
        }

        // === Route Groningen-Vlissingen ===
        for (int hour = 7; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(trainLocationMap.get("Groningen"), departure);
            route.addStopOver(trainLocationMap.get("Amersfoort"), LocalTime.of(++hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addStopOver(trainLocationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 52));
            route.addStopOver(trainLocationMap.get("Rotterdam"), LocalTime.of(++hourTraject, 30), LocalTime.of(hourTraject, 33));
            route.addEndPoint(trainLocationMap.get("Vlissingen"), LocalTime.of(++hourTraject, 58));
            routeMap.put(route.getKey(), route);
        }

        // === Route Vlissingen-Groningen ===
        for (int hour = 8; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(trainLocationMap.get("Vlissingen"), departure);
            route.addStopOver(trainLocationMap.get("Rotterdam"), LocalTime.of(++hourTraject, 8), LocalTime.of(hourTraject, 10));
            route.addStopOver(trainLocationMap.get("Utrecht"), LocalTime.of(hourTraject, 49), LocalTime.of(hourTraject, 52));
            route.addStopOver(trainLocationMap.get("Amersfoort"), LocalTime.of(++hourTraject, 5), LocalTime.of(hourTraject, 8));
            route.addEndPoint(trainLocationMap.get("Groningen"), LocalTime.of(++hourTraject, 46));
            routeMap.put(route.getKey(), route);
        }
        
        // === Route Emmen-Maastricht ===
        for (int hour = 7; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(trainLocationMap.get("Emmen"), departure);
            hourTraject = hourTraject + 2;
            route.addStopOver(trainLocationMap.get("Arnhem"), LocalTime.of(hourTraject, 8), LocalTime.of(hourTraject, 10));
            route.addStopOver(trainLocationMap.get("Nijmegen"), LocalTime.of(hourTraject, 28), LocalTime.of(hourTraject, 30));
            route.addEndPoint(trainLocationMap.get("Maastricht"), LocalTime.of(++hourTraject, 22));
            routeMap.put(route.getKey(), route);
        }

        // === Route Maastricht-Groningen ===
        for (int hour = 7; hour <= 19; hour += 2) {
            int hourTraject = hour;
            var departure = LocalTime.of(hour, 0);
            var route = new Route(trainLocationMap.get("Maastricht"), departure);
            route.addStopOver(trainLocationMap.get("Nijmegen"), LocalTime.of(++hourTraject, 51), LocalTime.of(hourTraject, 55));
            route.addStopOver(trainLocationMap.get("Arnhem"), LocalTime.of(++hourTraject, 8), LocalTime.of(hourTraject, 10));
            route.addEndPoint(trainLocationMap.get("Emmen"), LocalTime.of(hourTraject, 48));
            routeMap.put(route.getKey(), route);
        }
    }
    
    @Override
    public Location findLocation(String locationName)
    {
        Location foundLocation= null;
        for (Map.Entry<String, Location> entry : trainLocationMap.entrySet()) {
            Location location = entry.getValue();
            
            if (location.getName().equals(locationName)) {
                foundLocation = location;
                return foundLocation;
            }
        }return null;
        
    }
}

// https://www.ns.nl/binaries/_ht_1574840979865/content/assets/ns-nl/dienstregeling/december-2019/spoorkaart-trajecten.pdf