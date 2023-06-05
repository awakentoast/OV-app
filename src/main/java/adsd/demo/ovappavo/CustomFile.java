package adsd.demo.ovappavo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomFile {
    
    protected File file;
    
    protected CustomFile(String filepath) {
        file = new File(filepath);
        try {
            if (file.createNewFile()) {
                System.out.println(filepath + " has been created");
            } else {
                System.out.println(filepath + " already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error in TripHistoryFile constructor");
            System.out.println(filepath);
        }
    }
    
    protected String read() {
        //check for null when calling read()
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
            return data.toString();
        } else {
            return null;
        }
    }
}
