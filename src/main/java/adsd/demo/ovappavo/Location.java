package adsd.demo.ovappavo;

public class Location
{
    private final String name;
    private final double latitude;
    private final double longitude;
    Location(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String name)
    {
       this.name = name;
       latitude = getLatitude();
       longitude = getLongitude();
    }


    public String getName()
    {
        return name;
    }

    public double getLatitude( ){return latitude;}
    public double getLongitude(){return longitude;}
}
