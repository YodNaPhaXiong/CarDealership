package dealership.entities.vehicle;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

@JsonFilter("filter properties by name")
public abstract class Vehicle {
    Logger LOGGER = Logger.getLogger(Vehicle.class.getName());
    public enum VehicleType {SEDAN, SUV, SPORTS_CAR, PICK_UP}

    private String VehicleId;
    private String manufacturer;
    private String model;
    private Long acquisitionDate;
    private double price;
    protected String vehicleType;
    private String dealerId;
    private boolean isOnLoan;

    public Vehicle(){

    }

    public Vehicle(String vehicleId, String manufacturer, String model, Long acquisitionDate, double price, String vehicleType, String dealerId, boolean isOnLoan) {
        VehicleId = vehicleId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.dealerId = dealerId;
        setAcquisitionDate(acquisitionDate);
        setPrice(price);
        this.vehicleType = vehicleType;
        this.isOnLoan = isOnLoan;
    }
    
    public boolean addNewCar(String vehicleId, String model, double price, String vehicleType, String dealerId) {
        this.VehicleId = vehicleId;
        this.model = model;
        this.price = price;
        this.dealerId = dealerId;
        this.vehicleType = vehicleType;
        return true;
    }


    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getParsedAcquisitionDate() {
        return parseEpochMilliDate(acquisitionDate).toLocalDate().toString();
    }

    public Long getAcquisitionDate(){
        return acquisitionDate;
    }

    public void setAcquisitionDate(Long acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    private ZonedDateTime parseEpochMilliDate(Long unparsedDate) {
        Instant parsedDateInstant = Instant.ofEpochMilli(unparsedDate);
        return parsedDateInstant.atZone(ZoneId.of("America/Chicago"));
    }

    public double getPrice() {
        return price;
    }

    public <T extends Number> void setPrice(T price) {
        this.price = price.doubleValue();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setDealerId(String newDealerId){
        dealerId = newDealerId;
    }

    public String getDealerId(){
        return this.dealerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;

        return Double.compare(vehicle.price, price) == 0 && Objects.equals(VehicleId, vehicle.VehicleId) &&
                Objects.equals(manufacturer, vehicle.manufacturer) && Objects.equals(model, vehicle.model)
                && Objects.equals(acquisitionDate, vehicle.acquisitionDate)
                && Objects.equals(vehicleType, vehicle.vehicleType) && Objects.equals(dealerId, vehicle.dealerId);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "VehicleId=" + VehicleId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", acquisitionDate=" + acquisitionDate +
                ", price=" + price +
                ", vehicleType=" + vehicleType +
                ", dealerId=" + dealerId +
                '}';
    }
    
    boolean isVehicleOnLoan(){ 
    	if (isOnLoan == true) {
    		return isOnLoan;
    	}
    	else {
    		return false;
    	}
    }
    
    public void vehicleInventory(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the vehicle you are interested in: ");
    }
    public void searchResults(){
        System.out.println("Here is the following information regarding your search: ");

    }
}
