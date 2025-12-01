package dealership.entities.vehicle;
/*
* sedan class inherits from vehicle class
* */
public class Sedan extends Vehicle {
    public Sedan(String vehicleId, String manufacturer, String model, Long acquisitionDate, double price, String dealerId) {
        super(vehicleId, manufacturer, model, acquisitionDate, price, null, dealerId);
    }


    public Sedan() {
        setVehicleType(String.valueOf(VehicleType.SEDAN));
    }
}
