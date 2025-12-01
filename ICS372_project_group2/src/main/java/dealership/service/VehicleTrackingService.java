package dealership.service;

import dealership.entities.dealer.Dealer;
import dealership.entities.vehicle.Vehicle;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import utils.JSONFileHandler;
import utils.IFileHandler;

import java.util.*;
@Service
public final class VehicleTrackingService {
    private static final VehicleTrackingService VehicleTrackingServiceInstance = new VehicleTrackingService();
    private final Map<String, Dealer> dealerList;
    private final Map<String, Vehicle> vehicleList;
    IFileHandler fileHandler;
    


    private VehicleTrackingService() {
        dealerList = new HashMap<>();
        vehicleList = new HashMap<>();

    }

    /**
     * Returns the singleton instance of this class
     * @return the unique instance of the VehicleTrackingService class
     */
    public static VehicleTrackingService getInstance(IFileHandler fileHandler) {
        VehicleTrackingServiceInstance.setFileHandler(fileHandler);
        return VehicleTrackingServiceInstance;
    }

    public  Optional<JSONArray> readJSONFileData(String filePath) {
        return JSONFileHandler.uploadJSONData(filePath);
    }

    public void loadJSONData(Optional<JSONArray> vehicleJSONArray) {
        if(vehicleJSONArray.isEmpty()){
            return;
        }

        List<Vehicle> newVehicles =  fileHandler.parse(vehicleJSONArray.get());

        newVehicles.forEach(vehicle -> vehicleList.putIfAbsent(vehicle.getVehicleId(), vehicle));

//        newVehicles.forEach(vehicle -> addVehicleToDealer(vehicle.getDealerId(), vehicle));

    }

    /**
     * Adds a given Vehicle object to a given Dealer if Dealer is accepting new vehicles
     * @param dealerId
     * @param vehicle object
     *
     */
//    private void addVehicleToDealer(String dealerId, Vehicle vehicle) {
//        if(!dealerList.containsKey(dealerId)){
//            return;
//        }
//
//        Dealer d = dealerList.get(dealerId);
//        if(d.acceptingNewVehicles()){
//            d.addNewVehicle(vehicle);
//        }
//
//        dealerList.put(dealerId, d);
//    }

    /**
     * Adds a given Vehicle object to a given Dealer if Dealer is accepting new vehicles
     * @param dealerId
     * @param vehicleId
     *
     */
//    public boolean addVehicleToDealer(String dealerId, String vehicleId){
//        if (!(dealerList.containsKey(dealerId) && vehicleList.containsKey(vehicleId))){
//            return false;
//        }
//
//        Dealer dealer = dealerList.get(dealerId);
//        Vehicle vehicle = vehicleList.get(vehicleId);
//
//        if(dealer.acceptingNewVehicles()){
//            return dealer.addNewVehicle(vehicle);
//        };
//
//        return false;
//    }

    /** Exports dealer vehicleList to JSON file
     *
     * @param dealer
     */
//    public void exportVehicles(Dealer dealer){
//        fileHandler.exportVehicles(dealer.getVehicleList(), dealer.getDealerId());
//    }

     /**
     * returns a an unmodifiable collection of dealers in the dealerList
     * @return copy of dealerList
     */
    public Collection<Dealer> getDealerList() {
        return dealerList.values().stream().toList();
    }

    /**
     * returns an unmodifiable collection of the of vehicles in the vehicleList
     * @return copy of dealerList
     */
    public Collection<Vehicle> getVehicleList(){
        return Collections.unmodifiableCollection(vehicleList.values());
    }


    public void addNewDealer(Dealer d){
        if(d == null){
            throw new IllegalArgumentException("Cannot add null to DealerList");
        }
         dealerList.putIfAbsent(d.getDealerId(), d);
    }
    
//    public void showVehiclesList()
//    {
//        for(Dealer dealer : dealerList.values())
//        {
//            System.out.println("Dealer Id \'"+dealer.toString()+"\' :");
//            for(Vehicle vehicle : dealer.getVehicleList())
//            {
//                //use vehicles by dealer id here
//                System.out.println("\t"+vehicle.toString());
//            }
//        }
//    }

     public void setFileHandler(IFileHandler JSONFileHandler) {
        this.fileHandler = JSONFileHandler;
    }
     
     
}

