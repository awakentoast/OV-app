package adsd.demo.ovappavo;

import java.time.LocalTime;

public class StopOver extends Location
{
    private final LocalTime arrival;
    private final LocalTime departure;

    public StopOver( String name, LocalTime arrival, LocalTime departure )
    {
        super( name );
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