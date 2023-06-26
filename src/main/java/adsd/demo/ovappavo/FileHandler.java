package adsd.demo.ovappavo;

import java.io.*;

//this class is for the creation of the file, getting BufferedReader and BufferedWriter
public class FileHandler {
    
    protected File file;
    
    protected FileHandler(String filepath) {
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
        //check for empty file
        if (file.length() != 0) {
            StringBuilder data = new StringBuilder();
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    //appending \n to make it nicer in the file, and be able top slit on the \n character
                    data.append(line).append("\n");
                }
            } catch (Exception e) {
                System.out.println("Something wrong when reading file: " +  file.getPath());
                e.printStackTrace();
            }
            return data.toString();
        } else {
            return null;
        }
    }

    protected void write(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data);
        } catch (Exception e) {
            System.out.println("Something wrong when writing to file in save");
            e.printStackTrace();
        }
    }
}
