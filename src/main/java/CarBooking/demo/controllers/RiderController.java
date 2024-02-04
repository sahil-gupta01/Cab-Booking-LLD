package CarBooking.demo.controllers;

import CarBooking.demo.dtos.ResponseDto;
import CarBooking.demo.dtos.ResponseStatus;
import CarBooking.demo.models.Cab;
import CarBooking.demo.models.Location;
import CarBooking.demo.models.Rider;
import CarBooking.demo.models.Trip;
import CarBooking.demo.repositories.RiderManager;
import CarBooking.demo.repositories.TripManager;

import java.util.List;

public class RiderController {
    private RiderManager riderManager;
    private TripManager tripManager;

    public RiderController(RiderManager riderManager, TripManager tripManager) {
        this.riderManager = riderManager;
        this.tripManager = tripManager;
    }
    public ResponseDto registerNewRider(String id, String riderName){
        riderManager.registerUser(new Rider(id, riderName));
        return new ResponseDto("Rider got successfully registered", ResponseStatus.SUCCESS);
    }
    public ResponseDto getTripHistory(String id){
        List<Trip> tripHistory = tripManager.getTripHistory(riderManager.getUser(id));
        //use trip history as per requirement
        return new ResponseDto("History Provided", ResponseStatus.SUCCESS);
    }
    public ResponseDto bookCab(String id, Double xStart, Double yStart, Double xEnd, Double yEnd){
        tripManager.createTrip( riderManager.getUser(id),
                                new Location(xStart,yStart),
                                new Location(xEnd, yEnd) );
        return new ResponseDto("Cab Booked", ResponseStatus.SUCCESS);
    }
}
