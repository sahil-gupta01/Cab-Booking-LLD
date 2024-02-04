package CarBooking.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cab {
    private String carNumber;
    private String driverName;
    private Trip currTrip;
    private Location currLocation;
    private CarStatus carStatus;

    public Cab(String carNumber, String driverName) {
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.carStatus = CarStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "CabDetails{" + "vehicle number: " + carNumber + ", driver's name: " + driverName +
                ", current Location: " + currLocation;
    }
}
