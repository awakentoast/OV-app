package adsd.demo.ovappavo;

import java.time.LocalTime;

public class Train extends Data {

    // set all train routes
    public void setRoute() {
        /// === Routes Haarlem-Xanten ========
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
        //=== Routes Xanten-Nijmegen ===
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
        // === Routes Groningen-Vlissingen ===
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
        // === Routes Vlissingen-Groningen ===
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
        // === Routes Emmen-Maastricht ===
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

        // === Routes Maastricht-Groningen ===
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
}

// https://www.ns.nl/binaries/_ht_1574840979865/content/assets/ns-nl/dienstregeling/december-2019/spoorkaart-trajecten.pdf