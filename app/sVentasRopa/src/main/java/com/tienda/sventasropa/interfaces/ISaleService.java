/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for Sale business logic.
 */
public interface ISaleService {
    Sale createSale(int id, com.tienda.sventasropa.model.Client client);
    void addProductToSale(Sale sale, SaleDetail detail);
    void saveSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSale(int id);
    List<Sale> getAllSales();
    Optional<Sale> findSale(int id);
    double getTotalRevenue();
}
