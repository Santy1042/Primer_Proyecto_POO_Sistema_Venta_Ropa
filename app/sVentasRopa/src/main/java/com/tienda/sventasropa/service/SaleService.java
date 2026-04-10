package com.tienda.sventasropa.service;

import com.tienda.sventasropa.interfaces.ISaleRepository;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import java.util.List;
import java.util.Optional;

/**
 * Business logic for sales transactions.
 */
public class SaleService implements ISaleService {
    private final ISaleRepository repository;

    public SaleService(ISaleRepository repository) {
        if (repository == null) throw new IllegalArgumentException("repository required");
        this.repository = repository;
    }

    @Override
    public Sale createSale(int id, Client client) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        if (client == null) throw new IllegalArgumentException("client required");
        return new Sale(id, client);
    }

    @Override
    public void addProductToSale(Sale sale, SaleDetail detail) {
        if (sale == null) throw new IllegalArgumentException("sale required");
        if (detail == null) throw new IllegalArgumentException("detail required");
        if (detail.getProductName() == null || detail.getProductName().trim().isEmpty()) 
            throw new IllegalArgumentException("product name required");
        if (detail.getQuantity() <= 0) throw new IllegalArgumentException("quantity must be > 0");
        if (detail.getUnitPrice() < 0) throw new IllegalArgumentException("unit price must be >= 0");
        
        sale.addDetail(detail);
    }

    @Override
    public void saveSale(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("sale required");
        if (!sale.hasDetails()) throw new IllegalArgumentException("sale must have items");
        if (sale.getClient() == null) throw new IllegalArgumentException("client required");
        repository.save(sale);
    }

    @Override
    public void updateSale(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("sale required");
        if (!sale.hasDetails()) throw new IllegalArgumentException("sale must have items");
        if (sale.getClient() == null) throw new IllegalArgumentException("client required");
        repository.update(sale);
    }

    @Override
    public void deleteSale(int id) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        repository.delete(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return repository.list();
    }

    @Override
    public Optional<Sale> findSale(int id) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        return repository.findById(id);
    }

    @Override
    public double getTotalRevenue() {
        return repository.list().stream()
                .mapToDouble(Sale::getTotal)
                .sum();
    }
}
