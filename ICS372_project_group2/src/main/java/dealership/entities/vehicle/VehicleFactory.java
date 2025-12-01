package dealership.entities.vehicle;

public class VehicleFactory {

    /**
     * A simple factory to create a Vehicle subclass of Sedan, SUV, PickUP, or SportsCar based on given vehicle type
     * @params vehicleType
     * @return instance of Vehicle subclass
     */
     public static Vehicle createVehicle(String vehicleType){
        Vehicle vehicle = null;

        if(vehicleType.equalsIgnoreCase("sedan")){
            vehicle = new Sedan();
        }else if (vehicleType.equalsIgnoreCase("suv")){
            vehicle = new SUV();
        }else if (vehicleType.equalsIgnoreCase("pickup")) {
            vehicle = new PickUp();
        }else if (vehicleType.equalsIgnoreCase("sports car")) {
            vehicle = new SportsCar();
        }

        return vehicle;
    }
}
