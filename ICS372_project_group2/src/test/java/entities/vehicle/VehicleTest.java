package entities.vehicle;

import dealership.entities.vehicle.Vehicle;
import dealership.entities.vehicle.VehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Logger;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

class VehicleTest {
    static Logger LOGGER = Logger.getLogger(VehicleTest.class.getName());
    Vehicle testVehicle;

    @BeforeEach
    void setUp() {
        testVehicle = VehicleFactory.createVehicle("sedan");
        var vehicleType = "seDAN";
        testVehicle = VehicleFactory.createVehicle(vehicleType);
        testVehicle.setVehicleId("0001");
        testVehicle.setManufacturer("Honda");
        testVehicle.setModel("Accord");
        testVehicle.setAcquisitionDate(1515354694451L);
        testVehicle.setPrice(20123.0);
        LOGGER.info(testVehicle.toString());
    }




    @Test
    void getAcquisitionDateReturnsExpectedDate() {
        Long input = 1663379660000L;
        System.out.println(testVehicle.getParsedAcquisitionDate());
        testVehicle.setAcquisitionDate(input);

        var expected = LocalDate.of(2022, Month.SEPTEMBER, 16).toString();
        var result = testVehicle.getParsedAcquisitionDate();

        assertThat((result), equalTo(expected));
    }

}
