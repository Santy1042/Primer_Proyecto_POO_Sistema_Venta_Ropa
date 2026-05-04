package com.tienda.sventasropa.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.tienda.sventasropa.interfaces.ISalePersistence;
import com.tienda.sventasropa.model.Sale;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para persistir ventas en archivos JSON utilizando Gson
 * @author Apolo
 */
public class SalePersistence implements ISalePersistence {

    private final Path filePath = Paths.get("SalesJson.json");

    @Override
    public boolean saveSales(List<Sale> sales) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString());
                    }
                })
                .create();
        try {
            String jsonValue = gson.toJson(sales);
            Files.writeString(filePath, jsonValue, StandardCharsets.UTF_8);
            return true;
        } catch (Exception exe) {
            System.err.println("Error al guardar ventas: " + exe.getMessage());
            return false;
        }
    }

    @Override
    public List<Sale> loadSales() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString());
                    }
                })
                .create();
        try {
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            String jsonValue = Files.readString(filePath, StandardCharsets.UTF_8);

            if (jsonValue == null || jsonValue.trim().isEmpty()) {
                return new ArrayList<>();
            }

            Type listType = new TypeToken<List<Sale>>() { }.getType();
            return gson.fromJson(jsonValue, listType);

        } catch (Exception exe) {
            System.err.println("Error al cargar ventas: " + exe.getMessage());
            return new ArrayList<>();
        }
    }
}
