package CarBooking.demo.controllers;

import CarBooking.demo.dtos.ResponseDto;
import CarBooking.demo.dtos.ResponseStatus;
import CarBooking.demo.models.Cab;
import CarBooking.demo.models.CarStatus;
import CarBooking.demo.models.Location;
import CarBooking.demo.repositories.CabManager;
import CarBooking.demo.repositories.RiderManager;
import CarBooking.demo.repositories.TripManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
@RestController
public class CabController {

    private CabManager cabManager;
    private TripManager tripManager;

    @Autowired
    public CabController(CabManager cabManager, TripManager tripManager) {
        this.cabManager = cabManager;
        this.tripManager = tripManager;
    }

    public ResponseDto registerNewCab(String cabNumber, String driverName){
        cabManager.registerCab(new Cab(cabNumber, driverName));
        return new ResponseDto("Successfully registered", ResponseStatus.SUCCESS);
    }
    public ResponseDto updateCabLocation(String carNumber, Double newX, Double newY){
        cabManager.updateCabLocation(carNumber, new Location(newX, newY));
        return new ResponseDto("Successfully location has been updated", ResponseStatus.SUCCESS);
    }
    public ResponseDto updateCabAvailabilityStatus(String carNumber, CarStatus carStatus){
        cabManager.updateCabAvailabilityStatus(carNumber, carStatus);
        return new ResponseDto("Successfully status updated", ResponseStatus.SUCCESS);
    }
    public ResponseDto endTrip(String carNumber){
        tripManager.endTrip(cabManager.getCab(carNumber));
        return new ResponseDto("Successfully trip ended", ResponseStatus.SUCCESS);
    }
}
