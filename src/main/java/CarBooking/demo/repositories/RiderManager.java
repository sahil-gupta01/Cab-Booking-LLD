package CarBooking.demo.repositories;

import CarBooking.demo.exceptions.RiderAlreadyRegisterException;
import CarBooking.demo.exceptions.RiderNotFoundException;
import CarBooking.demo.models.Rider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class RiderManager {
    Map<String, Rider> riderList;

    public RiderManager() {
        riderList = new HashMap<>();
    }
    public void registerUser(Rider rider){
        if(riderList.containsKey(rider.getId())){
            throw new RiderAlreadyRegisterException();
        }
        riderList.put(rider.getId(), rider);
    }

    public Rider getUser(String riderId){
        if(!riderList.containsKey(riderId)){
            throw new RiderNotFoundException();
        }
        return riderList.get(riderId);
    }
}
