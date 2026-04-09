/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.model.Sale;
import java.util.List;
import java.util.Optional;

/**
 * Minimal repository contract for Sale persistence.
 */
public interface ISaleRepository {
    void save(Sale sale);
    List<Sale> list();
    Optional<Sale> findById(int id);
}
