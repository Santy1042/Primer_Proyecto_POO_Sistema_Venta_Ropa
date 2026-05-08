package com.tienda.sventasropa.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tienda.sventasropa.interfaces.IClientPersistence;
import com.tienda.sventasropa.model.Client;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ClientPersistence implements IClientPersistence {

    private final Path filePath = Paths.get("ClientsJson.json");

    @Override
    public boolean saveClients(List<Client> clients) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            String jsonValue = gson.toJson(clients);
            Files.writeString(filePath, jsonValue, StandardCharsets.UTF_8);
            return true;
        } catch (Exception exe) {
            System.err.println("Error: " + exe.getMessage());
            return false;
        }
    }

    @Override
    public List<Client> loadClients() {
        Gson gson = new Gson();
        try {
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            String jsonValue = Files.readString(filePath, StandardCharsets.UTF_8);

            if (jsonValue == null || jsonValue.trim().isEmpty()) {
                return new ArrayList<>();
            }

            Type listType = new TypeToken<List<Client>>() { }.getType();
            return gson.fromJson(jsonValue, listType);

        } catch (Exception exe) {
            System.err.println("Error: " + exe.getMessage());
            return new ArrayList<>();
        }
    }
}