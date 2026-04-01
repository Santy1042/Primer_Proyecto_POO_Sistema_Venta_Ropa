package com.tienda.sventasropa.service;

import com.tienda.sventasropa.model.Product;
import com.tienda.sventasropa.repository.ProductRepository;
import com.tienda.sventasropa.repository.IProductRepository;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ProductService {
    private final IProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registrarProducto(int id, String name, String size, String color, double price, int stock) { 
        for (Product product : productRepository.getAllProducts()) {
            if (product.getProductId() == id) {
                throw new IllegalArgumentException("El ID del producto ya existe. Por favor, elija un ID único.");
            }
        }

        if (id < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }

        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("El tamaño del producto es obligatorio.");
        }

        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color del producto es obligatorio.");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser un valor positivo.");
        }

        if (stock <= 0) {
            throw new IllegalArgumentException("El stock del producto debe ser un valor positivo.");
        }

        Product product = new Product(id, name, size, color, price, stock);
        if (!productRepository.addProduct(product)) {
            throw new IllegalArgumentException("No se pudo registrar el producto.");
        }
        System.out.println("Producto registrado exitosamente: " + product.getProductName());
    }

    public void eliminarProducto(int productId) {
        if (productId < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }
        if (!productRepository.deleteProduct(productId)) {
            throw new IllegalArgumentException("No se encontró un producto con el ID especificado.");
        }
        System.out.println("Producto con ID " + productId + " eliminado exitosamente.");
    }

    public void actualizarProducto(int id, String name, String size, String color, double price, int stock) {
       Product existingProduct = productRepository.findProductById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("No se encontró un producto con el ID especificado.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }

        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("El tamaño del producto es obligatorio.");
        }

        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color del producto es obligatorio.");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser un valor positivo.");
        }

        if (stock <= 0) {
            throw new IllegalArgumentException("El stock del producto debe ser un valor positivo.");
        }

        Product updatedProduct = new Product(id, name, size, color, price, stock);
        if (!productRepository.updateProduct(id, updatedProduct)) {
            throw new IllegalArgumentException("No se pudo actualizar el producto.");
        }
        System.out.println("Producto actualizado exitosamente: " + updatedProduct.getProductName());
    }

    public Product buscarProductoPorId(int productId) {
        if (productId < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("No se encontró un producto con el ID especificado.");
        }
       return product;
    }

    public List<Product> obtenerTodosLosProductos() {
        if (productRepository.getAllProducts().isEmpty()) {
            throw new IllegalArgumentException("No hay productos registrados.");
        }
        return productRepository.getAllProducts();
    }
    
}
