package adsd.demo.ovappavo;

import java.util.ArrayList;
import java.util.List;

public class TripFile extends FileHandler {
    private final List<Trip> allTrips = new ArrayList<>();
    
    public TripFile(String filepath) {
        super(filepath);
        readTripsFromFile();
    }
    
    private void readTripsFromFile() {
        String data = read();
        if (data != null) {
            String[] trips = data.split("\n");
            
            for (String trip : trips) {
                allTrips.add(stringToTripFromFile(trip));
            }
        }
    }
    
    public Trip stringToTripFromFile(String tripString)
    {
        return Trip.savedStringToTrip(tripString);
    }
    
    public void save() {
        StringBuilder data = new StringBuilder();
        
        for (Trip trip : allTrips) {
            data.append(trip.getStringForSaving()).append("\n");
            System.out.println(data);
        }
        
        write(data.toString());
    }

    public void addTrip(Trip trip) {
        allTrips.add(trip);
    }
    public List<Trip> getAllTrips() {
        return allTrips;
    }
}
