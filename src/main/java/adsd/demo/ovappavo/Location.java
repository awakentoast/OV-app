package adsd.demo.ovappavo;

public class Location
{
    private final String name;
    private final double latitude;
    private final double longitude;
    private boolean hasRamp;
    private boolean hasToilet;
    private boolean hasServiceEmployee;
    private boolean hasAED;

    private final String[] services = {"toilet.string", "EAD.string", "ramp.string", "serviceEmployee.string"};

    public Location(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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
        this.hasRamp = hasRamp;
        this.hasToilet = hasToilet;
        this.hasServiceEmployee = hasServiceEmployee;
        this.hasAED = hasEAD;
    }

    public Location (String name)
    {
       this.name = name;
       latitude = getLatitude();
       longitude = getLongitude();
    }

    public boolean[] getServices() {
        return new boolean[]{hasRamp, hasToilet, hasServiceEmployee, hasAED};
    }

    public String[] getServiceStrings() {
        return services;
    }


    public String getName()
    {
        return name;
    }
    public double getLatitude( ){return latitude;}
    public double getLongitude(){return longitude;}
}
