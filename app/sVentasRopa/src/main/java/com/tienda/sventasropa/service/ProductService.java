package com.tienda.sventasropa.service;

import java.util.List;

import com.tienda.sventasropa.interfaces.IProductRepository;
import com.tienda.sventasropa.interfaces.IProductService;
import com.tienda.sventasropa.model.Product;


public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean addProduct(int id, String name, String size, String color, double price, int stock) { 
        if (id < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }

        if (productRepository.findProductById(id) != null) {
            throw new IllegalArgumentException("El ID del producto ya existe. Por favor, elija un ID único.");
        }

        if (name == null || name.trim().isEmpty() || name.length() > 100) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio y no puede exceder los 100 caracteres.");
        }

        if (size == null || size.trim().isEmpty() || size.length() > 30) {
            throw new IllegalArgumentException("El tamaño del producto es obligatorio y no puede exceder los 30 caracteres.");
        }

        if (color == null || color.trim().isEmpty() || color.length() > 30) {
            throw new IllegalArgumentException("El color del producto es obligatorio y no puede exceder los 30 caracteres.");
        }

        if (price <= 0 || price > 99999999) {
            throw new IllegalArgumentException("El precio del producto debe ser un valor positivo y no mayor a 99,999,999.");
        }

        if (stock < 0 || stock > 1000000) {
            throw new IllegalArgumentException("El stock del producto no puede ser un valor negativo ni mayor a 1,000,000.");
        }

        Product product = new Product(id, name, size, color, price, stock);
        if (!productRepository.addProduct(product)) {
            throw new IllegalArgumentException("No se pudo registrar el producto.");
        }
        return true;
    }

    @Override
    public boolean deleteProduct(int productId) {
        if (productId < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }
        if (!productRepository.deleteProduct(productId)) {
            throw new IllegalArgumentException("No se encontró un producto con el ID especificado.");
        }
        return true;
    }

    @Override
    public boolean updateProduct(int id, String name, String size, String color, double price, int stock) {
       Product existingProduct = productRepository.findProductById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("No se encontró un producto con el ID especificado.");
        }

        if (name == null || name.trim().isEmpty() || name.length() > 100) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio y no puede exceder los 100 caracteres.");
        }

        if (size == null || size.trim().isEmpty() || size.length() > 30) {
            throw new IllegalArgumentException("El tamaño del producto es obligatorio y no puede exceder los 30 caracteres.");
        }

        if (color == null || color.trim().isEmpty() || color.length() > 30) {
            throw new IllegalArgumentException("El color del producto es obligatorio y no puede exceder los 30 caracteres.");
        }

        if (price <= 0 || price > 99999999) {
            throw new IllegalArgumentException("El precio del producto debe ser un valor positivo y no mayor a 99,999,999.");
        }

        if (stock < 0 || stock > 1000000) {
            throw new IllegalArgumentException("El stock del producto no puede ser un valor negativo ni mayor a 1,000,000.");
        }

        Product updatedProduct = new Product(id, name, size, color, price, stock);
        if (!productRepository.updateProduct(id, updatedProduct)) {
            throw new IllegalArgumentException("No se pudo actualizar el producto.");
        }
        return true;
    }

    @Override
    public Product findProductById(int productId) {
        if (productId < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("No se encontró un producto con el ID especificado.");
        }
       return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    
}
