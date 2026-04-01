package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.model.Product;
import java.util.List;

/**
 *
 * @author Santy
 */
public interface IProductRepository {
    void addProduct (Product producto);
    void deleteProduct (int productId);
    void updateProduct (int productId, Product updatedProduct);
    Product findProductById (int productId);
    List<Product> getAllProducts();

}
