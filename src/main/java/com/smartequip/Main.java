package com.smartequip;

import com.smartequip.model.EquipmentCalculationDto;
import com.smartequip.service.EquipmentCalculationService;

import java.io.IOException;

public class Main {

    static EquipmentCalculationService calculationService = new EquipmentCalculationService("api-response.json");

    public static void main(String[] args) {

        calculate("67352", "2007");
        calculate("87964", "2011");

    }

    private static void calculate(String modelId, String year) {
        try {
            EquipmentCalculationDto equipmentCalculationDto = calculationService.calculateMarketAndAuctionValues(modelId, year);
            System.out.println("## Auction Value: " + equipmentCalculationDto.getAuctionValue());
            System.out.println("## Market Value: " + equipmentCalculationDto.getMarketValue());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }






}