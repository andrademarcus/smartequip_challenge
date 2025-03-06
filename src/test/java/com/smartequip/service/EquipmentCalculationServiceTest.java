package com.smartequip.service;

import com.smartequip.exception.EquipmentModelNotFoundException;
import com.smartequip.model.EquipmentCalculationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquipmentCalculationServiceTest {

    private EquipmentCalculationService service;

    @BeforeEach
    public void setUp() {
        service = new EquipmentCalculationService("api-response.json");
    }

    @Test
    void calculate_ValidModelAndYear_ReturnsCorrectValues2007() throws IOException {
        EquipmentCalculationDto result = service.calculateMarketAndAuctionValues("67352", "2007");
        assertNotNull(result);
        assertEquals(new BigDecimal("126089.526420"), result.getAuctionValue());
        assertEquals(new BigDecimal("216384.710256"), result.getMarketValue());
    }

    @Test
    void calculate_ValidModelAndYear_ReturnsCorrectValues2008() throws IOException {
        EquipmentCalculationDto result = service.calculateMarketAndAuctionValues("67352", "2008");
        assertNotNull(result);
        assertEquals(new BigDecimal("128662.615224"), result.getAuctionValue());
        assertEquals(new BigDecimal("220801.266972"), result.getMarketValue());
    }

    @Test
    void calculate_ModelNotFound_ThrowsException() {
        assertThrows(EquipmentModelNotFoundException.class, () -> service.calculateMarketAndAuctionValues("87964", "2011"));
    }

    @Test
    void calculate_NullSaleDetails_ReturnsZeroValues() throws IOException {
        EquipmentCalculationDto result = service.calculateMarketAndAuctionValues("67352", "0000");
        assertNotNull(result);
        assertEquals(BigDecimal.ZERO, result.getAuctionValue());
        assertEquals(BigDecimal.ZERO, result.getMarketValue());
    }
}