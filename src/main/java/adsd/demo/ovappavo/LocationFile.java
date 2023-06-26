package adsd.demo.ovappavo;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LocationFile extends FileHandler {

    public LocationFile(String filepath) {
        super(filepath);
    }

    public Map<String, Location> getLocations() {
        Map<String, Location> locationMap = new TreeMap<>();
        String dataString = read();

        if (dataString != null) {
            String[] data = dataString.split("\n");

            for (String locationString : Arrays.copyOfRange(data, 1, data.length)) {
                String[] locationStringArray = locationString.split("-");
                boolean[] services = new boolean[4]; //ramp, toilet, service employee, aed
                
                for (int i = 0; i < 4; i++) {
                    try {
                        services[i] = servicePresent(locationStringArray[i + 3]);
                    } catch (Exception e) {
                        //System.out.println("there is not sufficient service info provided for" + locationStringArray[0] + "service " + i);
                        services[i] = false;
                    }
                }
                Location location = new Location(locationStringArray[0], Double.parseDouble(locationStringArray[1]), Double.parseDouble(locationStringArray[2]),
                        services[0], services[1], services[2], services[3]);
                locationMap.put(locationStringArray[0], location);
            }
        }

        return locationMap;
    }
    
    private boolean servicePresent(String availibality) {
        availibality = availibality.toLowerCase();
        return availibality.equals("true") || availibality.equals("ja") || availibality.equals("y") || availibality.equals("yes");
    }
}
