package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractFileManager {
    public static void saveContract(Contract contract ) {


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv",true));

            if(contract instanceof SalesContract){
                String isFinancedChoice;
                if (((SalesContract) contract).isFinanced()) {
                    isFinancedChoice = "YES";
                } else {
                    isFinancedChoice = "NO";
                }

                String salesContractLine = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),
                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getType(),
                        contract.getVehicleSold().getColor(),
                        contract.getVehicleSold().getOdometer(),
                        contract.getVehicleSold().getPrice(),
                        ((SalesContract) contract).getSalesTaxAmount(),
                        ((SalesContract) contract).getRecordingFee(),
                        ((SalesContract) contract).getProcessingFee(),
                        contract.getTotalPrice(),
                        isFinancedChoice,
                        contract.getMonthlyPayment()

                );
                bufferedWriter.write(salesContractLine);
                bufferedWriter.newLine();
                bufferedWriter.close();

            }
            else if (contract instanceof LeaseContract){
                String leaseContractLine = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|",

                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),
                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getType(),
                        contract.getVehicleSold().getColor(),
                        contract.getVehicleSold().getOdometer(),
                        contract.getVehicleSold().getPrice(),
                        ((LeaseContract)contract).getExpectedEndingValue(),
                        ((LeaseContract)contract).getLeaseFee(),
                        contract.getTotalPrice(),
                        contract.getMonthlyPayment()
                );
                bufferedWriter.write(leaseContractLine);
                bufferedWriter.newLine();
                bufferedWriter.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
