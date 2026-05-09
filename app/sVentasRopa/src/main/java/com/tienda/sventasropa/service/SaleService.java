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
    public void addProductToSale(Sale sale, SaleDetail saledetail) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        if (saledetail == null) throw new IllegalArgumentException("El detalle de venta no puede ser nulo");
        
        int quantity = saledetail.getQuantity();
        if (quantity <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");

        com.tienda.sventasropa.model.Product product = productRepository.findProductById(saledetail.getProductId());
        if (product == null) {
            throw new IllegalArgumentException("El producto con ID " + saledetail.getProductId() + " no existe");
        }
        
        if (product.getProductStock() < quantity) {
            throw new IllegalArgumentException("Stock insuficiente. Disponible: " + product.getProductStock() + ", solicitado: " + quantity);
        }
        
        product.setProductStock(product.getProductStock() - quantity);
        
        productRepository.updateProduct(product.getProductId(), product); 
        
        sale.addDetail(saledetail);
    }

    @Override
    public void saveSale(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        if (!sale.hasDetails()) throw new IllegalArgumentException("La venta debe tener al menos un artículo");
        if (sale.getClient() == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        repository.save(sale);
    }

   @Override
    public boolean deleteSale(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");

        Optional<Sale> optSale = repository.findById(id);
        
        if (optSale.isPresent()) {
            Sale saleToDelete = optSale.get();
            
            for (SaleDetail detail : saleToDelete.getDetails()) {
                com.tienda.sventasropa.model.Product product = productRepository.findProductById(detail.getProductId());
                
                if (product != null) {
                    product.setProductStock(product.getProductStock() + detail.getQuantity());
            
                    productRepository.updateProduct(product.getProductId(), product); 
                }
            }
            return repository.delete(id);
        }
        return false;
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

    @Override
    public int getNextSaleId() {
        List<Sale> allSales = repository.list();
        if (allSales == null || allSales.isEmpty()) {
            return 1;
        }
        // Busca el ID más alto y le suma 1
        return allSales.stream()
                       .mapToInt(Sale::getId)
                       .max()
                       .getAsInt() + 1;
    }
}
