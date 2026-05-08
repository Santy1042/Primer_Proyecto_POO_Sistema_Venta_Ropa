package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;

import java.util.List;
import java.util.Optional;

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
