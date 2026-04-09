/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.model.Sale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Simple in-memory Sale repository.
 */
public class SaleRepository implements ISaleRepository {
    private final List<Sale> storage = new ArrayList<>();

    @Override
    public void save(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("sale required");
        storage.add(sale);
    }

    @Override
    public List<Sale> list() {
        return Collections.unmodifiableList(new ArrayList<>(storage));
    }

    @Override
    public Optional<Sale> findById(int id) {
        return storage.stream().filter(s -> s.getId() == id).findFirst();
    }
}
