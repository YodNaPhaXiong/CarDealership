package dealership.entities.vehicle;
/*
 suv class inherits from vehicle class
* */
public class SUV extends Vehicle {
    public SUV(String vehicleId, String manufacturer, String model, Long acquisitionDate, double price, String dealerId) {
        super(vehicleId, manufacturer, model, acquisitionDate, price,  "SUV", dealerId);
    }

    public SUV() {
        setVehicleType(String.valueOf(VehicleType.SUV));
    }

  }
