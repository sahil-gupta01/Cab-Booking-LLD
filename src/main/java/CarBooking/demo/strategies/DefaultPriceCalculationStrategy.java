package CarBooking.demo.strategies;

import CarBooking.demo.models.Location;
import CarBooking.demo.models.Rider;

public class DefaultPriceCalculationStrategy implements PriceCalculatingStrategy{
    private Double RATE = 8.0;
    @Override
    public Double calculateTripPrice(Rider rider, Location startingLocation, Location endingLocation) {
        return endingLocation.distance(startingLocation)*RATE;
    }
}
