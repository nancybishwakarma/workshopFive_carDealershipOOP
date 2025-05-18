package com.ps;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();

    }


    public ArrayList<Vehicle> vehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> priceResult = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            double vehiclePrice = vehicle.getPrice();
            if (vehiclePrice >= min && vehiclePrice <= max) {
                priceResult.add(vehicle);
            }
        }

        return priceResult;
    }

    public ArrayList<Vehicle> vehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> makeModelResult = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            String vehicleMake = vehicle.getMake();
            String vehicleModel = vehicle.getModel();
            if (make.equalsIgnoreCase(vehicleMake) && model.equalsIgnoreCase(vehicleModel)) {
                makeModelResult.add(vehicle);
            }
        }

        return makeModelResult;
    }

    public ArrayList<Vehicle> vehiclesByYear(int min, int max) {
        ArrayList<Vehicle> yearResult = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            int vehicleYear = vehicle.getYear();

            if (vehicleYear >= min && vehicleYear <= max) {
                yearResult.add(vehicle);
            }
        }

        return yearResult;
    }

    public ArrayList<Vehicle> vehiclesByColor(String color) {
        ArrayList<Vehicle> colorResult = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            String vehicleColor = vehicle.getColor();

            if (vehicleColor.equalsIgnoreCase(color)) {
                colorResult.add(vehicle);
            }
        }

        return colorResult;
    }

    public ArrayList<Vehicle> vehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> mileageResult = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            int vehicleMileage = vehicle.getOdometer();

            if (vehicleMileage >= min && vehicleMileage <= max) {
                mileageResult.add(vehicle);
            }
        }

        return mileageResult;

    }

    public ArrayList<Vehicle> vehiclesByType(String type) {
        ArrayList<Vehicle> typeResult = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            String vehicleType = vehicle.getType();

            if (vehicleType.equalsIgnoreCase(type)) {
                typeResult.add(vehicle);
            }
        }

        return typeResult;

    }

    public ArrayList<Vehicle> getAllVehicles() {

        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(int vin) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vin) {
                inventory.remove(i);
                System.out.println("Vehicle removed from inventory.");
                return;
            }
        }
    }
    public Vehicle getVehicleByVin(int vin){
        for(int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vin) {
                return inventory.get(i);
            }
        }

        System.out.println("Vehicle not found");
         return null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

