package com.tienda.sventasropa.service;

import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.model.Product;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import com.tienda.sventasropa.repository.ISaleRepository;
import java.util.List;
import java.util.Optional;

/**
 * Business logic for sales transactions.
 */
public class SaleService {
    private final ISaleRepository repository;

    public SaleService(ISaleRepository repository) {
        if (repository == null) throw new IllegalArgumentException("repository required");
        this.repository = repository;
    }

    // Create a new sale for a client
    public Sale createSale(int id, Client client) {
        if (client == null) throw new IllegalArgumentException("client required");
        return new Sale(id, client);
    }

    // Add product to sale (creates SaleDetail internally)
    public void addProductToSale(Sale sale, Product product, int quantity) {
        if (sale == null) throw new IllegalArgumentException("sale required");
        if (product == null) throw new IllegalArgumentException("product required");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        
        SaleDetail detail = new SaleDetail(product, quantity, product.getUnitPrice());
        sale.addDetail(detail);
    }

    // Save completed sale
    public void saveSale(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("sale required");
        if (!sale.hasDetails()) throw new IllegalArgumentException("sale must have items");
        repository.save(sale);
    }

    // Retrieve all sales
    public List<Sale> getAllSales() {
        return repository.list();
    }

    // Find sale by ID
    public Optional<Sale> findSale(int id) {
        return repository.findById(id);
    }

    // Calculate total revenue
    public double getTotalRevenue() {
        return repository.list().stream()
                .mapToDouble(Sale::getTotal)
                .sum();
    }
}
