package com.smartequip.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EquipmentCalculationDto {

    private String year;
    private String modelId;
    private BigDecimal marketValue;
    private BigDecimal auctionValue;

    public EquipmentCalculationDto(String modelId, String year) {
        this.modelId = modelId;
        this.year = year;
    }

}
