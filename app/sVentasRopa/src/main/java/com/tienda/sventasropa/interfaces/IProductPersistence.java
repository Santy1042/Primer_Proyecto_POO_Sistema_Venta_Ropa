package com.tienda.sventasropa.interfaces;

import java.util.List;

import com.tienda.sventasropa.model.Product;

/**
 *
 * @author Santy
 */
public interface IProductPersistence {
    boolean saveProducts(List<Product> products);
    List<Product> loadProducts();
}
