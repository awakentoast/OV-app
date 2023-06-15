package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripFile extends CustomFile {
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
        Data data;
        String[] tripData = tripString.split(",");

        if (Objects.equals(tripData[5], "Train")) {
            data = new TrainData();
        } else {
            data = new BusData();
        }

        LocalTime departure = LocalTime.parse(tripData[0]);
        Location begin = data.findLocation(tripData[1]);
        Location end = data.findLocation(tripData[2]);
        double distance = Double.parseDouble(tripData[3]);
        int duration = Integer.parseInt(tripData[4]);
        String transportType = tripData[5];
        List<Location> locationList = new ArrayList<>();
        
        for (String locationString : tripData[6].split("-")) {
            locationList.add(data.findLocation(locationString));
        }

        return new Trip(departure, begin, end, distance, duration, transportType, locationList);
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
