package adsd.demo.ovappavo;

import java.util.Objects;

public class Location
{
    Data data;
    
    private final String name;
    private final double latitude;
    private final double longitude;
    private double locationX = 0;
    private double locationY = 0;


    private final boolean hasRamp;
    private final boolean hasToilet;
    private final boolean hasServiceEmployee;
    private final boolean hasAED;
    
    //if there aren't services provides, by example for bus locations
    public Location(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationX = calcPointOnMap(longitude, "longitude");
        this.locationY = calcPointOnMap(latitude, "latitude");

        this.hasRamp = false;
        this.hasToilet = false;
        this.hasServiceEmployee = false;
        this.hasAED = false;
    }

    public Location(String name, double latitude, double longitude, boolean hasRamp, boolean hasToilet, boolean hasServiceEmployee, boolean hasEAD)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationX = calcPointOnMap(longitude, "longitude");
        this.locationY = calcPointOnMap(latitude, "latitude");

        this.hasRamp = hasRamp;
        this.hasToilet = hasToilet;
        this.hasServiceEmployee = hasServiceEmployee;
        this.hasAED = hasEAD;
    }

    private double calcPointOnMap(double earthLineValue, String typeEarthLine) {
        //the top right point expressen in longitude and latitude
        double x0 = 3.5697;
        double y0 = 50.4871;
        
        //how many longitude or latitude is between each pixel
        double xStepDelta = 0.004967;
        double yStepDelta = 0.003545;

        if (Objects.equals(typeEarthLine, "longitude")) {
            // else vlissingen gets drawn too far left and is out of view
            return (earthLineValue - x0) / xStepDelta + 20;
        } else {
            return (earthLineValue - y0) / yStepDelta;
        }
    }
    

    public boolean[] getServices() {
        return new boolean[]{hasRamp, hasToilet, hasServiceEmployee, hasAED};
    }

    public String getName()
    {
        return name;
    }
    public double getLatitude( ){return latitude;}
    public double getLongitude(){return longitude;}

    public double getX() {
        return locationX;
    }

    public double getY() {
        return locationY;
    }

}
