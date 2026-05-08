package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.interfaces.IProductPersistence;
import com.tienda.sventasropa.interfaces.IProductRepository;
import com.tienda.sventasropa.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ProductRepository implements IProductRepository {
    private final List<Product> products;
    private final IProductPersistence persistence;

    public ProductRepository(IProductPersistence persistence) {
        this.persistence = persistence;
        this.products = this.persistence.loadProducts();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        boolean added = products.add(product);
        if (added) {
            persistence.saveProducts(products);
        }
        return added;
    }

    @Override
    public boolean deleteProduct(int productId) {
        if (productId > 0) {
            boolean exists = false;
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    exists = true;
                    break;
                }
            } 
            if (exists) {
                products.removeIf(p -> p.getProductId() == productId);
                persistence.saveProducts(products);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updateProduct(int productId, Product updatedProduct) {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("El producto actualizado no puede ser nulo.");
        }
        if (productId > 0) {
            Product existing = findProductById(productId);
            if (existing == null) return false;

            existing.setProductName(updatedProduct.getProductName());
            existing.setProductSize(updatedProduct.getProductSize());
            existing.setProductColor(updatedProduct.getProductColor());
            existing.setProductPrice(updatedProduct.getProductPrice());
            existing.setProductStock(updatedProduct.getProductStock());

            persistence.saveProducts(products);
            return true;
            }
        return false;
        }

    @Override
    public Product findProductById(int productId) {
        if (productId > 0) {
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
