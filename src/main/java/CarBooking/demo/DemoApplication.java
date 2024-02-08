package CarBooking.demo;

import CarBooking.demo.controllers.CabController;
import CarBooking.demo.controllers.RiderController;
import CarBooking.demo.dtos.ResponseDto;
import CarBooking.demo.repositories.CabManager;
import CarBooking.demo.repositories.RiderManager;
import CarBooking.demo.repositories.TripManager;
import CarBooking.demo.strategies.CabFindingStrategy;
import CarBooking.demo.strategies.DefaultCabFindingStrategy;
import CarBooking.demo.strategies.DefaultPriceCalculationStrategy;
import CarBooking.demo.strategies.PriceCalculatingStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	CabController cabController;
	RiderController riderController;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CabManager cabManager = new CabManager();
		RiderManager riderManager = new RiderManager();
		CabFindingStrategy cabFindingStrategy = new DefaultCabFindingStrategy();
		PriceCalculatingStrategy priceCalculatingStrategy = new DefaultPriceCalculationStrategy();

		TripManager tripManager = new TripManager(cabManager, riderManager, priceCalculatingStrategy, cabFindingStrategy);
		cabController = new CabController(cabManager, tripManager);
		riderController = new RiderController(riderManager, tripManager);

		riderController.registerNewRider("1", "A");
		riderController.registerNewRider("2", "B");

		cabController.registerNewCab("123", "driver1");
		cabController.registerNewCab("234", "driver2");
		cabController.registerNewCab("456", "driver3");

		cabController.updateCabLocation("123", 1.0, 5.0);
		cabController.updateCabLocation("234", 4.0, 1.0);

		ResponseDto responseDto = riderController.bookCab("1", 1.0, 2.0, 3.0, 4.0);
		System.out.println(responseDto.getStatus() + "  " + responseDto.getMessage());
	}
}