package adsd.demo.ovappavo;

import adsd.demo.ovappavo.Location;

import java.time.LocalTime;

public class Trip {
    private final LocalTime departure;
    private final Location locationA;
    private final Location  locationB;

    public Trip (LocalTime departure, Location locationA, Location locationB)
    {
        this.departure = departure;
        this.locationA = locationA;
        this.locationB = locationB;
    }
    public LocalTime getDeparture()
    {
        return departure;
    }
    public void write()
    {

    }
}
