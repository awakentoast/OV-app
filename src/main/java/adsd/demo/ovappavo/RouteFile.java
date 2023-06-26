package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RouteFile extends FileHandler {

    Map<String, Location> locationMap;
    public RouteFile(String filepath) {
        super(filepath);
    }

    public Map<String, Route> getRoutes(Map<String, Location> locationMap) {
        Map<String, Route> routeMap = new TreeMap<>();
        String data = read();
        if (data != null) {
            String[] dataArray = data.split("\n\n");

            for (String dataString :  Arrays.copyOfRange(dataArray, 1, dataArray.length)) {
                
                List<LocalTime> timeValues = new ArrayList<>();
                
                //splits everything up into the individual routes
                String[] currentRoute = dataString.split("\n");
                
                //adds all the times of the locations to a map, so we can increment the time later on
                for (int i = 0; i < currentRoute.length - 1; i++) {
                    String[] locationWithTimes = currentRoute[i].split("-");
                    
                    if (i == 0) {
                        //only departure time
                        timeValues.add(getLocalTime(locationWithTimes[1]));
                    } else if (i + 2 == currentRoute.length) {
                        //only arrival time
                        timeValues.add(getLocalTime(locationWithTimes[1]));
                    } else {
                        timeValues.add(getLocalTime(locationWithTimes[1]));
                        timeValues.add(getLocalTime(locationWithTimes[2]));
                    }
                }
                
                int increment = Integer.parseInt(currentRoute[currentRoute.length - 1]);
                int time = timeValues.get(0).getHour() * 60 + timeValues.get(0).getMinute();
                int tripDuration = (int) timeValues.get(0).until(timeValues.get(timeValues.size() - 1), ChronoUnit.MINUTES);
                
                //do trips until the arrival time would be after 23:59
                for (int i = 0; time + tripDuration < 1440; time += increment) {
                    int timeIndex = 0;
                    Route route = new Route(locationMap.get(currentRoute[0].split("-")[0]), timeValues.get(timeIndex++).plusMinutes((long) increment * i));
                    
                    //loop trough route locations
                    for (int j = 1; j < currentRoute.length - 1; j++) {
                        String[] values = currentRoute[j].split("-");
                        if (j + 2 == currentRoute.length) {
                            route.addEndPoint(locationMap.get(values[0]), timeValues.get(timeIndex).plusMinutes((long) increment * i));
                        } else {
                            route.addStopOver(locationMap.get(values[0]), timeValues.get(timeIndex++).plusMinutes((long) increment * i), timeValues.get(timeIndex++).plusMinutes((long) increment * i));
                        }
                    }
                    
                    //System.out.println(route.getKey());
                    routeMap.put(route.getKey(), route);
                    i++;
                }
            }
        }
        
        return routeMap;
    }
    
    private LocalTime getLocalTime(String time) {
        String[] times = time.split(":");
        return LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
    }
}

