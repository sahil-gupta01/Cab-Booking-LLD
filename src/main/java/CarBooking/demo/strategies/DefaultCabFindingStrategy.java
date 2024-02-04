package CarBooking.demo.strategies;

import CarBooking.demo.models.Cab;
import CarBooking.demo.models.Location;
import CarBooking.demo.models.Rider;

import java.util.List;

public class DefaultCabFindingStrategy implements CabFindingStrategy{

    @Override
    public Cab findNearByCab(Location startingLocation, List<Cab> cabsAvailable, Rider rider, Location endingLocation) {
        if(cabsAvailable.size() == 0)return null;
        return cabsAvailable.get(0);
    }
}
