package adsd.demo.ovappavo;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class RouteFile extends CustomFile {

    Map<String, Location> locationMap;
    public RouteFile(String filepath) {
        super(filepath);
        readFromFile();
        locationMap =
    }

    private void readFromFile() {
        Map<String, Location> routeMap = new TreeMap<>();
        String data = read();

        if (data != null) {
            String[] dataArray = data.split("\n\n");

            for (String dataString :  Arrays.copyOfRange(dataArray, 1, dataArray.length)) {
                String[] routeStringArray = dataString.split("\n");
                String[] startStringArray = routeStringArray[0].split("-");
                Route route = new Route(startStringArray[0], routeStringArray[1]);
                for (String routeString : routeStringArray) {
                    String[] stops = routeString.split("-");
                    Route route = new Route(routeStringArray[0], routeStringArray)

                    for (int i = 0; i < routeStringArray.length; i++) {
                        if (i + 1 == routeStringArray.length) {

                        }
                    }
                }
            }
        }

        return locationMap;
    }
}

