package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Data {
    public final Map<String, Location> locationMap = new TreeMap<>();
    public final Map<String, Route> routeMap = new TreeMap<>();
    //public Map<String, Location> locations = new TreeMap<>();
    protected String transportType;
    Route route;
    
    protected Data(String transportType) {
        this.transportType = transportType;
    }

    
    public String[] getLocationNames() {
        String[] names = new String[locationMap.size()];
        int index = 0;
        for (var e : locationMap.values()) {
            names[index++] = e.getName();
        }
        return names;
    }
    
    
    public List<Trip> writeRoutes(String comboA, String comboB, LocalTime time) {
        List<Trip> trips = new ArrayList<>();
        
        for (var e : routeMap.entrySet()) {
            String key = e.getKey();
            String startLocation = comboA;
            String endLocation = comboB;
            var route = e.getValue();
            
            String patternString = startLocation + ".*?" + endLocation;
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(key);
            Location newComboA = findLocation(comboA);
            Location newComboB = findLocation(comboB);
            
            
            // Controleren of er een overeenkomst is gevonden
            while (matcher.find()) {
                //String filteredRoute = matcher.group(0);
                LocalTime departure = LocalTime.parse(key.split("\\|")[1]);
                if (departure.isAfter(time)) {
                    double distance = route.getDistance(newComboA, newComboB);
                    distance = Math.round(distance * 100.0) / 100.0;
                    int duration = route.getTripTime(newComboA, newComboB);
                    trips.add(new Trip(departure, newComboA, newComboB, distance, duration, transportType));
                    //route.write(newComboA, newComboB, filteredRoute, time,textArea);
                }
            }
            
            
        }
        return trips;
    }
    
    public abstract Location findLocation(String locationName);
}





