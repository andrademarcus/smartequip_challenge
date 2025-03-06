package com.smartequip.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquipmentFileReaderServiceTest {

    @Test
    void readFileAsMap_ValidFile_ReturnsCorrectMap() {
        EquipmentFileReaderService service = new EquipmentFileReaderService("api-response.json");
        assertNotNull(service);
    }

    @Test
    void readFileAsMap_FileNotFound_ThrowsIOException() {
        EquipmentFileReaderService service = new EquipmentFileReaderService("test.json");
        assertThrows(IOException.class, service::readFileAsMap);
    }

}