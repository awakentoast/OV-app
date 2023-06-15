package adsd.demo.ovappavo;

public class TrainData extends Data {
    
    private static TrainData trainData;
    private TrainData() {
        locationFile = new LocationFile("src/main/resources/data/trainLocationData.txt");
        locationMap = locationFile.getLocations();
        routeFile = new RouteFile("src/main/resources/data/trainRouteData.txt");
        routeMap = routeFile.getRoutes(locationMap);
    }
    
    //singleton pattern, so we don't load the files multiple times
    public static TrainData getTrainDataInstance() {
        if (trainData == null) {
            trainData = new TrainData();
        }
        return trainData;
    }
    
    @Override
    public Location findLocation(String locationName)
    {
        return locationMap.get(locationName);
    }
    
    @Override
    public String getTransportType() {
        return "Train";
    }
}

// https://www.ns.nl/binaries/_ht_1574840979865/content/assets/ns-nl/dienstregeling/december-2019/spoorkaart-trajecten.pdf