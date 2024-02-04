package CarBooking.demo.strategies;

import CarBooking.demo.models.Cab;
import CarBooking.demo.models.Location;
import CarBooking.demo.models.Rider;

import java.util.List;

public interface CabFindingStrategy {
    Cab findNearByCab(Location startingLocation, List<Cab> cabs, Rider rider, Location endingPoint);

}
