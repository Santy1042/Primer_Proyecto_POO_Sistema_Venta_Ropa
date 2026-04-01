package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.model.Product;
import java.util.List;

/**
 *
 * @author Santy
 */
public interface IProductRepository {
    boolean addProduct (Product producto);
    boolean deleteProduct (int productId);
    boolean updateProduct (int productId, Product updatedProduct);
    Product findProductById (int productId);
    List<Product> getAllProducts();

}
