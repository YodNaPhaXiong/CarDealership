package dealership.entities.vehicle;
/*
* pick up class inherits from vehicle class
* */
public class PickUp extends Vehicle {
    public PickUp(String vehicleId, String manufacturer, String model, Long acquisitionDate, double price, String dealerId) {

        super(vehicleId, manufacturer, model, acquisitionDate, price, String.valueOf(VehicleType.PICK_UP), dealerId);
    }

    public PickUp() {
        setVehicleType(String.valueOf(VehicleType.PICK_UP));;
    }
}
