package CarBooking.demo.models;

public class Trip {
    private Cab cab;
    private String driver;
    private Rider rider;
    private Location startingLocation;
    private Location endingLocation;
    private Double tripPrice;
    private TripStatus tripStatus;

    public Trip(Cab cab, String driver, Rider rider, Location startingLocation, Location endingLocation, Double tripPrice) {
        this.cab = cab;
        this.driver = driver;
        this.rider = rider;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.tripPrice = tripPrice;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }
    public void tripEnd(){
        this.tripStatus = TripStatus.FINISHED;
    }
}
