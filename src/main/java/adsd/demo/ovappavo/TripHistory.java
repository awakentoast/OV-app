package adsd.demo.ovappavo;


public class TripHistory extends TripFile {

    public TripHistory() {
        super("src/main/java/adsd/demo/ovappavo/tripHistory.txt");
    }
    
    public void addTrip(Trip trip) {
        allTrips.add(trip);
    }
}
