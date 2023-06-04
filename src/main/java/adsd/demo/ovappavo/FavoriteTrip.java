package adsd.demo.ovappavo;

import java.util.ArrayList;
import java.util.List;

public class FavoriteTrip extends TripFile {
    
    public FavoriteTrip() {
        super("src/main/java/adsd/demo/ovappavo/favoriteTrips.txt");
    }
    
    public void addFavorite(Trip trip) {
        allTrips.add(trip);
    }
}
