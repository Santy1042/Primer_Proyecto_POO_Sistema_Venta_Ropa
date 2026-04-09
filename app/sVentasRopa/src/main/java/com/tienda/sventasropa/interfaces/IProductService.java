package com.tienda.sventasropa.interfaces;


import com.tienda.sventasropa.model.Product;

/**
 *
 * @author Santy
 */
public interface IProductService {
    boolean addProduct(int id, String name, String size, String color, double price, int stock);
    boolean deleteProduct(int productId);
    boolean updateProduct(int id, String name, String size, String color, double price, int stock);
    Product findProductById(int productId);
}
