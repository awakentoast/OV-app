package adsd.demo.ovappavo;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Stop;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    public final Map<String, Location> trainLocationMap = new TreeMap<>();
    public final Map<String, Route> routeMap = new TreeMap<>();
    public final Map<String, Location> busLocationMap = new TreeMap<>();
    public Map<String, Location> locations = new TreeMap<>();
    Route route;

    public Data() {
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

        // === Bus stations ===

        location = new Location("Soesterberg",52.120899,5.283606);
        busLocationMap.put(location.getName(), location);

        location = new Location("Rijnsweerd",52.0892,5.150985);
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


    public void writeRoutes(String comboA, String comboB, LocalTime time, TextArea textArea)
    {

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
                String filteredRoute = matcher.group(0);
                route.write(newComboA, newComboB, filteredRoute, time,textArea);
            }


        }

    }

    public Location findLocation(String locationName)
    {
        Location foundLocation= null;
        for (Map.Entry<String, Location> entry : locations.entrySet()) {
            Location location = entry.getValue();

            if (location.getName().equals(locationName)) {
               foundLocation = location;
               return foundLocation;
            }
        }return null;

    }



}





