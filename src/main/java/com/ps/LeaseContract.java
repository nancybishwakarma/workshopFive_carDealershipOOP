package com.ps;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlyPayment;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public double getExpectedEndingValue() {
        double vehiclePrice = getVehicleSold().getPrice();
        expectedEndingValue = vehiclePrice * 0.5;
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        double vehiclePrice = getVehicleSold().getPrice();
        leaseFee = vehiclePrice * 0.07;
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }



    @Override
    public double getTotalPrice() {

        double leaseTotalPrice = getExpectedEndingValue()+getLeaseFee();

        return leaseTotalPrice;
    }

    @Override
    public double getMonthlyPayment() {
      double interestRate = 0.04/12;
      int interestPeriodInMonths = 36;
      double totalAmount = getTotalPrice();

      monthlyPayment = totalAmount* (interestRate * Math.pow(1+interestRate, interestPeriodInMonths)) / (Math.pow(1+interestRate,interestPeriodInMonths)-1);


    return monthlyPayment;

    }
}
