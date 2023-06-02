package adsd.demo.ovappavo;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripFile {
    Data dataHelper;
    protected List<Trip> allTrips = new ArrayList<>();
    protected File file;
    
    public TripFile(String filepath) {
        file = new File(filepath);
        try {
            if (file.createNewFile()) {
                System.out.println("file has been created");
            } else {
                System.out.println("file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error in TripHistory constructor");
            System.out.println(filepath);
        }
        readTripsFromFile();
    }
    
    private void readTripsFromFile() {
        if (file.length() != 0) {
            StringBuilder data = new StringBuilder();
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    //appending \n to make it nicer in the file, and be able top slit on the \n character
                    data.append(line).append("\n");
                }
            } catch (Exception e) {
                System.out.println("Something wrong when reading file in getFavoriteTrip");
                e.printStackTrace();
            }
            
            String[] trips = data.toString().split("\n");
            
            for (String trip : trips) {
                allTrips.add(stringToTripFromFile(trip));
            }
        }
    }
    
    public Trip stringToTripFromFile(String tripString)
    {
        if (Objects.equals(tripString.split("-")[5], "Train")) {
            dataHelper = new TrainData();
        } else {
            dataHelper = new BusData();
        }
        String[] tripData = tripString.split("-");
        
        LocalTime departure = LocalTime.parse(tripData[0]);
        Location begin = dataHelper.findLocation(tripData[1]);
        Location end = dataHelper.findLocation(tripData[2]);
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
