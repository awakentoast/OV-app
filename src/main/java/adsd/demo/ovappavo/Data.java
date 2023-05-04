package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Data

{
    public final Map<String, Location> trainLocationMap = new TreeMap<>();
    public final Map<String, Route>    routeMap    = new TreeMap<>();
    public final Map<String, Location> busLocationMap = new TreeMap<>();

    public Data() {
        // === Trein stations ===
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

        // === Bushaltes ===

        location = new Location("Soesterberg");
        busLocationMap.put(location.getName(), location);

        location = new Location("Rijnsweerd");
        busLocationMap.put(location.getName(),location);
        }

       public String[] getBusLocationName()
       {
           String[] names= new String[busLocationMap.size()];
           int index = 0;
           for (var e: busLocationMap.values())
           {
               names[index++] = e.getName();
           }
           return names;
       }

    public String[] getTrainLocationsName()
    {
        String[] names= new String[trainLocationMap.size()];
        int index = 0;
        for (var e: trainLocationMap.values())
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
