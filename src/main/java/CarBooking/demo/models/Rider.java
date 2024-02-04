package CarBooking.demo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rider {
    private String id;
    private String name;
    private String age;
    private Float rating;

    public Rider(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
