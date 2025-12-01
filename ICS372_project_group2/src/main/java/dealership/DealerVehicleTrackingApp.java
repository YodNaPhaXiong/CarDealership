package dealership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DealerVehicleTrackingApp {
    public static void main(String[] args) {
        SpringApplication.run(DealerVehicleTrackingApp.class, args);
    }

    @GetMapping("/")
    public String home() {
        return String.format("<h1>WELCOME TO ICS372 CAR DEALERSHIP - GROUP 2</h1>");
    }
}
