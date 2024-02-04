package CarBooking.demo.strategies;

import CarBooking.demo.models.Location;
import CarBooking.demo.models.Rider;

public interface PriceCalculatingStrategy {
    Double calculateTripPrice(Rider rider, Location startingLocation, Location endingLocation);
}
