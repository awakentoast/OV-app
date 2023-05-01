package adsd.demo.ovappavo;

import java.io.*;
import java.util.*;

public final class TripHistory {
    //instance of TripHistory that all classes can share (Singleton), invoke by 'TripHistory exampleName = TripHistory.getTripHistory'
    private static TripHistory tripHistory = null;
    private boolean fileHasBeenRead = false;

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
            try(FileOutputStream ignored = new FileOutputStream(PATH)) {
                System.out.println("file has been created");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error creating file");
            }
        } else {
            System.out.println("file already exists");
        }
        if (!fileHasBeenRead) {
            readFileAndPutInMap();
            fileHasBeenRead = true;
        }
    }


    public void addTrip(String trip) {
        if (tripOccurrences.containsKey(trip)) {
            tripOccurrences.put(trip, tripOccurrences.get(trip) + 1);
        } else {
            tripOccurrences.put(trip, 1);
        }
    }


    private void readFileAndPutInMap() {
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

            String[] trips = data.toString().split("\n\n");

            for (String trip : trips) {
                System.out.println(trip);
                String[] keyAndValue = trip.split("\\|");
                System.out.println(Arrays.toString(keyAndValue));
                tripOccurrences.put(keyAndValue[0], Integer.valueOf(keyAndValue[1].strip()));
            }
        }
    }


    public String getFavoriteTrip() {
        if (tripOccurrences.isEmpty()) {
            return "you have no trip history to showcase a favorite, first travel";
        } else {
            String prefix = "Favorite trip:\n ";
            StringBuilder favorite = new StringBuilder();
            int highestOccurrence = 0;

            //get the trip with the most occurrences
            for (Map.Entry<String, Integer> x : tripOccurrences.entrySet()) {
                int value = x.getValue();
                if (value > highestOccurrence) {
                    favorite = new StringBuilder(x.getKey());
                    highestOccurrence = value;
                }
                else if (value == highestOccurrence) {
                    favorite.append("\n").append(x.getKey());
                    prefix = "Favorite trips:\n";
                }
            }
            return prefix + favorite;
        }
    }


    //when the program stops write the current map to the history file
    public void save() {
        StringBuilder data = new StringBuilder();

        for (Map.Entry<String, Integer> entry : tripOccurrences.entrySet()) {
            data.append(entry.getKey()).append("|").append(entry.getValue()).append("\n\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data.toString());
        } catch (Exception e) {
            System.out.println("Something wrong when writing to file in save");
            e.printStackTrace();
        }
    }
}
