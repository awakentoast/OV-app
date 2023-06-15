package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.*;

public abstract class Data {
    protected RouteFile routeFile;
    protected LocationFile locationFile;

    protected Map<String, Location> locationMap = new TreeMap<>();
    protected Map<String, Route> routeMap = new TreeMap<>();
    
    public String[] getLocationNames() {
        String[] names = new String[locationMap.size()];
        int index = 0;
        for (String locationName : locationMap.keySet()) {
            names[index++] = locationName;
        }
        return names;
    }

    public List<Location> getLocations() {
        List<Location> locationList = new ArrayList<>(locationMap.size());
        locationList.addAll(locationMap.values());
        return locationList;
    }
    
    public Set<String> getRouteStrings() {
        Set<String> routeSet = new HashSet<>();
        for (String route : routeMap.keySet()) {
            routeSet.add(route.split("\\|")[0]);
        }
        return routeSet;
    }
    
    
    public List<Trip> getValidRoutes(Location start, Location destination, LocalTime departure) {
        List<Trip> trips = new ArrayList<>();
        
        for (var e : routeMap.entrySet()) {
//            String patternString = start.getName() + ".*?" + destination.getName();
//            Pattern pattern = Pattern.compile(patternString);
//            Matcher matcher = pattern.matcher(key);
//            while (matcher.find()) {}

            String key = e.getKey();
            String stringRoute = e.getKey().split("\\|")[0];
            Route route = e.getValue();
            String[] locationStrings = stringRoute.split("-");

            int startIndex = -1;
            int endIndex = -1;

            for (int index = 0; index < locationStrings.length; index++) {
                if (Objects.equals(locationStrings[index], start.getName())) {
                    startIndex = index;
                }
                else if (Objects.equals(locationStrings[index], destination.getName())) {
                    endIndex = index;
                }
            }

            //both locations are found in route
            if (startIndex >= 0 && endIndex >= 0) {
                if (startIndex > endIndex) {
                    route = route.getSubRoute(startIndex, endIndex, true);
                }
                if (endIndex > startIndex) {
                    route = route.getSubRoute(startIndex, endIndex, false);
                }
                System.out.println(route);

                LocalTime departureTimeTrip = LocalTime.parse(key.split("\\|")[1]);

                if (departureTimeTrip.isAfter(departure)) {
                    double distance = route.getDistance(start, destination);
                    distance = Math.round(distance * 100.0) / 100.0;
                    int duration = route.getTripTime();
                    List<Location> locationList = route.getLocationList();
                    trips.add(new Trip(departureTimeTrip, start, destination, distance, duration, getTransportType(), locationList));
                }
            }
        }

        return trips;
    }
    
    public abstract Location findLocation(String locationName);
    
    public abstract String getTransportType();
}





