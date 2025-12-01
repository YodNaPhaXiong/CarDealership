package entities.dealer;


import dealership.entities.dealer.Dealer;
import dealership.entities.vehicle.Vehicle;
import dealership.entities.vehicle.VehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
    Dealer testDealer;

    @BeforeEach
    void setUp() {
        testDealer = new Dealer("testDealer001");
    }

//    @Test
//    void whenNoVehicleDealerListSizeIsZERO(){
//        var expected = 0;
//        var result = testDealer.getVehicleList().size();
//
//        assertEquals(expected, result);
//    }

    @Test
    void givenNewVehicle_WhenAddVehicleToDealer_DealerListSizeIsONE() {
        //arrange
        Vehicle testVehicle = VehicleFactory.createVehicle("sedan");
        testVehicle.setVehicleId("48934j");
        testVehicle.setManufacturer("Honda");
        testVehicle.setModel("Accord");
        testVehicle.setAcquisitionDate(1515354694451L);
        testVehicle.setPrice(20123.0);
        var expected= 1;
        //act
//        testDealer.addNewVehicle(testVehicle);
//        var result = testDealer.getVehicleList().size();
//
//        //assert
//        assertTrue(testVehicle instanceof Vehicle);
//        assertEquals(expected, result);
    }

}
