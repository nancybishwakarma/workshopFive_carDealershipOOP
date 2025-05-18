package com.ps;

public class SalesContract extends Contract{
    private double salesTaxAmount;
    private double recordingFee = 100;
    private double processingFee;
    private boolean isFinanced;



    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    public double getSalesTaxAmount(){
        double salesTaxRate = 0.05;
        double vehiclePrice = getVehicleSold().getPrice();
        salesTaxAmount = salesTaxRate * vehiclePrice;

        return salesTaxAmount;
    }
    public void setSalesTaxAmount(double salesTaxAmount){
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee(){
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee){
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee(){
        double vehiclePrice = getVehicleSold().getPrice();
        if(vehiclePrice < 1000){
            processingFee = 295;
        }
        else{
            processingFee = 495;
        }
        return processingFee;
    }
    public void setProcessingFee(double processingFee){
        this.processingFee= processingFee;
    }

    public boolean isFinanced(){
        return isFinanced;
    }

    public void setIsFinanced(boolean isFinanced){
        this.isFinanced = isFinanced;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double salesTotalPrice = vehiclePrice+getSalesTaxAmount()+getRecordingFee()+getProcessingFee();

        return salesTotalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        double interestRate;
        int interestPeriodInMonths;
        double loanAmount = getTotalPrice();
        double vehiclePrice = getVehicleSold().getPrice();

        if(isFinanced) {
            if (vehiclePrice >= 10_000) {
                interestRate = 0.0425 / 12;
                interestPeriodInMonths = 48;
            } else {
                interestRate = 0.0525 / 12;
                interestPeriodInMonths = 24;
            }

           double monthlyPayment = loanAmount * (interestRate * Math.pow(1 + interestRate, interestPeriodInMonths)) / (Math.pow(1 + interestRate, interestPeriodInMonths) - 1);

            return monthlyPayment;
        }
        else {
            return 0;
        }


    }

}
