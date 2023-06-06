package adsd.demo.ovappavo;

public class FavoriteTripFile extends TripFile {
    
    public FavoriteTripFile() {
        super("src/main/java/adsd/demo/ovappavo/favoriteTrips.txt");
    }
    
    public void addFavorite(Trip trip) {
        allTrips.add(trip);
    }
}
