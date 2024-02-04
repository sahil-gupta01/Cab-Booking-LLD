package CarBooking.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Location {
    private Double xCoordinate;
    private Double yCoordinate;

    public Double distance(Location location){
        return Math.pow( Math.pow(location.getXCoordinate() - this.xCoordinate, 2) +
                Math.pow(location.getYCoordinate() - this.yCoordinate, 2), 0.5);
    }

}
