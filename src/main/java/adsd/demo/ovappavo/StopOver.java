package adsd.demo.ovappavo;

import java.time.LocalTime;

public class StopOver extends Location
{
    private final LocalTime arrival;
    private final LocalTime departure;


    public StopOver(Location location, LocalTime arrival, LocalTime departure)
    {
        super(location.getName(), location.getLatitude(), location.getLongitude());
        this.arrival   = arrival;
        this.departure = departure;
    }

    public LocalTime getArrival()
    {
        return arrival;
    }

    public LocalTime getDeparture()
    {
        return departure;
    }
}