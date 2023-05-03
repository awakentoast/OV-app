package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Data

{
    public final Map<String, Location> locationMap = new TreeMap<>();
    public final Map<String, Route>    routeMap    = new TreeMap<>();


    public Data() {
        /// === Locations A ... F ========
        var location = new Location("Abcoude");
        locationMap.put(location.getName(), location);

        location = new Location("Amersfoort");
        locationMap.put(location.getName(), location);

        location = new Location("Amsterdam");
        locationMap.put(location.getName(), location);

        location = new Location("Arnhem");
        locationMap.put(location.getName(), location);

        location = new Location("Emmen");
        locationMap.put(location.getName(), location);

        location = new Location("Groningen");
        locationMap.put(location.getName(), location);

        location = new Location("Haarlem");
        locationMap.put(location.getName(), location);

        location = new Location("Maastricht");
        locationMap.put(location.getName(), location);

        location = new Location("Nijmegen");
        locationMap.put(location.getName(), location);

        location = new Location("Rotterdam");
        locationMap.put(location.getName(), location);

        location = new Location("Utrecht");
        locationMap.put(location.getName(), location);

        location = new Location("Vlissingen");
        locationMap.put(location.getName(), location);

        location = new Location("Xanten");
        locationMap.put(location.getName(), location);

        }
    public String[] getLocationsName() {
        String[] names= new String[locationMap.size()];
        int index = 0;
        for (var e: locationMap.values())
        {
            names[index++] = e.getName();
        }
        return names;
    }

    public void writeRoutes(String comboA, String comboB)
    {

        var count = 0;
        for (var e : routeMap.entrySet())
        {
            var key = e.getKey();
            var pos1 = key.indexOf(comboA);

            if (pos1 >= 0)
            {

                var pos2 = key.indexOf(comboB);
                var route = e.getValue();
                if (pos2 > pos1)
                {

                    var halte = route.getStopOver(comboA); assert (halte != null);
//                    if (halte.getDeparture().isAfter(t))
//                    {
//                        System.out.format( "%2d: ", ++count);
//                       // route.write();
//                    }
                   route.write(comboA,comboB);
                }
            }
        }
    }

}
