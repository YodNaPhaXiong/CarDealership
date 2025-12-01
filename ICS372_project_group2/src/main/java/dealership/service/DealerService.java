package dealership.service;

import dealership.entities.dealer.Dealer;
import dealership.entities.vehicle.Vehicle;
import dealership.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;
    private static Scanner sc = new Scanner(System.in);

    public void add(Dealer dealer) {
            dealerRepository.save(dealer);
    }
    public List<Dealer> findAll(){
        return dealerRepository.findAll();
    }

    public Dealer findById(String id){
        return dealerRepository.findById(id).get(); //refactor to avoid null exception
    }

    public void updateName(Dealer dealer) {
        var d = dealerRepository.findById(dealer.getDealerId());

        if (d.isPresent()){
            Dealer oldDealer = d.get();
            oldDealer.setName(dealer.getName());
        }else{
            dealerRepository.save(dealer);
        }
    }

    public void addNewVehicle(Dealer dealer, Vehicle vehicle) {
        var dealerId = dealer.getDealerId();
        var isAcceptingNewVehicles = dealerRepository.findById(dealerId).get().acceptingNewVehicles();

        if (isAcceptingNewVehicles) {

            vehicle.setDealerId(dealerId);
            //update vehicle Repository
        }
    }

    public void enableVehicleAcquisition(Dealer dealer) {
        var d = dealerRepository.findById(dealer.getDealerId());
        if(d.isPresent()){
            dealer = d.get();
            dealer.enableVehicleAcquisition();
            dealerRepository.save(dealer);
        }
    }

    public void disableVehicleAcquisition(Dealer dealer) {
        var d = dealerRepository.findById(dealer.getDealerId());
        if(d.isPresent()){
            dealer = d.get();
            dealer.disableVehicleAcquisition();
            dealerRepository.save(dealer);
        }
    }
    
    private void InventoryCheck() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of Pick Up: ");
        System.out.print("Number of SUV: ");
        System.out.print("Number of Sedans : ");
        System.out.print("Number of Sports Car: ");
    }
    
    public static void searchVehicle(ArrayList<Vehicle> vehicles)
    {
        String VIN;
        Vehicle foundVehicle = null;
        
        do {
            System.out.print("Enter VIN of the car (5 Alphanumeric): ");
            VIN = sc.next();   
        }while(!VIN.matches("^[a-zA-Z0-9]{3,15}$"));

        for (Vehicle vehicle : vehicles) {
            if(vehicle.getVehicleId().equals(VIN))
            {
           	 foundVehicle = vehicle;
            }    
        }
        if(foundVehicle != null)
            displayVehicles(foundVehicle);
        else
            System.out.println("No Cars found.");
    }

	private static void displayVehicles(Vehicle car) {
		System.out.println(car.getDealerId());
		System.out.println(car.getModel());
		System.out.println(car.getVehicleId());
		System.out.println(car.getVehicleType());
		System.out.println(car.getParsedAcquisitionDate());
		System.out.println(car.getPrice());
	}
   

}
