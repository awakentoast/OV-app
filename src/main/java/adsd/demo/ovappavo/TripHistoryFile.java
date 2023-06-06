package adsd.demo.ovappavo;


public class TripHistoryFile extends TripFile {

    public TripHistoryFile() {
        super("src/main/java/adsd/demo/ovappavo/tripHistory.txt");
    }
    
    public void addTrip(Trip trip) {
        allTrips.add(trip);
    }
}
