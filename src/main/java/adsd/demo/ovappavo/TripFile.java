package adsd.demo.ovappavo;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripFile extends CustomFile {
    protected List<Trip> allTrips = new ArrayList<>();
    
    protected TripFile(String filepath) {
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
        String[] tripData = tripString.split("-");

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

        return new Trip(departure, begin, end, distance, duration, transportType);
    }
    
    
    public void save() {
        StringBuilder data = new StringBuilder();
        
        for (Trip trip : allTrips) {
            data.append(trip.getStringForSaving()).append("\n");
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data.toString());
        } catch (Exception e) {
            System.out.println("Something wrong when writing to file in save");
            e.printStackTrace();
        }
    }
    
    public List<Trip> getAllTrips() {
        return allTrips;
    }
}
