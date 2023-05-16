package adsd.demo.ovappavo;

import javafx.scene.paint.Stop;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    public final Map<String, Location> trainLocationMap = new TreeMap<>();
    public final Map<String, Route> routeMap = new TreeMap<>();
    public final Map<String, Location> busLocationMap = new TreeMap<>();
    Route route;

    public Data() {
        // === Train stations ===
        var location = new Location("Abcoude");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Amersfoort");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Amsterdam");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Arnhem");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Emmen");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Groningen");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Haarlem");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Maastricht");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Nijmegen");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Rotterdam");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Utrecht");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Vlissingen");
        trainLocationMap.put(location.getName(), location);

        location = new Location("Xanten");
        trainLocationMap.put(location.getName(), location);

        // === Bus stations ===

        location = new Location("Soesterberg");
        busLocationMap.put(location.getName(), location);

        location = new Location("Rijnsweerd");
        busLocationMap.put(location.getName(), location);
    }

    public String[] getBusLocationName() {
        String[] names = new String[busLocationMap.size()];
        int index = 0;
        for (var e : busLocationMap.values()) {
            names[index++] = e.getName();
        }
        return names;
    }

    public String[] getTrainLocationsName() {
        String[] names = new String[trainLocationMap.size()];
        int index = 0;
        for (var e : trainLocationMap.values()) {
            names[index++] = e.getName();
        }
        return names;
    }


    public void writeRoutes(String comboA, String comboB) {


        for (var e : routeMap.entrySet()) {
            String key = e.getKey();
            String startLocation = comboA;
            String endLocation = comboB;
            var route = e.getValue();


            String patternString = startLocation + ".*?" + endLocation;
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(key);

            // Controleren of er een overeenkomst is gevonden
            while (matcher.find()) {

                String filteredRoute = matcher.group(0);
                route.write(comboA, comboB, filteredRoute);
            }


            }
        }

    }




