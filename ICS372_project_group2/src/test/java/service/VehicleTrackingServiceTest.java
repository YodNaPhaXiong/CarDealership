package service;

import dealership.entities.dealer.Dealer;
import dealership.entities.vehicle.*;
import dealership.service.VehicleTrackingService;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.JSONFileHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;

class VehicleTrackingServiceTest {
    Logger LOGGER = Logger.getLogger(VehicleTrackingServiceTest.class.getName());
    VehicleTrackingService trackingService;
    VehicleFactory vehicleFactory;
    private final String emptyJSONTestFile = "src/main/resources/empty_json_file.json";
    private final String nullJSONTestFile = "src/main/resources/null_file.json";
    String validTestFile = "src/main/resources/Project1_input.json";

    JSONFileHandler fileHandler;


    @BeforeEach
    void setUp() {
        trackingService = VehicleTrackingService.getInstance(fileHandler);
    }

    @Test
    void testVehicleTrackingServiceInstantiation() {
        assertEquals(trackingService.getDealerList().size(), 0);
        LOGGER.info(trackingService.getDealerList().toString());
    }

    @Test
    void givenEmptyJSONObjectThenReturnEmptyArray() {

        trackingService.readJSONFileData(emptyJSONTestFile);
        LOGGER.info(trackingService.getDealerList().toString());
        assertTrue(trackingService.getDealerList().isEmpty());
    }

    @Test
    void givenNULLJSONFileThenReturnEmptyArray() {

        trackingService.readJSONFileData(nullJSONTestFile);
        LOGGER.info(trackingService.getDealerList().toString());
        assertTrue(trackingService.getDealerList().isEmpty());
    }

    // 1   The software shall read a file that is in JSON format containing various vehicle information.
    @Test
    void trackingServiceReadsJSONDataFromFile() {
        Optional<JSONArray> data = trackingService.readJSONFileData(validTestFile);
        assertThat(true, is(data.isPresent()));
    }

    //    2    The software shall support 4 different types of vehicles in the input file: suv, sedan, pickup, and sports car.
    @ParameterizedTest
    @ValueSource(strings = {"suv", "SEDAN", "PickUP", "sports CAR"})
    void givenVehicleTypeStringReturnMatchingVehicleObject(String vehicleType) {
        Vehicle testVehicle = VehicleFactory.createVehicle(vehicleType);
        switch (vehicleType) {
            case "suv" -> assertTrue(testVehicle instanceof SUV);
            case "SEDAN" -> assertTrue(testVehicle instanceof Sedan);
            case "PickUP" -> assertTrue(testVehicle instanceof PickUp);
            case "sports CAR" -> assertTrue(testVehicle instanceof SportsCar);

        }
    }

    //  3  The software shall read and store the vehicle ID, manufacturer, model, acquisition date, and price
//    for each entry and associate it with the specified dealer ID.
    @Nested
    @DisplayName("Software reads and stores data")
    class UploadAndParseData {
        Optional<JSONArray> vehicleJsonArray;
        List<Vehicle> vehicleList;
        @BeforeEach
        void setUp() {
            vehicleJsonArray = trackingService.readJSONFileData(validTestFile);

        }

        @Test
        void eachVehiclePropertyIsReadIntoVehicleObj() {
            int EXPECTED_SIZE = 4;

            //arrange
            Vehicle testVehicle = VehicleFactory.createVehicle("suv");
            testVehicle.setVehicleId("48934j");
            testVehicle.setManufacturer("Ford");
            testVehicle.setModel("Explorer");
            testVehicle.setAcquisitionDate(1515354694451L);
            testVehicle.setPrice(20123.0);
            testVehicle.setDealerId("12513");

            //act
            trackingService.loadJSONData(vehicleJsonArray);
            vehicleList = trackingService.getVehicleList().stream().toList();


            //assert
            assertEquals(vehicleList.size(), EXPECTED_SIZE);
            MatcherAssert.assertThat(vehicleList, hasItem(testVehicle));

        }

        @Test
        void associatedVehicleMetaDataIsReadAndStored() {
            Dealer testDealer = new Dealer("12513");


//            act
//            trackingService.add(testDealer);
//            trackingService.loadJSONData(vehicleJsonArray);
//            trackingService.exportVehicles(testDealer);

//            assert
            assertTrue(Files.exists(Path.of("src/main/resources/vehicle-storage/dealerVehicles_" + testDealer.getDealerId() + ".json")));
        }

    }

}

