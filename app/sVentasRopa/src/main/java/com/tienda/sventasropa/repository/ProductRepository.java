package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean deleteProduct(int productId) {
        if (productId >= 0) {
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    products.remove(product);
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public boolean updateProduct(int productId, Product updatedProduct) {
        if (productId >= 0) {
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    products.set(products.indexOf(product), updatedProduct);
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public Product findProductById(int productId) {
        if (productId >= 0) {
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    return product;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
