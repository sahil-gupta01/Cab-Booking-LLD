package CarBooking.demo.repositories;

import CarBooking.demo.exceptions.NoCabAvailableException;
import CarBooking.demo.models.*;
import CarBooking.demo.strategies.CabFindingStrategy;
import CarBooking.demo.strategies.PriceCalculatingStrategy;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Repository
public class TripManager {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
    Map<String, List<Trip>> trips;
    private CabManager cabManager;
    private RiderManager riderManager;
    private PriceCalculatingStrategy priceCalculatingStrategy;
    private CabFindingStrategy cabFindingStrategy;

    public TripManager(CabManager cabManager, RiderManager riderManager, PriceCalculatingStrategy priceCalculatingStrategy, CabFindingStrategy cabFindingStrategy) {
        this.cabManager = cabManager;
        this.riderManager = riderManager;
        this.priceCalculatingStrategy = priceCalculatingStrategy;
        this.cabFindingStrategy = cabFindingStrategy;
    }
    public void createTrip(Rider rider, Location startingLocation, Location endingLocation){
        List<Cab> nearByCabs = cabManager.getCabsAvailable(startingLocation, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        List<Cab> cabsAvailable = nearByCabs.stream()
                                .filter(cab -> cab.getCurrTrip() == null)
                                .toList();
        Cab cabAllocated = cabFindingStrategy.findNearByCab(startingLocation, cabsAvailable, rider, endingLocation);
        if(cabAllocated == null){
            throw new NoCabAvailableException();
        }
        Double calculatedPrice = priceCalculatingStrategy.calculateTripPrice(rider, startingLocation, endingLocation);
        Trip currTrip = new Trip(cabAllocated, cabAllocated.getDriverName(), rider, startingLocation, endingLocation, calculatedPrice);
        if(!trips.containsKey(rider.getId())){
            trips.put(rider.getId(), new ArrayList<>());
        }
        cabAllocated.setCurrTrip(currTrip);
        trips.get(rider.getId()).add(currTrip);
    }
    public List<Trip> getTripHistory(Rider rider){
        return trips.get(rider.getId());
    }
    public void endTrip(Cab cab){
        cab.getCurrTrip().tripEnd();
    }
}
