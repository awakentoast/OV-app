package adsd.demo.ovappavo;

public class BusData extends Data {
    
    private static BusData busdata;
    private BusData() {
        locationFile = new LocationFile("src/main/resources/data/busLocationData.txt");
        locationMap = locationFile.getLocations();
        routeFile = new RouteFile("src/main/resources/data/busRouteData.txt");
        routeMap = routeFile.getRoutes(locationMap);
    }
    
    //singleton pattern, so we don't load the files multiple times
    public static BusData getBusDataInstance() {
        if (busdata == null) {
            busdata = new BusData();
        }
        return busdata;
    }
    
    @Override
    public Location findLocation(String locationName) {
        return locationMap.get(locationName);
    }
    
    @Override
    public String getTransportType() {
        return "Bus";
    }
}
