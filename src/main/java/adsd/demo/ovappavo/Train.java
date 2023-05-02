package adsd.demo.ovappavo;

import java.time.LocalTime;

public class Train extends Data {
  ///  { "Abcoude", "Amersfoort","Amsterdam","Arnhem","Emmen","Groningen","Haarlem","Maastricht" ,"Nijmegen", "Rotterdam","Utrecht","Vlissingen","Xanten" }
    public void setRoute() {


        /// === Routes A-B-C-D ========
        for (int hour = 7; hour <= 19; hour += 2) {
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Haarlem"), departure);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour, 15), LocalTime.of(hour, 16));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hour, 48));
            routeMap.put(route.getKey(), route);
        }

        for (int hour = 7; hour <= 19; hour += 2) {
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Haarlem"), departure);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour, 15), LocalTime.of(hour, 16));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hour, 48));
            routeMap.put(route.getKey(), route);
        }

        for (int hour = 7; hour <= 19; hour += 2) {
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Haarlem"), departure);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour, 15), LocalTime.of(hour, 16));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hour, 48));
            routeMap.put(route.getKey(), route);
        }

        for (int hour = 7; hour <= 19; hour += 2) {
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Haarlem"), departure);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour, 15), LocalTime.of(hour, 16));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hour, 48));
            routeMap.put(route.getKey(), route);
        }

        for (int hour = 7; hour <= 19; hour += 2) {
            var departure = LocalTime.of(hour, 0);
            var route = new Route(locationMap.get("Haarlem"), departure);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour, 15), LocalTime.of(hour, 16));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Arnhem"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour, 31), LocalTime.of(hour, 33));
            route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hour, 48));
            routeMap.put(route.getKey(), route);
            // writeRoutes("a","b",LocalTime.of(1,15));
        }
    }


    public void writeRoutesHaarlemXanten()
    {
        var count = 0;
        for (var e : routeMap.entrySet())
        {
            var key = e.getKey();
            if (key.contains( "Haarlem-Amsterdam-Utrecht-Arnhem-Nijmegen-Xanten"))
            {
                var route = e.getValue();
                System.out.format("%2d: ", ++count);
                // route.write();
            }
        }
    }


}

// https://www.ns.nl/binaries/_ht_1574840979865/content/assets/ns-nl/dienstregeling/december-2019/spoorkaart-trajecten.pdf