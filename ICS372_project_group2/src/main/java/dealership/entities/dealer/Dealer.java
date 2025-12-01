package dealership.entities.dealer;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import dealership.entities.vehicle.PickUp;
import dealership.entities.vehicle.Vehicle;

@Entity
public class Dealer {
	
	@Id
	@Column(name = "dealerId", nullable = false)
	private String dealerId;
	private boolean isAcceptingNewVehicles;

	private String name;
	public Dealer() {
	}
	
	public Dealer(String dealerId) {
		this.dealerId = dealerId;
		isAcceptingNewVehicles = true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void enableVehicleAcquisition() {
		this.isAcceptingNewVehicles = true;
	}
	
	public void disableVehicleAcquisition() {
		isAcceptingNewVehicles = false;
	}
	
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	
	public String getDealerId() {
		return this.dealerId;
	}
	
	public boolean acceptingNewVehicles(){
		return isAcceptingNewVehicles;
	}
	
	@Override
	public String toString() {
		return "Dealer{" +
				"dealerId='" + dealerId + '\'' +
				", isAcceptingNewVehicles=" + isAcceptingNewVehicles +
//				", vehicleList=" + vehicleList +
				'}';
	}
	
	public void listDealerships(ArrayList<Dealer> dealerList) {
        System.out.println("Dealership Locations:");
        for (Dealer dealer : dealerList) {
            System.out.println();
            System.out.println("Dealership ID: " + dealer.getDealerId());
            System.out.println("Name: " + dealer.getName());
        }
    }
	
	
}

	
	


