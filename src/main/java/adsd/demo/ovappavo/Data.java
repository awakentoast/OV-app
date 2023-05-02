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


        ////////////////////////////////////////////////////////////

//        /// === Routes A-B-C-D ========
//        for (int hour = 7; hour <= 19; hour += 2)
//        {
//            var departure = LocalTime.of( hour, 0 );
//            var route     = new Route( locationMap.get( "Haarlem" ), departure );
//            route.addStopOver( locationMap.get( "Amsterdam" ), LocalTime.of( hour, 15 ), LocalTime.of( hour, 16 ) );
//            route.addStopOver( locationMap.get( "Utrecht" ), LocalTime.of( hour, 31 ), LocalTime.of( hour, 33 ) );
//            route.addStopOver( locationMap.get( "Arnhem" ), LocalTime.of( hour, 31 ), LocalTime.of( hour, 33 ) );
//            route.addStopOver( locationMap.get( "Nijmegen" ), LocalTime.of( hour, 31 ), LocalTime.of( hour, 33 ) );
//            route.addEndPoint( locationMap.get( "Xanten" ), LocalTime.of( hour, 48 ) );
//            routeMap.put( route.getKey(), route );
//        }
//
//        /// === Routes D-C-B-A
//        for (int hour = 7; hour <= 19; hour += 2)
//        {
//            var departure = LocalTime.of( hour, 0 );
//            var route     = new Route( locationMap.get( "D" ), departure );
//            route.addStopOver( locationMap.get( "C" ), LocalTime.of( hour, 15 ), LocalTime.of( hour, 17 ) );
//            route.addStopOver( locationMap.get( "B" ), LocalTime.of( hour, 32 ), LocalTime.of( hour, 33 ) );
//            route.addEndPoint( locationMap.get( "A" ), LocalTime.of( hour, 48 ) );
//            routeMap.put( route.getKey(), route );
//        }
//        /// === Routes E-B-C-F ========
//        for (int hour = 8; hour <= 17; hour += 1)
//        {
//            var departure = LocalTime.of( hour, 30 );
//            var route     = new Route( locationMap.get( "E" ), departure );
//            route.addStopOver( locationMap.get( "B" ), LocalTime.of( hour, 45 ), LocalTime.of( hour, 46 ) );
//            route.addStopOver( locationMap.get( "C" ), LocalTime.of( hour, 01 ), LocalTime.of( hour, 03 ) );
//            route.addEndPoint( locationMap.get( "F" ), LocalTime.of( hour, 18 ) );
//            routeMap.put( route.getKey(), route );
//        }
//
//        /// === Routes F-C-B-E ========
//        for (int hour = 8; hour <= 17; hour += 1)
//        {
//            var departure = LocalTime.of( hour, 30 );
//            var route     = new Route( locationMap.get( "F" ), departure );
//            route.addStopOver( locationMap.get( "C" ), LocalTime.of( hour, 45 ), LocalTime.of( hour, 47 ) );
//            route.addStopOver( locationMap.get( "B" ), LocalTime.of( hour, 02 ), LocalTime.of( hour, 03 ) );
//            route.addEndPoint( locationMap.get( "E" ), LocalTime.of( hour, 18 ) );
//            routeMap.put( route.getKey(), route );
//        }
//
//        // === Route B-C ===
//        int hour = 12;
//        {
//            var departure = LocalTime.of(hour, 0);
//            var route = new Route(locationMap.get("B"), departure);
//            route.addEndPoint(locationMap.get("C"), LocalTime.of(hour, 15));
//            routeMap.put(route.getKey(), route);
//        }
//
//
//
//        // === Route C-B ===
//
//        hour  = 12;
//        var departure = LocalTime.of(hour, 0);
//        var route = new Route(locationMap.get("C"), departure);
//        route.addEndPoint(locationMap.get("B"), LocalTime.of(hour, 15));
//        routeMap.put(route.getKey(), route);
////         writeRoutes();
//    //    writeRoutesABCD();
//       // train.writeRoutes("Amsterdam","Utrecht",LocalTime.of(13,15));

    public void writeAllRoutes()
    {
        var count = 0;
        for (var route : routeMap.values())
        {
            System.out.format( "%2d: ", ++count);
            route.write();
        }
    }


    public void writeRoutes(String key1, String key2)
    {
        var count = 0;
        for (var e : routeMap.entrySet())
        {
            var key = e.getKey();
            var pos1 = key.indexOf(key1);
            if (pos1 >= 0)
            {
                var pos2 = key.indexOf(key2);
                if (pos2 > pos1)
                {
                    var route = e.getValue();
                    var halte = route.getStopOver(key1);   assert (halte != null);
//                    if (halte.getDeparture().isAfter(t))
//                    {
//                        System.out.format( "%2d: ", ++count);
//                       // route.write();
//                    }
                    route.write();
                }
            }
        }
    }
}
