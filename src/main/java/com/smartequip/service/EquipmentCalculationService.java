package com.smartequip.service;

import com.smartequip.exception.EquipmentModelNotFoundException;
import com.smartequip.model.Equipment;
import com.smartequip.model.EquipmentCalculationDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class EquipmentCalculationService {

    private EquipmentFileReaderService equipmentFileReaderService;

    public EquipmentCalculationService(String fileName) {
        this.equipmentFileReaderService = new EquipmentFileReaderService(fileName);
    }

    public EquipmentCalculationDto calculateMarketAndAuctionValues(String modelId, String year) throws IOException {

        System.out.println("=======\nCalculating Market And Auction Values. Model=" + modelId + ", Year=" + year);

        Map<String, Equipment> equipmentMap = this.equipmentFileReaderService.readFileAsMap();

        if (equipmentMap == null || !equipmentMap.containsKey(modelId)) {
            throw new EquipmentModelNotFoundException("Equipment Model not found: " + modelId);
        }

        Equipment equipment = equipmentMap.get(modelId);

        System.out.println("Equipment make: " + equipment.getClassification().getMake());
        System.out.println("Equipment model: " + equipment.getClassification().getModel());

        EquipmentCalculationDto equipmentCalculationDto = new EquipmentCalculationDto(modelId, year);
        equipmentCalculationDto.setAuctionValue(calculateAuctionValue(equipment, year));
        equipmentCalculationDto.setMarketValue(calculateMarketValue(equipment, year));
        return equipmentCalculationDto;

    }

    private BigDecimal calculateAuctionValue(Equipment equipment, String year) {
        BigDecimal results = new BigDecimal(0);
        if (equipment.getSaleDetails() != null && equipment.getSchedule() != null
                && equipment.getSchedule().getYears() != null
                && equipment.getSchedule().getYears().containsKey(year)) {

            BigDecimal cost = new BigDecimal(String.valueOf(equipment.getSaleDetails().getCost()));
            BigDecimal auction = new BigDecimal(String.valueOf(equipment.getSchedule().getYears().get(year).getAuctionRatio()));
            results = cost.multiply(auction);

        }
        return results;
    }

    private BigDecimal calculateMarketValue(Equipment equipment, String year) {
        BigDecimal results = new BigDecimal(0);
        if (equipment.getSaleDetails() != null && equipment.getSchedule() != null
                && equipment.getSchedule().getYears() != null
                && equipment.getSchedule().getYears().containsKey(year)) {

            BigDecimal cost = BigDecimal.valueOf(equipment.getSaleDetails().getCost());
            BigDecimal marketRatio = BigDecimal.valueOf(equipment.getSchedule().getYears().get(year).getMarketRatio());

            results = cost.multiply(marketRatio);
        }
        return results;
    }

}
