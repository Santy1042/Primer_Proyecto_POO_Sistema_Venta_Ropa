/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Sale;
import java.util.List;
import java.util.Optional;

/**
 * @author Apolo
 */
public interface ISaleService {
    Sale createSale(int id, com.tienda.sventasropa.model.Client client);
    void addProductToSale(Sale sale, int productId, int quantity);
    void saveSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSale(int id);
    List<Sale> getAllSales();
    Optional<Sale> findSale(int id);
    double getTotalRevenue();
}
