package dealership.entities.vehicle;
/*
* sportsCar class inherits from vehicle class
* */
public class SportsCar extends Vehicle {
    public SportsCar(String vehicleId, String manufacturer, String model, Long acquisitionDate, double price, String dealerId) {
        super(vehicleId, manufacturer, model, acquisitionDate, price, null, dealerId);
    }

    public SportsCar() {
        setVehicleType(String.valueOf(VehicleType.SPORTS_CAR));
    }
}
