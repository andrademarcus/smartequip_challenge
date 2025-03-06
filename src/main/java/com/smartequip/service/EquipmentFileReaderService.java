package com.smartequip.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartequip.Main;
import com.smartequip.model.Equipment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class EquipmentFileReaderService {

    private final String fileName;

    public EquipmentFileReaderService(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Equipment> readFileAsMap() throws IOException {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            return new ObjectMapper().readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new IOException("File not found: " + fileName, e);
        }
    }

}
