package com.tienda.sventasropa.service;

import com.tienda.sventasropa.interfaces.ISaleRepository;
import com.tienda.sventasropa.interfaces.IProductRepository;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======
/**
 * Lógica de negocio para transacciones de venta.
 * Gestiona la creación, edición y eliminación de ventas.
 */
>>>>>>> origin/develop
public class SaleService implements ISaleService {
    private final ISaleRepository repository;
    private final IProductRepository productRepository;
    private final IClientService clientService;

    public SaleService(ISaleRepository repository, IProductRepository productRepository, IClientService clientService) {
        if (repository == null) throw new IllegalArgumentException("El repositorio de ventas no puede ser nulo");
        if (productRepository == null) throw new IllegalArgumentException("El repositorio de productos no puede ser nulo");
        if (clientService == null) throw new IllegalArgumentException("El servicio de clientes no puede ser nulo");
        this.repository = repository;
        this.productRepository = productRepository;
        this.clientService = clientService;
    }

    @Override
    public Sale createSale(int id, Client client) {
        if (id <= 0) throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");
        if (client == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        
        // Validar que el cliente existe en el sistema
        Client clientValidated = clientService.findClientById(client.getId());
        if (clientValidated == null) {
            throw new IllegalArgumentException("El cliente con ID " + client.getId() + " no existe en el sistema");
        }
        return new Sale(id, clientValidated);
    }

    @Override
    public void addProductToSale(Sale sale, int productId, int quantity) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        if (productId <= 0) throw new IllegalArgumentException("El ID del producto debe ser mayor a 0");
        if (quantity <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        
        com.tienda.sventasropa.model.Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("El producto con ID " + productId + " no existe");
        }
        
        if (product.getProductStock() < quantity) {
            throw new IllegalArgumentException("Stock insuficiente. Disponible: " + product.getProductStock() + ", solicitado: " + quantity);
        }
        
        product.setProductStock(product.getProductStock() - quantity);
        
        SaleDetail detail = new SaleDetail(productId, product, quantity);
        sale.addDetail(detail);
    }

    @Override
    public void saveSale(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        if (!sale.hasDetails()) throw new IllegalArgumentException("La venta debe tener al menos un artículo");
        if (sale.getClient() == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        repository.save(sale);
    }

    @Override
    public void updateSale(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        if (!sale.hasDetails()) throw new IllegalArgumentException("La venta debe tener al menos un artículo");
        if (sale.getClient() == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        repository.update(sale);
    }

    @Override
    public void deleteSale(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");
        repository.delete(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return repository.list();
    }

    @Override
    public Optional<Sale> findSale(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");
        return repository.findById(id);
    }

    @Override
    public double getTotalRevenue() {
        return repository.list().stream()
                .mapToDouble(Sale::getTotal)
                .sum();
    }
}
