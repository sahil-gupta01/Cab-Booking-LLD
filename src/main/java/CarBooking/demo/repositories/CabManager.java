package CarBooking.demo.repositories;

import CarBooking.demo.exceptions.CabAlreadyExistsException;
import CarBooking.demo.exceptions.CabNotFoundException;
import CarBooking.demo.models.Cab;
import CarBooking.demo.models.CarStatus;
import CarBooking.demo.models.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CabManager {
    Map<String, Cab> cabs;

    public CabManager() {
        cabs = new HashMap<>();
    }
    public void registerCab(Cab cab){
        if(cabs.containsKey(cab.getCarNumber())){
            throw new CabAlreadyExistsException();
        }
        cabs.put(cab.getCarNumber(), cab);
    }
    public Cab getCab(String carNumber){
        if(!cabs.containsKey(carNumber)){
            throw new CabNotFoundException();
        }
        return cabs.get(carNumber);
    }
    public void updateCabAvailabilityStatus(String cabNumber, CarStatus carStatus){
        if(!cabs.containsKey(cabNumber)){
            throw new CabNotFoundException();
        }
        cabs.get(cabNumber).setCarStatus(carStatus);
    }
    public void updateCabLocation(String cabNumber, Location location){
        if(!cabs.containsKey(cabNumber)){
            throw new CabNotFoundException();
        }
        cabs.get(cabNumber).setCurrLocation(location);
    }
    public List<Cab> getCabsAvailable(Location startingPoint, Double distance){
        List<Cab> cabsAvailable = new ArrayList<>();
        for(Cab cab : cabs.values()){
            System.out.println("check  " + cab.getCurrLocation() );
            if(CarStatus.AVAILABLE.equals(cab.getCarStatus()) && cab.getCurrLocation().distance(startingPoint) <= distance){
                cabsAvailable.add(cab);
            }
        }
        return cabsAvailable;
    }
}
