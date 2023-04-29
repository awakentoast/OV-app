package adsd.demo.ovappavo;

import java.io.*;
import java.util.*;

public final class TripHistory {
    //instance of TripHistory that all classes can share, invoke by 'TripHistory exampleName = TripHistory.getTripHistory'
    private static TripHistory tripHistory = null;
    public static TripHistory getTripHistory() {
        if (tripHistory == null) {
            tripHistory = new TripHistory();
        }
        return tripHistory;
    }


    private static final String PATH = "src\\main\\java\\adsd\\demo\\ovappavo\\history.txt";
    File file = new File(PATH);

    Map<String, Integer> tripOccurrences = new HashMap<>();


    private TripHistory() {
        if (!file.isFile()) {
            System.out.println("file dit not exist already");
            try(FileOutputStream fileOutputStream = new FileOutputStream(PATH);) {
                System.out.println("file has been created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file already exists");
        }
        readFileAndPutInMap();
    }


    public void addTrip(String trip) {
        if (tripOccurrences.containsKey(trip)) {
            tripOccurrences.put(trip, tripOccurrences.get(trip) + 1);
        } else {
            tripOccurrences.put(trip, 1);
        }
    }


    public void readFileAndPutInMap() {
        if (file.length() != 0) {
            StringBuilder data = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    //appending \n to make splitting possible on \n, and making it nicer in the file
                    data.append(line).append("\n");
                }
            } catch (Exception e) {
                System.out.println("Something wrong when reading file in getFavoriteTrip");
                e.printStackTrace();
            }

            String[] trips = data.toString().split("\n");

            for (String trip : trips) {
                String[] keyAndValue = trip.split("\\|");
                tripOccurrences.put(keyAndValue[0], Integer.valueOf(keyAndValue[1]));
            }
        }
    }


    public void getFavoriteTrip() {
        if (tripOccurrences.isEmpty()) {
            System.out.println("you have no trip history to showcase a favorite, first travel");
        } else {
            String favorite = "";
            int highestOccurrence = 0;

            //get the trip with the most occurrences
            for (Map.Entry<String, Integer> x : tripOccurrences.entrySet()) {
                int value = x.getValue();
                if (value > highestOccurrence) {
                    favorite = x.getKey();
                    highestOccurrence = value;
                }
            }

            System.out.println("\nFavorite trip:");
            System.out.println(favorite + "\n");
        }
    }


    //when the program stops write the current map to the history file
    public void save() {
        StringBuilder data = new StringBuilder();

        for (Map.Entry<String, Integer> entry : tripOccurrences.entrySet()) {
            data.append(entry.getKey()).append("|").append(entry.getValue()).append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data.toString());
        } catch (Exception e) {
            System.out.println("Something wrong when writing to file in save");
            e.printStackTrace();
        }
    }
}
