package com.tienda.sventasropa.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tienda.sventasropa.interfaces.IProductPersistence;
import com.tienda.sventasropa.model.Product;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ProductPersistence implements IProductPersistence {

    private final Path filePath = Paths.get("ProductsJson.json");

    @Override
    public boolean saveProducts(List<Product> products) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            String jsonValue = gson.toJson(products);
            Files.writeString(filePath, jsonValue, StandardCharsets.UTF_8);
            return true;
        } catch (Exception exe) {
            System.err.println("Error: " + exe.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> loadProducts() {
        Gson gson = new Gson();
        try {
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            String jsonValue = Files.readString(filePath, StandardCharsets.UTF_8);

            if (jsonValue == null || jsonValue.trim().isEmpty()) {
                return new ArrayList<>();
            }

            Type listType = new TypeToken<List<Product>>() { }.getType();
            return gson.fromJson(jsonValue, listType);

        } catch (Exception exe) {
            System.err.println("Error: " + exe.getMessage());
            return new ArrayList<>();
        }
    }
}