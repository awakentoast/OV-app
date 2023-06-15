package adsd.demo.ovappavo;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LocationFile extends CustomFile {

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
                Location location = new Location(locationStringArray[0], Double.parseDouble(locationStringArray[1]), Double.parseDouble(locationStringArray[2]));
                locationMap.put(locationStringArray[0], location);
            }
        }

        return locationMap;
    }
}
