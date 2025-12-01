package utils;

import dealership.entities.vehicle.Vehicle;
import dealership.entities.vehicle.VehicleFactory;
import dealership.service.VehicleTrackingService;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class JSONFileHandlerTest {
    VehicleTrackingService trackingService;
    String sampleFile;
    JSONFileHandler fileHandler;

    @BeforeEach
    void setUp() {

        sampleFile = "src/main/resources/Project1_input.json";
        trackingService = VehicleTrackingService.getInstance(fileHandler);
    }

    @Test
    void readJSONFile() {
    }
    @Test
    void parsonJSONDataReturnsVehicleList() {
        Vehicle testVehicle = VehicleFactory.createVehicle("suv");
        testVehicle.setVehicleId("48934j");
        testVehicle.setManufacturer("Ford");
        testVehicle.setModel("Explorer");
        testVehicle.setAcquisitionDate(1515354694451L);
        testVehicle.setPrice(20123.0);
        testVehicle.setDealerId("12513");

        JSONArray jsonArray = JSONFileHandler.uploadJSONData(sampleFile).orElse(new JSONArray());
        List<Vehicle> vehicles = JSONFileHandler.parse(jsonArray);
//
//        assertThat(vehicles, hasItem(testVehicle));
//
//        assertThat(testVehicle.getClass(), is(SUV.class));

    }


}