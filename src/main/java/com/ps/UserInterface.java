package com.ps;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    private void init() {
        //ToDO: Load dealership details from a file
        dealership = DealershipFileManager.getDealership();
    }

    public UserInterface() {
        init();
    }

    public void display() {

        //TODO: Create your main menu with do while
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  Welcome to the dealership program");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int mainMenuCommand;
        do {
            System.out.println("1. Get by price");
            System.out.println("2. Get by make/model");
            System.out.println("3. Get by year");
            System.out.println("4. Get by color");
            System.out.println("5. Get by mileage");
            System.out.println("6. Get by type");
            System.out.println("7. Get all vehicles");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. Sell/Lease a vehicle");
            System.out.println("0. Exit");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Please enter a number");
            mainMenuCommand = scanner.nextInt();

            switch (mainMenuCommand) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehicleRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    sellOrLeaseVehicle();
                    break;
                case 0:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Command not found. Try again!");
            }
        } while (mainMenuCommand != 0);

    }

    private void sellOrLeaseVehicle(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Please select one of the following options:" );
        System.out.println("1. Sell a vehicle");
        System.out.println("2. Lease a vehicle");

        int sellOrLeaseOption = scanner.nextInt();
        scanner.nextLine();
        if(sellOrLeaseOption==1) {
            System.out.println("~~~~~~~~~~~~You have selected to sell~~~~~~~~~~~~~~~~");


            System.out.println("Please enter the date as (yyyymmdd)");
            String date = scanner.nextLine();

            System.out.println("Please enter the name of the owner");
            String name = scanner.nextLine();

            System.out.println("Please enter the email of the owner");
            String email = scanner.nextLine();


            System.out.println("PLease enter the vin of the vehicle");
            int vin = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicleSale = dealership.getVehicleByVin(vin);
            if (vehicleSale == null) {
                System.out.println("Vehicle not found. Please try again.");
                return;
            }

            boolean isFinanced = false;
            System.out.println("Is the vehicle financed? Enter yes or no");
            String userFinanceInput = scanner.nextLine();
            if(userFinanceInput.equalsIgnoreCase("yes")){
                isFinanced=true;
            }
            else if (userFinanceInput.equalsIgnoreCase("no"))
            {
                System.out.println("The vehicle is not financed.");
            }
            SalesContract salesContract = new SalesContract(date,name,email,vehicleSale,isFinanced);
            ContractFileManager.saveContract(salesContract);

            System.out.println("The sales contract has been saved");
            System.out.println("The total price is $"+String.format("%.2f",salesContract.getTotalPrice()));
            System.out.println("Thank you for shopping with us");
            dealership.removeVehicle(vin);
            DealershipFileManager.saveDealership(dealership);


        }
        else if (sellOrLeaseOption==2){
            System.out.println("~~~~~~~~~~~~You have selected to lease~~~~~~~~~~~~~~~~");


            System.out.println("Please enter the date as (yyyymmdd)");
            String date = scanner.nextLine();

            System.out.println("Please enter the name of the owner");
            String name = scanner.nextLine();

            System.out.println("Please enter the email of the owner");
            String email = scanner.nextLine();


            System.out.println("PLease enter the vin of the vehicle");
            int vin = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicleLease = dealership.getVehicleByVin(vin);
            if (vehicleLease == null) {
                System.out.println("Vehicle not found. Please try again.");
                return;
            }

            LeaseContract leaseContract = new LeaseContract(date,name,email,vehicleLease);
            ContractFileManager.saveContract(leaseContract);

            System.out.println("The lease contract has been saved");
            System.out.println("The total price is $"+String.format("%.2f",leaseContract.getTotalPrice()));
            System.out.println("Thank you for shopping with us");
            dealership.removeVehicle(vin);
            DealershipFileManager.saveDealership(dealership);


        }
        else{
            System.out.println("Please try again");
        }



    }

    private void processGetByPriceRequest() {
        // TODO: Ask the user for a starting price and ending price.

        // ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(pass the price range. starting price, ending price);

        // Display vehicles with for loop

        System.out.println("________________Display vehicles by price___________________");
        System.out.println("Enter a value for minimum price");
        double min = scanner.nextDouble();

        System.out.println("Enter a value for maximum price");
        double max = scanner.nextDouble();

        ArrayList<Vehicle> filteredVehiclesPrice = dealership.vehiclesByPrice(min, max);
        displayVehicles(filteredVehiclesPrice);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void processGetByMakeModelRequest() {
        scanner.nextLine();
        System.out.println("________________Display vehicles by Make and Model___________________");
        System.out.println("Please enter the make of the vehicle");
        String make = scanner.nextLine();
        System.out.println("Please enter the model of the vehicle");
        String model = scanner.nextLine();

        ArrayList<Vehicle> filteredVehiclesMakeModel = dealership.vehiclesByMakeModel(make, model);
        displayVehicles(filteredVehiclesMakeModel);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


    }

    private void processGetByYearRequest() {
        scanner.nextLine();
        System.out.println("________________Display vehicles by Year Range___________________");
        System.out.println("Please enter a value for minimum year");
        int min = scanner.nextInt();
        System.out.println("Please enter a value for maximum year");
        int max = scanner.nextInt();

        ArrayList<Vehicle> filteredVehiclesYear = dealership.vehiclesByYear(min, max);
        displayVehicles(filteredVehiclesYear);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void processGetByColorRequest() {
        scanner.nextLine();
        System.out.println("________________Display vehicles by Color___________________");
        System.out.println("Please enter the color of the vehicle");
        String color = scanner.nextLine();

        ArrayList<Vehicle> filteredVehiclesColor = dealership.vehiclesByColor(color);
        displayVehicles(filteredVehiclesColor);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


    }

    private void processGetByMileageRequest() {
        scanner.nextLine();
        System.out.println("________________Display vehicles by Mileage___________________");
        System.out.println("Please enter a value for minimum mileage");
        int min = scanner.nextInt();
        System.out.println("Please enter a value for maximum mileage");
        int max = scanner.nextInt();
        ArrayList<Vehicle> filteredVehiclesMileage = dealership.vehiclesByMileage(min, max);
        displayVehicles(filteredVehiclesMileage);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void processGetByVehicleTypeRequest() {
        scanner.nextLine();
        System.out.println("________________Display vehicles by Type___________________");
        System.out.println("Please enter the type of vehicle");
        String type = scanner.nextLine();

        ArrayList<Vehicle> filteredVehiclesType = dealership.vehiclesByType(type);
        displayVehicles(filteredVehiclesType);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void processGetAllVehicleRequest() {

        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("________________Displaying All Vehicles__________________");
        displayVehicles(vehicles);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void processAddVehicleRequest() {

        scanner.nextLine();

        System.out.println("Please enter the vin of the vehicle");
        int vin = scanner.nextInt();

        System.out.println("Please enter the year of the vehicle");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the make of the vehicle");
        String make = scanner.nextLine();
        make = Character.toUpperCase(make.charAt(0)) + make.substring(1);

        System.out.println("Please enter the model of the vehicle");
        String model = scanner.nextLine();
        model = Character.toUpperCase(model.charAt(0)) + model.substring(1);

        System.out.println("Please enter the type of the vehicle");
        String type = scanner.nextLine();
        type = Character.toUpperCase(type.charAt(0)) + type.substring(1);

        System.out.println("Please enter the color of the vehicle");
        String color = scanner.nextLine();
        color = Character.toUpperCase(color.charAt(0)) + color.substring(1);


        System.out.println("Please enter the mileage of the vehicle");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the price of the vehicle");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        DealershipFileManager.saveDealership(dealership);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You have added:\n" + vehicle);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void processRemoveVehicleRequest() {
        System.out.println("Please enter the vin of the car to remove");
        int vin = scanner.nextInt();

        dealership.removeVehicle(vin);
        DealershipFileManager.saveDealership(dealership);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }


    public static void displayVehicles(ArrayList<Vehicle> vehicles) {
        Vehicle.vehicleCategories();
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle);
        }

    }

}

